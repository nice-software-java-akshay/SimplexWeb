package com.nss.simplexweb.po.repository;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nss.simplexweb.po.model.PODetail;
import com.nss.simplexweb.po.model.POTrackingHistory;

@Repository("poTrackingHistoryRepository")
public interface POTrackingHistoryRepository extends JpaRepository<POTrackingHistory, Long> {

	ArrayList<POTrackingHistory> findAll();
	
	ArrayList<POTrackingHistory> findByPoIdOrderByPoTrackingStatusUpdateTimestampDesc(PODetail poId);
	
	POTrackingHistory findByPoTrackingHistoryId(Long poTrackingHistoryId);
}
