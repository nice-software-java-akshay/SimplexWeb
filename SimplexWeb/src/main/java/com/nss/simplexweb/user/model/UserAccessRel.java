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
@Table(name="user_access_tbl")
public class UserAccessRel implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="role_access_id")
	private long roleAccessId;
	
	@ManyToOne
	@JoinColumn(name="role_id")
	private Role role;
	
	@ManyToOne
	@JoinColumn(name="access_id")
	private Access access;
	
	@Column(name = "is_active", nullable = false, columnDefinition = "int default 1")
	private int isActive;
}
