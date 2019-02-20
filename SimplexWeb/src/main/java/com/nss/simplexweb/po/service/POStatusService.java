package com.nss.simplexweb.po.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nss.simplexweb.po.model.POStatus;
import com.nss.simplexweb.po.repository.POStatusRepository;

@Service("poStatusService")
public class POStatusService {

	@Autowired
	private POStatusRepository poStatusRepository; 
	
	public POStatusService(POStatusRepository poStatusRepository) {
		this.poStatusRepository = poStatusRepository;
	}
	
	public ArrayList<POStatus> getPOStatusTypeList(){
		return poStatusRepository.findAll();
	}
	
	public POStatus getPOStatusByPoStatusAbbr(String PO_STATUS_ABBR) {
		return poStatusRepository.findByPoStatusAbbr(PO_STATUS_ABBR);
	}

	public ArrayList<POStatus> getPONextApplicableStatusType(POStatus poStatus) {
		// TODO Auto-generated method stub
		int currentPoStatusOrder = poStatus.getPoStatusOrder();
		int nextPoStatusOrder = currentPoStatusOrder + 1;
		return poStatusRepository.findByPoStatusOrder(nextPoStatusOrder);
	}
	
	
}
