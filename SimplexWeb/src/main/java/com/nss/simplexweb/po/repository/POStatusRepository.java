package com.nss.simplexweb.po.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nss.simplexweb.po.model.POStatus;

@Repository("poStatusRepository")
public interface POStatusRepository extends JpaRepository<POStatus, Long> {

}
