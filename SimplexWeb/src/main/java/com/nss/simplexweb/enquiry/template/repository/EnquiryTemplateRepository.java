package com.nss.simplexweb.enquiry.template.repository;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nss.simplexweb.enquiry.template.model.EnquiryTemplateBean;
import com.nss.simplexweb.user.model.User;

@Repository("enquiryTemplateRepository")
public interface EnquiryTemplateRepository extends JpaRepository<EnquiryTemplateBean, Long> {

	EnquiryTemplateBean findByEnquiryId(Long enquiryId);
	
	EnquiryTemplateBean findByEnquiryIdAndEnquiryNumber(Long enquiryId, String enquiryNumber);
	
	ArrayList<EnquiryTemplateBean> findByRequester(User user);
}
