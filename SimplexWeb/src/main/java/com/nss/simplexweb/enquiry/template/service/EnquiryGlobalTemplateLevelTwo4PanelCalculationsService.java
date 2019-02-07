package com.nss.simplexweb.enquiry.template.service;

import org.springframework.stereotype.Service;

import com.nss.simplexweb.enquiry.template.model.EnquiryTemplateBean;
import com.nss.simplexweb.enums.ENQUIRY;

@Service("enquiryGlobalTemplateLevelTwo4PanelCalculationsService")
public class EnquiryGlobalTemplateLevelTwo4PanelCalculationsService {

	//Calculate Bag Weight
	public EnquiryTemplateBean calculateBagWeight(EnquiryTemplateBean enquiryTemplateBean) {
		Double BAG_SIDE1 = 0.0, BAG_SIDE2 = 0.0, BAG_BASE = 0.0, BAG_CROSS_CORNER_LOOP = 0.0;
		Double bagInletAttachment = 0.0, bagOutletAttachment = 0.0;	//top filling, bottom discharge
		Double bagThread = 0.0, bagFillerCord = 0.0;
		int bagTieForBottom = 0;
		int bagTieForTop = 0;
		int bagDoc = 0;
		int bagLabel = 0;
		Double bagLiner = 0.0;
		
		//1. Surface type
			if(enquiryTemplateBean.getProductSurfaceType().getSurfaceTypeAbbr().equalsIgnoreCase(ENQUIRY.SURFACE_TYPE_INTERNAL)) {
				//1. Bag side1, side2, base
					BAG_SIDE1 = calculateBagSideAndBase(enquiryTemplateBean, "SIDE1");
					BAG_SIDE2 = calculateBagSideAndBase(enquiryTemplateBean, "SIDE2");
					BAG_BASE = calculateBagSideAndBase(enquiryTemplateBean, "BASE");
					BAG_CROSS_CORNER_LOOP = calculateBagCrossCornerLoop(enquiryTemplateBean);
				
				//2. Inlet/ If Top Filling - Yes
					if(enquiryTemplateBean.isTopFilling()) {
						//a. top skirt
						if(enquiryTemplateBean.getTopType().getTopTypeAbbr().equalsIgnoreCase(ENQUIRY.TOP_TYPE_TOP_SKIRT)) {
							bagInletAttachment =  calculateAttachmentInletTopSkirt(enquiryTemplateBean.getSurfaceLength(), enquiryTemplateBean.getSurfaceWidth(), enquiryTemplateBean.getTopSkirtLength(), enquiryTemplateBean.getTopSkirtGSM());
						}
						//b. top spout
						else if(enquiryTemplateBean.getTopType().getTopTypeAbbr().equalsIgnoreCase(ENQUIRY.TOP_TYPE_TOP_SPOUT)) {
							bagInletAttachment = calculateAttachmentInletTopSpout(enquiryTemplateBean.getSurfaceLength(), enquiryTemplateBean.getSurfaceWidth(), enquiryTemplateBean.getTopSpoutLength(), enquiryTemplateBean.getTopSpoutGSM(), enquiryTemplateBean.getTopSpoutDiameter());
							bagInletAttachment = bagInletAttachment + calculateAttachmentTopFabric(enquiryTemplateBean.getSurfaceLength(), enquiryTemplateBean.getSurfaceWidth(), enquiryTemplateBean.getTopSpoutGSM());
						}
						//c. top flap
						else if(enquiryTemplateBean.getTopType().getTopTypeAbbr().equalsIgnoreCase(ENQUIRY.TOP_TYPE_TOP_FLAP)) {
							bagInletAttachment = calculateAttachmentInletTopFlap(enquiryTemplateBean.getSurfaceLength(), enquiryTemplateBean.getSurfaceWidth(), enquiryTemplateBean.getTopFlapGSM());
						}
						//d. Top Skirt with Flap
						else if(enquiryTemplateBean.getTopType().getTopTypeAbbr().equalsIgnoreCase(ENQUIRY.TOP_TYPE_TOP_SKIRT_WITH_FLAP)) {
							bagInletAttachment = calculateAttachmentInletTopSkirt(enquiryTemplateBean.getSurfaceLength(), enquiryTemplateBean.getSurfaceWidth(), enquiryTemplateBean.getTopSkirtLength(), enquiryTemplateBean.getTopSkirtGSM());
							bagInletAttachment  =bagInletAttachment + calculateAttachmentInletTopFlap(enquiryTemplateBean.getSurfaceLength(), enquiryTemplateBean.getSurfaceWidth(), enquiryTemplateBean.getTopFlapGSM());
						}
						//e. Top Spout with Flap
						else if(enquiryTemplateBean.getTopType().getTopTypeAbbr().equalsIgnoreCase(ENQUIRY.TOP_TYPE_TOP_SPOUT_WITH_FLAP)) {
							bagInletAttachment = calculateAttachmentInletTopSpout(enquiryTemplateBean.getSurfaceLength(), enquiryTemplateBean.getSurfaceWidth(), enquiryTemplateBean.getTopSpoutLength(), enquiryTemplateBean.getTopSpoutGSM(), enquiryTemplateBean.getTopSpoutDiameter());
							bagInletAttachment = bagInletAttachment + calculateAttachmentTopFabric(enquiryTemplateBean.getSurfaceLength(), enquiryTemplateBean.getSurfaceWidth(), enquiryTemplateBean.getTopSpoutGSM());
							bagInletAttachment = bagInletAttachment + calculateAttachmentInletTopFlap(enquiryTemplateBean.getSurfaceLength(), enquiryTemplateBean.getSurfaceWidth(), enquiryTemplateBean.getTopFlapGSM());
						}
						//f. Conical Top
						else if(enquiryTemplateBean.getTopType().getTopTypeAbbr().equalsIgnoreCase(ENQUIRY.TOP_TYPE_CONICAL_TOP)) {
							bagInletAttachment = calculateAttachmentInletConicalTop(enquiryTemplateBean.getSurfaceLength(),enquiryTemplateBean.getSurfaceWidth());
						}
						//g. Platted Top
						else if(enquiryTemplateBean.getTopType().getTopTypeAbbr().equalsIgnoreCase(ENQUIRY.TOP_TYPE_PLEATED_TOP)) {
							bagInletAttachment = calculateAttachmentInletPlattedTop(enquiryTemplateBean.getSurfaceLength(),enquiryTemplateBean.getSurfaceWidth());
						}
						//h. Conical with Top Flap
						else if(enquiryTemplateBean.getTopType().getTopTypeAbbr().equalsIgnoreCase(ENQUIRY.TOP_TYPE_CONICAL_TOP_WITH_FLAP)) {
							bagInletAttachment = calculateAttachmentInletConicalTop(enquiryTemplateBean.getSurfaceLength(),enquiryTemplateBean.getSurfaceWidth());
							bagInletAttachment = bagInletAttachment + calculateAttachmentInletTopFlap(enquiryTemplateBean.getSurfaceLength(),enquiryTemplateBean.getSurfaceWidth(), enquiryTemplateBean.getTopFlapGSM());
						}
						//e. Platted with Top Flap
						else if(enquiryTemplateBean.getTopType().getTopTypeAbbr().equalsIgnoreCase(ENQUIRY.TOP_TYPE_PLEATED_TOP_WITH_FLAP)) {
							bagInletAttachment = calculateAttachmentInletPlattedTop(enquiryTemplateBean.getSurfaceLength(),enquiryTemplateBean.getSurfaceWidth());
							bagInletAttachment = bagInletAttachment + calculateAttachmentInletTopFlap(enquiryTemplateBean.getSurfaceLength(),enquiryTemplateBean.getSurfaceWidth(), enquiryTemplateBean.getTopFlapGSM());
						}
					}
					
				//3. Outlet/ If bottom discharge - Yes
					if(enquiryTemplateBean.isBottomDischarge()) {
						bagOutletAttachment = calculateAttachmentOutletBottomSpout(enquiryTemplateBean.getBottomSpoutDiameter(), enquiryTemplateBean.getBottomSpoutGSM(), enquiryTemplateBean.getBottomSpoutLength(),enquiryTemplateBean.getSurfaceLength(),enquiryTemplateBean.getSurfaceWidth(), enquiryTemplateBean.getSurfaceHeight(), enquiryTemplateBean.getBottomDischargeType().getBottomDischargeTypeAbbr());	
					}
					
				//4. Variables for calculation
					Double inletLength=0.0, outletLength=0.0, outletDimeter=0.0, inletDiameter=0.0;
					//If top filling is - Yes
					if(enquiryTemplateBean.isTopFilling()) {
						if(enquiryTemplateBean.getTopType().getTopTypeAbbr().equalsIgnoreCase(ENQUIRY.TOP_TYPE_TOP_SKIRT)
								|| enquiryTemplateBean.getTopType().getTopTypeAbbr().equalsIgnoreCase(ENQUIRY.TOP_TYPE_TOP_SKIRT_WITH_FLAP)){
							inletLength = enquiryTemplateBean.getTopSkirtLength();
							
						}
						else if(enquiryTemplateBean.getTopType().getTopTypeAbbr().equalsIgnoreCase(ENQUIRY.TOP_TYPE_TOP_SPOUT)
								|| enquiryTemplateBean.getTopType().getTopTypeAbbr().equalsIgnoreCase(ENQUIRY.TOP_TYPE_TOP_SPOUT_WITH_FLAP)){
							inletLength = enquiryTemplateBean.getTopSpoutLength();
							inletDiameter = enquiryTemplateBean.getTopSpoutDiameter();
						}
					}
					
					//If bottom discharge is - Yes
					if(enquiryTemplateBean.isBottomDischarge()) {
						outletLength = enquiryTemplateBean.getBottomSpoutLength();
						outletDimeter = enquiryTemplateBean.getBottomSpoutDiameter();
					}
					
					//If top filling is - Yes
					if(enquiryTemplateBean.isTopFilling()) {
						if(enquiryTemplateBean.getTopType().getTopTypeAbbr().equalsIgnoreCase(ENQUIRY.TOP_TYPE_TOP_SKIRT_WITH_FLAP)
								|| enquiryTemplateBean.getTopType().getTopTypeAbbr().equalsIgnoreCase(ENQUIRY.TOP_TYPE_TOP_SPOUT_WITH_FLAP) 
								|| enquiryTemplateBean.getTopType().getTopTypeAbbr().equalsIgnoreCase(ENQUIRY.TOP_TYPE_CONICAL_TOP_WITH_FLAP) 
								|| enquiryTemplateBean.getTopType().getTopTypeAbbr().equalsIgnoreCase(ENQUIRY.TOP_TYPE_PLEATED_TOP_WITH_FLAP)){
							
							bagThread = calculateThreadForBag(enquiryTemplateBean.getSurfaceLength(), enquiryTemplateBean.getSurfaceWidth(), enquiryTemplateBean.getSurfaceHeight(), enquiryTemplateBean.isTopFilling(), enquiryTemplateBean.getTopFillingType().getTopFillingTypeAbbr() , inletLength,inletDiameter, enquiryTemplateBean.isBottomDischarge(), outletLength, outletDimeter);
							bagThread = bagThread + calculateThreadForBag(enquiryTemplateBean.getSurfaceLength(), enquiryTemplateBean.getSurfaceWidth(), enquiryTemplateBean.getSurfaceHeight(), enquiryTemplateBean.isTopFilling(), enquiryTemplateBean.getTopFillingType().getTopFillingTypeAbbr(),  0.0, 0.0, enquiryTemplateBean.isBottomDischarge(), outletLength, outletDimeter);
						}
						else {
							bagThread=calculateThreadForBag(enquiryTemplateBean.getSurfaceLength(), enquiryTemplateBean.getSurfaceWidth(), enquiryTemplateBean.getSurfaceHeight(), enquiryTemplateBean.isTopFilling(), enquiryTemplateBean.getTopFillingType().getTopFillingTypeAbbr() , inletLength,inletDiameter, enquiryTemplateBean.isBottomDischarge(), outletLength, outletDimeter);
						}
					}
					//If  top filling is - No
					else
					{
						bagThread=calculateThreadForBag(enquiryTemplateBean.getSurfaceLength(), enquiryTemplateBean.getSurfaceWidth(), enquiryTemplateBean.getSurfaceHeight(), enquiryTemplateBean.isTopFilling(), enquiryTemplateBean.getTopFillingType().getTopFillingTypeAbbr() , inletLength,inletDiameter, enquiryTemplateBean.isBottomDischarge(), outletLength, outletDimeter);
					}
					
					//
					bagFillerCord = calculateFillerCord(bagThread, enquiryTemplateBean.getFabricBagSeamType().getBagseamTypeAbbr());
					bagTieForTop = tieForTop(enquiryTemplateBean);
					bagTieForBottom = tieForBottom(enquiryTemplateBean);
					bagDoc = totalDocument((enquiryTemplateBean.isDocPouch()) ? enquiryTemplateBean.getDocPouchValue() : 0);
					bagLabel = totalLabel((enquiryTemplateBean.isOtherLabels()) ? 1 : 0);
					
					//Liner tube value
					if(enquiryTemplateBean.getLinerTubeValue() <= 0)
					{
						enquiryTemplateBean.setLinerTubeValue(calculateLinerTube(enquiryTemplateBean.getSurfaceLength(), enquiryTemplateBean.getSurfaceWidth()));
					}
					
					double topLen=0,bottomLen=0;
					if(enquiryTemplateBean.getLinerHeightValue() <= 0)
					{
						if(enquiryTemplateBean.isTopFilling())
						{
							if(enquiryTemplateBean.getTopFillingType().getTopFillingTypeAbbr().equalsIgnoreCase(ENQUIRY.TOP_TYPE_TOP_SKIRT)|| enquiryTemplateBean.getTopFillingType().getTopFillingTypeAbbr().equalsIgnoreCase(ENQUIRY.TOP_TYPE_TOP_SKIRT_WITH_FLAP))
							{
								topLen=enquiryTemplateBean.getTopSkirtLength();
								
							}
							else if((enquiryTemplateBean.getTopFillingType().getTopFillingTypeAbbr().equalsIgnoreCase(ENQUIRY.TOP_TYPE_TOP_SPOUT)|| enquiryTemplateBean.getTopFillingType().getTopFillingTypeAbbr().equalsIgnoreCase(ENQUIRY.TOP_TYPE_TOP_SPOUT_WITH_FLAP)))
							{
								topLen=enquiryTemplateBean.getTopSpoutLength();
							}
						}
						
						if(enquiryTemplateBean.isBottomDischarge())
						{
							bottomLen=enquiryTemplateBean.getBottomSpoutLength();
						}
						enquiryTemplateBean.setLinerHeightValue(calculateLinerHeight(enquiryTemplateBean.getSurfaceWidth(), enquiryTemplateBean.getSurfaceHeight(), topLen, bottomLen, enquiryTemplateBean.getTopFillingType().getTopFillingTypeAbbr(), enquiryTemplateBean.getLinerType().getLinerTypeAbbr()));
					}
					
					//If Liner
					if(enquiryTemplateBean.isLiner()) {
						bagLiner= calculateLiner(enquiryTemplateBean.getLinerMicronValue(), enquiryTemplateBean.getLinerTubeValue(), enquiryTemplateBean.getLinerHeightValue());
					}
					
					double bagPerimeterBend=0;
					double bagBellyBand=0;
					double bagSteevdoorStrapBand=0;
					double bagBaffelGSM=0;
					double bagLinerGlued=0;
					double bagLoopProtector=0;
					
					if(enquiryTemplateBean.isPerimeterBand())
					{
						bagPerimeterBend=calculatePerimeterBand(enquiryTemplateBean.getSurfaceLength(), enquiryTemplateBean.getSurfaceWidth(), enquiryTemplateBean.getSwl());
					}
					if(enquiryTemplateBean.isBellyBand())
					{
						bagBellyBand=calculateBellyBand(enquiryTemplateBean.getSurfaceLength(), enquiryTemplateBean.getSurfaceWidth());
					}
					if(enquiryTemplateBean.isSteevdoorStrap())
					{
						bagSteevdoorStrapBand=calculateSteevdoorStrap(enquiryTemplateBean.getSurfaceLength(), enquiryTemplateBean.getSurfaceWidth(), enquiryTemplateBean.getSwl(), enquiryTemplateBean.getProductSFtype().getSfTypeValue());
					}
					
					if(enquiryTemplateBean.isLoopProtector())
					{
						bagLoopProtector=calculateLoopProtector(enquiryTemplateBean.getLoopHeight(), enquiryTemplateBean.getLoopProtectorValue(), enquiryTemplateBean.getLoopNumber());
					}
					if(enquiryTemplateBean.isBaffle())
					{
						bagBaffelGSM=calculateBaffleWeight(enquiryTemplateBean.getSurfaceLength(), enquiryTemplateBean.getSurfaceWidth(), enquiryTemplateBean.getSurfaceHeight(), enquiryTemplateBean.getBaffleValue());
					}
					
					if(enquiryTemplateBean.isLiner())
					{
						if(enquiryTemplateBean.isLinerGlued())
						{
							bagLinerGlued=100;
						}
					}
					
					enquiryTemplateBean.setTotalBagWeight(0.0);
					Double xyz = (BAG_SIDE1 + BAG_SIDE2 + BAG_BASE + BAG_CROSS_CORNER_LOOP + bagInletAttachment+bagOutletAttachment+bagThread+bagFillerCord+bagTieForTop+bagTieForBottom+bagDoc+bagLabel+bagLiner+bagPerimeterBend+ bagBellyBand + bagSteevdoorStrapBand +bagBaffelGSM + bagLinerGlued+ bagLoopProtector)/1000;
					System.out.println("total bag weight xyz : "+xyz);
			}else {
				
			}
			
		return enquiryTemplateBean;
	}

