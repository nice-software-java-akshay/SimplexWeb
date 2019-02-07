package com.nss.simplexweb.utility.pdf;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;
import org.xhtmlrenderer.pdf.ITextRenderer;

@Component
public class PdfGenaratorUtilService_Useless {
	
	//Email Template Path
    private final String PDF_TEMPLATE_PATH = "email-template";
	
	@Autowired
    private PdfContentBuilder pdfContentBuilder;
	
	public void createPdf(String templateName, String fileName, HashMap<String, String> bodyparams) throws Exception {
		Assert.notNull(templateName, "The templateName can not be null");
		//Build mail body
        /*String pdfContent = pdfContentBuilder.buildPdfContent(bodyparams, PDF_TEMPLATE_PATH + File.separator + templateName);
        
		  FileOutputStream os = null;
	        try {
	            final File outputFile = File.createTempFile(fileName, ".pdf");
	            os = new FileOutputStream(outputFile);

	            ITextRenderer renderer = new ITextRenderer();
	            renderer.setDocumentFromString(pdfContent);
	            renderer.layout();
	            renderer.createPDF(os, false);
	            renderer.finishPDF();
	            System.out.println("PDF created successfully");
	        }
	        finally {
	            if (os != null) {
	                try {
	                    os.close();
	                } catch (IOException e) { e.printStackTrace(); }
	            }
	        }*/
	}
}