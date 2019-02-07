package com.nss.simplexweb.master.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.nss.simplexweb.enums.ENQUIRY;

@Controller
@RequestMapping(value={"/master/templateMaster"})
public class TemplateMasterController {
	
	/*@Autowired
	private TemplateService templateService;*/

	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView getTemplateMaster() {
		ModelAndView mav = new ModelAndView();
		mav
			//.addObject(ENQUIRY.PRODUCT_TYPE_LIST.name(), templateService.getProductTypeList())
			.setViewName("master/enquiry-template/MasterTemplate");
		return mav;
	}
}
