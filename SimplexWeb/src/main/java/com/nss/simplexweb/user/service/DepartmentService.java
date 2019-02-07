package com.nss.simplexweb.user.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nss.simplexweb.user.model.Department;
import com.nss.simplexweb.user.repository.DepartmentRepository;

@Service("departmentService")
public class DepartmentService {
	
	@Autowired
	private DepartmentRepository departmentRepository;
	
	@Autowired
	public DepartmentService(DepartmentRepository departmentRepository) {
		this.departmentRepository = departmentRepository;
	}
	
	public ArrayList<Department> getDepartmetList(){
		return departmentRepository.findAll();
	}
}
