package com.nss.simplexweb.enquiry.template.service.shipment;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nss.simplexweb.enquiry.template.model.shipment.ShipmentType;
import com.nss.simplexweb.enquiry.template.repository.shipment.ShipmentTypeRepository;

@Service("shipmentTypeService")
public class ShipmentTypeService {

	@Autowired
	private ShipmentTypeRepository shipmentTypeRepository;
	
	public ShipmentTypeService(ShipmentTypeRepository shipmentTypeRepository) {
		// TODO Auto-generated constructor stub
		this.shipmentTypeRepository = shipmentTypeRepository;
	}
	
	public List<ShipmentType> getShipmentTypeList() {
		// TODO Auto-generated method stub
		return shipmentTypeRepository.findAll();
	}
}
