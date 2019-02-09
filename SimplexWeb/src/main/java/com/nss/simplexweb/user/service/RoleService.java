package com.nss.simplexweb.user.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nss.simplexweb.enums.ROLE;
import com.nss.simplexweb.user.model.Role;
import com.nss.simplexweb.user.repository.RoleRepository;

@Service("roleService")
public class RoleService {
	
	@Autowired
	private RoleRepository roleRepository;
	
	@Autowired
	public RoleService(RoleRepository roleRepository) {
		this.roleRepository = roleRepository;
	}
	
	public Role getRoleByRoleAbbr(String roleAbbr) {
		return roleRepository.findByRoleAbbr(roleAbbr);
	}
	
	//Parse All Level Hierarchy
	public ArrayList<Role> getAllLevelChildRolesById(long roleId) {
		ArrayList<Role> allLevelChildRolesList = null;
		allLevelChildRolesList = roleLevelHierarchyParser(null, allLevelChildRolesList, roleId);
		return allLevelChildRolesList;
	}
	
	private ArrayList<Role> roleLevelHierarchyParser(ArrayList<Role> immediateLevelChildRolesList, 
										ArrayList<Role> allLevelChildRolesList,
										long roleId) {
		immediateLevelChildRolesList = getImmediateLevelChildRolesById(roleId);
		if(immediateLevelChildRolesList != null) {
			int listSize = immediateLevelChildRolesList.size();
			for(int i=0; i<listSize; i++) {
				if(allLevelChildRolesList == null) {
					allLevelChildRolesList = new ArrayList<>();
				}
				allLevelChildRolesList.add(immediateLevelChildRolesList.get(i));
				if(i==listSize) {
					roleLevelHierarchyParser(null, allLevelChildRolesList, roleId);
				}
			}
		}
		return allLevelChildRolesList;
	}
	
	public ArrayList<Role> getImmediateLevelChildRolesById(long roleId) {
		return roleRepository.findByManagerRoleId(roleId);
	}

	public ArrayList<Role> getAllRoleList() {
		return (ArrayList<Role>) roleRepository.findAll();
	}
	
	public ArrayList<Role> getRoleListExcludingAdminRole() {
		return roleRepository.findByRoleAbbrNotIn(ROLE.DIR.name());
	}
	
	public ArrayList<Role> getAllEmployeeRoleList() {
		return (ArrayList<Role>) roleRepository.findByRoleAbbrNotIn(ROLE.DIST.name());
	}
	
	public ArrayList<Role> getAllRoleListExcludingAdminRole() {
		return roleRepository.findByRoleAbbrNotIn(ROLE.DIR.name());
	}
	
	public ArrayList<Role> getAllEmployeeRoleListExcludingAdminRole() {
		ArrayList<String> roleExclude = new ArrayList<>();
		roleExclude.add(ROLE.DIR.name());
		roleExclude.add(ROLE.DIST.name());
		return roleRepository.findByRoleAbbrNotIn(roleExclude);
	}
}
