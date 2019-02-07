package com.nss.simplexweb.user.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@Entity
@AllArgsConstructor 
@NoArgsConstructor
@Table(name="user_role_tbl")
public class Role implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="role_id")
	private long roleId;
	
	@Column(name="role_abbr", unique=true)
	private String roleAbbr;
	
	@Column(name="role_name", unique=true)
	private String roleName;
	
	@Column(name="manager_role_id")
	private long managerRoleId;
	
	@ManyToOne
	@JoinColumn(name="dept_id")
	private Department dept;
	
	
	@Column(name = "is_active", nullable = false, columnDefinition = "int default 1")
	private int isActive;
}
