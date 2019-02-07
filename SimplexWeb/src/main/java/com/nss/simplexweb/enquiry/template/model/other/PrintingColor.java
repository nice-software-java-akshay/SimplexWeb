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
@Table(name="other_printing_color_tbl")
public class PrintingColor implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="printing_color_id")
	private Long printingColorId;
	
	@Column(name="printing_color_name", unique=true)
	private String printingColorName;
	
	@Column(name="printing_color_abbr", unique=true)
	private String printingColorAbbr;
}
