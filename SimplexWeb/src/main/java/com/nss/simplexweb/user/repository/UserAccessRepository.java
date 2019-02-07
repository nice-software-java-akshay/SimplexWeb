package com.nss.simplexweb.user.repository;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nss.simplexweb.user.model.UserAccessRel;


@Repository
public interface UserAccessRepository extends JpaRepository<UserAccessRel, Long> {
	ArrayList<UserAccessRel> findAllByOrderByAccessAsc();
}
