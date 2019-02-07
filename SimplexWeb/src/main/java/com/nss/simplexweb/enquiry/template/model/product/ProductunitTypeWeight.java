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
@Table(name="product_unit_type_weight_tbl")
public class ProductunitTypeWeight implements Serializable {/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="unit_type_id")
	private Long unitTypeId;
	
	@Column(name="unit_type_name", unique=true)
	private String unitTypeName;
	
	@Column(name="unit_type_abbr", unique=true)
	private String unitTypeAbbr;
}
