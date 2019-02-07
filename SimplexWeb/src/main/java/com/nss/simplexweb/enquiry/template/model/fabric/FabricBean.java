package com.nss.simplexweb.enquiry.template.model.fabric;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor 
@NoArgsConstructor
public class FabricBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private FabricType fabricType;
	private FabricColor fabricColor;
	private FabricGSMType fabricGSMType;
	private Long fabricGSMValue;
	private boolean reinforcement;
	private FabricBagSeamType fabricBagSeamType;
	private FabricBagSeamColor fabricBagSeamColor;
	
}
