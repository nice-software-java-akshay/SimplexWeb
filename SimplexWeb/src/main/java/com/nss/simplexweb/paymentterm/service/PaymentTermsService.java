package com.nss.simplexweb.paymentterm.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nss.simplexweb.enums.PROJECT;
import com.nss.simplexweb.paymentterm.model.PaymentTermPartnerRel;
import com.nss.simplexweb.paymentterm.repository.PaymentTermPartnerRelRepository;
import com.nss.simplexweb.paymentterm.repository.PaymentTermsRepository;
import com.nss.simplexweb.po.model.PaymentTerms;
import com.nss.simplexweb.user.service.UserService;

@Service("paymentTermsService")
public class PaymentTermsService {

	@Autowired
	private PaymentTermsRepository paymentTermsRepository;
	
	@Autowired
	private PaymentTermPartnerRelRepository paymentTermPartnerRelRepository;
	
	@Autowired
	private UserService userService;
	
	public PaymentTermsService(PaymentTermsRepository paymentTermsRepository) {
		this.paymentTermsRepository = paymentTermsRepository;
	}
	
	public ArrayList<PaymentTerms> getActivePaymentTermsList(){
		return paymentTermsRepository.findByIsActive(1);
	}

	public String saveNewPaymentTerm(PaymentTerms paymentTerms) {
		String retMsg = PROJECT.SUCCESS_MSG.name();
		try {
			paymentTerms.setIsActive(1);
			paymentTermsRepository.save(paymentTerms);
		}catch (Exception e) {
			retMsg = PROJECT.ERROR_MSG.name();
		}
		return retMsg;
	}

	public PaymentTerms getPaymentTermDetailsById(Long paymentTermId) {
		return paymentTermsRepository.findByPaymentTermId(paymentTermId);
	}

	public PaymentTerms deletePaymentTermDetailsById(Long paymentTermId) {
		PaymentTerms paymentTerms = paymentTermsRepository.findByPaymentTermId(paymentTermId);
		paymentTerms.setIsActive(0);
		return paymentTermsRepository.save(paymentTerms);
	}

	public ArrayList<PaymentTermPartnerRel> getPaymentTermsListByPartnerId(Long partnerId) {
		return paymentTermPartnerRelRepository.findByPartner(userService.findUserByUserId(partnerId));
	}
	
}
