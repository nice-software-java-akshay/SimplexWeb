package com.nss.simplexweb.enquiry.template.repository.loop;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nss.simplexweb.enquiry.template.model.loop.LoopType;

@Repository("loopTypeRepository")
public interface LoopTypeRepository extends JpaRepository<LoopType, Long> {

}
