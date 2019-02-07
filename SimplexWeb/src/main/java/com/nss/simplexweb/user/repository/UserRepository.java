package com.nss.simplexweb.user.repository;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nss.simplexweb.user.model.Role;
import com.nss.simplexweb.user.model.User;

@Repository("userRepository")
public interface UserRepository extends JpaRepository<User, Long> {
	User findByEmail(String email);
	
	User findByUserId(long userId);
	
	ArrayList<User> findByRoleAndEmpIdIsNotNull(Role role);
	
	ArrayList<User> findByRoleAndEmpIdIsNull(Role role);
}
