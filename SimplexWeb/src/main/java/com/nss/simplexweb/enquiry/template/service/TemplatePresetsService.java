package com.nss.simplexweb.enquiry.template.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nss.simplexweb.enquiry.template.service.bottomdischarge.BottomDischargeTypeService;
import com.nss.simplexweb.enquiry.template.service.bottomdischarge.BottomTypeService;
import com.nss.simplexweb.enquiry.template.service.fabric.FabricBagSeamColorService;
import com.nss.simplexweb.enquiry.template.service.fabric.FabricBagSeamTypeService;
import com.nss.simplexweb.enquiry.template.service.fabric.FabricColorService;
import com.nss.simplexweb.enquiry.template.service.fabric.FabricGSMTypeService;
import com.nss.simplexweb.enquiry.template.service.fabric.FabricTypeService;
import com.nss.simplexweb.enquiry.template.service.loop.LoopColorService;
import com.nss.simplexweb.enquiry.template.service.loop.LoopMaterialService;
import com.nss.simplexweb.enquiry.template.service.loop.LoopSewingTypeService;
import com.nss.simplexweb.enquiry.template.service.loop.LoopTypeService;
import com.nss.simplexweb.enquiry.template.service.other.DocPouchDetailTypeService;
import com.nss.simplexweb.enquiry.template.service.other.DocPouchTypeService;
import com.nss.simplexweb.enquiry.template.service.other.LinerTypeService;
import com.nss.simplexweb.enquiry.template.service.other.PackagingTypeService;
import com.nss.simplexweb.enquiry.template.service.other.PrintingColorService;
import com.nss.simplexweb.enquiry.template.service.other.SidesService;
import com.nss.simplexweb.enquiry.template.service.product.ProductModelTypeService;
import com.nss.simplexweb.enquiry.template.service.product.ProductSFtypeService;
import com.nss.simplexweb.enquiry.template.service.product.ProductSurfaceTypeService;
import com.nss.simplexweb.enquiry.template.service.product.ProductTypeService;
import com.nss.simplexweb.enquiry.template.service.product.ProductUnitTypeLengthService;
import com.nss.simplexweb.enquiry.template.service.product.ProductUnitTypeWeightService;
import com.nss.simplexweb.enquiry.template.service.shipment.ShipmentTypeService;
import com.nss.simplexweb.enquiry.template.service.topfilling.TopFillingTypeService;
import com.nss.simplexweb.enquiry.template.service.topfilling.TopTypeService;
import com.nss.simplexweb.enums.ENQUIRY;

@Service("templatePresetsService")
public class TemplatePresetsService {
	
	//Product
	@Autowired
	private ProductTypeService productTypeService;
	@Autowired
	private ProductModelTypeService productModelTypeService;
	@Autowired
	private ProductUnitTypeLengthService productUnitTypeLengthService;
	@Autowired
	private ProductUnitTypeWeightService productUnitTypeWeightService;
	@Autowired
	private ProductSurfaceTypeService productSurfaceTypeService;
	@Autowired
	private ProductSFtypeService ProductSFtypeService;
	
	//Fabric
	@Autowired
	private FabricTypeService fabricTypeService;
	@Autowired
	private FabricColorService fabricColorService;
	@Autowired
	private FabricGSMTypeService fabricGSMTypeService;
	@Autowired
	private FabricBagSeamTypeService fabricBagSeamTypeService;
	@Autowired
	private FabricBagSeamColorService fabricBagSeamColorService;
	
	//Top Filling
	@Autowired
	private TopFillingTypeService topFillingTypeService;
	@Autowired
	private TopTypeService topTypeService;
	
	//Bottom Discharge
	@Autowired
	private BottomDischargeTypeService bottomDischargeTypeService;
	@Autowired
	private BottomTypeService bottomTypeService;
	
	//Loop
	@Autowired
	private LoopTypeService loopTypeService;
	@Autowired
	private LoopMaterialService loopMaterialService;
	@Autowired
	private LoopColorService loopColorService;
	@Autowired
	private LoopSewingTypeService loopSewingTypeService;
	
	//Other
	//A
	@Autowired
	private DocPouchTypeService docPouchTypeService;
	@Autowired
	private DocPouchDetailTypeService docPouchDetailTypeService;
	//B
	@Autowired
	private SidesService sidesService;
	@Autowired
	private PrintingColorService printingColorService;
	@Autowired
	private PackagingTypeService packagingTypeService;
	@Autowired
	private LinerTypeService linerTypeService;
	
