package com.nss.simplexweb.user.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.nss.simplexweb.SessionUtility;
import com.nss.simplexweb.enums.COUNTRY;
import com.nss.simplexweb.enums.PROJECT;
import com.nss.simplexweb.enums.ROLE;
import com.nss.simplexweb.enums.USER;
import com.nss.simplexweb.user.model.User;
import com.nss.simplexweb.user.repository.CountryRepository;
import com.nss.simplexweb.user.service.DistributerService;
import com.nss.simplexweb.user.service.UserService;

/**
 * 
 * @author NICESSLP100
 *Note: When anything in profile data is being changed, forcefully update the session attribute "USER" with new value
 */

@Controller
@RequestMapping(value="/profile")
public class ProfileController {
	
	@Autowired
	private CountryRepository countryRepository;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private DistributerService distributerService;
	
	@Autowired
	private ResourceLoader resourceLoader;
	
	@RequestMapping(value={"/viewMyProfile"}, method = RequestMethod.GET)
    public ModelAndView userProfile(HttpServletRequest request) {
    	ModelAndView mav = new ModelAndView();
    	User currentUser = userService.findUserByUserId(SessionUtility.getUserFromSession(request).getUserId());
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

    @RequestMapping(value={"/saveMyProfile"}, method = RequestMethod.POST)
    public ModelAndView saveProfile(HttpServletRequest request) {
    	ModelAndView mav = new ModelAndView();
    	User currentUser = userService.findUserByUserId(SessionUtility.getUserFromSession(request).getUserId());
    	mav
    		.addObject(USER.USER.name(), currentUser)
    		.addObject(COUNTRY.COUNTRY_LIST.name(), countryRepository.findAllByOrderByCountryNameAsc())
    		.setViewName("profile");
    	
    	return mav;
    }
    
    @RequestMapping(value={"/updateMyProfileDistributer"}, method = RequestMethod.POST)
    public String updateDistributerProfile(User user, HttpServletRequest request) {
    	user = userService.processUseNameBeforeSaving(user);
    	user = distributerService.updateDistributerWithoutPassword(user);
    	request.getSession(true).setAttribute(USER.USER.name(), user);
    	return "redirect:/profile/viewMyProfile";
    }
   
    @RequestMapping(value={"/uploadMyProfilePicture"}, method = RequestMethod.POST)
    public String uploadMyProfilePicture(@RequestParam("file") MultipartFile file,
                                   RedirectAttributes redirectAttributes,
                                   HttpServletRequest request) {
    	 if (file.isEmpty()) {
             redirectAttributes.addFlashAttribute(PROJECT.ERROR_MSG.name(), "Please select a file to upload");
             return "redirect:uploadStatus";
         }
    	 User currentUser = SessionUtility.getUserFromSession(request);
    	 userService.uploadMyProfilePictureDistributer(file, currentUser);
    	 
    	 return "redirect:/profile/viewMyProfile";
    }
    
    @RequestMapping(value = "/getMyProfilePictureByPath")
    @ResponseBody
    public byte[] getImage(@RequestParam("imageFullPath") String imageFullPath) throws IOException {
        File file = new File(imageFullPath);
    	if(file.exists() && file.isFile()) {
    		return Files.readAllBytes(file.toPath());
    	}else {
    		Resource fileResource = resourceLoader.getResource("classpath:static/img/default_profile_img.jpg");
    		return Files.readAllBytes(fileResource.getFile().toPath());
    	}        
    }
    
    @RequestMapping(value = "/getMyProfilePictureByUserId")
    @ResponseBody
    public byte[] getImage(@RequestParam("userId") Long userId) throws IOException {
    	User user = userService.findUserByUserId(userId);
    	String filepath = user.getProfilePicFolderpath()+ File.separator + user.getProfilePicFilename();
    	
    	File file = new File(filepath);
    	if(file.exists() && file.isFile()) {
    		return Files.readAllBytes(file.toPath());
    	}else {
    		Resource fileResource = resourceLoader.getResource("classpath:static/img/default_profile_img.jpg");
    		return Files.readAllBytes(fileResource.getFile().toPath());
    	}        
    }
}
