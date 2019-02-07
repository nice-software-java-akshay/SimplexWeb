package com.nss.simplexweb.enquiry.template.service;

import java.util.ArrayList;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nss.simplexweb.enquiry.template.model.EnquiryTemplateBean;
import com.nss.simplexweb.enquiry.template.repository.EnquiryTemplateRepository;
import com.nss.simplexweb.enquiry.template.service.calculations.levelone.EnquiryGlobalTemplateLevelOneBottomCalculationsService;
import com.nss.simplexweb.enquiry.template.service.calculations.levelone.EnquiryGlobalTemplateLevelOneCalculationsService;
import com.nss.simplexweb.enquiry.template.service.calculations.levelone.EnquiryGlobalTemplateLevelOneFabricCalculationsService;
import com.nss.simplexweb.enquiry.template.service.calculations.levelone.EnquiryGlobalTemplateLevelOneLoopCalculationsService;
import com.nss.simplexweb.enquiry.template.service.calculations.levelone.EnquiryGlobalTemplateLevelOneOtherCalculationsService;
import com.nss.simplexweb.enquiry.template.service.calculations.levelone.EnquiryGlobalTemplateLevelOneProductCalculationsService;
import com.nss.simplexweb.enquiry.template.service.calculations.levelone.EnquiryGlobalTemplateLevelOneTopCalculationsService;
import com.nss.simplexweb.po.repository.POStatusRepository;
import com.nss.simplexweb.user.model.User;
import com.nss.simplexweb.utility.mail.EmailController;
import com.nss.simplexweb.utility.mail.MailBean;
import com.nss.simplexweb.utility.pdf.PdfGeneratorUtilController;

@Service("enquiryTemplateService")
public class EnquiryTemplateService {
	
	@Autowired
	private EmailController emailController;
	
	@Autowired
	private PdfGeneratorUtilController pdfGeneratorUtilController;
	
	@Autowired
	private EnquiryTemplateRepository enquiryTemplateRepository;
	
	@Autowired
	private POStatusRepository pOStatusRepository;
	
	@Autowired
	private EnquiryGlobalTemplateLevelOneCalculationsService enquiryGlobalTemplateLevelOneCalculationsService;
	
	@Autowired
	private EnquiryGlobalTemplateLevelOneProductCalculationsService enquiryGlobalTemplateLevelOneProductCalculationsService;

	@Autowired
	private EnquiryGlobalTemplateLevelOneFabricCalculationsService enquiryGlobalTemplateLevelOneFabricCalculationsService;
	
	@Autowired
	private EnquiryGlobalTemplateLevelOneTopCalculationsService enquiryGlobalTemplateLevelOneTopcCalculationsService;
	
	@Autowired
	private EnquiryGlobalTemplateLevelOneBottomCalculationsService enquiryGlobalTemplateLevelOneBottomcCalculationsService;
	
	@Autowired
	private EnquiryGlobalTemplateLevelOneLoopCalculationsService enquiryGlobalTemplateLevelOneLoopCalculationsService;
	
	@Autowired
	private EnquiryGlobalTemplateLevelOneOtherCalculationsService enquiryGlobalTemplateLevelOneOtherCalculationsService;
	
	@Autowired
	private EnquiryGlobalTemplateLevelTwo4PanelCalculationsService enquiryGlobalTemplateLevelTwo4PanelCalculationsService;
	
	@Autowired
	public EnquiryTemplateService(
			EnquiryTemplateRepository enquiryTemplateRepository,
			POStatusRepository pOStatusRepository,
			EnquiryGlobalTemplateLevelOneProductCalculationsService enquiryGlobalTemplateLevelOneProductCalculationsService,
			EnquiryGlobalTemplateLevelOneFabricCalculationsService enquiryGlobalTemplateLevelOneFabricCalculationsService,
			EnquiryGlobalTemplateLevelOneTopCalculationsService enquiryGlobalTemplateLevelOneTopcCalculationsService,
			EnquiryGlobalTemplateLevelOneBottomCalculationsService enquiryGlobalTemplateLevelOneBottomcCalculationsService,
			EnquiryGlobalTemplateLevelOneLoopCalculationsService enquiryGlobalTemplateLevelOneLoopCalculationsService,
			EnquiryGlobalTemplateLevelOneOtherCalculationsService enquiryGlobalTemplateLevelOneOtherCalculationsService,
			EnquiryGlobalTemplateLevelTwo4PanelCalculationsService enquiryGlobalTemplateLevelTwo4PanelCalculationsService) {
		
		this.enquiryTemplateRepository = enquiryTemplateRepository;
		this.pOStatusRepository = pOStatusRepository;
		this.enquiryGlobalTemplateLevelOneProductCalculationsService = enquiryGlobalTemplateLevelOneProductCalculationsService;
		this.enquiryGlobalTemplateLevelOneFabricCalculationsService = enquiryGlobalTemplateLevelOneFabricCalculationsService;
		this.enquiryGlobalTemplateLevelOneTopcCalculationsService = enquiryGlobalTemplateLevelOneTopcCalculationsService;
		this.enquiryGlobalTemplateLevelOneBottomcCalculationsService = enquiryGlobalTemplateLevelOneBottomcCalculationsService;
		this.enquiryGlobalTemplateLevelOneLoopCalculationsService = enquiryGlobalTemplateLevelOneLoopCalculationsService;
		this.enquiryGlobalTemplateLevelOneOtherCalculationsService = enquiryGlobalTemplateLevelOneOtherCalculationsService;
		this.enquiryGlobalTemplateLevelTwo4PanelCalculationsService = enquiryGlobalTemplateLevelTwo4PanelCalculationsService;
	}
	
