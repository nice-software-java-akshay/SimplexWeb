package com.nss.simplexweb.po.model;

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
@Table(name="delivery_method_tbl")
public class DeliveryMethod implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="delivery_method_id")
	private Long deliveryMethodId;
	
	@Column(name="delivery_method_desc")
	private String deliveryMethodDesc;
	
}
