package com.nss.simplexweb.documents.controller;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.nss.simplexweb.documents.model.DocumentCategory;
import com.nss.simplexweb.documents.service.DocumentCategoriesService;
import com.nss.simplexweb.documents.service.DocumentManagerService;
import com.nss.simplexweb.enums.DISTRIBUTER;
import com.nss.simplexweb.enums.DOCUMENT;
import com.nss.simplexweb.enums.DOCUMENT_CATEGORY_TYPE;
import com.nss.simplexweb.user.model.User;
import com.nss.simplexweb.user.service.DistributerService;
import com.nss.simplexweb.utility.document.model.Document;

@Controller
@RequestMapping(value={"/documents/documentManagerController"})
public class DocumentManagerController {

	@Autowired
	private DocumentManagerService documentManagerService;
	
	@Autowired
	private DistributerService distributerService;
	
	@Autowired
	private DocumentManagerService documentUploadService;
	
	@Autowired
	private DocumentCategoriesService documentCategoriesService;
	
	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView getDocumentManagerPage(/*@RequestParam("documentCategoryId") Long documentCategoryId,
												@RequestParam("documentCategoryAbbr") String documentCategoryAbbr,*/
												ModelAndView mav,
												HttpServletRequest request) {
		ArrayList<DocumentCategory> allDocumentCategoriesList = documentCategoriesService.getAllCategoriesList();
		
		mav
			.addObject(DISTRIBUTER.DISTRIBUTER_LIST.name(), distributerService.findAllActiveDistributersList())
			.addObject(DOCUMENT_CATEGORY_TYPE.ALL_DOCUMENT_CATEGORIES_LIST.name(), allDocumentCategoriesList)
			.setViewName("documents/documentManager");
		
		return mav;
	}
	
	@RequestMapping(path="/getDocumentsListAjaxPageForDocumentManager", method=RequestMethod.POST)
	public ModelAndView getDocumentsListForDocumentManager(
			@RequestParam("documentOwnerPartnerId") Long documentOwnerPartnerId,
			@RequestParam("documentCategoryId") Long documentCategoryId,
			ModelAndView mav) {
		User currentUser = distributerService.findDistributerByUserId(documentOwnerPartnerId);
		ArrayList<Document> documentsList = documentManagerService
												.getActiveDocumentsByDocumentOwnerPartnerAndDocumentCategoryId(currentUser, documentCategoryId);
		
		mav
			.addObject(DOCUMENT.DOCUMENTS_LIST.name(), documentsList)
			.setViewName("documents/documentsListAjaxPage");
		
		return mav;
	}
}
