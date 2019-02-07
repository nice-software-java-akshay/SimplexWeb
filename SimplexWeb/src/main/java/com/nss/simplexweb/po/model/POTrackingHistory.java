package com.nss.simplexweb.po.model;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
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
@Table(name="po_tracking_history_tbl")
public class POTrackingHistory implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="po_tracking_history_id")
	private long poTrackingHistoryId;
	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "po_id")
	private PODetail poId;
	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "po_status_id")
	private POStatus poStatus;
	
	@Column(name = "po_tracking_status_update_timestamp", nullable = false, updatable = false, insertable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
	private Timestamp poTrackingStatusUpdateTimestamp;
	
	@Column(name="po_tracking_comment")
	private String poTrackingComment;
	
	@ManyToOne
	@JoinColumn(name="user_id")
	private User updatedBy;
}
