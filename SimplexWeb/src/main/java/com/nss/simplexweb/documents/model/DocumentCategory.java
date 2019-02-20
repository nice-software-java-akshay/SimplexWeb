package com.nss.simplexweb.documents.model;

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
@Table(name="document_category_tbl")
public class DocumentCategory implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="document_category_id")
	private Long documentCategoryId;
	
	@Column(name="document_category_name")
	private String documentCategoryName;
	
	@Column(name="document_category_abbr")
	private String documentCategoryAbbr;
	
	@Column(name="is_active", nullable = false, columnDefinition = "int default 1")
	private int isActive;
}
