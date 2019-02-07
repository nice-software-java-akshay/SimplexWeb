package com.nss.simplexweb.enquiry.template.repository.other;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nss.simplexweb.enquiry.template.model.other.DocPouchType;

@Repository("docPouchTypeRepository")
public interface DocPouchTypeRepository extends JpaRepository<DocPouchType, Long> {

}
