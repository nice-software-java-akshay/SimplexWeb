package com.nss.simplexweb.enquiry.template.model.shipment;

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
public class ShipmentBean implements Serializable {

	/**
	 * 
	 */
	private static final Long serialVersionUID = 1L;

	private ArrayList<ShipmentType> shipmentType;
	private Long shipmentQuantity;
}
