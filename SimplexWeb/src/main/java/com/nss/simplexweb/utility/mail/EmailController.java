package com.nss.simplexweb.utility.mail;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.nss.simplexweb.enquiry.template.model.EnquiryTemplateBean;
import com.nss.simplexweb.enums.COMPANY;
import com.nss.simplexweb.enums.ENQUIRY;
import com.nss.simplexweb.enums.PROJECT;
import com.nss.simplexweb.enums.USER;
import com.nss.simplexweb.user.model.User;
import com.nss.simplexweb.user.service.MainComapanyService;
import com.nss.simplexweb.utility.Utility;
import com.nss.simplexweb.utility.pdf.PdfGeneratorUtilController;

@Service("emailController")
public class EmailController {
	
	private String ENQUIRY_FILE_NAME_PREFIX = "Simplex Enquiry Details - ";
	
	@Autowired
    private MailService mailService;
	
	@Autowired
	private MainComapanyService mainComapanyService;
	
	@Autowired
	private PdfGeneratorUtilController pdfGeneratorUtilController;

	//Registration email
	public void sendRegistrationEmail(User user) {
		//Mail Config
	        Properties mailprops = Utility.propertiesFileReader("mail-params");
	        MailBean mailbean = new MailBean();
	        mailbean.setSubject(mailprops.getProperty("mail.sub.registration"));
	        mailbean.setRecipients(new String[]{user.getEmail()});
	        mailbean.setFrom(mailprops.getProperty("mail.from.mailid"));
	        mailbean.setTemplateName("new-user-register");
        
        //Params
	        ObjectMapper objectMapper = new ObjectMapper();
			objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
			objectMapper.setDateFormat(new SimpleDateFormat("MMM dd, yyyy"));
			HashMap<String, HashMap<String, String>> bodyparams = new HashMap<>();
			
			HashMap<String, HashMap<String, String>> enquiryTemplateBeanMap = new HashMap<>();
			enquiryTemplateBeanMap.put(PROJECT.CONTEXT_ + USER.USER.name() ,objectMapper.convertValue(user, HashMap.class));	//POJO to Map				
			
			HashMap<String, HashMap<String, String>> mainComapnyMap = new HashMap<>();
			mainComapnyMap.put(PROJECT.CONTEXT_ + COMPANY.COMPANY.name(), objectMapper.convertValue(mainComapanyService.getMainComapnyInfo(), HashMap.class));	//POJO to Map
			
			bodyparams.putAll(enquiryTemplateBeanMap);
			bodyparams.putAll(mainComapnyMap);
	        mailbean.setBodyParams(bodyparams);
        mailService.prepareAndSend(mailbean);
	}
	
	//New Inquiry Quotation email
	public String sendNewEnquiryQuotation(EnquiryTemplateBean enquiryTemplateBean, MailBean mailbean) {
		String retMsg = PROJECT.SUCCESS_MSG.name();
		try {
			//Mail Config
		        Properties mailprops = Utility.propertiesFileReader("mail-params");
		        //mailbean.setSubject(mailprops.getProperty("mail.sub.registration"));	//Do not set subject here as user will enter it
		        mailbean.setFrom(mailprops.getProperty("mail.from.mailid"));
		        mailbean.setTemplateName("new-enquiry-quotation-mail-body");
	        
	        //Params
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
		        mailbean.setBodyParams(bodyparams);
		        
		   //Pdf Attachment
		        ArrayList<EmailAttachment> mailAttachmentList = new ArrayList<>();
		        EmailAttachment emailAttachment = new EmailAttachment();
		        emailAttachment.setAttachmentName(ENQUIRY_FILE_NAME_PREFIX + enquiryTemplateBean.getEnquiryNumber());
		        emailAttachment.setAttachmentType("application/pdf");
		        emailAttachment.setAttachmentBytes(pdfGeneratorUtilController.generateNewEnquiryPdf(enquiryTemplateBean));
		        mailAttachmentList.add(emailAttachment);
		        mailbean.setMailAttachmentList(mailAttachmentList);
		        
		   retMsg = mailService.prepareAndSend(mailbean);
		}catch (Exception e) {
			retMsg = PROJECT.ERROR_MSG.name();
			e.getMessage();
		}
		return retMsg;
	}

	
}
