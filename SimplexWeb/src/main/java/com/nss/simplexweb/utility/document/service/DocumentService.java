package com.nss.simplexweb.utility.document.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nss.simplexweb.utility.document.model.Document;
import com.nss.simplexweb.utility.document.repository.DocumentRepository;

@Service("documentService")
public class DocumentService {

	@Autowired
	private DocumentRepository documentRepository;
	
	@Autowired
	public DocumentService(DocumentRepository documentRepository) {
		// TODO Auto-generated constructor stub
		this.documentRepository = documentRepository;
	}
	
	public ArrayList<Document> findActiveDocumentsByParentIdAndParentEntityType(Long documentParentId, String documentParentEntityType){
		return documentRepository.findBydocumentParentIdAndDocumentParentEntityTypeAndIsDeleted(documentParentId, documentParentEntityType, 0);
	}

	public Document saveDocumentDetails(Document document) {
		return documentRepository.save(document);
	}

	public Document findDocumentByDocumentDetailId(Long documentDetailId) {
		return documentRepository.findBydocumentDetailId(documentDetailId);
	}
}
