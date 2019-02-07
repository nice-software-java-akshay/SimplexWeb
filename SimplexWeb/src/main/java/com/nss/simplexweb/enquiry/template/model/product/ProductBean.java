package com.nss.simplexweb.enquiry.template.model.product;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor 
@NoArgsConstructor
public class ProductBean implements Serializable {

	/**
	 * 
	 */
	private static final Long serialVersionUID = 1L;

	private ProductType productType;
	private ProductModelType productModelType;
	private ProductUnitTypeLength productUnitTypeLength;
	private ProductunitTypeWeight productUnitTypeWeight;
	private boolean baffle;
		private Long baffleValue;
	private ProductSurfaceType surfaceType;
		private Long surfaceLength;
		private Long surfaceWidth;
		private Long surfaceHeight;
	private boolean swl;
	private boolean un;
	private boolean uv;
	private ProductSFtype productSFtype;
	private String remarks;
}
