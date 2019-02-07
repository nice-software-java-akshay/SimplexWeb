package com.nss.simplexweb.enquiry.template.service.calculations.levelone;

import org.springframework.stereotype.Service;

import com.nss.simplexweb.enquiry.template.model.EnquiryTemplateBean;
import com.nss.simplexweb.enums.ENQUIRY;

@Service("enquiryGlobalTemplateLevelOneOtherCalculationsService")
public class EnquiryGlobalTemplateLevelOneOtherCalculationsService {

	//Default liner micron value
		private static final Double DEFAULT_LINER_MICRON_VALUE = 75.0;
	
	//Perform Level One Calculations For Other
	public EnquiryTemplateBean performLevelOneCalculationsForOther(EnquiryTemplateBean enquiryTemplateBean) {
		//1. Liner macron
			if(enquiryTemplateBean.getLinerMicronValue() == null) {
				if(enquiryTemplateBean.getProductModelType().getModelTypeAbbr().equalsIgnoreCase(ENQUIRY.MODEL_TYPE_HOOD)) {
					enquiryTemplateBean.setLinerMicronValue(50.0);
				}else {
					enquiryTemplateBean.setLinerMicronValue(DEFAULT_LINER_MICRON_VALUE);
				}
			}
		
		return enquiryTemplateBean;
	}
}
