package com.nss.simplexweb.enquiry.template.model;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.nss.simplexweb.enquiry.template.model.bottomdischarge.BottomDischargeType;
import com.nss.simplexweb.enquiry.template.model.bottomdischarge.BottomType;
import com.nss.simplexweb.enquiry.template.model.fabric.FabricBagSeamColor;
import com.nss.simplexweb.enquiry.template.model.fabric.FabricBagSeamType;
import com.nss.simplexweb.enquiry.template.model.fabric.FabricColor;
import com.nss.simplexweb.enquiry.template.model.fabric.FabricGSMType;
import com.nss.simplexweb.enquiry.template.model.fabric.FabricType;
import com.nss.simplexweb.enquiry.template.model.loop.LoopColor;
import com.nss.simplexweb.enquiry.template.model.loop.LoopMaterial;
import com.nss.simplexweb.enquiry.template.model.loop.LoopSewingType;
import com.nss.simplexweb.enquiry.template.model.loop.LoopType;
import com.nss.simplexweb.enquiry.template.model.other.DocPouchDetailType;
import com.nss.simplexweb.enquiry.template.model.other.DocPouchType;
import com.nss.simplexweb.enquiry.template.model.other.LinerType;
import com.nss.simplexweb.enquiry.template.model.other.PackagingType;
import com.nss.simplexweb.enquiry.template.model.other.PrintingColor;
import com.nss.simplexweb.enquiry.template.model.other.Sides;
import com.nss.simplexweb.enquiry.template.model.product.ProductModelType;
import com.nss.simplexweb.enquiry.template.model.product.ProductSFtype;
import com.nss.simplexweb.enquiry.template.model.product.ProductSurfaceType;
import com.nss.simplexweb.enquiry.template.model.product.ProductType;
import com.nss.simplexweb.enquiry.template.model.product.ProductUnitTypeLength;
import com.nss.simplexweb.enquiry.template.model.product.ProductunitTypeWeight;
import com.nss.simplexweb.enquiry.template.model.shipment.ShipmentType;
import com.nss.simplexweb.enquiry.template.model.topfilling.TopFillingType;
import com.nss.simplexweb.enquiry.template.model.topfilling.TopType;
import com.nss.simplexweb.po.model.POStatus;
import com.nss.simplexweb.user.model.User;
import com.nss.simplexweb.utility.Utility;
import com.nss.simplexweb.utility.mail.MailBean;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@Entity
@AllArgsConstructor 
@NoArgsConstructor
@Table(name="enquiry_tbl")
public class EnquiryTemplateBean implements Serializable {

	


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="equiry_id")
	private Long enquiryId;
	
	@Builder.Default
	@Column(name="equiry_number")
	private String enquiryNumber = Utility.generateRandomEnquiryNumber(5);
	
	/* 
	 * 
	 * --------------------------------------------------------------- Product Start
	 * 
	 */
		@ManyToOne
		@JoinColumn(name = "product_type_id")
		private ProductType productType;
		
		@ManyToOne
		@JoinColumn(name = "model_type_id")
		private ProductModelType productModelType;
		
		@ManyToOne
		@JoinColumn(name = "product_unit_type_length")
		private ProductUnitTypeLength productUnitTypeLength;
		
		@ManyToOne
		@JoinColumn(name = "product_unit_type_weight")
		private ProductunitTypeWeight productUnitTypeWeight;
		
		@Column(name="baffle")
		private boolean baffle;
		
			@Column(name="baffle_value")
			private Double baffleValue;
			
		@ManyToOne
		@JoinColumn(name = "surface_type_id")
		private ProductSurfaceType productSurfaceType;
			
			@Column(name="surface_length")
			private Double surfaceLength;
			
			@Column(name="surface_width")
			private Double surfaceWidth;
			
			@Column(name="surface_height")
			private Double surfaceHeight;
			
		@Column(name="swl")
		private Double swl;
		
		@Column(name="un")
		private boolean un;
		
		@Column(name="uv")
		private boolean uv;
		
		@ManyToOne
		@JoinColumn(name = "sf_type_id")
		private ProductSFtype productSFtype;
		
		@Column(name="product_remarks")
		private String productRemarks;
	
	/* 
	 * 
	 * --------------------------------------------------------------- Product End
	 * 
	 */
	
	/* 
	 * 
	 * --------------------------------------------------------------- Fabric Start
	 * 
	 */
		@ManyToOne
		@JoinColumn(name = "fabric_type_id")
		private FabricType fabricType;
		
		@ManyToOne
		@JoinColumn(name = "fabric_color_id")
		private FabricColor fabricColor;
		
		@ManyToOne
		@JoinColumn(name = "fabric_gsm_type_id")
		private FabricGSMType fabricGSMType;
		
		@Column(name="fabric_gsm_value")
		private Double fabricGSMValue;
		
		@Column(name="reinforcement")
		private boolean reinforcement;
		
		@ManyToOne
		@JoinColumn(name = "bag_seam_type_id")
		private FabricBagSeamType fabricBagSeamType;
		
		@ManyToOne
		@JoinColumn(name = "bag_seam_color_id")
		private FabricBagSeamColor fabricBagSeamColor;
	
	/* 
	 * 
	 * --------------------------------------------------------------- Fabric End
	 * 
	 */
		
	/* 
	 * 
	 * --------------------------------------------------------------- Top Filling Start
	 * 
	 */
		@Column(name="top_filling")
		private boolean topFilling;
		
		@ManyToOne
		@JoinColumn(name = "top_filling_type_id")
		private TopFillingType topFillingType;
		
		@ManyToOne
		@JoinColumn(name = "top_type_id")
		private TopType topType;
		
		@Column(name = "top_skirt_length")
		private Double topSkirtLength;
		
		@Column(name = "top_skirt_gsm")
		private Double topSkirtGSM;
		
		@Column(name = "top_spout_diameter")
		private Double topSpoutDiameter;
		
		@Column(name = "top_spout_length")
		private Double topSpoutLength;
		
		@Column(name = "top_spout_gsm")
		private Double topSpoutGSM;
		
		@Column(name = "top_flap_gsm")
		private Double topFlapGSM;
		
		/*Standard Tie*/
			@Column(name = "top_standard_tie")
			private boolean topStandardTie;
			
			@Column(name = "top_standard_tie_number")
			private Double topStandardTieNumber;
				
		/*Rope Tie*/
			@Column(name = "top_rope_tie")
			private boolean topRopeTie;
		
			@Column(name = "top_rope_tie_number")
			private Double topRopeTieNumber;
		
		
		/*Velcro Tie*/
			@Column(name = "top_velcro_tie")
			private boolean topVelcroTie;
		
			@Column(name = "top_velcro_tie_number")
			private Double topVelcroTieNumber;
		
		/*B-Lock Tie*/
			@Column(name = "top_block")
			private boolean topBlock;
			
			@Column(name = "top_block_number")
			private Double topBlockNumber;
	/* 
	 * 
	 * --------------------------------------------------------------- Top Filling End
	 * 
	 */
			
	/* 
	 * 
	 * --------------------------------------------------------------- Bottom Discharge Start
	 * 
	 */
		@Column(name = "bottom_discharge")
		private boolean bottomDischarge;
		
		@ManyToOne
		@JoinColumn(name = "bottom_discharge_type_id")
		private BottomDischargeType bottomDischargeType;
		
		@ManyToOne
		@JoinColumn(name = "bottom_type_id")
		private BottomType bottomType;
		
		@Column(name = "bottom_spout_diameter")
		private Double bottomSpoutDiameter;
		
		@Column(name = "bottom_spout_length")
		private Double bottomSpoutLength;
		
		@Column(name = "bottom_spout_gsm")
		private Double bottomSpoutGSM;
		
		/*Standard Tie*/
			@Column(name = "bottom_standard_tie")
			private boolean bottomStandardTie;
			
			@Column(name = "bottom_standard_tie_number")
			private Double bottomStandardTieNumber;
			
		/*Rope Tie*/
			@Column(name = "bottom_rope_tie")
			private boolean bottomRopeTie;
			
			@Column(name = "bottom_rope_tie_number")
			private Double bottomRopeTieNumber;
			
		/*Velcro Tie*/
			@Column(name = "bottom_velcro_tie")
			private boolean bottomVelcroTie;
			
			@Column(name = "bottom_velcro_tie_number")
			private Double bottomVelcroTieNumber;
			
		/*B-Lock Tie*/
			@Column(name = "bottom_block")
			private boolean bottomBlock;
			
			@Column(name = "bottom_block_number")
			private Double bottomBlockNumber;
	
	/* 
	 * 
	 * --------------------------------------------------------------- Bottom Discharge End
	 * 
	 */
			
	/* 
	 * 
	 * --------------------------------------------------------------- Loop Start
	 * 
	 */
		@ManyToOne
		@JoinColumn(name = "loop_type_id")
		private LoopType loopType;
		
		@Column(name = "loop_number")
		private Double loopNumber;
		
		@ManyToOne
		@JoinColumn(name = "loop_material_id")
		private LoopMaterial loopMaterial;
		
		@Column(name = "loop_height")
		private Double loopHeight;
		
		@ManyToOne
		@JoinColumn(name = "loop_color_id")
		private LoopColor loopColor;
		
		@Column(name = "loop_protector")
		private boolean loopProtector;
		
		@Column(name = "loop_protector_value")
		private Double loopProtectorValue;
		
		@ManyToOne
		@JoinColumn(name = "loop_sewing_type_id")
		private LoopSewingType loopSewingType;
	/* 
	 * 
	 * --------------------------------------------------------------- Loop End
	 * 
	 */
		
	/* 
	 * 
	 * --------------------------------------------------------------- Other Start
	 * 
	 */
		//Other A
			@Column(name = "perimeter_band")
			private boolean perimeterBand;
			
			@Column(name = "belly_band")
			private boolean bellyBand;
			
			@Column(name = "steevdoor_strap")
			private boolean steevdoorStrap;
			
			@Column(name = "doc_pouch")
			private boolean docPouch;
			
			@ManyToOne
			@JoinColumn(name = "doc_pouch_type_id")
			private DocPouchType docPouchType;
			
			@ManyToOne
			@JoinColumn(name = "doc_pouch_detail_type_id")
			private DocPouchDetailType docPoucDetailType;
			
			@Column(name = "doc_pouch_value")
			private Double docPouchValue;
			
			@Column(name = "other_labels")
			private boolean otherLabels;
			
			@Column(name = "other_labels_value")
			private String otherLabelsValue;
		
		//Other B
			@Column(name = "printing")
			private boolean printing;
			
			@ManyToOne
			@JoinColumn(name = "sides_id")
			private Sides sides;
			
			@ManyToOne
			@JoinColumn(name = "printing_color_id")
			private PrintingColor printingColor;
			
			@Column(name = "extra_cleaning")
			private boolean extraCleaning;
			
			@Column(name = "metal_detector")
			private boolean metalDetector;
			
			@ManyToOne
			@JoinColumn(name = "packaging_type_id")
			private PackagingType packagingType;
			
			@Column(name = "liner")
			private boolean liner;
			
				@ManyToOne
				@JoinColumn(name = "liner_type_id")
				private LinerType linerType;
				
				@Column(name = "line_tube_value")
				private Double linerTubeValue;
				
				@Column(name = "line_height_value")
				private Double linerHeightValue;
				
				@Column(name = "liner_micron_value")
				private Double linerMicronValue;
				
				@Column(name = "liner_tabbing")
				private boolean linerTabbing;
				
					@Column(name = "liner_tabbing_number")
					private boolean linerTabbingNumber;
					
				@Column(name = "liner_glued")
				private boolean linerGlued;
			
			
	/* 
	 * 
	 * --------------------------------------------------------------- Other End
	 * 
	 */
			
	/* 
	 * 
	 * --------------------------------------------------------------- Shipment Start
	 * 
	 */
		@ManyToOne
		@JoinColumn(name = "shipment_type_id")
		private ShipmentType shipmentType;
		
		@Column(name = "shipment_quantity")
		private Double shipmentQuantity;
	/* 
	 * 
	 * --------------------------------------------------------------- Shipment End
	 * 
	 */
		
	/* 
	 * 
	 * --------------------------------------------------------------- Bag cost and weight
	 * 
	 */
		@Column(name = "total_bag_cost")
		private Double totalBagCost;
		
		@Column(name = "total_bag_wight")
		private Double totalBagWeight;
	/* 
	 * 
	 * --------------------------------------------------------------- Bag cost and weight
	 * 
	 */
	
	/* 
	 * 
	 * --------------------------------------------------------------- Requester Details Start
	 * 
	 */
		@ManyToOne
		@JoinColumn(name = "user_id")
		private User requester;
		
		@Column(name = "request_timestamp", nullable = false, updatable = false, insertable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
		private Timestamp requestTimestamp;
		
	/* 
	 * 
	 * --------------------------------------------------------------- Requester Details End
	 * 
	 */
		
	/* 
	 * 
	 * --------------------------------------------------------------- Enquiry Status Start
	 * 
	 */
		@ManyToOne
		@JoinColumn(name = "enquiry_status_id")
		private POStatus pOStatus;
	/* 
	 * 
	 * --------------------------------------------------------------- Enquiry Status End
	 * 
	 */
}
