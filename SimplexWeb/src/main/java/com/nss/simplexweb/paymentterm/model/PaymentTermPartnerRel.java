package com.nss.simplexweb.paymentterm.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.nss.simplexweb.po.model.PaymentTerms;
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
@Table(name="payment_term_partner_rel_tbl")
public class PaymentTermPartnerRel implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="payment_term_partner_rel_id")
	private Long paymentTermPartnerRelId;
	
	@ManyToOne
	@JoinColumn(name="partner_id")
	private User partner;
	
	@ManyToOne
	@JoinColumn(name="payment_term_id")
	private PaymentTerms paymentTerms;
}
