package com.nss.simplexweb.enquiry.template.repository.other;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nss.simplexweb.enquiry.template.model.other.Sides;

@Repository("sidesRepository")
public interface SidesRepository extends JpaRepository<Sides, Long> {

}
