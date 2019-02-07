package com.nss.simplexweb.configuration;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class NoAuthHandler {

	/*
	 * 
	 * For Page Configuration Options
	 * 
	 * */
	@RequestMapping(value={"/skin-config.html"}, method = RequestMethod.GET)
    public ModelAndView skinConfig(){
        ModelAndView mav = new ModelAndView();
        mav
        	.setViewName("skin-config");
        return mav;
    }
	
	/*
	 * 
	 * For Topbar Page
	 * 
	 * */
	@RequestMapping(value={"/topbar"}, method = RequestMethod.GET)
    public ModelAndView topbar(){
		System.out.println("NoAuthHandler : /topbar called");
        ModelAndView mav = new ModelAndView();
        mav
        	.setViewName("topbar");
        return mav;
    }
	
	/*
	 * 
	 * For Sidebar Page
	 * 
	 * */
	@RequestMapping(value={"/sidebar"}, method = RequestMethod.GET)
    public ModelAndView sidebar(){
        ModelAndView mav = new ModelAndView();
        mav
        	.setViewName("sidebar");
        return mav;
    }
	
	/*
	 * 
	 * For Footer Page
	 * 
	 * */
	@RequestMapping(value={"/footer"}, method = RequestMethod.GET)
    public ModelAndView footer(){
        ModelAndView mav = new ModelAndView();
        mav
        	.setViewName("footer");
        return mav;
    }
	
	/*
	 * 
	 * For Context Variable Page
	 * 
	 * */
	@RequestMapping(value={"/contextPage"}, method = RequestMethod.GET)
    public ModelAndView contextPage(){
        ModelAndView mav = new ModelAndView();
        mav
        	.setViewName("contextPage");
        return mav;
    }
}
