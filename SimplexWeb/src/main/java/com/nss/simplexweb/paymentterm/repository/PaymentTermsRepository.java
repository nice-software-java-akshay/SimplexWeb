package com.nss.simplexweb.paymentterm.repository;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nss.simplexweb.po.model.PaymentTerms;

@Repository("paymentTermsRepository")
public interface PaymentTermsRepository extends JpaRepository<PaymentTerms, Long> {

	ArrayList<PaymentTerms> findByIsActive(int isActive);
	
	PaymentTerms findByPaymentTermId(Long paymentTermId);
}
