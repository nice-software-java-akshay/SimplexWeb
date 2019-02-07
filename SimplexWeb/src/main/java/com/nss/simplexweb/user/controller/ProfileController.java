package com.nss.simplexweb.user.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.nss.simplexweb.SessionUtility;
import com.nss.simplexweb.enums.COUNTRY;
import com.nss.simplexweb.enums.ROLE;
import com.nss.simplexweb.enums.USER;
import com.nss.simplexweb.user.model.User;
import com.nss.simplexweb.user.repository.CountryRepository;

@Controller
@RequestMapping(value="/profile")
public class ProfileController {
	
	@Autowired
	private CountryRepository countryRepository;
	
	@RequestMapping(value={"/viewProfile"}, method = RequestMethod.GET)
    public ModelAndView userProfile(HttpServletRequest request) {
    	ModelAndView mav = new ModelAndView();
    	User currentUser = SessionUtility.getUserFromSession(request);
    	mav
    		.addObject(USER.USER.name(), currentUser)
    		.addObject(COUNTRY.COUNTRY_LIST.name(), countryRepository.findAllByOrderByCountryNameAsc());
    	if(currentUser.getRole().getRoleAbbr().equalsIgnoreCase(ROLE.DIST.name())) {
    		mav.setViewName("profile/distributer-profile");
    	}else {
    		mav.setViewName("profile/employee-profile");
    	}	
    	return mav;
    }

    @RequestMapping(value={"/saveProfile"}, method = RequestMethod.POST)
    public ModelAndView saveProfile(HttpServletRequest request) {
    	ModelAndView mav = new ModelAndView();
    	mav
    		.addObject(USER.USER.name(), SessionUtility.getUserFromSession(request))
    		.addObject(COUNTRY.COUNTRY_LIST.name(), countryRepository.findAllByOrderByCountryNameAsc())
    		.setViewName("profile");
    	return mav;
    }
}
