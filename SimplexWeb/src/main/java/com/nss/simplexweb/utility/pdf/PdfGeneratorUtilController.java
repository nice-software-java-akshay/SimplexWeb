package com.nss.simplexweb.utility.pdf;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.nss.simplexweb.enquiry.template.model.EnquiryTemplateBean;
import com.nss.simplexweb.enums.COMPANY;
import com.nss.simplexweb.enums.ENQUIRY;
import com.nss.simplexweb.enums.PO;
import com.nss.simplexweb.enums.PROJECT;
import com.nss.simplexweb.enums.TEMPLATE_CONSTANT;
import com.nss.simplexweb.po.model.PODetail;
import com.nss.simplexweb.user.service.MainComapanyService;

@Service("pdfGeneratorUtilController")
public class PdfGeneratorUtilController {

	private String ENQUIRY_FILE_NAME_PREFIX = "Simplex Enquiry Details - ";
	
	private String PO_FILE_NAME_PREFIX = "Simplex PO Details - ";
	
	//PDF Template Path
    private final String PDF_TEMPLATE_PATH = "email-template";
	
	@Autowired
    private PdfContentBuilder pdfContentBuilder;
	
	@Autowired
	private MainComapanyService mainComapanyService;
	
	
	@Autowired
	private PdfDownloader pdfDownloader;
	
	//New Enquiry Quotation PDF downloader
	public void downloadNewEnquiryPdf(EnquiryTemplateBean enquiryTemplateBean, HttpServletResponse response) {
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
		objectMapper.setDateFormat(new SimpleDateFormat("MMM dd, yyyy"));
		HashMap<String, HashMap<String, String>> bodyparams = new HashMap<>();
		
		HashMap<String, HashMap<String, String>> enquiryTemplateBeanMap = new HashMap<>();
		enquiryTemplateBeanMap.put(PROJECT.CONTEXT_ + ENQUIRY.ENQUIRY ,objectMapper.convertValue(enquiryTemplateBean, HashMap.class));	//POJO to Map
		
		HashMap<String, HashMap<String, String>> mainComapnyMap = new HashMap<>();
		mainComapnyMap.put(PROJECT.CONTEXT_ + COMPANY.COMPANY.name(), objectMapper.convertValue(mainComapanyService.getMainComapnyInfo(), HashMap.class));	//POJO to Map
		
		
		bodyparams.putAll(enquiryTemplateBeanMap);
		bodyparams.putAll(mainComapnyMap);
		try {
			//Build pdf content
	        String pdfContent = pdfContentBuilder.buildPdfContent(bodyparams, PDF_TEMPLATE_PATH + File.separator + TEMPLATE_CONSTANT.NEW_ENQUIRY_QUOTATION_PDF_TEMPLATE);
	        
	        //Download PDF
			pdfDownloader.downloadPdf(pdfContent, response, ENQUIRY_FILE_NAME_PREFIX + enquiryTemplateBean.getEnquiryNumber());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//New Enquiry Quotation PDF generator
	public byte[] generateNewEnquiryPdf(EnquiryTemplateBean enquiryTemplateBean) {
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
		objectMapper.setDateFormat(new SimpleDateFormat("MMM dd, yyyy"));
		HashMap<String, HashMap<String, String>> bodyparams = new HashMap<>();
		
		HashMap<String, HashMap<String, String>> enquiryTemplateBeanMap = new HashMap<>();
		enquiryTemplateBeanMap.put(PROJECT.CONTEXT_ + ENQUIRY.ENQUIRY ,objectMapper.convertValue(enquiryTemplateBean, HashMap.class));	//POJO to Map
		
		HashMap<String, HashMap<String, String>> mainComapnyMap = new HashMap<>();
		mainComapnyMap.put(PROJECT.CONTEXT_ + COMPANY.COMPANY.name(), objectMapper.convertValue(mainComapanyService.getMainComapnyInfo(), HashMap.class));	//POJO to Map
		
		
		bodyparams.putAll(enquiryTemplateBeanMap);
		bodyparams.putAll(mainComapnyMap);
		try {
			//Build pdf content
	        String pdfContent = pdfContentBuilder.buildPdfContent(bodyparams, PDF_TEMPLATE_PATH + File.separator + TEMPLATE_CONSTANT.NEW_ENQUIRY_QUOTATION_PDF_TEMPLATE);
	        
	        //Generate PDF
			return pdfDownloader.pdfByteStream(pdfContent);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * PO
	 * 
	 * */
	
	public void downloadPOAsPDF(PODetail poDetailBean, HttpServletResponse response) {
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.setVisibility(PropertyAccessor.FIELD, Visibility.ANY);
		objectMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
		objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
		objectMapper.setDateFormat(new SimpleDateFormat("MMM dd, yyyy"));
		HashMap<String, HashMap<String, String>> bodyparams = new HashMap<>();
		
		HashMap<String, HashMap<String, String>> enquiryTemplateBeanMap = new HashMap<>();
		enquiryTemplateBeanMap.put(PROJECT.CONTEXT_ + PO.PO.name() ,objectMapper.convertValue(poDetailBean, HashMap.class));	//POJO to Map
		
		HashMap<String, HashMap<String, String>> mainComapnyMap = new HashMap<>();
		mainComapnyMap.put(PROJECT.CONTEXT_ + COMPANY.COMPANY.name(), objectMapper.convertValue(mainComapanyService.getMainComapnyInfo(), HashMap.class));	//POJO to Map
		
		
		bodyparams.putAll(enquiryTemplateBeanMap);
		bodyparams.putAll(mainComapnyMap);
		try {
			//Build pdf content
	        String pdfContent = pdfContentBuilder.buildPdfContent(bodyparams, PDF_TEMPLATE_PATH + File.separator + TEMPLATE_CONSTANT.PO_DETAILS_PDF_TEMPLATE);
	        
	        //Download PDF
			pdfDownloader.downloadPdf(pdfContent, response, PO_FILE_NAME_PREFIX + poDetailBean.getPoNumber());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
