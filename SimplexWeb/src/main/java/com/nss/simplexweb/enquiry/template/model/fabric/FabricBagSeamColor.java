package com.nss.simplexweb.enquiry.template.model.fabric;

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
@Table(name="fabric_bag_seam_color_tbl")
public class FabricBagSeamColor implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="bag_seam_color_id")
	private Long bagSeamColorId;
	
	@Column(name="bag_seam_color_name", unique=true)
	private String bagSeamColorName;
	
	@Column(name="bag_seam_color_hash", unique=true)
	private String colorHash;
}
