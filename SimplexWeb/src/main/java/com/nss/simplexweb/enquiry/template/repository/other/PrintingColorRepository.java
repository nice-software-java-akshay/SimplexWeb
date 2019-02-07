package com.nss.simplexweb.enquiry.template.repository.other;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nss.simplexweb.enquiry.template.model.other.PrintingColor;

@Repository("printingColorRepository")
public interface PrintingColorRepository extends JpaRepository<PrintingColor, Long> {

}