	//Calculate Liner
	public Double calculateLiner(Double linerMicronValue, Double linerTubeValue, Double linerHeightValue) {
		double liner = linerTubeValue * linerHeightValue * 2 * (linerMicronValue *  0.923)/10000;
		return liner;
	}

	//Calculate Liner Height
	private Double calculateLinerHeight(Double surfaceWidth, Double surfaceHeight, double topLen, double bottomLen,
			String topFillingTypeAbbr, String linerTypeAbbr) {
		Double LinerHeight=0.0;
		if(topFillingTypeAbbr.equalsIgnoreCase(ENQUIRY.TOP_TYPE_TOP_SKIRT))
		{
			surfaceWidth = surfaceWidth/2;
		}
		LinerHeight= surfaceWidth + surfaceHeight +  (topLen + 5 ) + (bottomLen + 5 );
		
		if(linerTypeAbbr.equalsIgnoreCase(ENQUIRY.LINER_TYPE_LOOSELY_FIT))
		{
			LinerHeight=LinerHeight+20;
		}
		return LinerHeight;
	}


	//Calculate Bag Cost
	private EnquiryTemplateBean calculateBagCost(EnquiryTemplateBean enquiryTemplateBean) {
		
		
		return enquiryTemplateBean;
	}
	
	
	/**
	 * 
	 * Supporting methods
	 * 
	 */
	
