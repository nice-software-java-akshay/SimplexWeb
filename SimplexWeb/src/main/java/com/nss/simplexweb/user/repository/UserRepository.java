package com.nss.simplexweb.user.repository;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nss.simplexweb.user.model.Role;
import com.nss.simplexweb.user.model.User;

@Repository("userRepository")
public interface UserRepository extends JpaRepository<User, Long> {
	
	//Read (User)
	User findByUserId(Long userId);
	
	User findByEmail(String email);
	
	User findByEmailAndIsActive(String email, int isActive);
	
	User findByEmailAndPasswordAndIsActive(String email, String password, int isActive);
	
	ArrayList<User> findByIsActive(int isActive);
	
	ArrayList<User> findByManagerIdAndIsActive(Long managerId, int isActive);
	
	ArrayList<User> findByRoleAndIsActive(Role role, int isActive);
	
	ArrayList<User> findByRoleInAndIsActive(ArrayList<Role> roles, int isActive);
	
	
	//Read (Employee)
	User findByEmpId(Long empId);
	
	ArrayList<User> findByIsActiveAndEmpIdIsNotNull(int isActive);
	
	ArrayList<User> findByManagerIdAndIsActiveAndEmpIdIsNotNull(Long managerId, int isActive);
	
	ArrayList<User> findByRoleAndIsActiveAndEmpIdIsNotNull(Role role, int isActive);
	
	ArrayList<User> findByRoleInAndIsActiveAndEmpIdIsNotNull(ArrayList<Role> roles, int isActive);
	
	
	//Read (Distributer	
	ArrayList<User> findByIsActiveAndEmpIdIsNull(int isActive);
	
	ArrayList<User> findByManagerIdAndIsActiveAndEmpIdIsNull(Long managerId, int isActive);
	
	ArrayList<User> findByRoleAndIsActiveAndEmpIdIsNull(Role role, int isActive);
	
	ArrayList<User> findByRoleInAndIsActiveAndEmpIdIsNull(ArrayList<Role> roles, int isActive);
	
}
