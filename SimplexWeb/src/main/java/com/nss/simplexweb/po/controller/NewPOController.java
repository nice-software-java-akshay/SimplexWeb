package com.nss.simplexweb.po.controller;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.nss.simplexweb.SessionUtility;
import com.nss.simplexweb.enquiry.template.model.EnquiryTemplateBean;
import com.nss.simplexweb.enquiry.template.service.EnquiryTemplateService;
import com.nss.simplexweb.enquiry.template.service.product.ProductModelTypeService;
import com.nss.simplexweb.enums.COMPANY;
import com.nss.simplexweb.enums.DOCUMENT_PARENT_ENTITY_TYPE;
import com.nss.simplexweb.enums.ENQUIRY;
import com.nss.simplexweb.enums.PAYMENT_TERMS;
import com.nss.simplexweb.enums.PO;
import com.nss.simplexweb.enums.PO_TRACKING;
import com.nss.simplexweb.enums.PROJECT;
import com.nss.simplexweb.enums.ROLE;
import com.nss.simplexweb.enums.SHIPMENT_TERMS;
import com.nss.simplexweb.paymentterm.model.PaymentTerms;
import com.nss.simplexweb.paymentterm.service.PaymentTermsService;
import com.nss.simplexweb.po.model.PODetail;
import com.nss.simplexweb.po.model.POStatus;
import com.nss.simplexweb.po.model.POTrackingHistory;
import com.nss.simplexweb.po.service.PODetailService;
import com.nss.simplexweb.po.service.POStatusService;
import com.nss.simplexweb.po.service.POTrackingHistoryService;
import com.nss.simplexweb.shipmenterm.service.ShipmentTermsService;
import com.nss.simplexweb.user.model.User;
import com.nss.simplexweb.user.service.MainComapanyService;
import com.nss.simplexweb.utility.document.model.Document;
import com.nss.simplexweb.utility.document.service.DocumentService;

@Controller
@RequestMapping("/po/newPOController")
public class NewPOController {
	
	@Autowired
	private PODetailService poDetailService;
	
	@Autowired
	private MainComapanyService mainComapanyService;
	
	@Autowired
	private EnquiryTemplateService enquiryTemplateService;
	
	@Autowired
	private PaymentTermsService paymentTermsService;
	
	@Autowired
	private DocumentService documentService;
	
	@Autowired
	private POStatusService poStatusService;
	
	@Autowired
	private ShipmentTermsService shipmentTermsService;
	
	@Autowired
	private POTrackingHistoryService poTrackingHistoryService;
	
	@Autowired
	private ProductModelTypeService productModelTypeService;
	

	@RequestMapping(value = "/placeOrder", method = RequestMethod.GET)
	public ModelAndView placeNewOrder(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		User user = SessionUtility.getUserFromSession(request);
		ArrayList<EnquiryTemplateBean> enquiryHistoryList = enquiryTemplateService.getEnquiryHistoryForUser(user);
		
		ArrayList<PaymentTerms> paymentTermsList = null;
		if(user.getRole().getRoleAbbr().equalsIgnoreCase(ROLE.DIST.name())) {
			paymentTermsList = paymentTermsService.getPaymentTermsListByPartnerId(user.getUserId());
		}else {
			paymentTermsList = paymentTermsService.getActivePaymentTermsList();	//For employee
		}
		
		mav
			.addObject(COMPANY.COMPANY.name(), mainComapanyService.getMainComapnyInfo())
			.addObject(ENQUIRY.ENQUIRY_HISTORY_LIST, enquiryHistoryList)
			.addObject(PAYMENT_TERMS.PYAMENT_TERMS_LIST.name(), paymentTermsList)
			.addObject(ENQUIRY.PRODUCT_MODEL_TYPE_LIST, productModelTypeService.getProductModelTypeList())
			.addObject(SHIPMENT_TERMS.SHIPMENT_TERMS_LIST.name(), shipmentTermsService.getActiveShipmentTermsList())
			.addObject(PO.PO_DETAIL.name(), new PODetail())
			.setViewName("po/place-order");
		return mav;
	}
	
	@RequestMapping(value = "/saveNewOrder", method = RequestMethod.POST)
	public String saveNewOrder(PODetail poDetail, @RequestParam("poPDFFile") MultipartFile[] files, HttpServletRequest request) {
		User currentUser = SessionUtility.getUserFromSession(request);
		poDetail.setRequester(currentUser);
		poDetail = poDetailService.saveNewPurchaseOrder(poDetail);
		poDetailService.savePODocuments(files, poDetail, currentUser);
		return "redirect:/po/newPOController/getPODetails?poId="+poDetail.getPoId()+"&poNumber="+poDetail.getPoNumber();
	}
	
