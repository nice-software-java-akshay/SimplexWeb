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
@Table(name="main_company_tbl")
public class MainCompany implements Serializable {/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="company_id")
	private long companyId;
	
	@Column(name="company_name", unique=true)
	private String companyName;
	
	@Column(name="company_full_name", unique=true)
	private String companyFullName;
	
	@Column(name="company_address_line_one", unique=true)
	private String companyAddressLineOne;
	
	@Column(name="company_address_line_two", unique=true)
	private String companyAddressLineTwo;
	
	@Column(name="company_address_pin", unique=true)
	private String companyAddressPIN;
	
	@Column(name="company_phone_primary", unique=true)
	private String companyPhonePrimary;
	
	@Column(name="company_phone_secondary", unique=true)
	private String companyPhoneSecondary;
	
	@Column(name="company_logo_path", unique=true)
	private String companyLogoPath;
}
