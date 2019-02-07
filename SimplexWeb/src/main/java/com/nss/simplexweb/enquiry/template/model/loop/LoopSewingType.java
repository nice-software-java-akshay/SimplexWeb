package com.nss.simplexweb.enquiry.template.model.loop;

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
@Table(name="loop_sewing_type_tbl")
public class LoopSewingType implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="loop_sewing_type_id")
	private Long loopSewingTypeId;
	
	@Column(name="loop_sewing_type_name")
	private String loopSewingTypeName;
	
	@Column(name="loop_sewing_type_abbr")
	private String loopSewingTypeAbbr;
}