	//1. Perform All level one calculations
	private EnquiryTemplateBean performLevelOneCalculations(EnquiryTemplateBean enquiryTemplateBean) {
		//1. Level one calculations
			enquiryTemplateBean = enquiryGlobalTemplateLevelOneCalculationsService.performLevelOneCalculations(enquiryTemplateBean);
		
		//2. Product : level one calc
			enquiryTemplateBean = enquiryGlobalTemplateLevelOneProductCalculationsService.performLevelOneCalculationsForProduct(enquiryTemplateBean);
		
		//3. Fabric : level one calc
			enquiryTemplateBean = enquiryGlobalTemplateLevelOneFabricCalculationsService.performLevelOneCalculationsForFabric(enquiryTemplateBean);
			
		//4. Top : level one calc
			enquiryTemplateBean = enquiryGlobalTemplateLevelOneTopcCalculationsService.performLevelOneCalculationsForTop(enquiryTemplateBean);
			
		//5. Bottom :  level one calc
			enquiryTemplateBean = enquiryGlobalTemplateLevelOneBottomcCalculationsService.performLevelOneCalculationsForBottom(enquiryTemplateBean);
			
		//6. Loop : level one calc
			enquiryTemplateBean = enquiryGlobalTemplateLevelOneLoopCalculationsService.performLevelOneCalculationsForLoop(enquiryTemplateBean);
			
		//7. Other : level one calc 
			enquiryTemplateBean = enquiryGlobalTemplateLevelOneOtherCalculationsService.performLevelOneCalculationsForOther(enquiryTemplateBean);
			
		//Shipment calculations :)
			
		return enquiryTemplateBean;
	}
	
	//2. Perform All level two calculations
	private EnquiryTemplateBean performLevelTwoCalculations(EnquiryTemplateBean enquiryTemplateBean) {
		//All level two calculations
		enquiryGlobalTemplateLevelTwo4PanelCalculationsService.calculateBagWeight(enquiryTemplateBean);
		return enquiryTemplateBean;
	}
	
	//3. Perform all calculations
	public EnquiryTemplateBean performAllCalculations(EnquiryTemplateBean enquiryTemplateBean) {
		enquiryTemplateBean = performLevelOneCalculations(enquiryTemplateBean);
		enquiryTemplateBean = performLevelTwoCalculations(enquiryTemplateBean);
		return enquiryTemplateBean;
	}

	public EnquiryTemplateBean saveNewEnquiry(EnquiryTemplateBean enquiryBean) {
		return enquiryTemplateRepository.save(enquiryBean);
	}
	
	public EnquiryTemplateBean getEnquiryDetailsByEnquiryId(Long enquiryId) {
		return enquiryTemplateRepository.findByEnquiryId(enquiryId);
	}

	public EnquiryTemplateBean getEnquiryDetailsByEnquiryIdAndEnquiryNumber(Long enquiryId, String enquiryNumber) {
		return enquiryTemplateRepository.findByEnquiryIdAndEnquiryNumber(enquiryId, enquiryNumber);
	}

	public String emailGlobalTemplateEnquiryQuotation(Long enquiryId, String enquiryNumber, MailBean mailBean) {
		EnquiryTemplateBean enquiryTemplateBean = getEnquiryDetailsByEnquiryIdAndEnquiryNumber(enquiryId, enquiryNumber);
		return emailController.sendNewEnquiryQuotation(enquiryTemplateBean, mailBean);
	}
	
	public void downloadGlobalTemplateEnquiryQuotation(Long enquiryId, String enquiryNumber, HttpServletResponse response) {
		EnquiryTemplateBean enquiryTemplateBean = getEnquiryDetailsByEnquiryIdAndEnquiryNumber(enquiryId, enquiryNumber);
		pdfGeneratorUtilController.downloadNewEnquiryPdf(enquiryTemplateBean, response);
	}

	public ArrayList<EnquiryTemplateBean> getEnquiryHistoryForUser(User user) {
		return enquiryTemplateRepository.findByRequester(user);
	}
}
