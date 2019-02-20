package com.nss.simplexweb.po.repository;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nss.simplexweb.po.model.POStatus;

@Repository("poStatusRepository")
public interface POStatusRepository extends JpaRepository<POStatus, Long> {

	ArrayList<POStatus> findAll();
	
	POStatus findByPoStatusAbbr(String PO_STATUS_ABBR);
	
	ArrayList<POStatus> findByPoStatusOrder(int orderValue);
	
}
