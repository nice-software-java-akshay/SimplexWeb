package com.nss.simplexweb.po.controller;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.nss.simplexweb.SessionUtility;
import com.nss.simplexweb.enquiry.template.model.EnquiryTemplateBean;
import com.nss.simplexweb.enquiry.template.model.product.ProductModelType;
import com.nss.simplexweb.enquiry.template.service.EnquiryTemplateService;
import com.nss.simplexweb.enums.COMPANY;
import com.nss.simplexweb.enums.ENQUIRY;
import com.nss.simplexweb.enums.PO;
import com.nss.simplexweb.po.model.PODetail;
import com.nss.simplexweb.po.model.POItems;
import com.nss.simplexweb.user.model.User;
import com.nss.simplexweb.user.service.MainComapanyService;

@Controller
@RequestMapping("/po/newPOController")
public class NewPOController {
	
	@Autowired
	private MainComapanyService mainComapanyService;
	
	@Autowired
	private EnquiryTemplateService enquiryTemplateService;

	@RequestMapping(value = "/placeOrder", method = RequestMethod.GET)
	public ModelAndView placeNewOrder(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		User user = SessionUtility.getUserFromSession(request);
		ArrayList<EnquiryTemplateBean> enquiryHistoryList = enquiryTemplateService.getEnquiryHistoryForUser(user);
		ArrayList<POItems> poItmesList = new ArrayList<>(); 
		POItems poItem = new POItems(1L, "skjdgc45", "sdkjv", 10.0, 12.0, 120.0, new ProductModelType(), new PODetail());
		poItmesList.add(poItem);
		
		PODetail poDetail = new PODetail();
		poDetail.setPoItmesList(poItmesList);
		
		mav
			.addObject(COMPANY.COMPANY.name(), mainComapanyService.getMainComapnyInfo())
			.addObject(ENQUIRY.ENQUIRY_HISTORY_LIST, enquiryHistoryList)
			.addObject(PO.PO_DETAIL.name(), poDetail)
			.setViewName("po/place-order");
		return mav;
	}
	
	@RequestMapping(value = "/saveNewOrder", method = RequestMethod.POST)
	public ModelAndView saveNewOrder(PODetail poDetail) {
		System.out.println("poDetail : " + poDetail);
		return null;
	}
	
}
