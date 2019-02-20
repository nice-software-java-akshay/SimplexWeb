package com.nss.simplexweb.shipmenterm.model;

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
@Table(name="shipment_terms_tbl")
public class ShipmentTerms implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="shipment_term_id")
	private Long shipmentTermId;
	
	@Column(name="shipment_term_name")
	private String shipmentTermName;
	
	@Column(name="shipment_term_desc")
	private String shipmentTermDesc;
	
	@ManyToOne
	@JoinColumn(name="created_by")
	private User createdBy;
	
	/*@OneToMany(mappedBy = "shipmentTerms")
    Set<PaymentTermPartnerRel> shipmentTermPartnerRel;*/
	
	@Column(name="is_active", nullable = false, columnDefinition = "int default 1")
	private int isActive;
}
