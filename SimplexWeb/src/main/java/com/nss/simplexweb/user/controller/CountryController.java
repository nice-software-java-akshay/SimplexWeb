package com.nss.simplexweb.user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.nss.simplexweb.enums.COUNTRY;
import com.nss.simplexweb.user.service.CountryService;

@Controller
public class CountryController {

	@Autowired
	private CountryService countryService;
	
	@RequestMapping(value={"/noAuth/countryList"}, method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ModelAttribute
    public void addAttributes(Model model) {
		System.out.println("CountryController : " + countryService.getAllCountryList());
        model.addAttribute(COUNTRY.COUNTRY_LIST.name(), countryService.getAllCountryList());
    }
}