	//Shipment
	@Autowired
	private ShipmentTypeService shipmentTypeService;

	
	@Autowired
	public TemplatePresetsService(
			//Product
				ProductTypeService productTypeService,
				ProductModelTypeService productModelTypeService,
				ProductUnitTypeLengthService productUnitTypeLengthService,
				ProductUnitTypeWeightService productUnitTypeWeightService,
				ProductSurfaceTypeService productSurfaceTypeService,
				ProductSFtypeService ProductSFtypeService,
			//Fabric
				FabricTypeService fabricTypeService,
				FabricColorService fabricColorService,
				FabricGSMTypeService fabricGSMTypeService,
				FabricBagSeamTypeService fabricBagSeamTypeService,
				FabricBagSeamColorService fabricBagSeamColorService,
			//Top Filling
				TopFillingTypeService topFillingTypeService,
				TopTypeService topTypeService,
			//Bottom Discharge
				BottomDischargeTypeService bottomDischargeTypeService,
				BottomTypeService bottomTypeService,
			//Loop
				LoopTypeService loopTypeService,
				LoopMaterialService loopMaterialService,
				LoopColorService loopColorService,
				LoopSewingTypeService loopSewingTypeService,
			//Other
				//A
				DocPouchTypeService docPouchTypeService,
				DocPouchDetailTypeService docPouchDetailTypeService,
				//B
				SidesService sidesService,
				PrintingColorService printingColorService,
				PackagingTypeService packagingTypeService,
				LinerTypeService linerTypeService,
			//Shipment
				ShipmentTypeService shipmentTypeService
			) 
	{
		//Product
			this.productTypeService = productTypeService;
			this.productModelTypeService = productModelTypeService;
			this.productUnitTypeLengthService = productUnitTypeLengthService;
			this.productUnitTypeWeightService = productUnitTypeWeightService;
			this.productSurfaceTypeService = productSurfaceTypeService;
			this.ProductSFtypeService = ProductSFtypeService;
		//Fabric
			this.fabricTypeService = fabricTypeService;
			this.fabricColorService = fabricColorService;
			this.fabricGSMTypeService = fabricGSMTypeService;
			this.fabricBagSeamTypeService = fabricBagSeamTypeService;
			this.fabricBagSeamColorService = fabricBagSeamColorService;
		//Top Filling
			this.topFillingTypeService = topFillingTypeService;
			this.topTypeService = topTypeService;
		//Bottom Discharge
			this.bottomDischargeTypeService = bottomDischargeTypeService;
			this.bottomTypeService = bottomTypeService;
		//Loop
			this.loopTypeService = loopTypeService;
			this.loopMaterialService = loopMaterialService;
			this.loopColorService = loopColorService;
			this.loopSewingTypeService = loopSewingTypeService;
		//Other
		//A
			this.docPouchTypeService = docPouchTypeService;
			this.docPouchDetailTypeService = docPouchDetailTypeService;
		//B
			this.sidesService = sidesService;
			this.printingColorService = printingColorService;
			this.packagingTypeService = packagingTypeService;
			this.linerTypeService = linerTypeService;
		//Shipment
			this.shipmentTypeService = shipmentTypeService;
	}

	public Map<String, List<?>> getAllPresets() {
		Map<String, List<?>> presetMap = new HashMap<>();
		//Product
			presetMap.put(ENQUIRY.PRODUCT_TYPE_LIST, productTypeService.getProductTypeList());
			presetMap.put(ENQUIRY.PRODUCT_MODEL_TYPE_LIST, productModelTypeService.getProductModelTypeList());
			presetMap.put(ENQUIRY.PRODUCT_UNIT_TYPE_LENGTH_LIST, productUnitTypeLengthService.getProductUnitTypeLengthList());
			presetMap.put(ENQUIRY.PRODUCT_UNIT_TYPE_WEIGHT_LIST, productUnitTypeWeightService.getProductUnitTypeWeightList());
			presetMap.put(ENQUIRY.PRODUCT_SURFACE_TYPE_LIST, productSurfaceTypeService.getProductSurfaceTypeList());
			presetMap.put(ENQUIRY.PRODUCT_SF_TYPE_LIST, ProductSFtypeService.getProductSFTypeList());
		//Fabric
			presetMap.put(ENQUIRY.FABRIC_TYPE_LIST, fabricTypeService.getFabricTypeList());
			presetMap.put(ENQUIRY.FABRIC_COLOR_LIST, fabricColorService.getFabricColorList());
			presetMap.put(ENQUIRY.FABRIC_GSM_TYPE_LIST, fabricGSMTypeService.getFabricGSMTypeList());
			presetMap.put(ENQUIRY.FABRIC_BAG_SEAM_TYPE_LIST, fabricBagSeamTypeService.getFabricBagSeamTypeList());
			presetMap.put(ENQUIRY.FABRIC_BAG_SEAM_COLOR_LIST, fabricBagSeamColorService.getFabricBagSeamColorList());
		//Top Filling
			presetMap.put(ENQUIRY.TOP_FILLING_TYPE_LIST, topFillingTypeService.getTopFillingTypeList());
			presetMap.put(ENQUIRY.TOP_TYPE_LIST, topTypeService.getTopTypeList());
		//Bottom
			presetMap.put(ENQUIRY.BOTTOM_DISCHARGE_TYPE_LIST, bottomDischargeTypeService.getBottomDischargeTypeList());
			presetMap.put(ENQUIRY.BOTTOM_TYPE_LIST, bottomTypeService.getBottomTypeList());
        //Loop
			presetMap.put(ENQUIRY.LOOP_TYPE_LIST, loopTypeService.getLoopTypeList());
			presetMap.put(ENQUIRY.LOOP_MATERIAL_LIST, loopMaterialService.getLoopMaterialList());
			presetMap.put(ENQUIRY.LOOP_COLOR_LIST, loopColorService.getLoopColorList());
			presetMap.put(ENQUIRY.LOOP_SEWING_TYPE_LIST, loopSewingTypeService.getLoopSewingTypeList());
       //Other
       //A
			presetMap.put(ENQUIRY.DOC_POUCH_TYPE_LIST, docPouchTypeService.getDcoPouchTypeList());
			presetMap.put(ENQUIRY.DOC_POUCH_DETAIL_TYPE_LIST, docPouchDetailTypeService.getDocPouchDetailTypeList());
       //B
			presetMap.put(ENQUIRY.SIDES_LIST, sidesService.getSidesList());
			presetMap.put(ENQUIRY.PRINTING_COLOR_LIST, printingColorService.getPrintingColorList());
			presetMap.put(ENQUIRY.PACKAGING_TYPE_LIST, packagingTypeService.getPackagingTypeList());
			presetMap.put(ENQUIRY.LINER_TYPE_LIST, linerTypeService.getLinerTypeList());
                                     
       //Shipment                               
			presetMap.put(ENQUIRY.SHIPMENT_TYPE_LIST, shipmentTypeService.getShipmentTypeList());

		return presetMap;
	}
}
