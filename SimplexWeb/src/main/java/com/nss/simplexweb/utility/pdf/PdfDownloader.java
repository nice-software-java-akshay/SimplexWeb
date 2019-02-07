package com.nss.simplexweb.utility.pdf;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import org.xhtmlrenderer.pdf.ITextRenderer;

import lombok.Cleanup;

@Service("pdfDownloader")
public class PdfDownloader {

	public void downloadPdf(@NonNull final String html, HttpServletResponse response, String filename) {
	    try {
	        final ITextRenderer renderer = new ITextRenderer();
	        
	        renderer.setDocumentFromString(html);
	        renderer.layout();

	        @Cleanup final ByteArrayOutputStream os = new ByteArrayOutputStream();
	        renderer.createPDF(os);

	        ServletOutputStream outStream = null;
			try {
				outStream = response.getOutputStream();
				response.setContentType("application/pdf");
				response.setHeader("Content-Disposition", "attachment; filename="+filename+".pdf"+"");
				outStream.flush();
				outStream.write(os.toByteArray());
			} catch (IOException e) {
				e.printStackTrace();
			}
	    } catch(Exception e) {
	    	e.printStackTrace();
	    }
	}
	
	public byte[] pdfByteStream(@NonNull final String html) {
	    try {
	        final ITextRenderer renderer = new ITextRenderer();
	        
	        renderer.setDocumentFromString(html);
	        renderer.layout();

	        @Cleanup final ByteArrayOutputStream os = new ByteArrayOutputStream();
	        renderer.createPDF(os);

	       return os.toByteArray();
	    } catch(Exception e) {
	    	e.printStackTrace();
	    }
	    return null;
	}
}