	//1. Calculate bag side and base
	private Double calculateBagSideAndBase(EnquiryTemplateBean enquiryTemplateBean, final String SIDE_TYPE) {
		//a. for internal surface
		if(enquiryTemplateBean.getProductSurfaceType().getSurfaceTypeAbbr().equalsIgnoreCase(ENQUIRY.SURFACE_TYPE_INTERNAL)){
			switch (SIDE_TYPE) {
			case "SIDE1":
					return ( 2 * (enquiryTemplateBean.getSurfaceLength() + 12) * (enquiryTemplateBean.getSurfaceHeight() + 12) * enquiryTemplateBean.getFabricGSMValue()  )  / 10000 ;				
				
			case "SIDE2":
				return ( 2 * (enquiryTemplateBean.getSurfaceWidth() + 12) * (enquiryTemplateBean.getSurfaceHeight() + 12) * enquiryTemplateBean.getFabricGSMValue()  )  / 10000 ;			
			
			case "BASE":
				return ( 2 * (enquiryTemplateBean.getSurfaceLength() + 12) * (enquiryTemplateBean.getSurfaceWidth() + 12) * enquiryTemplateBean.getFabricGSMValue()  )  / 10000 ;			

			default:
				return 0.0;
				
			}		
		//b. for external surface
		}else if(enquiryTemplateBean.getProductSurfaceType().getSurfaceTypeAbbr().equalsIgnoreCase(ENQUIRY.SURFACE_TYPE_EXTERNAL)){
			switch (SIDE_TYPE) {
			case "SIDE1":
					return ( 2 * (enquiryTemplateBean.getSurfaceLength() + 8) * (enquiryTemplateBean.getSurfaceHeight() + 8) * enquiryTemplateBean.getFabricGSMValue()  )  / 10000 ;
				
			case "SIDE2":
				return ( 2 * (enquiryTemplateBean.getSurfaceWidth() + 8) * (enquiryTemplateBean.getSurfaceHeight() + 8) * enquiryTemplateBean.getFabricGSMValue()  )  / 10000 ;			
			
			case "BASE":
				return ( 2 * (enquiryTemplateBean.getSurfaceLength() + 8) * (enquiryTemplateBean.getSurfaceWidth() + 8) * enquiryTemplateBean.getFabricGSMValue()  )  / 10000 ;			

			default:
				return 0.0;
				
			}
		}else {
			return 0.0;
		}
	}
	
