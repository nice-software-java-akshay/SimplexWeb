package com.nss.simplexweb.enquiry.template.repository.other;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nss.simplexweb.enquiry.template.model.other.LinerType;


@Repository("linerTypeRepository")
public interface LinerTypeRepository extends JpaRepository<LinerType, Long> {

}
