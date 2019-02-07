package com.nss.simplexweb.enquiry.template.service.calculations.levelone;

import org.springframework.stereotype.Service;

import com.nss.simplexweb.enquiry.template.model.EnquiryTemplateBean;
import com.nss.simplexweb.enums.ENQUIRY;

@Service("enquiryGlobalTemplateLevelOneBottomCalculationsService")
public class EnquiryGlobalTemplateLevelOneBottomCalculationsService {
	
	//Default Bottom GSM value
			private static final Double DEFAULT_BOTTOM_GSM_VALUE = 80.0;
			
	//DEFAULT coated value
			private static final Double DEFAULT_COATED_VALUE = 20.0;

	//Perform Level One Calculations For Bottom
	public EnquiryTemplateBean performLevelOneCalculationsForBottom(EnquiryTemplateBean enquiryTemplateBean) {
		//1.1. Bottom discharge status
			if(enquiryTemplateBean.isBottomDischarge()) {
				//1. Bottom GSM
					if(enquiryTemplateBean.getBottomSpoutGSM() == null){
						enquiryTemplateBean.setBottomSpoutGSM(DEFAULT_BOTTOM_GSM_VALUE);
					}
				
				//2. Bottom discharge type
					if(enquiryTemplateBean.getBottomDischargeType().getBottomDischargeTypeAbbr().equalsIgnoreCase(ENQUIRY.BOTTOM_DISCHARGE_TYPE_UNCOATED)) {
						//a. Bottom spout length and diameter
						if(enquiryTemplateBean.getBottomSpoutDiameter() == null || enquiryTemplateBean.getBottomSpoutLength() == null) {
							if(enquiryTemplateBean.getBottomSpoutDiameter() == null) {
								enquiryTemplateBean.setBottomSpoutDiameter(0.0);
							}
							if(enquiryTemplateBean.getBottomSpoutLength() == null) {
								enquiryTemplateBean.setBottomSpoutLength(0.0);
							}
						}else{
							//Length Unit type is already checked and calculated for bottom spot length and diameter in main - level one calculations
						}
					}else {
						//a. Bottom spout length and diameter
						if(enquiryTemplateBean.getBottomSpoutDiameter() == null || enquiryTemplateBean.getBottomSpoutLength() == null) {
							if(enquiryTemplateBean.getBottomSpoutDiameter() == null) {
								enquiryTemplateBean.setBottomSpoutDiameter(0.0);
							}
							if(enquiryTemplateBean.getBottomSpoutLength() == null) {
								enquiryTemplateBean.setBottomSpoutLength(0.0);
							}
						}else{
							//Length Unit type is already checked and calculated for bottom spot length and diameter in main - level one calculations
						}
						
						//b.Bottom GSM
						if(enquiryTemplateBean.getBottomSpoutGSM() != null)
						{
							if(enquiryTemplateBean.getProductUnitTypeWeight().getUnitTypeAbbr().equalsIgnoreCase(ENQUIRY.UNIT_OUNCES)) {
								enquiryTemplateBean.setBottomSpoutGSM(enquiryTemplateBean.getBottomSpoutGSM() * 33.906);
							}
							enquiryTemplateBean.setBottomSpoutGSM(enquiryTemplateBean.getBottomSpoutGSM() + DEFAULT_COATED_VALUE);
						}
					}
					
			//1.2. Bottom Tie Calculations
				enquiryTemplateBean = performBottomTieCalculations(enquiryTemplateBean);
			}
		
		return enquiryTemplateBean;
	}
	
	
	// Bottom Tie Calculations
		private EnquiryTemplateBean performBottomTieCalculations(EnquiryTemplateBean enquiryTemplateBean) {
			//a. Bottom standard tie
				if(enquiryTemplateBean.isBottomStandardTie()){
					enquiryTemplateBean.setBottomStandardTieNumber(0.0);
				}else{
					if(enquiryTemplateBean.getBottomStandardTieNumber() == null){
						enquiryTemplateBean.setBottomStandardTieNumber(0.0);
					}
				}
				
			//b. Bottom rope tie
				if(enquiryTemplateBean.isBottomRopeTie()){
					enquiryTemplateBean.setBottomRopeTieNumber(0.0);
				}else{
					if(enquiryTemplateBean.getBottomRopeTieNumber() == null){
						enquiryTemplateBean.setBottomRopeTieNumber(0.0);
					}
				}
				
			//c. Bottom velcro tie
				if(enquiryTemplateBean.isBottomVelcroTie()){
					enquiryTemplateBean.setBottomVelcroTieNumber(0.0);
				}else{
					if(enquiryTemplateBean.getBottomVelcroTieNumber() == null){
						enquiryTemplateBean.setBottomVelcroTieNumber(0.0);
					}
				}
				
			//d. Bottom b-lcok
				if(enquiryTemplateBean.isBottomBlock()){
					enquiryTemplateBean.setBottomBlockNumber(0.0);
				}else{
					if(enquiryTemplateBean.getBottomBlockNumber() == null){
						enquiryTemplateBean.setBottomBlockNumber(0.0);
					}
				}
				
			return enquiryTemplateBean;
		}
}
