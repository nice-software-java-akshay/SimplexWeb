package com.nss.simplexweb.enquiry.template.model.other;

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
@Table(name="other_liner_type_tbl")
public class LinerType implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="liner_type_id")
	private Long linerTypeId;
	
	@Column(name="liner_type_name", unique=true)
	private String linerTypeName;
	
	@Column(name="liner_type_abbr", unique=true)
	private String linerTypeAbbr;
}