package com.nss.simplexweb.user.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nss.simplexweb.user.model.MainCompany;
import com.nss.simplexweb.user.repository.MainComapnyRepository;

@Service("mainCompanyService")
public class MainComapanyService {

	@Autowired
	private MainComapnyRepository mainComapnyRepository;
	
	public MainComapanyService(MainComapnyRepository mainComapnyRepository) {
		this.mainComapnyRepository = mainComapnyRepository;
	}
	
	
	public MainCompany getMainComapnyInfo() {
		return mainComapnyRepository.findByCompanyId(1);
	}
}