	//2. calculate Bag Cross Corner Loop
	private Double calculateBagCrossCornerLoop(EnquiryTemplateBean enquiryTemplateBean) {
		Double loopCutLength= calLoopCutLength(enquiryTemplateBean.getSurfaceHeight(), enquiryTemplateBean.getLoopHeight(), enquiryTemplateBean.getSwl(), enquiryTemplateBean.getProductSFtype().getSfTypeValue());
		
		if(enquiryTemplateBean.getLoopType().getLoopTypeAbr().equalsIgnoreCase(ENQUIRY.LOOP_TYPE_CORNER_LOOP)){
			Double loopGram = ((enquiryTemplateBean.getSwl() * enquiryTemplateBean.getProductSFtype().getSfTypeValue())/ enquiryTemplateBean.getLoopNumber())/ 40;
			Double cornerLoop = (4 * (loopGram * loopCutLength))/ 100;
			return cornerLoop;
		}else {
			double crossLoopGram = 0;
			if(enquiryTemplateBean.getProductSFtype().getSfTypeValue() > 5)
			{
				crossLoopGram=setCrossLoopGramFromRange(enquiryTemplateBean.getSwl());
			}
			else
			{
				crossLoopGram=setCrossLoopGramForSFFiveFromRange(enquiryTemplateBean.getSwl());
			}			
			Double crossCornerLoop = (Double) ((4 * (crossLoopGram * loopCutLength))/ 100);
			return crossCornerLoop;
		}
	}
		//2.a. Loop cut length
		private Double calLoopCutLength(Double surfaceHeight, Double loopHeight, Double swl, int sfTypeValue) {
			Double loopCutLength=0.0,loopShortLen=0.0,loopDoubleLen=0.0;
			if(sfTypeValue > 5)
			{
				loopShortLen= setLoopShortLengthFromRange(swl) ;
				loopDoubleLen = setLoopDoubleLengthFromRange(swl, surfaceHeight);
			}
			else
			{
				loopShortLen= setLoopShortLengthForSFFiveFromRange(swl) ;
				loopDoubleLen = setLoopDoubleLengthForSfFiveFromRange(swl, surfaceHeight);
			}
			//loopCutLength=(2 * loopFreeHeight) + setLoopShortLengthFromRange(bagSWL) + setLoopDoubleLengthFromRange(bagSWL, bagHeight);
			loopCutLength=(2 * loopHeight) + loopShortLen + loopDoubleLen;
			return loopCutLength;
		}
			
