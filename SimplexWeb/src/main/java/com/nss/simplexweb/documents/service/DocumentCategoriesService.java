package com.nss.simplexweb.documents.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nss.simplexweb.documents.model.DocumentCategory;
import com.nss.simplexweb.documents.repository.DocumentCategoriesRepository;

@Service("documentCategoriesService")
public class DocumentCategoriesService {

	@Autowired
	private DocumentCategoriesRepository documentCategoriesRepository;
	
	public DocumentCategoriesService(DocumentCategoriesRepository documentCategoriesRepository) {
		// TODO Auto-generated constructor stub
		this.documentCategoriesRepository = documentCategoriesRepository;
	}
	
	public ArrayList<DocumentCategory> getAllCategoriesList(){
		return documentCategoriesRepository.findAll();
	}
	
	public DocumentCategory getDocumentCategoryByDocumentCategoryId(Long documentCategory) {
		return documentCategoriesRepository.findByDocumentCategoryId(documentCategory); 
	}
	
	public DocumentCategory getDocumentCategoryByDocumentCategoryAbbr(String DOCUNENT_CATEGORY_ABBR) {
		return documentCategoriesRepository.findByDocumentCategoryAbbr(DOCUNENT_CATEGORY_ABBR);
	}
	
}
