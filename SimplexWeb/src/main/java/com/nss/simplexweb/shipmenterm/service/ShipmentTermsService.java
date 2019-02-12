package com.nss.simplexweb.shipmenterm.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nss.simplexweb.enums.PROJECT;
import com.nss.simplexweb.shipmenterm.model.ShipmentTerms;
import com.nss.simplexweb.shipmenterm.repository.ShipmentTermsRepository;

@Service("shipmentTermsService")
public class ShipmentTermsService {

	@Autowired
	private ShipmentTermsRepository shipmentTermsRepository;
	
	public ShipmentTermsService(ShipmentTermsRepository shipmentTermsRepository) {
		this.shipmentTermsRepository = shipmentTermsRepository;
	}
	
	public Object getActiveShipmentTermsList() {
		// TODO Auto-generated method stub
		return shipmentTermsRepository.findByIsActive(1);
	}
	
	public String saveNewShipmentTerm(ShipmentTerms shipmentTerms) {
		String retMsg = PROJECT.SUCCESS_MSG.name();
		try {
			shipmentTerms.setIsActive(1);
			shipmentTermsRepository.save(shipmentTerms);
		}catch (Exception e) {
			retMsg = PROJECT.ERROR_MSG.name();
		}
		return retMsg;
	}

	public ShipmentTerms getShipmentTermDetailsById(Long shipmentTermId) {
		return shipmentTermsRepository.findByShipmentTermId(shipmentTermId);
	}

	public ShipmentTerms deleteShipmentTermDetailsById(Long shipmentTermId) {
		ShipmentTerms shipmentTerms = shipmentTermsRepository.findByShipmentTermId(shipmentTermId);
		shipmentTerms.setIsActive(0);
		return shipmentTermsRepository.save(shipmentTerms);
	}

}
