package com.nss.simplexweb.user.service.intf;

import java.util.ArrayList;

import com.nss.simplexweb.user.model.User;

public interface DistributerServiceInterface {

	//Create
	User saveNewDistributerAutoGeneratePassword(User user);
	
	User saveNewDistributerWithoutAutoGeneratePassword(User user);
	
	
	//Read
	User findDistributerByUserId(Long userId);
	
	User findDistributerByEmailIdAndNonEncrPassword(String emailId, String password);
	
	User findDistributerByEmailIdAndEncrPassword(String emailId, String password);
	
	ArrayList<User> findAllActiveDistributersList();
	
	ArrayList<User> findAllInActiveDistributersList();
	
	//Update
	User updateDistributerWithPassword(User user);
	
	User updateDistributerWithoutPassword(User user);
	
	User updateMyProfilePictureDistributer(User user);

	//Delete
	User deleteDistributerById(Long userId);

	User inactivateDistributerById(Long userId);
}
