package com.nss.simplexweb.documents.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nss.simplexweb.documents.model.DocumentCategory;
import com.nss.simplexweb.enums.DOCUMENT_CATEGORY_TYPE;
import com.nss.simplexweb.user.model.User;
import com.nss.simplexweb.utility.document.model.Document;
import com.nss.simplexweb.utility.document.repository.DocumentRepository;

@Service("documentUploadService")
public class DocumentManagerService {

	@Autowired
	private DocumentCategoriesService documentCategoriesService;
	
	@Autowired
	private DocumentRepository documentRepository;
	
	public DocumentManagerService(
			DocumentCategoriesService documentCategoriesService,
			DocumentRepository documentRepository) {
		// TODO Auto-generated constructor stub
		
		this.documentCategoriesService = documentCategoriesService;
		this.documentRepository = documentRepository;
	}

	public ArrayList<Document> getActiveDocumentsByDocumentOwnerPartnerAndDocumentCategoryId(User currentUser,
			Long documentCategoryId) {
		// TODO Auto-generated method stub
		ArrayList<Document> documentList = null;
		try {
			String DOCUMENT_PARENT_ENTITY_TYPE = DOCUMENT_CATEGORY_TYPE.ALL.name();
			if(documentCategoryId < 1) {
				DOCUMENT_PARENT_ENTITY_TYPE = DOCUMENT_CATEGORY_TYPE.ALL.name();
			}else {
				DOCUMENT_PARENT_ENTITY_TYPE = documentCategoriesService.getDocumentCategoryByDocumentCategoryId(documentCategoryId).getDocumentCategoryAbbr();
			}
			
			if(DOCUMENT_PARENT_ENTITY_TYPE.equalsIgnoreCase(DOCUMENT_CATEGORY_TYPE.ALL.name())) {
				ArrayList<DocumentCategory> allCategoriesList = documentCategoriesService.getAllCategoriesList();
				for(DocumentCategory documentCategory : allCategoriesList) {
					if(documentList != null) {
						documentList = new ArrayList<>();
					}
					ArrayList<Document> documentListChild = documentRepository.findByDocumentOwnerPartnerAndDocumentParentEntityTypeAndIsDeleted(currentUser, documentCategory.getDocumentCategoryAbbr(), 0);
					if(documentListChild != null && documentListChild.size()>0) {
						documentList.addAll(documentListChild);
					}
				}
				
			}else {
				documentList = documentRepository.findByDocumentOwnerPartnerAndDocumentParentEntityTypeAndIsDeleted(currentUser, DOCUMENT_PARENT_ENTITY_TYPE, 0);
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return documentList;
	}
	
	
}
