package com.nss.simplexweb.user.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
@Table(name="user_dept_tbl")
public class Department implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="dept_id")
	private long deptId;
	
	@Column(name="dept_abbr", unique=true)
	private String deptAbbr;
	
	@Column(name="dept_name", unique=true)
	private String deptName;
	
	@Column(name = "is_active", nullable = false, columnDefinition = "int default 1")
	private int isActive;
}
