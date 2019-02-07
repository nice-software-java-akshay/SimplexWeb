package com.nss.simplexweb.enquiry.template.repository.bottomdischarge;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nss.simplexweb.enquiry.template.model.bottomdischarge.BottomDischargeType;

@Repository("bottomDischargeTypeRepository")
public interface BottomDischargeTypeRepository extends JpaRepository<BottomDischargeType, Long> {

}
