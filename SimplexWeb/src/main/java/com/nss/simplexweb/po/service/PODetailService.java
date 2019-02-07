package com.nss.simplexweb.po.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nss.simplexweb.po.repository.PODetailRepository;

@Service("poDetailService")
public class PODetailService {

	@Autowired
	private PODetailRepository poDetailRepository;
	
	public PODetailService(PODetailRepository poDetailRepository) {
		this.poDetailRepository = poDetailRepository;
	}
}
