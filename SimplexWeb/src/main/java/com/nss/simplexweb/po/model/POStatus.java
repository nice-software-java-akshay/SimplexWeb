package com.nss.simplexweb.po.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
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
@Table(name="po_tracking_status_master_tbl")
public class POStatus implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="po_status_id")
	private Long poStatusId;
	
	@Column(name="po_status_name")
	private String poStatusName;
	
	@Column(name="po_status_abbr")
	private String poStatusAbbr;
	
	@Column(name="po_status_is_main")
	private Long poStatusIsMain;
}
