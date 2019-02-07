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
@Table(name="other_doc_pouch_type_tbl")
public class DocPouchType implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="doc_pouch_type_id")
	private Long docPouchTypeId;
	
	@Column(name="doc_pouch_type_name")
	private String docPouchTypeName;
	
	@Column(name="doc_pouch_type_abbr")
	private String docPouchTypeAbbr;
}
