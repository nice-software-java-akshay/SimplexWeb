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
@Table(name="access_tbl")
public class Access implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="access_id")
	private long accessId;
	
	@Column(name="menu_abbr")
	private String menuAbbr;
	
	@Column(name="menu_title")
	private String menuTitle;
	
	@Column(name="is_main_menu")
	private boolean isMainMenu;
	
	@Column(name="is_submenu")
	private boolean isSubmenu;
	
	@Column(name="is_small_menu")
	private boolean isSmallMenu;
	
	@Column(name="parent_access_id")
	private int parentAccessId;
	
	@Column(name="url")
	private String url;
	
	@Column(name = "is_active", nullable = false, columnDefinition = "int default 1")
	private int isActive;

}
