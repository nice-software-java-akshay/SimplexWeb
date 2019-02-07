package com.nss.simplexweb.enquiry.template.model.other;

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
public class OtherBean implements Serializable {/**
	 * 
	 */
	private static final Long serialVersionUID = 1L;

	//Other A
	private boolean perimeterBand;
	private boolean bellyBand;
	private boolean steevdoorStrap;
	private boolean docPouch;
	private ArrayList<DocPouchType> docPouchType;
	private ArrayList<DocPouchDetailType> docPoucDetailType;
	private Long docPouchNumber;
	private boolean otherLabels;
	private String remarks;
	
	//Other B
	private boolean printing;
	private ArrayList<Sides> sides;
	private ArrayList<PrintingColor> printingColor;
	private boolean extraCleaning;
	private boolean metalDetector;
	private ArrayList<PackagingType> packagingType;
	private boolean liner;
	
}
