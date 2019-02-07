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
public class BLock implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private boolean blockTie;
	private Long blockTieNumber;
}