			//2.a.1
			private Double setLoopShortLengthForSFFiveFromRange(Double bag_SWL){
				if (bag_SWL <= 499) {
		        	return 25.0;
		        } else if (bag_SWL >= 500 && bag_SWL <= 600) {
		        	return 35.0;
		        } else if (bag_SWL >= 601 && bag_SWL <= 850) {
		        	return 40.0;
		        } else if (bag_SWL >= 851 && bag_SWL <= 1150) {
		        	return 45.0;
		        } else if (bag_SWL >= 1151 && bag_SWL <= 1350) {
		        	return 50.0;
		        } else if (bag_SWL >= 1351 && bag_SWL <= 1650) {
		        	return 55.0;
		        }else if (bag_SWL >= 1651 && bag_SWL <= 1850) {
		        	return 60.0;
		        }else if (bag_SWL > 1850) {
		        	return 65.0;
		        }else{
		        	return 0.0;
		        }
			}
			//2.a.2
			private Double setLoopShortLengthFromRange(Double bag_SWL){
				if (bag_SWL <= 499) {
		        	return 30.0;
		        } else if (bag_SWL >= 500 && bag_SWL <= 600) {
		        	return 40.0;
		        } else if (bag_SWL >= 601 && bag_SWL <= 850) {
		        	return 45.0;
		        } else if (bag_SWL >= 851 && bag_SWL <= 1150) {
		        	return 50.0;
		        } else if (bag_SWL >= 1151 && bag_SWL <= 1350) {
		        	return 55.0;
		        } else if (bag_SWL >= 1351 && bag_SWL <= 1650) {
		        	return 60.0;
		        }else if (bag_SWL >= 1651 && bag_SWL <= 1850) {
		        	return 65.0;
		        }else if (bag_SWL > 1850) {
		        	return 70.0;
		        }else{
		        	return 0.0;
		        }
			}
			//2.a.3
			private Double setLoopDoubleLengthForSfFiveFromRange(Double bag_SWL, Double bag_height){
				if (bag_SWL <= 499) {
			    	return (bag_height/100 ) * 55 ;
		        } else if (bag_SWL >= 500 && bag_SWL <= 600) {
		        	return (bag_height/100 ) * 55;
		        } else if (bag_SWL >= 601 && bag_SWL <= 850) {
		        	return (bag_height/100 ) * 60;
		        } else if (bag_SWL >= 851 && bag_SWL <= 1150) {
		        	return (bag_height/100 ) * 65;
		        } else if (bag_SWL >= 1151 && bag_SWL <= 1350) {
		        	return (bag_height/100 ) * 70;
		        } else if (bag_SWL >= 1351 && bag_SWL <= 1650) {
		        	return (bag_height/100 ) * 75;
		        }else if (bag_SWL >= 1651 && bag_SWL <= 1850) {
		        	return (bag_height/100 ) * 80;
		        }else if (bag_SWL > 1850) {
		        	return (bag_height/100 ) * 100;
		        }else{
		        	return 0.0;
		        }
			}
			//2.a.4
			private Double setLoopDoubleLengthFromRange(Double bag_SWL, Double bag_height){
				if (bag_SWL <= 499) {
			    	return (bag_height/100 ) * 60 ;
		        } else if (bag_SWL >= 500 && bag_SWL <= 600) {
		        	return (bag_height/100 ) * 60;
		        } else if (bag_SWL >= 601 && bag_SWL <= 850) {
		        	return (bag_height/100 ) * 65;
		        } else if (bag_SWL >= 851 && bag_SWL <= 1150) {
		        	return (bag_height/100 ) * 70;
		        } else if (bag_SWL >= 1151 && bag_SWL <= 1350) {
		        	return (bag_height/100 ) * 75;
		        } else if (bag_SWL >= 1351 && bag_SWL <= 1650) {
		        	return (bag_height/100 ) * 80;
		        }else if (bag_SWL >= 1651 && bag_SWL <= 1850) {
		        	return (bag_height/100 ) * 100;
		        }else if (bag_SWL > 1850) {
		        	return (bag_height/100 ) * 100;
		        }else{
		        	return 0.0;
		        }
			}
			//2.a.5
			private Double setCrossLoopGramForSFFiveFromRange(double bagSWL) {
				// TODO Auto-generated method stub
				if (bagSWL <= 499) {
		         	return 25.0;
		         } else if (bagSWL >= 500 && bagSWL <= 600) {
		        	 return 30.0;
		         } else if (bagSWL >= 601 && bagSWL <= 850) {
		        	 return 32.0;
		         } else if (bagSWL >= 851 && bagSWL <= 1150) {
		        	 return 42.0;
		         } else if (bagSWL >= 1151 && bagSWL <= 1350) {
		        	 return 45.0;
		         } else if (bagSWL >= 1351 && bagSWL <= 1650) {
		        	 return 50.0;
		         }else if (bagSWL >= 1651 && bagSWL <= 1850) {
		        	 return  60.0;
		         }else if (bagSWL > 1850) {
		        	 return 65.0;
		         }else{
		        	 return 0.0;
		         }
			}
			//2.a.6
			private Double setCrossLoopGramFromRange(double bagSWL) {
				// TODO Auto-generated method stub
				if (bagSWL <= 499) {
		         	return 42.0;
		         } else if (bagSWL >= 500 && bagSWL <= 600) {
		        	 return 42.0;
		         } else if (bagSWL >= 601 && bagSWL <= 850) {
		        	 return 45.0;
		         } else if (bagSWL >= 851 && bagSWL <= 1150) {
		        	 return 50.0;
		         } else if (bagSWL >= 1151 && bagSWL <= 1350) {
		        	 return 55.0;
		         } else if (bagSWL >= 1351 && bagSWL <= 1650) {
		        	 return 60.0;
		         }else if (bagSWL >= 1651 && bagSWL <= 1850) {
		        	 return  65.0;
		         }else if (bagSWL > 1850) {
		        	 return 65.0;
		         }else{
		        	 return 0.0;
		         }
			}
			
