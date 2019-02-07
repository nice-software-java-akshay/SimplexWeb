package com.nss.simplexweb.enquiry.template.service.calculations.levelone;

import org.springframework.stereotype.Service;

import com.nss.simplexweb.enquiry.template.model.EnquiryTemplateBean;
import com.nss.simplexweb.enums.ENQUIRY;

@Service("enquiryGlobalTemplateLevelOneCalculationsService")
public class EnquiryGlobalTemplateLevelOneCalculationsService {

	public EnquiryTemplateBean performLevelOneCalculations(EnquiryTemplateBean enquiryTemplateBean) {
		//1.Perform Product Calculations
		//Product
			if(enquiryTemplateBean.getSurfaceLength() == null){
				enquiryTemplateBean.setSurfaceLength(0.0);
			}
			if(enquiryTemplateBean.getSurfaceWidth() == null){
				enquiryTemplateBean.setSurfaceWidth(0.0);
			}
			if(enquiryTemplateBean.getSurfaceHeight() == null){
				enquiryTemplateBean.setSurfaceHeight(0.0);
			}
		//Top Filling
			if(enquiryTemplateBean.getTopSkirtLength() == null){
				enquiryTemplateBean.setTopSkirtLength(0.0);
			}
			if(enquiryTemplateBean.getTopSpoutLength() == null){
				enquiryTemplateBean.setTopSpoutLength(0.0);
			}
			if(enquiryTemplateBean.getTopSpoutDiameter() == null){
				enquiryTemplateBean.setTopSpoutDiameter(0.0);
			}
		//Bottom discharge
			if(enquiryTemplateBean.getBottomSpoutDiameter() == null){
				enquiryTemplateBean.setBottomSpoutDiameter(0.0);
			}
			if(enquiryTemplateBean.getBottomSpoutLength() == null){
				enquiryTemplateBean.setBottomSpoutLength(0.0);
			}
		//Loop
			if(enquiryTemplateBean.getLoopHeight() == null){
				enquiryTemplateBean.setLoopHeight(0.0);
			}
		//Other:Liner
			if(enquiryTemplateBean.getLinerTubeValue() == null){
				enquiryTemplateBean.setLinerTubeValue(0.0);
			}
			if(enquiryTemplateBean.getLinerHeightValue() == null){
				enquiryTemplateBean.setLinerHeightValue(0.0);
			}	

		if(enquiryTemplateBean.getProductUnitTypeLength().getUnitTypeAbbr().equalsIgnoreCase(ENQUIRY.UNIT_INCHES))
		{
		//Product
			if(enquiryTemplateBean.getSurfaceWidth()!=null)
			{
				enquiryTemplateBean.setSurfaceWidth(enquiryTemplateBean.getSurfaceWidth() * 2.54);
			}
			if(enquiryTemplateBean.getSurfaceLength()!=null)
			{
				enquiryTemplateBean.setSurfaceLength(enquiryTemplateBean.getSurfaceLength() * 2.54);
			}
			if(enquiryTemplateBean.getSurfaceHeight()!=null)
			{
				enquiryTemplateBean.setSurfaceHeight(enquiryTemplateBean.getSurfaceHeight() * 2.54);
			}
		//Top
			if(enquiryTemplateBean.getTopSkirtLength()!=null)
			{
				enquiryTemplateBean.setTopSkirtLength(enquiryTemplateBean.getTopSkirtLength() * 2.54);
			}
			if(enquiryTemplateBean.getTopSpoutLength()!=null)
			{
				enquiryTemplateBean.setTopSpoutLength(enquiryTemplateBean.getTopSpoutLength() * 2.54);
			}
			if(enquiryTemplateBean.getTopSpoutDiameter()!=null)
			{
				enquiryTemplateBean.setTopSpoutDiameter(enquiryTemplateBean.getTopSpoutDiameter() * 2.54);
			}
		//Bottom
			if(enquiryTemplateBean.getBottomSpoutDiameter()!=null)
			{
				enquiryTemplateBean.setBottomSpoutDiameter(enquiryTemplateBean.getBottomSpoutDiameter() * 2.54);
			}
			if(enquiryTemplateBean.getBottomSpoutLength()!=null)
			{
				enquiryTemplateBean.setBottomSpoutLength(enquiryTemplateBean.getBottomSpoutLength() * 2.54);
			}
		//Loop
			if(enquiryTemplateBean.getLoopHeight()!=null)
			{
				enquiryTemplateBean.setLoopHeight(enquiryTemplateBean.getLoopHeight() * 2.54);
			}
		//Other:Liner
			if(enquiryTemplateBean.getLinerTubeValue()!=null)
			{
				enquiryTemplateBean.setLinerTubeValue(enquiryTemplateBean.getLinerTubeValue() * 2.54);
			}
			if(enquiryTemplateBean.getLinerHeightValue()!=null)
			{
				enquiryTemplateBean.setLinerHeightValue(enquiryTemplateBean.getLinerHeightValue() * 2.54);
			}
		}
		else if(enquiryTemplateBean.getProductUnitTypeLength().getUnitTypeAbbr().equalsIgnoreCase(ENQUIRY.UNIT_MILLIMETER))
		{
		//Product
			if(enquiryTemplateBean.getSurfaceWidth()!=null)
			{
				enquiryTemplateBean.setSurfaceWidth(enquiryTemplateBean.getSurfaceWidth() * 0.1);
			}
			if(enquiryTemplateBean.getSurfaceLength()!=null)
			{
				enquiryTemplateBean.setSurfaceLength(enquiryTemplateBean.getSurfaceLength() * 0.1);
			}
			if(enquiryTemplateBean.getSurfaceHeight()!=null)
			{
				enquiryTemplateBean.setSurfaceHeight(enquiryTemplateBean.getSurfaceHeight() * 0.1);
			}
		//Top
			if(enquiryTemplateBean.getTopSkirtLength()!=null)
			{
				enquiryTemplateBean.setTopSkirtLength(enquiryTemplateBean.getTopSkirtLength() * 0.1);
			}
			if(enquiryTemplateBean.getTopSpoutLength()!=null)
			{
				enquiryTemplateBean.setTopSpoutLength(enquiryTemplateBean.getTopSpoutLength() * 0.1);
			}
			if(enquiryTemplateBean.getTopSpoutDiameter()!=null)
			{
				enquiryTemplateBean.setTopSpoutDiameter(enquiryTemplateBean.getTopSpoutDiameter() * 0.1);
			}
		//Bottom
			if(enquiryTemplateBean.getBottomSpoutDiameter()!=null)
			{
				enquiryTemplateBean.setBottomSpoutDiameter(enquiryTemplateBean.getBottomSpoutDiameter() * 0.1);
			}
			if(enquiryTemplateBean.getBottomSpoutLength()!=null)
			{
				enquiryTemplateBean.setBottomSpoutLength(enquiryTemplateBean.getBottomSpoutLength() * 0.1);
			}
		//Loop
			if(enquiryTemplateBean.getLoopHeight()!=null)
			{
				enquiryTemplateBean.setLoopHeight(enquiryTemplateBean.getLoopHeight() * 0.1);
			}
		//Other:Liner
			if(enquiryTemplateBean.getLinerTubeValue()!=null)
			{
				enquiryTemplateBean.setLinerTubeValue(enquiryTemplateBean.getLinerTubeValue() * 0.1);
			}
			if(enquiryTemplateBean.getLinerHeightValue()!=null)
			{
				enquiryTemplateBean.setLinerHeightValue(enquiryTemplateBean.getLinerHeightValue() * 0.1);
			}
		}
		return enquiryTemplateBean;
	}
}
