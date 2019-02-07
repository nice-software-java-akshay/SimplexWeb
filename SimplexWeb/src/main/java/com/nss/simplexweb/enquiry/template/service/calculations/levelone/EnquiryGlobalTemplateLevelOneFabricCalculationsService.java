package com.nss.simplexweb.enquiry.template.service.calculations.levelone;

import org.springframework.stereotype.Service;

import com.nss.simplexweb.enquiry.template.model.EnquiryTemplateBean;
import com.nss.simplexweb.enums.ENQUIRY;

@Service("enquiryGlobalTemplateLevelOneFabricCalculationsService")
public class EnquiryGlobalTemplateLevelOneFabricCalculationsService {

	//Default baffle GSM value
		private static final Double DEFAULT_FABRIC_TOP_GSM_VALUE = 60.0;
	
	//Default fabric coated value
		private static final Double  DEFAULT_FABRIC_COATED_VALUE = 30.0;
	
	//Default fabric reinforcement value
		private static final Double  DEFAULT_FABRIC_REINFORCEMENT_VALUE = 90.0;
		
	
	//Perform Level One Calculations For Fabric
	public EnquiryTemplateBean performLevelOneCalculationsForFabric(EnquiryTemplateBean enquiryTemplateBean) {
		//1. Fabric GSM
			if(enquiryTemplateBean.getFabricGSMValue() == null){
				enquiryTemplateBean.setFabricGSMValue(DEFAULT_FABRIC_TOP_GSM_VALUE);
			}else{
				if(enquiryTemplateBean.getProductUnitTypeWeight().getUnitTypeAbbr().equalsIgnoreCase(ENQUIRY.UNIT_OUNCES)){
					enquiryTemplateBean.setFabricGSMValue(enquiryTemplateBean.getFabricGSMValue() * 33.906);
				}
			}
			
		//2. Fabric coated
			if(enquiryTemplateBean.getFabricGSMType().getFabricGSMTypeAbbr().equals(ENQUIRY.FABRIC_GSM_COATED))
			{
				enquiryTemplateBean.setFabricGSMValue(enquiryTemplateBean.getFabricGSMValue() + DEFAULT_FABRIC_COATED_VALUE);
			}
			
		//3. Fabric reinforcement
			if(enquiryTemplateBean.isReinforcement())
			{
				enquiryTemplateBean.setFabricGSMValue(enquiryTemplateBean.getFabricGSMValue() + DEFAULT_FABRIC_REINFORCEMENT_VALUE);
			}

		
		return enquiryTemplateBean;
	}
}
