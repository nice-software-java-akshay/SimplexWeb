package com.nss.simplexweb.po.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nss.simplexweb.po.repository.POStatusRepository;

@Service("poStatusService")
public class POStatusService {

	@Autowired
	private POStatusRepository poStatusRepository; 
	
	public POStatusService(POStatusRepository poStatusRepository) {
		this.poStatusRepository = poStatusRepository;
	}
}
