package com.nss.simplexweb.enquiry.template.repository.loop;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nss.simplexweb.enquiry.template.model.loop.LoopColor;

@Repository("loopColorRepository")
public interface LoopColorRepository extends JpaRepository<LoopColor, Long> {

}
