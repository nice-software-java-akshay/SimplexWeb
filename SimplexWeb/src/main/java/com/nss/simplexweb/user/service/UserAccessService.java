package com.nss.simplexweb.user.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nss.simplexweb.user.model.UserAccessRel;
import com.nss.simplexweb.user.repository.UserAccessRepository;

@Service("userAccessService")
public class UserAccessService {

	@Autowired
	private UserAccessRepository userAccessRepository;
	
	@Autowired
	public UserAccessService(UserAccessRepository userAccessRepository) {
		this.userAccessRepository = userAccessRepository;
	}
	
	public ArrayList<UserAccessRel> getAllAccessList() {
		System.out.println("UserAccessRepository > getAllAccessList : " + userAccessRepository.findAllByOrderByAccessAsc());
		return userAccessRepository.findAllByOrderByAccessAsc();
	}
}
