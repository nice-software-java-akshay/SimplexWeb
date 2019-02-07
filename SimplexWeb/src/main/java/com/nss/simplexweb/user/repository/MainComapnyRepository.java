package com.nss.simplexweb.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nss.simplexweb.user.model.MainCompany;

@Repository("mainComapnyRepository")
public interface MainComapnyRepository extends JpaRepository<MainCompany, Long> {

	MainCompany findByCompanyId(long comapnyId);
}
