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
@Table(name="payment_terms_tbl")
public class PaymentTerms implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="payment_term_id")
	private Long paymentTermId;
	
	@Column(name="payment_term_code")
	private String paymentTermCode;
	
	@Column(name="payment_term_days")
	private Long paymentTermDays;
	
	@Column(name="payment_term_desc")
	private String paymentTermDesc;
	
	@ManyToOne
	@JoinColumn(name="created_by")
	private User createdBy;
	
	/*@OneToMany(mappedBy = "paymentTerms")
    Set<PaymentTermPartnerRel> paymentTermPartnerRel;*/
	
	@Column(name="is_active", nullable = false, columnDefinition = "int default 1")
	private int isActive;
}
