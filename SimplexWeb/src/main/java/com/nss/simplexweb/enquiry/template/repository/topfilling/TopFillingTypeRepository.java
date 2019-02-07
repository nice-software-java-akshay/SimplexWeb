package com.nss.simplexweb.enquiry.template.repository.topfilling;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nss.simplexweb.enquiry.template.model.topfilling.TopFillingType;

@Repository("topFillingTypeRepository")
public interface TopFillingTypeRepository extends JpaRepository<TopFillingType, Long>{

}
