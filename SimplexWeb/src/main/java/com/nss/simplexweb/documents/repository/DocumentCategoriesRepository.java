package com.nss.simplexweb.documents.repository;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nss.simplexweb.documents.model.DocumentCategory;

@Repository("documentCategoriesRepository")
public interface DocumentCategoriesRepository extends JpaRepository<DocumentCategory, Long> {

	ArrayList<DocumentCategory> findAll();
	
	DocumentCategory findByDocumentCategoryId(Long documentCategory);
	
	DocumentCategory findByDocumentCategoryAbbr(String documentCategoryAbbr);
}
