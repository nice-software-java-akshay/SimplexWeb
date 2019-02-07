package com.nss.simplexweb.enquiry.template.service.calculations.levelone;

import org.springframework.stereotype.Service;

import com.nss.simplexweb.enquiry.template.model.EnquiryTemplateBean;
import com.nss.simplexweb.enums.ENQUIRY;

@Service("enquiryGlobalTemplateLevelOneTopCalculationsService")
public class EnquiryGlobalTemplateLevelOneTopCalculationsService {

	//Default Top GSM value
		private static final Double DEFAULT_TOP_GSM_VALUE = 60.0;
	
	//DEFAULT coated value
		private static final Double DEFAULT_COATED_VALUE = 20.0;
	
	//Perform Level One Calculations For Top
	public EnquiryTemplateBean performLevelOneCalculationsForTop(EnquiryTemplateBean enquiryTemplateBean) {
		//1.1. Top Filling Status
			if(enquiryTemplateBean.isTopFilling()) {
			//1. Top Type
			//a. Top Skirt
				if(enquiryTemplateBean.getTopType().getTopTypeAbbr().equalsIgnoreCase(ENQUIRY.TOP_TYPE_TOP_SKIRT)) {
					//1. Top skirt GSM
						if(enquiryTemplateBean.getTopSkirtGSM() == null) {
							enquiryTemplateBean.setTopSkirtGSM(DEFAULT_TOP_GSM_VALUE);
						}
					//2. Top filling type status
						if(enquiryTemplateBean.getTopFillingType().getTopFillingTypeAbbr().equalsIgnoreCase(ENQUIRY.TOP_FILLING_TYPE_UNCOATED)) {
							if(enquiryTemplateBean.getTopSkirtLength() == null){
								enquiryTemplateBean.setTopSkirtLength(0.0);
							}else{
								//Length Unit type is already checked and calculated for top skirt length in main - level one calculations
							}
						}else {
							if(enquiryTemplateBean.getTopSkirtLength() == null){
								enquiryTemplateBean.setTopSkirtLength(0.0);
							}else{
								//Length Unit type is already checked and calculated for top skirt length in main - level one calculations
							}
							if(enquiryTemplateBean.getTopSkirtGSM()!=null) {
								if(enquiryTemplateBean.getProductUnitTypeWeight().getUnitTypeAbbr().equalsIgnoreCase(ENQUIRY.UNIT_OUNCES)) {
									enquiryTemplateBean.setTopSkirtGSM(enquiryTemplateBean.getTopSkirtGSM() * 33.906);
								}
								enquiryTemplateBean.setTopSkirtGSM(enquiryTemplateBean.getTopSkirtGSM() + DEFAULT_COATED_VALUE);
							}
						}
					
			//b. Top Spout
				}else if(enquiryTemplateBean.getTopType().getTopTypeAbbr().equalsIgnoreCase(ENQUIRY.TOP_TYPE_TOP_SPOUT)) {
					//1. Top spout GSM
						if(enquiryTemplateBean.getTopSpoutGSM() == null) {
							enquiryTemplateBean.setTopSpoutGSM(DEFAULT_TOP_GSM_VALUE);
						}
					
					//2. Top filling type status
						if(enquiryTemplateBean.getTopFillingType().getTopFillingTypeAbbr().equalsIgnoreCase(ENQUIRY.TOP_FILLING_TYPE_UNCOATED)) {
							if(enquiryTemplateBean.getTopSpoutLength() == null || enquiryTemplateBean.getTopSpoutDiameter() == null){
								if(enquiryTemplateBean.getTopSpoutLength() == null) {
									enquiryTemplateBean.setTopSpoutLength(0.0);
								}
								if(enquiryTemplateBean.getTopSpoutDiameter() == null) {
									enquiryTemplateBean.setTopSpoutDiameter(0.0);
								}
							}else{
								//Length Unit type is already checked and calculated for top skirt length in main - level one calculations
							}
						}else {
							if(enquiryTemplateBean.getTopSpoutLength() == null || enquiryTemplateBean.getTopSpoutDiameter() == null){
								if(enquiryTemplateBean.getTopSpoutLength() == null) {
									enquiryTemplateBean.setTopSpoutLength(0.0);
								}
								if(enquiryTemplateBean.getTopSpoutDiameter() == null) {
									enquiryTemplateBean.setTopSpoutDiameter(0.0);
								}
							}else{
								//Length Unit type is already checked and calculated for top skirt length in main - level one calculations
							}
							if(enquiryTemplateBean.getTopSpoutGSM()!=null) {
								if(enquiryTemplateBean.getProductUnitTypeWeight().getUnitTypeAbbr().equalsIgnoreCase(ENQUIRY.UNIT_OUNCES)) {
									enquiryTemplateBean.setTopSpoutGSM(enquiryTemplateBean.getTopSpoutGSM() * 33.906);
								}
								enquiryTemplateBean.setTopSpoutGSM(enquiryTemplateBean.getTopSpoutGSM() + DEFAULT_COATED_VALUE);
							}
						}
					
			//c. Top Flap
				}else if(enquiryTemplateBean.getTopType().getTopTypeAbbr().equalsIgnoreCase(ENQUIRY.TOP_TYPE_TOP_FLAP)) {
					//1. Top flap gsm
						if(enquiryTemplateBean.getTopFlapGSM() == null){
							enquiryTemplateBean.setTopFlapGSM(DEFAULT_TOP_GSM_VALUE);
						}
					
					//2. Top filling type status
						if(enquiryTemplateBean.getTopFillingType().getTopFillingTypeAbbr().equalsIgnoreCase(ENQUIRY.TOP_FILLING_TYPE_UNCOATED)) {
							//Nothing todo here
						}else {
							if(enquiryTemplateBean.getTopFlapGSM()!=null) {
								if(enquiryTemplateBean.getProductUnitTypeWeight().getUnitTypeAbbr().equalsIgnoreCase(ENQUIRY.UNIT_OUNCES)) {
									enquiryTemplateBean.setTopFlapGSM(enquiryTemplateBean.getTopFlapGSM() * 33.906);
								}
								enquiryTemplateBean.setTopFlapGSM(enquiryTemplateBean.getTopFlapGSM() + DEFAULT_COATED_VALUE);
							}
						}
					
			//d. Top Skirt with flap
				}else if(enquiryTemplateBean.getTopType().getTopTypeAbbr().equalsIgnoreCase(ENQUIRY.TOP_TYPE_TOP_SKIRT_WITH_FLAP)) {
					//1. Top skirt gsm
						if(enquiryTemplateBean.getTopSkirtGSM() == null){
							enquiryTemplateBean.setTopSkirtGSM(DEFAULT_TOP_GSM_VALUE);
						}
						
					//2. Top filling type status
						if(enquiryTemplateBean.getTopFillingType().getTopFillingTypeAbbr().equalsIgnoreCase(ENQUIRY.TOP_FILLING_TYPE_UNCOATED)) {
							if(enquiryTemplateBean.getTopSkirtLength() == null){
								enquiryTemplateBean.setTopSkirtLength(0.0);
							}else{
								//Length Unit type is already checked and calculated for top skirt length in main - level one calculations
							}
						}else {
							if(enquiryTemplateBean.getTopSkirtLength() == null){
								enquiryTemplateBean.setTopSkirtLength(0.0);
							}else{
								//Length Unit type is already checked and calculated for top skirt length in main - level one calculations
							}
							if(enquiryTemplateBean.getTopSkirtGSM()!=null) {
								if(enquiryTemplateBean.getProductUnitTypeWeight().getUnitTypeAbbr().equalsIgnoreCase(ENQUIRY.UNIT_OUNCES)) {
									enquiryTemplateBean.setTopSkirtGSM(enquiryTemplateBean.getTopSkirtGSM() * 33.906);
								}
								enquiryTemplateBean.setTopSkirtGSM(enquiryTemplateBean.getTopSkirtGSM() + DEFAULT_COATED_VALUE);
							}
						}
					
			//e. Top spout with flap
				}else if(enquiryTemplateBean.getTopType().getTopTypeAbbr().equalsIgnoreCase(ENQUIRY.TOP_TYPE_TOP_SPOUT_WITH_FLAP)) {
					//1. Top spout GSM
						if(enquiryTemplateBean.getTopSpoutGSM() == null) {
							enquiryTemplateBean.setTopSpoutGSM(DEFAULT_TOP_GSM_VALUE);
						}
					
					//2. Top filling type status
						if(enquiryTemplateBean.getTopFillingType().getTopFillingTypeAbbr().equalsIgnoreCase(ENQUIRY.TOP_FILLING_TYPE_UNCOATED)) {
							if(enquiryTemplateBean.getTopSpoutLength() == null || enquiryTemplateBean.getTopSpoutDiameter() == null){
								if(enquiryTemplateBean.getTopSpoutLength() == null) {
									enquiryTemplateBean.setTopSpoutLength(0.0);
								}
								if(enquiryTemplateBean.getTopSpoutDiameter() == null) {
									enquiryTemplateBean.setTopSpoutDiameter(0.0);
								}
							}else{
								//Length Unit type is already checked and calculated for top skirt length in main - level one calculations
							}
						}else {
							if(enquiryTemplateBean.getTopSpoutLength() == null || enquiryTemplateBean.getTopSpoutDiameter() == null){
								if(enquiryTemplateBean.getTopSpoutLength() == null) {
									enquiryTemplateBean.setTopSpoutLength(0.0);
								}
								if(enquiryTemplateBean.getTopSpoutDiameter() == null) {
									enquiryTemplateBean.setTopSpoutDiameter(0.0);
								}
							}else{
								//Length Unit type is already checked and calculated for top skirt length in main - level one calculations
							}
							if(enquiryTemplateBean.getTopSpoutGSM()!=null) {
								if(enquiryTemplateBean.getProductUnitTypeWeight().getUnitTypeAbbr().equalsIgnoreCase(ENQUIRY.UNIT_OUNCES)) {
									enquiryTemplateBean.setTopSpoutGSM(enquiryTemplateBean.getTopSpoutGSM() * 33.906);
								}
								enquiryTemplateBean.setTopSpoutGSM(enquiryTemplateBean.getTopSpoutGSM() + DEFAULT_COATED_VALUE);
							}
						}
					
			//f. Conical top
				/*}else if(enquiryTemplateBean.getTopType().getTopTypeAbbr().equalsIgnoreCase(ENQUIRY.TOP_TYPE_CONICAL_TOP)) {*/
					
					
			//g. Pleated top
				/*}else if(enquiryTemplateBean.getTopType().getTopTypeAbbr().equalsIgnoreCase(ENQUIRY.TOP_TYPE_PLEATED_TOP)) {*/
					
			//h. Conical top with flap
				}else if(enquiryTemplateBean.getTopType().getTopTypeAbbr().equalsIgnoreCase(ENQUIRY.TOP_TYPE_CONICAL_TOP_WITH_FLAP)) {
					//1. Top flap gsm
						if(enquiryTemplateBean.getTopFlapGSM() == null){
							enquiryTemplateBean.setTopFlapGSM(DEFAULT_TOP_GSM_VALUE);
						}
					
					//2. Top filling type status
						if(enquiryTemplateBean.getTopFillingType().getTopFillingTypeAbbr().equalsIgnoreCase(ENQUIRY.TOP_FILLING_TYPE_UNCOATED)) {
							//Nothing todo here
						}else {
							if(enquiryTemplateBean.getTopFlapGSM()!=null) {
								if(enquiryTemplateBean.getProductUnitTypeWeight().getUnitTypeAbbr().equalsIgnoreCase(ENQUIRY.UNIT_OUNCES)) {
									enquiryTemplateBean.setTopFlapGSM(enquiryTemplateBean.getTopFlapGSM() * 33.906);
								}
								enquiryTemplateBean.setTopFlapGSM(enquiryTemplateBean.getTopFlapGSM() + DEFAULT_COATED_VALUE);
							}
						}
					
			//i. Pleated top with flap
				}else if(enquiryTemplateBean.getTopType().getTopTypeAbbr().equalsIgnoreCase(ENQUIRY.TOP_TYPE_PLEATED_TOP_WITH_FLAP)) {
					//1. Top flap gsm
						if(enquiryTemplateBean.getTopFlapGSM() == null){
							enquiryTemplateBean.setTopFlapGSM(DEFAULT_TOP_GSM_VALUE);
						}
					
					//2. Top filling type status
						if(enquiryTemplateBean.getTopFillingType().getTopFillingTypeAbbr().equalsIgnoreCase(ENQUIRY.TOP_FILLING_TYPE_UNCOATED)) {
							//Nothing todo here
						}else {
							if(enquiryTemplateBean.getTopFlapGSM()!=null) {
								if(enquiryTemplateBean.getProductUnitTypeWeight().getUnitTypeAbbr().equalsIgnoreCase(ENQUIRY.UNIT_OUNCES)) {
									enquiryTemplateBean.setTopFlapGSM(enquiryTemplateBean.getTopFlapGSM() * 33.906);
								}
								enquiryTemplateBean.setTopFlapGSM(enquiryTemplateBean.getTopFlapGSM() + DEFAULT_COATED_VALUE);
							}
						}
				}
				
				//1.2. Top Tie Calculations
				enquiryTemplateBean = performTopTieCalculations(enquiryTemplateBean);
				
		}	//Top Filling End
			
		return enquiryTemplateBean;
	}
	
	
	// Top Tie Calculations
	private EnquiryTemplateBean performTopTieCalculations(EnquiryTemplateBean enquiryTemplateBean) {
		//a. Top standard tie
			if(enquiryTemplateBean.isTopStandardTie()){
				enquiryTemplateBean.setTopStandardTieNumber(0.0);
			}else{
				if(enquiryTemplateBean.getTopStandardTieNumber() == null){
					enquiryTemplateBean.setTopStandardTieNumber(0.0);
				}
			}
			
		//b. Top rope tie
			if(enquiryTemplateBean.isTopRopeTie()){
				enquiryTemplateBean.setTopRopeTieNumber(0.0);
			}else{
				if(enquiryTemplateBean.getTopRopeTieNumber() == null){
					enquiryTemplateBean.setTopRopeTieNumber(0.0);
				}
			}
			
		//c. Top velcro tie
			if(enquiryTemplateBean.isTopVelcroTie()){
				enquiryTemplateBean.setTopVelcroTieNumber(0.0);
			}else{
				if(enquiryTemplateBean.getTopVelcroTieNumber() == null){
					enquiryTemplateBean.setTopVelcroTieNumber(0.0);
				}
			}
			
		//d. Top b-lcok
			if(enquiryTemplateBean.isTopBlock()){
				enquiryTemplateBean.setTopBlockNumber(0.0);
			}else{
				if(enquiryTemplateBean.getTopBlockNumber() == null){
					enquiryTemplateBean.setTopBlockNumber(0.0);
				}
			}
			
		return enquiryTemplateBean;
	}
	
}
