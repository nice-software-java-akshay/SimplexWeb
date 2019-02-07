package com.nss.simplexweb.enquiry.template.repository.bottomdischarge;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nss.simplexweb.enquiry.template.model.bottomdischarge.BottomType;

@Repository("BottomTypeRepository")
public interface BottomTypeRepository extends JpaRepository<BottomType, Long> {

}
