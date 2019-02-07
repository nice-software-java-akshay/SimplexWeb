package com.nss.simplexweb.enquiry.template.model.topfilling;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor 
@NoArgsConstructor
public class VelcroTie implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private boolean velcroTie;
	private Long velcroTieNumber;
}