	//3. Calculate Attachment Inlet 
		//3.a. Top Skirt
		private Double calculateAttachmentInletTopSkirt(Double surfaceLength, Double surfaceWidth, Double topSkirtLength, Double topSkirtGSM) {
			Double topSkirtCutLength = calculateTopSkirtCutLength(surfaceLength, surfaceWidth);
			Double topSkirt = ((topSkirtLength + 5)* topSkirtCutLength * topSkirtGSM)/10000;
			return topSkirt;
		}
			//3.a.1 Calculate Top Skirt Cut Length
			private Double calculateTopSkirtCutLength(Double surfaceLength, Double surfaceWidth) {
				Double topSkirtCutLength = ((surfaceLength + surfaceWidth) * 2) + 12;
				return topSkirtCutLength;
			}
			
		//3.b Top Spout
		private Double calculateAttachmentInletTopSpout(Double surfaceLength, Double surfaceWidth, Double topSpoutLength,
				Double topSpoutGSM, Double topSpoutDiameter) {
			Double topSpoutCutLength = calculateTopSpoutCutLength(topSpoutDiameter);
			Double topSpout = ((topSpoutLength + 5) * topSpoutCutLength * topSpoutGSM)/10000;
			return topSpout;
		}
			//3.b.1 Calculate Top Spout Cut Length
			private Double calculateTopSpoutCutLength(double topSpoutDiameter){
				Double TopSpoutCutLength = (Double) ((topSpoutDiameter * 3.14) + 12);
				return TopSpoutCutLength;
			}
			//3.b.2
			private Double calculateAttachmentTopFabric(Double surfaceLength, Double surfaceWidth, Double topSpoutGSM) {
				Double topFabric = ((surfaceLength + 12) * (surfaceWidth + 12) * topSpoutGSM)/10000;
				return topFabric;
			}
			
		//3.c Top Flap
		private Double calculateAttachmentInletTopFlap(Double surfaceLength, Double surfaceWidth, Double topFlapGSM) {
			Double topFlap = ((surfaceLength + 12) * (surfaceWidth + 12) * topFlapGSM)/10000;
			return topFlap;
		}
		
