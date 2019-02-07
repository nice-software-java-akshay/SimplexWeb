package com.nss.simplexweb.po.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nss.simplexweb.po.repository.POTrackingHistoryRepository;

@Service("poTrackingHistoryService")
public class POTrackingHistoryService {

	@Autowired
	private POTrackingHistoryRepository poTrackingHistoryRepository;
	
	public POTrackingHistoryService(POTrackingHistoryRepository poTrackingHistoryRepository) {
		this.poTrackingHistoryRepository = poTrackingHistoryRepository;
	}
}