	@RequestMapping(value = "/getPODetails", method = RequestMethod.GET)
	public ModelAndView getPODetails(@RequestParam("poId") Long poId, @RequestParam("poNumber") String poNumber, HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		PODetail poDetail = poDetailService.getPODetailsByPoIdAndPoNumber(poId, poNumber);
		ArrayList<Document> document = documentService.findActiveDocumentsByParentIdAndParentEntityType(poDetail.getPoId(), DOCUMENT_PARENT_ENTITY_TYPE.PO_DOCUMENT.name());
		
		mav
			.addObject(COMPANY.COMPANY.name(), mainComapanyService.getMainComapnyInfo())
			.addObject(PO.PO_DETAIL.name(), poDetail)
			.addObject(DOCUMENT_PARENT_ENTITY_TYPE.PO_DOCUMENT.name(), document)
			.setViewName("po/po-details");
		
		return mav;
	}
	
	@RequestMapping(value = "/downloadPOAsPDF", method = RequestMethod.GET)
	//Intentionally two params required, so that end user can find the file by sending exactly two params combination which is hard to guess 
	public void downloadPOAsPDF(@RequestParam("poId")Long poId, @RequestParam("poNumber")String poNumber, HttpServletResponse response) {
		poDetailService.downloadPOAsPDF(poId, poNumber, response);
	}
	
	
	@RequestMapping(value = "/getPOHistoryForUser", method = RequestMethod.GET) 
	public ModelAndView getPOHistoryForUser(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		User currentUser = SessionUtility.getUserFromSession(request);
		
		mav
			.addObject(PO.PO_LIST.name(), poDetailService.getPOHistoryForUser(currentUser))
			.setViewName("po/po-history");
		
		return mav;
	}
	
	
	@RequestMapping(value = "/getPOHistoryForUser/trackPO", method = RequestMethod.GET)
	public ModelAndView trackPO(@RequestParam("poId") Long poId, @RequestParam("poNumber") String poNumber, HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		PODetail poDetail = poDetailService.getPODetailsByPoIdAndPoNumber(poId, poNumber);
		ArrayList<POTrackingHistory> poTrackingHistoryList = poTrackingHistoryService.getPOTrackingHistoryListForPODesc(poDetail);
		
		mav
			.addObject(PO_TRACKING.PO_TRACKING_HISTORY_LIST.name(), poTrackingHistoryList)
			.addObject(PO.PO_DETAIL.name(), poDetail)
			.setViewName("po/po-tracking");
		
		return mav;
	}
	
	@RequestMapping(value = "/getOpenPORequestsView", method = RequestMethod.GET)
	public ModelAndView getOpenPORequestsView(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		mav
			.setViewName("po/po-open-requests-list.html");
		
		return mav;
	}
	
	@RequestMapping(value = "/getOpenPORequestsList", method = RequestMethod.GET)
	@ResponseBody
	public ArrayList<PODetail> getOpenPORequestsList(HttpServletRequest request) {
		ArrayList<PODetail> openPORequestsList = poDetailService.getOpenPORequestsList();
		return openPORequestsList;
	}
	
