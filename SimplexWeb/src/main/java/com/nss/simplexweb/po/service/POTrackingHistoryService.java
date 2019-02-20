package com.nss.simplexweb.po.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nss.simplexweb.po.model.PODetail;
import com.nss.simplexweb.po.model.POTrackingHistory;
import com.nss.simplexweb.po.repository.POTrackingHistoryRepository;

@Service("poTrackingHistoryService")
public class POTrackingHistoryService {

	@Autowired
	private POTrackingHistoryRepository poTrackingHistoryRepository;
	
	public POTrackingHistoryService(
			POTrackingHistoryRepository poTrackingHistoryRepository) {
		this.poTrackingHistoryRepository = poTrackingHistoryRepository;
	}
	
	public POTrackingHistory addPOTrackingEntry(POTrackingHistory poTrackingHistory) {
		return poTrackingHistoryRepository.save(poTrackingHistory);
	}
	
	public ArrayList<POTrackingHistory> getPOTrackingStatusList(){
		return poTrackingHistoryRepository.findAll();
	}

	public ArrayList<POTrackingHistory> getPOTrackingHistoryListForPODesc(PODetail poDetail) {
		// TODO Auto-generated method stub
		return poTrackingHistoryRepository.findByPoIdOrderByPoTrackingStatusUpdateTimestampDesc(poDetail);
	}

}
