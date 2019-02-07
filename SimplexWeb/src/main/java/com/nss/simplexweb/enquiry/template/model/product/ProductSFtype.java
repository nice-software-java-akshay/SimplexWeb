package com.nss.simplexweb.enquiry.template.model.product;

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
@Table(name="product_sf_type_tbl")
public class ProductSFtype implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="sf_type_id")
	private Long sfTypeId;
	
	@Column(name="sf_type_name", unique=true)
	private String sfTypeName;
	
	@Column(name="sf_type_value", unique=true)
	private int sfTypeValue;
	
	@Column(name="sf_type_abbr", unique=true)
	private String sfTypeAbbr;
}
