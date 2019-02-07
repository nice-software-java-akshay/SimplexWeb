package com.nss.simplexweb.enquiry.template.service.calculations.leveltwo;

import org.springframework.stereotype.Service;

import com.nss.simplexweb.enquiry.template.model.EnquiryTemplateBean;
import com.nss.simplexweb.enums.ENQUIRY;

@Service("enquiryGlobalTemplateLevelTwoCalculationsService")
public class EnquiryGlobalTemplateLevelTwoCalculationsService {

	//Perform level two calculations depending upon the bag model type
	public EnquiryTemplateBean performLvelTwoCalculations(EnquiryTemplateBean enquiryTemplateBean) {
		final String MODEL_TYPE = enquiryTemplateBean.getProductModelType().getModelTypeAbbr();
		
		switch (MODEL_TYPE) {
		case ENQUIRY.MODEL_TYPE_4PANEL:
			
			break;
		case ENQUIRY.MODEL_TYPE_UPANEL:
			
			break;
		case ENQUIRY.MODEL_TYPE_SINGLE_LOOP:
					
			break;
		case ENQUIRY.MODEL_TYPE_TWO_LOOP:
			
			break;
		case ENQUIRY.MODEL_TYPE_PLATEN:
			
			break;
		case ENQUIRY.MODEL_TYPE_Q_BAFFLE:
			
			break;
		case ENQUIRY.MODEL_TYPE_TUBULAR_CIRCULAR:
			
			break;
		case ENQUIRY.MODEL_TYPE_CONTAINER_LINER:
			
			break;
		case ENQUIRY.MODEL_TYPE_HOOD:
			
			break;
		default:
			
			break;
		}
		
		return enquiryTemplateBean;
	}
	
	//1. 4 Panel bag
	/*private EnquiryTemplateBean performLevelTwoCalculationsFor4Panel(EnquiryTemplateBean enquiryTemplateBean) {
		
	}*/
}
