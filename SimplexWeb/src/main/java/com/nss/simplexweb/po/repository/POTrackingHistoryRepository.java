package com.nss.simplexweb.po.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nss.simplexweb.po.model.POTrackingHistory;

@Repository("poTrackingHistoryRepository")
public interface POTrackingHistoryRepository extends JpaRepository<POTrackingHistory, Long> {

}