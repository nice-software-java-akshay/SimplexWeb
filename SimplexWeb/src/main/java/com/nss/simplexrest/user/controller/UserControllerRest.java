package com.nss.simplexrest.user.controller;

import java.util.ArrayList;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nss.simplexrest.custom.exception.AlreadyExistsException;
import com.nss.simplexrest.custom.exception.NotFoundException;
import com.nss.simplexweb.user.model.User;
import com.nss.simplexweb.user.service.DistributerService;
import com.nss.simplexweb.user.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/rest/user")
@Api(value = "User Resource REST Endpoint", description = "All User Related Operations")
public class UserControllerRest {

	@Autowired
    private UserService userService;
	
	@Autowired
    private DistributerService distributerService;

    @PostMapping(value="/login")
    @ApiOperation(value = "Returns user details to be persist in application scope"
    			, notes = "User model needs be sent for authentication")
    public User login(@RequestBody User user){
    	System.out.println("user : " + user);
        User userDetails = userService.findUserByEmailIdAndNonEncrPassword(user.getEmail(), user.getPassword());
    		if(!userService.checkIfUserExistsByUserId(userDetails.getUserId())) {
    			throw new NotFoundException("User", "email : " + user.getEmail(), null);
    		}
    		
    	return userDetails;
    }
    
    
    @PostMapping(value = "/registration")
    public User registerDistributer(@RequestBody @Valid User user, BindingResult bindingResult) {
        User userExists = userService.findUserByEmailId(user.getEmail());
        if (userExists != null) {
            bindingResult
                    .rejectValue("email", "error.user",
                            "There is already a user registered with the email provided");
        }
        if (bindingResult.hasErrors()) {
        	throw new AlreadyExistsException("User", "email : " + user.getEmail(), null);
        } else {
        	user = userService.processUseNameBeforeSaving(user);
            user = distributerService.saveNewDistributerWithoutAutoGeneratePassword(user);
        }
        return user;
    }

    
    @GetMapping(value="/getAllUsersList")
    public ArrayList<User> getAllUsersList() {
    	return userService.findAllActiveUsersList();
    }
    
    @GetMapping(value="/getUserProfileDetails/{userId}")
    public User getUserProfileDetails(@NonNull @PathVariable("userId") Long userId) {
    	User user = userService.findUserByUserId(userId);
        if (user == null) {
        	throw new NotFoundException("User", "UserId : " + userId, null);
        }
        return user;
    }
    
    /*@GetMapping(value="/getEmployeesUnderUser/{userId}")
    public ArrayList<User> getEmployeesUnderUser(@NonNull @PathVariable("userId") Long userId) {
    	return userService.getAllUsersImmediatelyUnderMe(userId);
    }
    
    
    @PutMapping("/updateUser/{userId}")
    public User updateUser(@RequestBody User user, @NonNull @PathVariable long userId) {
    	User existingUser = userService.findUserByUserId(userId);
    	if (!userService.checkIfUserExists(existingUser))
    		throw new NotFoundException("User", "UserId : " + userId, null);
    	user.setUserId(userId);
    	user = userService.saveNewEmployee(user);
    	return user;
    }
    
    @DeleteMapping("/deleteUser/{userId}")
    public User deleteUser(@NonNull @PathVariable long userId) {
    	User existingUser = userService.findUserByUserId(userId);
    	if (!userService.checkIfUserExists(existingUser))
    		throw new NotFoundException("User", "UserId : " + userId, null);
    	else
    		userService.deleteUser(userId);
    	return existingUser;
    } 
    
    
    @GetMapping("/doesPasswordExist/{userId}/{password}")
    @ApiOperation(value = "Returns boolean (true/false) for user existence"
	, notes = "user id of user whose password is to be checked")
    public boolean doesPasswordExist(@NonNull @PathVariable Long userId, @NonNull @PathVariable String password) {
    	User existingUser = userService.findUserByUserId(userId);
    	if (!userService.checkIfUserExists(existingUser))
    		throw new NotFoundException("User", "UserId : " + userId, null);
    	return userService.doesPasswordExist(existingUser, password);
    }
    
    @GetMapping("/doesEmailExist/{email}")
    @ApiOperation(value = "Returns boolean (true/false) for user existence"
	, notes = "user id of user whose password is to be checked")
    public boolean doesEmailExist(@NonNull @PathVariable String email) {
    	User existingUser = userService.findUserByEmail(email);
    	if (!userService.checkIfUserExists(existingUser))
    		throw new NotFoundException("User", "Email : " + email, null);
    		
    	return true;
    }
    
    @PutMapping("/updatePassword/{userId}/{password}")
    @ApiOperation(value = "Returns boolean (true/false) for user existence"
	, notes = "user id of user whose password is to be checked")
    public User updatePassword(@NonNull @PathVariable Long userId, @NonNull @PathVariable String password) {
    	User existingUser = userService.findUserByUserId(userId);
    	if (!userService.checkIfUserExists(existingUser))
    		throw new NotFoundException("User", "UserId : " + userId, null);
    	return userService.updatePassword(existingUser, password);
    }
    
    @PutMapping("/resetPassword/{email}")
    @ApiOperation(value = "Returns boolean (true/false) for user existence"
	, notes = "user id of user whose password is to be checked")
    public User upadePassword(@NonNull @PathVariable String email) {
    	User existingUser = userService.findUserByEmail(email);
    	if (!userService.checkIfUserExists(existingUser))
    		throw new NotFoundException("User", "Email : " + email, null);
    	return userService.updatePassword(existingUser, Utility.generateRandomPassword(8));
    }*/
}
