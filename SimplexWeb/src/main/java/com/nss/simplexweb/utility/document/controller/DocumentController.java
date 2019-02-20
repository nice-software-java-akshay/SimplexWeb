package com.nss.simplexweb.utility.document.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.nss.simplexweb.utility.document.model.Document;
import com.nss.simplexweb.utility.document.service.DocumentService;

@Controller
@RequestMapping(value= {"/document"})
public class DocumentController {

	@Autowired
	private DocumentService documentService;
	
	@RequestMapping(value = "/downloadDocumentByDocumentDetailId", method = RequestMethod.GET)
	public void downloadDocumentByDocumentDetailId(
			@RequestParam("documentDetailId") Long documentDetailId, 
			HttpServletRequest request,
			HttpServletResponse response) {
		
		//If user is not authorized - he should be thrown out from here itself
        
        //Authorized user will download the file
		Document document = documentService.findDocumentByDocumentDetailId(documentDetailId);
        String filepath = document.getDocumentStorePath() + File.separator + document.getDocumentStoreByName();
        File file = new File(filepath);
        if (file.exists() && file.isFile())
        {
            response.setContentType(document.getDocumentMimeType());
            response.addHeader("Content-Disposition", "attachment; filename="+document.getDocumentDownloadByName()+'.'+document.getDocumentExtension());
            try
            {
                Files.copy(file.toPath(), response.getOutputStream());
                response.getOutputStream().flush();
            }
            catch (IOException ex) {
                ex.printStackTrace();
            }
        }
	}
}
