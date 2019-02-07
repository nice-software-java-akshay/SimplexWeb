package com.nss.simplexweb.enquiry.template.repository.fabric;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nss.simplexweb.enquiry.template.model.fabric.FabricBagSeamType;

@Repository("fabricBagSeamTypeRepository")
public interface FabricBagSeamTypeRepository extends JpaRepository<FabricBagSeamType, Long>{

}
