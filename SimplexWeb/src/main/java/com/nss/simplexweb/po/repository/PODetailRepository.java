package com.nss.simplexweb.po.repository;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nss.simplexweb.po.model.PODetail;
import com.nss.simplexweb.po.model.POStatus;
import com.nss.simplexweb.user.model.User;

@Repository("poDetailRepository")
public interface PODetailRepository extends JpaRepository<PODetail, Long> {

	//Read
	PODetail findByPoIdAndPoNumber(Long poId, String poNumber);
	
	ArrayList<PODetail> findByRequester(User user);
	
	ArrayList<PODetail> findByPoStatus(POStatus poStatus);
	
	ArrayList<PODetail> findByProcessorAndIsClosed(User processor, int isClosed);

	PODetail findByPoId(Long poId);
}
