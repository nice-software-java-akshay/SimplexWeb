package com.nss.simplexweb.enquiry.template.repository.shipment;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nss.simplexweb.enquiry.template.model.shipment.ShipmentType;

@Repository("shipmentTypeRepository")
public interface ShipmentTypeRepository extends JpaRepository<ShipmentType, Long> {

}
