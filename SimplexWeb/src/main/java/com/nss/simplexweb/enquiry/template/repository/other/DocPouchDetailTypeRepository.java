package com.nss.simplexweb.enquiry.template.repository.other;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nss.simplexweb.enquiry.template.model.other.DocPouchDetailType;

@Repository("docPouchDetailTypeRepository")
public interface DocPouchDetailTypeRepository extends JpaRepository<DocPouchDetailType, Long> {

}
