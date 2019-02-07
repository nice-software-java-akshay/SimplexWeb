package com.nss.simplexweb.utility.mail;

import java.io.File;

import javax.mail.util.ByteArrayDataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.nss.simplexweb.enums.PROJECT;

@Service
public class MailService {

    private JavaMailSender mailSender;
    private MailContentBuilder mailContentBuilder;
    
    //Email Template Path
    private final String EMAIL_TEMPLATE_PATH = "email-template";

    @Autowired
    public MailService(JavaMailSender mailSender, MailContentBuilder mailContentBuilder) {
        this.mailSender = mailSender;
        this.mailContentBuilder = mailContentBuilder;
    }

    @Async
    public String prepareAndSend(MailBean mailbean) {
    	String retMsg = PROJECT.SUCCESS_MSG.name();
		try {
	        MimeMessagePreparator messagePreparator = mimeMessage -> {
	            MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage, true);
	            
	            //Set email from
	            messageHelper.setFrom(mailbean.getFrom());
	            
	            //Set recipients list
	            if(mailbean.getRecipients() != null) {
		            for(String recipient : mailbean.getRecipients()) {
		            	messageHelper.setTo(recipient);
		            }
	            }
	            
	            //Set cc list
	            if(mailbean.getCc() != null) {
	            	for(String cc : mailbean.getCc()) {
	                	messageHelper.setCc(cc);
	                }
	            }
	            
	            
	            //Set bcc list
	            if(mailbean.getBcc() != null) {
		            for(String bcc : mailbean.getBcc()) {
		            	messageHelper.setCc(bcc);
		            }
	            }
	            
	            //Set mail subject
	            messageHelper.setSubject(mailbean.getSubject());
	            
	            //Build mail body
	            String content = mailContentBuilder.buildMilContent(mailbean.getBodyParams(), EMAIL_TEMPLATE_PATH + File.separator + mailbean.getTemplateName());
	            messageHelper.setText(content, true);
	            
	            if(mailbean.getMailAttachmentList() != null) {
	            	for(EmailAttachment emailAttachment : mailbean.getMailAttachmentList()) {
	            		messageHelper.addAttachment(emailAttachment.getAttachmentName(), new ByteArrayDataSource(emailAttachment.getAttachmentBytes(), emailAttachment.getAttachmentType()));
	            	}
	            }
	        };
            mailSender.send(messagePreparator);
        } catch (MailException e) {
            // runtime exception; compiler will not force you to handle it
        	retMsg = PROJECT.ERROR_MSG.name();
        	e.printStackTrace();
        }
		return retMsg;
    }

}