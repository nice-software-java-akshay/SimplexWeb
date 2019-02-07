package com.nss.simplexweb.enquiry.template.model.bottomdischarge;

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
@Table(name="bottom_discharge_type_tbl")
public class BottomDischargeType implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="bottom_discharge_type_id")
	private Long bottomDischargeTypeId;
	
	@Column(name="bottom_discharge_type_name", unique=true)
	private String bottomDischargeTypeName;
	
	@Column(name="bottom_discharge_type_abbr", unique=true)
	private String bottomDischargeTypeAbbr;
}