	@RequestMapping(value = "/getPODetailsBeforeProcessing", method = RequestMethod.GET)
	public ModelAndView getPODetailsBeforeProcessing(@RequestParam("poId") Long poId, @RequestParam("poNumber") String poNumber, HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		PODetail poDetail = poDetailService.getPODetailsByPoIdAndPoNumber(poId, poNumber);
		ArrayList<Document> document = documentService.findActiveDocumentsByParentIdAndParentEntityType(poDetail.getPoId(), DOCUMENT_PARENT_ENTITY_TYPE.PO_DOCUMENT.name());
		
		mav
			.addObject(COMPANY.COMPANY.name(), mainComapanyService.getMainComapnyInfo())
			.addObject(PO.PO_DETAIL.name(), poDetail)
			.addObject(DOCUMENT_PARENT_ENTITY_TYPE.PO_DOCUMENT.name(), document)
			.setViewName("po/po-details-before-processing");
		
		return mav;
	}
	
	
	@RequestMapping(value = "/processNewPO", method = RequestMethod.GET)
	public String processNewPO(@RequestParam("poId") Long poId, @RequestParam("poNumber") String poNumber, HttpServletRequest request) {
		User currentUser = SessionUtility.getUserFromSession(request);
		
		PODetail poDetail = poDetailService.getPODetailsByPoIdAndPoNumber(poId, poNumber);
		poDetail.setProcessor(currentUser);
		poDetail.setStatusUpdatedBy(currentUser);
		poDetail.setPoStatus(poStatusService.getPOStatusByPoStatusAbbr(PO_TRACKING.PO_UNDER_PROCESS.name()));
		poDetail = poDetailService.updatePOStatus(poDetail);
		
		POTrackingHistory poTrackingHistory = new POTrackingHistory();
		poTrackingHistory.setPoId(poDetail);
		poTrackingHistory.setPoStatus(poDetail.getPoStatus());
		poTrackingHistory.setPoTrackingComment(poDetail.getPoStatus().getPoStatusName());
		poTrackingHistory.setUpdatedBy(currentUser);
		poTrackingHistoryService.addPOTrackingEntry(poTrackingHistory);
		
		return "redirect:/po/newPOController/getUpdatePOStatusPage?poId="+poDetail.getPoId()+"&poNumber="+poDetail.getPoNumber();
	}
	
	@RequestMapping(value = "/getMyPORequestsList/getUpdatePOStatusPage", method = RequestMethod.GET)
	public ModelAndView getUpdatePOStatusPage(@RequestParam("poId") Long poId, @RequestParam("poNumber") String poNumber, HttpServletRequest request) {
		User currentUser = SessionUtility.getUserFromSession(request);
		PODetail poDetail = poDetailService.getPODetailsByPoIdAndPoNumber(poId, poNumber);
		ArrayList<POStatus> poNextApplicableStatusType = poStatusService.getPONextApplicableStatusType(poDetail.getPoStatus());
		ArrayList<POTrackingHistory> poTrackingHistoryList = poTrackingHistoryService.getPOTrackingHistoryListForPODesc(poDetail);
		ArrayList<PODetail> inProgressPOListForProcessor = poDetailService.getInProgressPOListForProcessor(currentUser);
		
		ModelAndView mav = new ModelAndView();
		mav
			.addObject(PO.PO_DETAIL.name(), poDetail)
			.addObject(PO_TRACKING.PO_TRACKING_HISTORY.name(), new POTrackingHistory())
			.addObject(PO.IN_PROGRESS_PO_LIST.name(), inProgressPOListForProcessor)
			.addObject(PO_TRACKING.PO_TRACKING_HISTORY_LIST.name(), poTrackingHistoryList)
			.addObject(PO_TRACKING.PO_NEXT_APPLICABLE_STATUS_TYPE.name(), poNextApplicableStatusType)
			.setViewName("po/po-status-update-page");
		
		return mav;
	}
	
	@RequestMapping(value = "/updatePOStatus", method = RequestMethod.POST)
	public String updatePOStatus(@ModelAttribute(value="PO_TRACKING_HISTORY")POTrackingHistory poTrackingHistory, HttpServletRequest request) {
		User currentUser = SessionUtility.getUserFromSession(request);
		PODetail poDetail = poDetailService.getPODetailsByPoId(poTrackingHistory.getPoId().getPoId());
		poDetail.setStatusUpdatedBy(currentUser);
		poDetail.setPoStatus(poTrackingHistory.getPoStatus());
		poDetailService.updatePOStatus(poDetail);
		
		poTrackingHistory.setUpdatedBy(currentUser);
		poTrackingHistoryService.addPOTrackingEntry(poTrackingHistory);
		
		return "redirect:/po/newPOController/getUpdatePOStatusPage?poId="+poDetail.getPoId()+"&poNumber="+poDetail.getPoNumber()+"&"+PROJECT.RET_MSG.name()+"="+PROJECT.SUCCESS_MSG.name();
	}
	
	@RequestMapping(value = "/getMyPORequestsList", method = RequestMethod.GET)
	public ModelAndView getMyPORequestsList(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		User currentUser = SessionUtility.getUserFromSession(request);
		ArrayList<PODetail> poDetailList = poDetailService.getInProgressPOListForProcessor(currentUser);
		
		mav
			.addObject(PO.PO_LIST.name(), poDetailList)
			.setViewName("po/po-my-requests-list");
		
		return mav;
	}
	
}
