package com.nss.simplexweb.utility.mail;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor 
@NoArgsConstructor
public class EmailAttachment implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String attachmentName;
	
	private byte[] attachmentBytes;
	
	private String attachmentType;
}
