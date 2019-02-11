package com.nss.simplexweb.master.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.validation.Valid;

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
import com.nss.simplexweb.enums.EMPLOYEE;
import com.nss.simplexweb.enums.PROJECT;
import com.nss.simplexweb.enums.ROLE;
import com.nss.simplexweb.enums.USER;
import com.nss.simplexweb.user.model.Role;
import com.nss.simplexweb.user.model.User;
import com.nss.simplexweb.user.service.CountryService;
import com.nss.simplexweb.user.service.EmployeeService;
import com.nss.simplexweb.user.service.RoleService;
import com.nss.simplexweb.user.service.UserService;

@Controller
@RequestMapping(value={"/master/employeeMaster"})
public class EmployeeMasterController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private EmployeeService employeeService;
	
	@Autowired
	private RoleService roleService;
	
	@Autowired
	private CountryService countryService;
	

	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView getEmployeeList(@SessionAttribute("USER") User user) {
		ModelAndView mav = new ModelAndView();
		String CURRENT_USER_ROLE = user.getRole().getRoleAbbr();
		ArrayList<Role> roleList = null;
		if(CURRENT_USER_ROLE.equalsIgnoreCase(ROLE.DIR.name())) {
			roleList = roleService.getAllRoleList();
		}else {
			roleList = roleService.getRoleListExcludingAdminRole();
		}
		mav
			.addObject(USER.EMP_LIST.name(), employeeService.findAllllEmployeesUnderRoleId(user.getRole().getRoleId()))
			.addObject(USER.USER.name(), new User())
			.addObject(ROLE.ROLE_LIST.name(), roleList)
			.addObject(COUNTRY.COUNTRY_LIST.name(), countryService.getAllCountryList())
			.setViewName("master/employee/employeeMaster");
		return mav;
	}
	
	
	@RequestMapping(value = "/addNewEmployee", method = RequestMethod.POST)
	public ModelAndView addNewEmployee(@Valid User user, BindingResult bindingResult, @SessionAttribute("USER") User currentUser) {
		ModelAndView mav = new ModelAndView();
		ArrayList<Role> roleList = null;
		String CURRENT_USER_ROLE = user.getRole().getRoleAbbr();
		if(CURRENT_USER_ROLE.equalsIgnoreCase(ROLE.DIR.name())) {
			roleList = roleService.getAllRoleList();
		}else {
			roleList = roleService.getRoleListExcludingAdminRole();
		}
		User userExists = userService.findUserByEmailId(user.getEmail());
        if (userExists != null) {
        	System.out.println("user exists");
            bindingResult
                    .rejectValue("email", "error.user",
                            "There is already a user registered with the email provided");
        }
        if (bindingResult.hasErrors()) {
        	mav
        		.addObject(USER.USER.name(), new User())
        		.addObject(USER.EMP_LIST.name(), employeeService.findAllllEmployeesUnderRoleId(user.getRole().getRoleId()))
    			.addObject(USER.USER.name(), new User())
    			.addObject(ROLE.ROLE_LIST.name(), roleService.getAllRoleList())
    			.addObject(COUNTRY.COUNTRY_LIST.name(), countryService.getAllCountryList())
    			.setViewName("master/employeeMaster");
        } else {
        	user = userService.processUseNameBeforeSaving(user);
            employeeService.saveNewEmployeeAutoGeneratePassword(user);
            mav
            	.addObject(PROJECT.SUCCESS_MSG.name(), "User has been saved successfully")
            	.addObject(USER.USER.name(), new User())
        		.addObject(USER.EMP_LIST.name(), employeeService.findAllllEmployeesUnderRoleId(user.getRole().getRoleId()))
    			.addObject(USER.USER.name(), new User())
    			.addObject(ROLE.ROLE_LIST.name(), roleList)
    			.addObject(COUNTRY.COUNTRY_LIST.name(), countryService.getAllCountryList())
    			.setViewName("master/employee/employeeMaster");
        }
		
		return mav;
	}
	
	
	@RequestMapping(value = "/getEmployeeDetailsById", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Map<String, Object> getEmployeeDetailsModal(@RequestParam("userid") Long userId) {
		HashMap<String, Object> map = new HashMap<>();
		map.put(EMPLOYEE.EMPLOYEE.name(), userService.findUserByUserId(userId)); 
		return map;
	}
 	
	/*@RequestMapping(value = "/getEditEmployeeDetailsModal", method = RequestMethod.GET)
	public ModelAndView getEditEmployeeDetailsModal(@RequestParam("userid") Long userId, @SessionAttribute("USER") User user) {
		ModelAndView mav = new ModelAndView();
		ArrayList<Role> roleList = null;
		String CURRENT_USER_ROLE = user.getRole().getRoleAbbr();
		if(CURRENT_USER_ROLE.equalsIgnoreCase(ROLE.DIR.name())) {
			roleList = roleService.getAllRoleList();
		}else {
			roleList = roleService.getRoleListExcludingAdminRole();
		}
		mav
			.addObject(USER.USER.name(), userService.findUserByUserId(userId))
			.addObject(ROLE.ROLE_LIST.name(), roleList)
			.addObject(COUNTRY.COUNTRY_LIST.name(), countryService.getAllCountryList())
			.setViewName("master/employee/employeeEditModal");
		return mav;
	}*/
	
	
	@RequestMapping(value = "/updateEmployee", method = RequestMethod.POST)
	public @ResponseBody String saveEditedEmployee(User user, ModelMap map) {
        User updatedUser = employeeService.updateEmlpoyeeWithoutPassword(user);
        map
        	.addAttribute(PROJECT.SUCCESS_MSG.name(), "User has been updated successfully")
        	.addAttribute(USER.USER.name(), updatedUser);
		
		return map.toString();
	}
	
	@RequestMapping(value = "/deleteEmployee", method = RequestMethod.GET)
	public @ResponseBody String deleteEmployee(Long userid, ModelMap map) {
        User deletedUser = employeeService.inActivateEmployeeById(userid);
        map
        	.addAttribute(PROJECT.SUCCESS_MSG.name(), "User has been updated successfully")
        	.addAttribute(USER.USER.name(), deletedUser);
		
		return map.toString();
	}
	
}
