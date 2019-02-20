package com.nss.simplexweb.po.service;

import java.util.ArrayList;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.nss.simplexweb.enums.DOCUMENT_PARENT_ENTITY_TYPE;
import com.nss.simplexweb.enums.PO_TRACKING;
import com.nss.simplexweb.po.model.PODetail;
import com.nss.simplexweb.po.model.POItems;
import com.nss.simplexweb.po.model.POStatus;
import com.nss.simplexweb.po.repository.PODetailRepository;
import com.nss.simplexweb.po.repository.PoItemsRepository;
import com.nss.simplexweb.user.model.User;
import com.nss.simplexweb.utility.Utility;
import com.nss.simplexweb.utility.document.model.Document;
import com.nss.simplexweb.utility.document.service.DocumentService;
import com.nss.simplexweb.utility.pdf.PdfGeneratorUtilController;

@Service("poDetailService")
public class PODetailService {

	@Autowired
	private PODetailRepository poDetailRepository;
	
	@Autowired
	private PoItemsRepository PoItemsRepository;
	
	@Autowired
	private DocumentService documentService;
	
	@Autowired
	private POStatusService poStatusService;
	
	@Autowired
	private PdfGeneratorUtilController pdfGeneratorUtilController;
	
	@Value("${file.po.po.file}")
	private String PO_PO_FILE_UPLOADED_FOLDER_PATH;
	
	@Autowired
	public PODetailService(
			PODetailRepository poDetailRepository,
			PoItemsRepository PoItemsRepository,
			DocumentService documentService,
			POStatusService poStatusService
			) {
		// TODO Auto-generated constructor stub
		this.poDetailRepository = poDetailRepository;
		this.PoItemsRepository = PoItemsRepository;
		this.documentService = documentService;
		this.poStatusService = poStatusService;
	}
	
	public PODetailService(PODetailRepository poDetailRepository) {
		this.poDetailRepository = poDetailRepository;
	}

	public PODetail saveNewPurchaseOrder(PODetail poDetail) {
		POStatus poStatus = poStatusService.getPOStatusByPoStatusAbbr(PO_TRACKING.PO_RECEIVED.name());
		poDetail.setPoStatus(poStatus);
		poDetail = poDetailRepository.save(poDetail);
		ArrayList<POItems> newPoItemsList = null;
		
		if(poDetail.getPoItmesList() != null) {
			for(POItems poItem : poDetail.getPoItmesList()) {
				poItem.setPoDetail(poDetail);
				
				if(newPoItemsList == null) {
					newPoItemsList = new ArrayList<>();
				}
				PoItemsRepository.save(poItem);
				newPoItemsList.add(poItem);
			}
		}
		poDetail.setPoItmesList(newPoItemsList);
		return poDetail;
	}

	public void savePODocuments(MultipartFile[] files, PODetail poDetail, User currentUser) {
		Document document = null;
		if(files != null){
			for(MultipartFile file : files) {
				document = Utility.getMultipartFileDetails(file);
				if(document != null) {
					document.setDocumentParentId(poDetail.getPoId());
					document.setDocumentParentEntityType(DOCUMENT_PARENT_ENTITY_TYPE.PO_DOCUMENT.name());
					document.setDocumentStorePath(PO_PO_FILE_UPLOADED_FOLDER_PATH);
					document.setDocumentStoreByName(Utility.generatePODocumentName(poDetail.getPoNumber()) + "." + document.getDocumentExtension());
					document.setDocumentUploader(currentUser);
					document.setDocumentOwnerPartner(poDetail.getRequester());	//Requester and current user may not be same : (Case : Simplex employee is uploading the document for received PO)
					document.setDocumentDownloadByName(document.getDocumentOriginalNameWithoutExtension());
					
					//Save
					Utility.singleFileUpload(file, document.getDocumentStorePath(), document.getDocumentStoreByName());
					documentService.saveDocumentDetails(document);
				}
			}
		}		
	}

	public PODetail getPODetailsByPoIdAndPoNumber(Long poId, String poNumber) {
		// TODO Auto-generated method stub
		return poDetailRepository.findByPoIdAndPoNumber(poId, poNumber);
	}
	
	public PODetail getPODetailsByPoId(Long poId) {
		// TODO Auto-generated method stub
		return poDetailRepository.findByPoId(poId);
	}

	public void downloadPOAsPDF(Long poId, String poNumber, HttpServletResponse response) {
		// TODO Auto-generated method stub
		PODetail poDetailBean = getPODetailsByPoIdAndPoNumber(poId, poNumber);
		pdfGeneratorUtilController.downloadPOAsPDF(poDetailBean, response);
	}

	public ArrayList<PODetail> getPOHistoryForUser(User currentUser) {
		// TODO Auto-generated method stub
		return poDetailRepository.findByRequester(currentUser);
	}

	public ArrayList<PODetail> getOpenPORequestsList() {
		// TODO Auto-generated method stub
		POStatus poStatus = poStatusService.getPOStatusByPoStatusAbbr(PO_TRACKING.PO_RECEIVED.name());
		return poDetailRepository.findByPoStatus(poStatus);
	}

	public PODetail updatePOStatus(PODetail poDetail) {
		poDetail.setIsClosed(poDetail.getPoStatus().getMarkPOClose());
		return poDetailRepository.save(poDetail);
	}

	public ArrayList<PODetail> getInProgressPOListForProcessor(User currentUser) {
		// TODO Auto-generated method stub
		return poDetailRepository.findByProcessorAndIsClosed(currentUser, 0);
	}
}
