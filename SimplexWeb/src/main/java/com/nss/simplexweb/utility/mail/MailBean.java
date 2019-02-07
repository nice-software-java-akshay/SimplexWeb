package com.nss.simplexweb.utility.mail;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor 
@NoArgsConstructor
public class MailBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String subject;
	
	private String from;
	
	private String[] recipients;
	
	private String[] cc;
	
	private String[] bcc;
	
	private HashMap<String, HashMap<String, String>> bodyParams;
	
	private String templateName;
	
	private ArrayList<EmailAttachment> mailAttachmentList;
}
