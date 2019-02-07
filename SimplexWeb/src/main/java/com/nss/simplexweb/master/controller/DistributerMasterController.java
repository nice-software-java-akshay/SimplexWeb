package com.nss.simplexweb.master.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.servlet.ModelAndView;

import com.nss.simplexweb.enums.COUNTRY;
import com.nss.simplexweb.enums.DISTRIBUTER;
import com.nss.simplexweb.enums.PROJECT;
import com.nss.simplexweb.enums.USER;
import com.nss.simplexweb.user.model.User;
import com.nss.simplexweb.user.service.CountryService;
import com.nss.simplexweb.user.service.UserService;

@Controller
@RequestMapping("/master/distributerMaster")
public class DistributerMasterController {

	@Autowired
	private UserService userService;
	
	@Autowired
	private CountryService countryService;
	
	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView getAllDistributersList() {
		ModelAndView mav = new ModelAndView();
		System.out.println(userService.getAllDistributersList());
		mav
			.addObject(DISTRIBUTER.DISTRIBUTER_LIST.name(), userService.getAllDistributersList())
			.addObject(USER.USER.name(), new User())
			.addObject(COUNTRY.COUNTRY_LIST.name(), countryService.getAllCountryList())
			.setViewName("master/distributer/distributerMaster");
		return mav;
	}
	
	@RequestMapping(value = "/addNewDistributer", method = RequestMethod.POST)
	public ModelAndView addNewDistributer(User user, BindingResult bindingResult, @SessionAttribute("USER") User currentUser) {
		ModelAndView mav = new ModelAndView();
		User userExists = userService.findUserByEmail(user.getEmail());
        if (userExists != null) {
        	System.out.println("user exists");
            bindingResult
                    .rejectValue("email", "error.user",
                            "There is already a user registered with the email provided");
        }
        if (bindingResult.hasErrors()) {
        	mav
        		.addObject(USER.USER.name(), new User())
        		.addObject(USER.EMP_LIST.name(), userService.getAllEmployeesUnderMe(currentUser.getRole().getRoleId()))
    			.addObject(USER.USER.name(), new User())
    			.addObject(COUNTRY.COUNTRY_LIST.name(), countryService.getAllCountryList())
    			.setViewName("master/distributerMaster");
        } else {
        	user = userService.processUseNameBeforeSaving(user);
            userService.saveDistributer(user);
            mav
            	.addObject(PROJECT.SUCCESS_MSG.name(), "User has been saved successfully")
            	.addObject(DISTRIBUTER.DISTRIBUTER_LIST.name(), userService.getAllDistributersList())
    			.addObject(USER.USER.name(), new User())
    			.addObject(COUNTRY.COUNTRY_LIST.name(), countryService.getAllCountryList())
    			.setViewName("master/distributer/distributerMaster");
        }
		
		return mav;
	}
	
	@RequestMapping(value = "/getDistributerDetailsById", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Map<String, Object> getDistributerDetailsById(@RequestParam("userid") Long userId) {
		HashMap<String, Object> map = new HashMap<>();
		map.put(DISTRIBUTER.DISTRIBUTER.name(), userService.getUserById(userId)); 
		return map;
	}
	
	
	@RequestMapping(value = "/updateDistributer", method = RequestMethod.POST)
	public @ResponseBody String saveEditedDistributer(User user, ModelMap map) {
        User updatedUser = userService.updateDistributer(user);
        map
        	.addAttribute(PROJECT.SUCCESS_MSG.name(), "User has been updated successfully")
        	.addAttribute(USER.USER.name(), updatedUser);
		
		return map.toString();
	}
	
	
	@RequestMapping(value = "/deleteDistributer", method = RequestMethod.GET)
	public @ResponseBody String deleteDistributer(Long userid, ModelMap map) {
        User deletedUser = userService.deleteUser(userid);
        map
        	.addAttribute(PROJECT.SUCCESS_MSG.name(), "User has been updated successfully")
        	.addAttribute(USER.USER.name(), deletedUser);
		
		return map.toString();
	}
}
