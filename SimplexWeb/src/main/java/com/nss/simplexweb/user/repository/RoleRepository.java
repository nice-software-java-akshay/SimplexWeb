package com.nss.simplexweb.user.repository;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nss.simplexweb.user.model.Department;
import com.nss.simplexweb.user.model.Role;

@Repository("roleRepository")
public interface RoleRepository extends JpaRepository<Role, Long> {
	Role findByRoleAbbr(String roleAbbr);
	
	Role findByRoleId(long roleId);
	
	ArrayList<Role> findByManagerRoleId(long managerRoleId);
	
	ArrayList<Role> findByDept(Department dept);
	
	ArrayList<Role> findByRoleAbbrNotIn(String roleAbbr);
	
	ArrayList<Role> findByRoleAbbrNotIn(ArrayList<String> roleAbbrList);
	//AndRoleAbbrNotIn(String roleAbbr);
}
