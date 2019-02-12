package com.nss.simplexweb.paymentterm.repository;

import java.util.ArrayList;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nss.simplexweb.paymentterm.model.PaymentTermPartnerRel;
import com.nss.simplexweb.user.model.User;

public interface PaymentTermPartnerRelRepository extends JpaRepository<PaymentTermPartnerRel, Long> {

	ArrayList<PaymentTermPartnerRel> findByPartner(User partner);
	
	@Transactional	//Need to @Transactional while creating custom deleteBy function
	ArrayList<PaymentTermPartnerRel> deleteByPartner(User partner);
}
