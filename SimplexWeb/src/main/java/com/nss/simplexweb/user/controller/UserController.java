package com.nss.simplexweb.user.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.nss.simplexweb.SessionUtility;
import com.nss.simplexweb.enums.COUNTRY;
import com.nss.simplexweb.enums.PROJECT;
import com.nss.simplexweb.enums.USER;
import com.nss.simplexweb.user.model.User;
import com.nss.simplexweb.user.repository.CountryRepository;
import com.nss.simplexweb.user.service.DistributerService;
import com.nss.simplexweb.user.service.UserService;
import com.nss.simplexweb.utility.Utility;

import ch.qos.logback.classic.pattern.Util;

@Controller
public class UserController {

	@Autowired
    private UserService userService;
	
	@Autowired
	private DistributerService distributerService;
	
	@Autowired
	private CountryRepository countryRepository;
	
	

    @RequestMapping(value={"/", "/login"}, method = RequestMethod.GET)
    public ModelAndView login(){
        ModelAndView mav = new ModelAndView();
        mav.setViewName("login");
        return mav;
    }
	
    @RequestMapping(value={"/registration"}, method = RequestMethod.GET)
    public ModelAndView register(){
        ModelAndView mav = new ModelAndView();
        User user = new User();
        mav
        	.addObject(COUNTRY.COUNTRY_LIST.name(), countryRepository.findAllByOrderByCountryNameAsc())
        	.addObject(USER.USER.name(), user)
        	.setViewName("registration");
        return mav;
    }
    
    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public ModelAndView createNewUser(@Valid User user, BindingResult bindingResult) {
        ModelAndView mav = new ModelAndView();
        User userExists = userService.findUserByEmailId(user.getEmail());
        if (userExists != null) {
        	System.out.println("user exists");
            bindingResult
                    .rejectValue("email", "error.user",
                            "There is already a user registered with the email provided");
        }
        if (bindingResult.hasErrors()) {
        	mav
        		.addObject(PROJECT.ERROR_MSG.name(), "User already exists!!")
        		.addObject(USER.USER.name(), new User())
        		.setViewName("registration");
        } else {
        	user = userService.processUseNameBeforeSaving(user);
        	distributerService.saveNewDistributerWithoutAutoGeneratePassword(user);
            mav
            	.addObject(PROJECT.SUCCESS_MSG.name(), "User has been registered successfully")
            	.addObject(USER.USER.name(), new User())
            	.setViewName("login");
        }
        return mav;
    } 
    
    @RequestMapping(value={"/forgotPassword"}, method = RequestMethod.GET)
    public ModelAndView forgotPassword(){
        ModelAndView mav = new ModelAndView();
        mav
        	.addObject(USER.USER.name(), new User())
        	.setViewName("forgot_password");
        return mav;
    }
    
    @RequestMapping(value={"/forgotPassword"}, method = RequestMethod.POST)
    public ModelAndView generateNewPassword(User user){
    	ModelAndView mav = new ModelAndView();
    	User userExists = userService.findUserByEmailIdAndIsActive(user.getEmail());
        if(userExists != null ){
        	userService.resetUserPassword(userExists);
        	mav
        		.addObject(PROJECT.RET_MSG.name(), "New password sent to registered email");
        }else {
        	mav
        		.addObject(PROJECT.RET_MSG.name(), "Invaid email, kindly register first");
        }
        mav
        	.addObject(USER.USER.name(), new User())
        	.setViewName("forgot_password");
        return mav;
    }
    
    @RequestMapping(value={"user/home"}, method = RequestMethod.GET)
    public ModelAndView userHome(HttpSession session){
        ModelAndView mav = new ModelAndView();
        
        mav
        	.setViewName("home");
        return mav;
    }
    
    @RequestMapping(value={"/changeMyPassword"}, method = RequestMethod.GET)
    public ModelAndView changeMyPassword(HttpSession session){
        ModelAndView mav = new ModelAndView();
        
        mav
        	.setViewName("home");
        return mav;
    }
    
    @RequestMapping(value={"/checkIfPasswordExists"}, method = RequestMethod.GET)
    @ResponseBody
    public boolean checkIfPasswordExists(String currentPassword ,HttpServletRequest request){
    	User user = SessionUtility.getUserFromSession(request);
        return userService.checkIfPasswordExists(user, currentPassword);
    }
    
    @RequestMapping(value={"/changeMyPassword"}, method = RequestMethod.POST)
    public ModelAndView changeMyPasswordPost(String newPassword, HttpServletRequest request, HttpSession session){
    	ModelAndView mav = new ModelAndView();
    	User user = SessionUtility.getUserFromSession(request);
        userService.changeUserPassword(user, newPassword);
        mav
	    	.addObject(PROJECT.SUCCESS_MSG.name(), "Please login again with new password")
	    	.addObject(USER.USER.name(), new User())
	    	.setViewName("login");
        
        //**important
        session.invalidate();
        return mav;
    }
    
}
