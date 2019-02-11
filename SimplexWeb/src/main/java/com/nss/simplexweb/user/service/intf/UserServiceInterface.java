package com.nss.simplexweb.user.service.intf;

import java.util.ArrayList;

import com.nss.simplexweb.user.model.User;

public interface UserServiceInterface {

	//Read
	User findUserByUserId(Long userId);
	
	User findUserByEmailIdAndIsActive(String emailId);
	
	User findUserByEmailId(String emailId);
	
	boolean checkIfUserExistsByUserId(Long userId);
	
	boolean checkIfUserExistsByEmailId(String emailId);
	
	User findUserByEmailIdAndNonEncrPassword(String emailId, String password);
	
	User findUserByEmailIdAndEncrPassword(String emailId, String password);
	
	ArrayList<User> findAllActiveInactiveUsersList();
	
	ArrayList<User> findAllActiveUsersList();
	
	ArrayList<User> findAllInActiveUsersList();
	
	
	//Update
	User resetUserPassword(User user);
	
	User changeUserPassword(User user, String newPassword);
	
	
	//Delete
	User deleteUserById(Long userId);
	
	User inactivateUserById(Long userId);
	
	
	//Utility
	User processUseNameBeforeSaving(User user);
	boolean checkIfPasswordExists(User user, String password);
}
