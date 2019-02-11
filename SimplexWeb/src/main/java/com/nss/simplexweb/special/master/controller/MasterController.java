package com.nss.simplexweb.special.master.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.nss.simplexweb.enums.USER;
import com.nss.simplexweb.enums.USER_ACCESS;
import com.nss.simplexweb.user.service.UserAccessService;
import com.nss.simplexweb.user.service.UserService;
import com.nss.simplexweb.utility.Utility;

@Controller
@RequestMapping(value="/special/master")
public class MasterController {
	@Autowired
    private UserService userService;
	
	@Autowired
    private UserAccessService userAccessService;
	
	@Autowired
	private Utility utility;
	
	@RequestMapping(value={"/accessMaster"}, method = RequestMethod.GET)
	public ModelAndView accessMaster() {
		ModelAndView mav = new ModelAndView();
    	mav
    		.addObject(USER.USER_LIST.name(), userService.findAllActiveUsersList())
    		.addObject(USER_ACCESS.ACCESS_LIST.name(), userAccessService.getAllAccessList())
    		.addObject(USER_ACCESS.URL_LIST.name(), utility.getEndPoints())
    		.setViewName("SPECIALAccessMaster");
    	return mav;
	}
	
	@RequestMapping(value= {"/userMaster"}, method = RequestMethod.GET)
	public ModelAndView userMaster(HttpSession session) {
		ModelAndView mav = new ModelAndView();
		mav
			.addObject(USER.USER_LIST.name(), userService.findAllActiveUsersList())
			.setViewName("SPECIALUserMaster");
		return mav;
	}
}
