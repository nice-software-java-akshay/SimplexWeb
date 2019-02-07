package com.nss.simplexweb.user.repository;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nss.simplexweb.user.model.Department;

@Repository("departmentRepository")
public interface DepartmentRepository extends JpaRepository<Department, Long>{
	
	ArrayList<Department> findAll();
	
	Department findByDeptId(long deptId);
	
	Department findByDeptAbbr(String septAbbr);
	
	Department findByDeptName(String deptName);
	
}
