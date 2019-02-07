package com.nss.simplexweb.enquiry.template.repository.fabric;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nss.simplexweb.enquiry.template.model.fabric.FabricGSMType;

@Repository("fabricGSMTypeRepository")
public interface FabricGSMTypeRepository extends JpaRepository<FabricGSMType, Long>{

}
