package com.nss.simplexweb.enquiry.template.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value={"/enquiry/ABCTemplateController"})
public class ABCTemplateController {

	
	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView getEmployeeList() {
		ModelAndView mav = new ModelAndView();
		mav
			.setViewName("enquiry/templates/ABCTemplate");
		return mav;
	}
}
