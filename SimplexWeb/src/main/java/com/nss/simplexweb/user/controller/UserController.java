package com.nss.simplexweb.user.controller;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.nss.simplexweb.enums.COUNTRY;
import com.nss.simplexweb.enums.PROJECT;
import com.nss.simplexweb.enums.USER;
import com.nss.simplexweb.user.model.User;
import com.nss.simplexweb.user.repository.CountryRepository;
import com.nss.simplexweb.user.service.UserService;

@Controller
public class UserController {

	@Autowired
    private UserService userService;
	
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
        		.setViewName("registration");
        } else {
        	user = userService.processUseNameBeforeSaving(user);
            userService.saveDistributer(user);
            mav
            	.addObject("successMessage", "User has been registered successfully")
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
    	User userExists = userService.findUserByEmail(user.getEmail());
        if(userExists != null ){
        	userService.emailNewPassword(userExists);
        	mav
        		.addObject(PROJECT.RET_MSG.name(), "New password sent to registered email");
        }else {
        	mav
        		.addObject(PROJECT.RET_MSG.name(), "Invaid email, kindly register");
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
    
}
