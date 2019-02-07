package com.nss.simplexweb.enquiry.template.service.calculations.levelone;

import org.springframework.stereotype.Service;

import com.nss.simplexweb.enquiry.template.model.EnquiryTemplateBean;
import com.nss.simplexweb.enums.ENQUIRY;

@Service("enquiryGlobalTemplateLevelOneLoopCalculationsService")
public class EnquiryGlobalTemplateLevelOneLoopCalculationsService {
	
	//Default fabric reinforcement value
		private static final Double  DEFAULT_LOOP_PROTECTOR_VALUE = 20.0;

	//Perform Level One Calculations For Loop
	public EnquiryTemplateBean performLevelOneCalculationsForLoop(EnquiryTemplateBean enquiryTemplateBean) {
		//1. Loop protector
			if(!enquiryTemplateBean.isLoopProtector()) {
				enquiryTemplateBean.setLoopProtectorValue(0.0);
				
			}else {
				if(enquiryTemplateBean.getLoopProtectorValue() == null) {
					enquiryTemplateBean.setLoopProtectorValue(DEFAULT_LOOP_PROTECTOR_VALUE);
				}
				else {	
					if(enquiryTemplateBean.getProductUnitTypeWeight().getUnitTypeAbbr().equalsIgnoreCase(ENQUIRY.UNIT_OUNCES)){
						enquiryTemplateBean.setFabricGSMValue(enquiryTemplateBean.getLoopProtectorValue() * 33.906);
					}
				}
			}
			
			//Total loops required
			if(enquiryTemplateBean.getProductModelType().getModelTypeAbbr().equalsIgnoreCase(ENQUIRY.MODEL_TYPE_UPANEL)
					|| enquiryTemplateBean.getProductModelType().getModelTypeAbbr().equalsIgnoreCase(ENQUIRY.MODEL_TYPE_4PANEL)
					|| enquiryTemplateBean.getProductModelType().getModelTypeAbbr().equalsIgnoreCase(ENQUIRY.MODEL_TYPE_Q_BAFFLE)
					|| enquiryTemplateBean.getProductModelType().getModelTypeAbbr().equalsIgnoreCase(ENQUIRY.MODEL_TYPE_HOOD)
					|| enquiryTemplateBean.getProductModelType().getModelTypeAbbr().equalsIgnoreCase(ENQUIRY.MODEL_TYPE_CONTAINER_LINER)
					|| enquiryTemplateBean.getProductModelType().getModelTypeAbbr().equalsIgnoreCase(ENQUIRY.MODEL_TYPE_PLATEN)
					|| enquiryTemplateBean.getProductModelType().getModelTypeAbbr().equalsIgnoreCase(ENQUIRY.MODEL_TYPE_TUBULAR_CIRCULAR)){
				enquiryTemplateBean.setLoopNumber(4.0);
				
			}else if(enquiryTemplateBean.getProductModelType().getModelTypeAbbr().equalsIgnoreCase(ENQUIRY.MODEL_TYPE_SINGLE_LOOP)){
				enquiryTemplateBean.setLoopNumber(1.0);
				
			}else if(enquiryTemplateBean.getProductModelType().getModelTypeAbbr().equalsIgnoreCase(ENQUIRY.MODEL_TYPE_TWO_LOOP)){
				enquiryTemplateBean.setLoopNumber(2.0);
			}
			
		return enquiryTemplateBean;
	}
}