		//3.d. Conical Top
		private Double calculateAttachmentInletConicalTop(Double surfaceLength, Double surfaceWidth){
			//((BAG WIDTH +12)+10 + (Bag length +12)+10 )/10000
			Double conicalTop = (((surfaceWidth + 12)+10)+((surfaceLength+12)+10))/10000;
			return conicalTop;
		}
		
		//3.e. Platted Top
		private Double calculateAttachmentInletPlattedTop(Double surfaceLength, Double surfaceWidth) {
			Double plattedTop =(((surfaceWidth + 12)+18)+(surfaceLength + 12)+18)/10000;
			return plattedTop;
		}
		
	//4. Calculate Attachment Outlet 
		//4.a calculate Attachment Outlet Bottom Spout
		private Double calculateAttachmentOutletBottomSpout(Double bottomSpoutDiameter, Double bottomSpoutGSM,
				Double bottomSpoutLength, Double surfaceLength, Double surfaceWidth, Double surfaceHeight,
				String bottomDischargeTypeAbbr) {
			Double bottomSpoutCutLength = calculateBottomSpoutCutLength(bottomSpoutDiameter);
			Double bottomAttach=0.0;
			
			//a. bottom spout standard
			if(bottomDischargeTypeAbbr.equalsIgnoreCase(ENQUIRY.BOTTOM_TYPE_BOTTOM_SPOUT_STANDARD)){
				bottomAttach = ((bottomSpoutLength + 5) * bottomSpoutCutLength * bottomSpoutGSM)/10000;
				
			//b. bottom spout petal
			}else if(bottomDischargeTypeAbbr.equalsIgnoreCase(ENQUIRY.BOTTOM_TYPE_BOTTOM_SPOUT_PETAL)){
				Double bottomSpoutStandard = ((bottomSpoutLength + 5) * bottomSpoutCutLength * bottomSpoutGSM)/10000;
				bottomAttach = bottomSpoutStandard + 25;
				
			//c. discharge spout, empty spout with iris, pyjama, bonnet closure
			}else if(bottomDischargeTypeAbbr.equalsIgnoreCase(ENQUIRY.BOTTOM_TYPE_DISCHARGE_SPOUT) 
					|| bottomDischargeTypeAbbr.equalsIgnoreCase(ENQUIRY.BOTTOM_TYPE_EMPTY_SPOUT_WITH_IRIS) 
					|| bottomDischargeTypeAbbr.equalsIgnoreCase(ENQUIRY.BOTTOM_TYPE_PYJAMA) 
					|| bottomDischargeTypeAbbr.equalsIgnoreCase(ENQUIRY.BOTTOM_TYPE_BONNET_CLOSURE)){
				Double bottomSpoutCal = (bottomSpoutDiameter/2) + (bottomSpoutLength + 17);
				bottomAttach = (bottomSpoutCal * bottomSpoutCutLength * bottomSpoutGSM)/10000;
			
			//d. conical bottom
			}else if(bottomDischargeTypeAbbr.equalsIgnoreCase(ENQUIRY.BOTTOM_TYPE_CONICAL_BOTTOM)){
				bottomAttach = (surfaceHeight+50+12)/10000;
			}
			
			//e. full discharge
			else if(bottomDischargeTypeAbbr.equalsIgnoreCase(ENQUIRY.BOTTOM_TYPE_FULL_DISCHARGE_BOTTOM)){
				Double bottomSkirtLength = 0.0, bottomSkirtCutLength = 0.0, bottomFabric = 0.0;
				bottomSkirtCutLength = ((surfaceLength + surfaceWidth ) * 2) + 12;
				bottomSkirtLength= (bottomSpoutLength + 5 ) * bottomSkirtCutLength * bottomSpoutGSM ;
				bottomFabric = 2*(surfaceLength + 12 )*(surfaceWidth+12) * bottomSpoutGSM;
				bottomAttach = (bottomSkirtLength + bottomFabric)/10000;
			}
			return bottomAttach;
		}
			//4.a.1 Calculate Bottom Spout Cut Length
			private Double calculateBottomSpoutCutLength(Double bottomSpoutDiameter) {
				return (bottomSpoutDiameter * 3.14) + 12;
			}
			
		//4.b Calculate Thread For Bag
		private Double calculateThreadForBag(Double surfaceLength, Double surfaceWidth, Double surfaceHeight,
				boolean topFilling, String topFillingTypeAbbr, Double inletLength, Double inletDiameter,
				boolean bottomDischarge, Double outletLength, Double outletDimeter) {
			
			Double thread=0.0, topThread=0.0, bottomThread=0.0, topCutLength=0.0;
			if(topFilling)
			{
				if(topFillingTypeAbbr.equalsIgnoreCase(ENQUIRY.TOP_TYPE_TOP_SKIRT) || topFillingTypeAbbr.equalsIgnoreCase(ENQUIRY.TOP_TYPE_TOP_SKIRT_WITH_FLAP))
				{
					topCutLength= 0.0;
				}
				else if(topFillingTypeAbbr.equalsIgnoreCase(ENQUIRY.TOP_TYPE_TOP_SPOUT) || topFillingTypeAbbr.equalsIgnoreCase(ENQUIRY.TOP_TYPE_TOP_SPOUT_WITH_FLAP))
				{
					topCutLength= calculateTopSpoutCutLength(inletDiameter);
				}
				topThread=(inletLength + 5) + topCutLength;
			}
			if(bottomDischarge)
			{
				bottomThread=(outletLength + 5) + calculateTopSpoutCutLength(outletDimeter);
			}
			thread=(((surfaceLength * 4) + (surfaceWidth * 4) + (surfaceHeight * 4) + topThread + bottomThread)/100)*5; 
			return thread;
		}
		
