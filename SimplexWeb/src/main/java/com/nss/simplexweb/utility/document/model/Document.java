package com.nss.simplexweb.utility.document.model;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.nss.simplexweb.user.model.User;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@Entity
@AllArgsConstructor 
@NoArgsConstructor
@Table(name="document_detail_tbl")
public class Document implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="document_detail_id")
	private Long documentDetailId;
	
	private Long documentParentId;
	
	private String documentParentEntityType;
	
	private String documentOriginalNameWithExtension;
	
	private String documentOriginalNameWithoutExtension;
	
	private String documentStorePath;
	
	private String documentStoreByName;
	
	private String documentDownloadByName;
	
	private Long documentSizeInBytes;
	
	private String documentSizeSmart;
	
	private String documentExtension;
	
	private String documentMimeType;
	
	@Column(name = "document_upload_timestamp", nullable = false, updatable = false, insertable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
	private Timestamp documentUploadTimestamp;
	
	@ManyToOne
	private User documentUploader;
	
	@ManyToOne
	private User documentOwnerPartner;
	
	@Column(name = "is_deleted", nullable = false, columnDefinition = "int default 0")
	private int isDeleted;
	
	@ManyToOne
	private User documentDeletedBy;
	
	@Column(name = "document_delete_timestamp")
	private Timestamp documentDeleteTimestamp;
}
