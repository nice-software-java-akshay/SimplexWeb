package com.nss.simplexweb.po.model;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.nss.simplexweb.paymentterm.model.PaymentTerms;
import com.nss.simplexweb.shipmenterm.model.ShipmentTerms;
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
@Table(name="po_detail_tbl")
public class PODetail implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="po_id")
	private Long poId;
	
	@Column(name="po_number")
	private String poNumber;
	
	
	/* --Ship To Params-- */
		@Column(name="shipto_company")
		private String shiptoCompany;
		
		@Column(name="shipto_contact_person")
		private String shiptoContactPerson;
		
		@Column(name="shipto_email")
		private String shiptoEmail;
		
		@Column(name="shipto_contact_number")
		private String shiptoContactNumber;
		
		
	 /* --PO Info-- */
		@Column(name="vendor_number")
		private String vendorNumber;
		
		@Column(name="vendor_account_number")
		private String vendorAccountNumber;
		
		@ManyToOne(fetch = FetchType.EAGER)
	    @JoinColumn(name = "payment_term_id")
		private PaymentTerms paymentTerms;
		
		@Column(name="etd")
		private String etd;
		
		/*@ManyToOne(fetch = FetchType.EAGER)
	    @JoinColumn(name = "delivery_method_id")
		private DeliveryMethod deliveryMethod;*/
		private String deliveryMethod;
		
		@ManyToOne(fetch = FetchType.EAGER)
	    @JoinColumn(name = "shipping_term_id")
		private ShipmentTerms shippingTerms;
		
		@Column(name="so_number")
		private String soNumber;
		
		@Column(name="purchasing_contact")
		private String purchasingContact;
		
		@Column(name="warehouse")
		private String warehouse;
	
		
	/* --PO Items Details-- */
		@OneToMany(mappedBy="poDetail", fetch = FetchType.EAGER)
		@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
		private List<POItems> poItmesList;
		
		/*@OneToMany(cascade = CascadeType.ALL)
		private List<POItems> poItmesList;*/
		
	@Column(name="po_remark")
	private String poRemark;
	
	@Column(name="po_total_amount")
	private Double poTotalAmount;
		
	@ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "po_status_id")
	private POStatus poStatus;
	
	@ManyToOne
	@JoinColumn(name="requester_id")
	private User requester;
	
	@ManyToOne
	@JoinColumn(name="processor_id")
	private User processor;
	
	@ManyToOne
	@JoinColumn(name="status_updated_by")
	private User statusUpdatedBy;
	
	@Column(name="is_closed", nullable = false, columnDefinition = "int default 0")
	private int isClosed;
	
	@Column(name = "po_create_timestamp", nullable = false, updatable = false, insertable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
	private Timestamp poCreateTimestamp;
}
