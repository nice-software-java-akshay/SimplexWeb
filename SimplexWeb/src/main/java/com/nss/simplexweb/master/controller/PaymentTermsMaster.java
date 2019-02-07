package com.nss.simplexweb.master.controller;

import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.servlet.ModelAndView;

import com.nss.simplexweb.SessionUtility;
import com.nss.simplexweb.enums.PAYMENT_TERMS;
import com.nss.simplexweb.enums.USER;
import com.nss.simplexweb.paymentterm.service.PaymentTermsService;
import com.nss.simplexweb.po.model.PaymentTerms;
import com.nss.simplexweb.user.model.User;
import com.nss.simplexweb.user.service.UserService;

@Controller
@RequestMapping(value={"/master/paymentTermsMaster"})
public class PaymentTermsMaster {
	
	@Autowired
	private PaymentTermsService paymentTermsService;
	
	@Autowired
	private UserService userService;

	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView getPaymentList(@SessionAttribute("USER") User user) {
		ModelAndView mav = new ModelAndView();
		mav
			.addObject(USER.USER.name(), new User())
			.addObject(PAYMENT_TERMS.PAYMENT_TERMS.name(), new PaymentTerms())
			.addObject(PAYMENT_TERMS.PYAMENT_TERMS_LIST.name(), paymentTermsService.getActivePaymentTermsList())
			.setViewName("master/payment-terms/payment_terms_master");
		
		return mav;
	}
	
	@RequestMapping(value={"/saveNewPaymentTerm"}, method = RequestMethod.POST)
	public @ResponseBody String saveNewPaymentTerm(PaymentTerms paymentTerms, HttpServletRequest request) {
		User user = SessionUtility.getUserFromSession(request);
		paymentTerms.setCreatedBy(user);
		return paymentTermsService.saveNewPaymentTerm(paymentTerms);
	}
	
	@RequestMapping(value= {"/getPaymentTermDetailsById"}, method = RequestMethod.GET)
	public @ResponseBody PaymentTerms getPaymentTermDetailsById(Long paymentTermId) {
		return paymentTermsService.getPaymentTermDetailsById(paymentTermId);
	}
	
	@RequestMapping(value= {"/deletePaymentTermDetailsById"}, method = RequestMethod.GET)
	public @ResponseBody PaymentTerms deletePaymentTermDetailsById(Long paymentTermId) {
		return paymentTermsService.deletePaymentTermDetailsById(paymentTermId);
	}
	
	@RequestMapping(value= {"/getAssignPaymentTermsToClientView"}, method = RequestMethod.GET)
	public ModelAndView getAssignPaymentTermsToClientView() {
		ModelAndView mav = new ModelAndView();
		mav
			.addObject(USER.USER.name(), new User())
			.addObject(PAYMENT_TERMS.PYAMENT_TERMS_LIST.name(), paymentTermsService.getActivePaymentTermsList())
			.addObject(USER.USER_LIST.name(), userService.getAllDistributersList())
			.setViewName("master/payment-terms/assign_payment_terms_to_partners");
		
		return mav;
	}
	
	@RequestMapping(value= "/getPaymentTermsListByPartnerId", method = RequestMethod.GET)
	@ResponseBody 
	public HashMap<String, ArrayList<?>> getPaymentTermsListByPartnerId(Long partnerId) {
		HashMap<String, ArrayList<?>> map = new HashMap<>();
		map.put(PAYMENT_TERMS.PYAMENT_TERMS_LIST_FOR_PARTNER.name(), paymentTermsService.getPaymentTermsListByPartnerId(partnerId));
		map.put(PAYMENT_TERMS.PYAMENT_TERMS_LIST.name(), paymentTermsService.getActivePaymentTermsList());
		return map;
	}
}
