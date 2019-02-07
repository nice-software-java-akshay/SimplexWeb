package com.nss.simplexweb.enquiry.template.model.topfilling;

import java.io.Serializable;
import java.util.ArrayList;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor 
@NoArgsConstructor
public class TopFillingBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private boolean topFilling;
	private TopFillingType topFillingType;
	private ArrayList<TopType> topType;
	private VelcroTie velcroTie;
	private BLock block;
	
}
