package com.nss.simplexweb.utility.document.repository;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nss.simplexweb.user.model.User;
import com.nss.simplexweb.utility.document.model.Document;

@Repository("documentRepository")
public interface DocumentRepository extends JpaRepository<Document, Long> {

	//Read
	ArrayList<Document> findBydocumentParentIdAndDocumentParentEntityTypeAndIsDeleted(Long documentParentId, String DOCUMENT_PARENT_ENTITY_TYPE, int isDeleted);
	
	Document findBydocumentDetailId(Long documentDetailId);
	
	ArrayList<Document> findByDocumentOwnerPartnerAndDocumentParentEntityTypeAndIsDeleted(User currentUser, String DOCUMENT_PARENT_ENTITY_TYPE, int isDeleted);
}
