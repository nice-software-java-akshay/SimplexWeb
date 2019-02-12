package com.nss.simplexweb.shipmenterm.repository;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nss.simplexweb.shipmenterm.model.ShipmentTerms;

@Repository("shipmentTermsRepository")
public interface ShipmentTermsRepository extends JpaRepository<ShipmentTerms, Long> {

	ArrayList<ShipmentTerms> findByIsActive(int isActive);
	
	ShipmentTerms findByShipmentTermId(Long shipmentTermId);
}
