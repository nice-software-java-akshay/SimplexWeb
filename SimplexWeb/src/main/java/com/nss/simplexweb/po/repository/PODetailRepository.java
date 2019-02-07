package com.nss.simplexweb.po.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nss.simplexweb.po.model.PODetail;

@Repository("poDetailRepository")
public interface PODetailRepository extends JpaRepository<PODetail, Long> {

}
