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
@Table(name="loop_material_tbl")
public class LoopMaterial implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="loop_material_id")
	private Long loopMaterialId;
	
	@Column(name="loop_material_name")
	private String loopMaterialName;
	
	@Column(name="loop_material_abbr")
	private String loopMaterialAbbr;
}