		//4.c Calculate Filler Cord
		private Double calculateFillerCord(Double bagThread, String bagseamTypeAbbr) {
			double fillerCord=0;
			if(bagseamTypeAbbr.equalsIgnoreCase(ENQUIRY.BAG_SEAM_TYPE_SINGLE_FILLER_CORD))
			{
				fillerCord=bagThread*1;
			}
			else if(bagseamTypeAbbr.equalsIgnoreCase(ENQUIRY.BAG_SEAM_TYPE_DOUBLE_FILLER_CORD))
			{
				fillerCord=bagThread*2;
			}
			else if(bagseamTypeAbbr.equalsIgnoreCase(ENQUIRY.BAG_SEAM_TYPE_TRIPLE_FILLER_CORD))
			{
				fillerCord=bagThread*3;
			}
			return fillerCord;
		}
		
	//5. 
		//5.a
		private int totalLabel(int numberofLabels) {
			int label = numberofLabels *5;
			return label;
		}

		//5.b
		private int totalDocument(Double docPouchValue) {
			int document = (int) (docPouchValue * 10);
			return document;
		}

		//5.c
		private int tieForBottom(EnquiryTemplateBean enquiryTemplateBean) {
			int totalBottomTie = (int) (enquiryTemplateBean.getBottomStandardTieNumber() + 
								 enquiryTemplateBean.getBottomRopeTieNumber() + 
								 enquiryTemplateBean.getBottomVelcroTieNumber() + 
								 enquiryTemplateBean.getBottomBlockNumber());
			
			if(enquiryTemplateBean.getBottomDischargeType().getBottomDischargeTypeAbbr().equalsIgnoreCase(ENQUIRY.BOTTOM_TYPE_BOTTOM_SPOUT_PETAL) || enquiryTemplateBean.getBottomDischargeType().getBottomDischargeTypeAbbr().equalsIgnoreCase(ENQUIRY.BOTTOM_TYPE_EMPTY_SPOUT_WITH_IRIS))
			{
				int tieForBottom = totalBottomTie*20;
				return tieForBottom;
			}
			else 
			{
				int tieForBottom = totalBottomTie*10;
				return tieForBottom;
			}
		}

		//5.d
		private int tieForTop(EnquiryTemplateBean enquiryTemplateBean) {
			int totalTopTie = (int) (enquiryTemplateBean.getTopStandardTieNumber() + 
							  enquiryTemplateBean.getTopRopeTieNumber() + 
							  enquiryTemplateBean.getTopVelcroTieNumber() + 
							  enquiryTemplateBean.getTopBlockNumber());
			
			return 10*totalTopTie;
		}
		
		//
		private Double calculateLinerTube(Double surfaceLength, Double surfaceWidth) {
			Double LinerTube=0.0;
			LinerTube= surfaceLength + surfaceWidth  + 5;
			return LinerTube;
		}
		
	//calculate Perimeter Band
	private double calculatePerimeterBand(Double surfaceLength, Double surfaceWidth, Double swl) {
		double perimeterBand=0,gramage=23;
		 if(swl< 1250)
		 {
			 gramage=15;
		 }
		 perimeterBand = (((surfaceWidth*2) + (surfaceLength*2) +25)/100)*gramage;
		 return perimeterBand;
	}
	
	//Calculate Belly Band
	private double calculateBellyBand(Double surfaceLength, Double surfaceWidth) {
		double bellyBand=0;
		bellyBand= (((surfaceWidth * 2)+ (surfaceLength * 2)+ 25)/100)* 25;
		return bellyBand;
	}
	
	//calculate Steevdoor Strap
	private double calculateSteevdoorStrap(Double surfaceLength, Double surfaceWidth, Double swl, int sfTypeValue) {
		double steevdooeStrap=0,gramage=0;
		 if(sfTypeValue > 5)
			{
			 gramage=setCrossLoopGramFromRange(swl);
			}
			else
			{
				gramage=setCrossLoopGramForSFFiveFromRange(swl);
			}
		 steevdooeStrap= (((surfaceLength+ surfaceWidth + 25)*2)/100)* gramage;
		 return steevdooeStrap;
	}
	
	//calculate Loop Protector
	private double calculateLoopProtector(Double loopHeight, Double loopProtectorValue, Double loopNumber) {
		double loopProtector=0, protectorCutLength=0;
		protectorCutLength= loopHeight* 2* loopNumber;
		loopProtector= (17 * loopNumber)* protectorCutLength * loopProtectorValue /10000;
		return loopProtector;
	}
	
	//calculate Baffle Weight
	private double calculateBaffleWeight(Double surfaceLength, Double surfaceWidth, Double surfaceHeight,
			Double baffleValue) {
		double baffleWidth = ((((surfaceLength + surfaceWidth)/2) / 3) * 1.41 ) + 7;
		double baffleWeight = (4 * (baffleWidth * (surfaceHeight - 16) * baffleValue))/10000;
		return baffleWeight;
	}
}
