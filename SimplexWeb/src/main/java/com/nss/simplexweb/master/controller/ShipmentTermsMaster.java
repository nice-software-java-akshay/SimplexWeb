package com.nss.simplexweb.master.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.servlet.ModelAndView;

import com.nss.simplexweb.SessionUtility;
import com.nss.simplexweb.enums.SHIPMENT_TERMS;
import com.nss.simplexweb.enums.USER;
import com.nss.simplexweb.shipmenterm.model.ShipmentTerms;
import com.nss.simplexweb.shipmenterm.service.ShipmentTermsService;
import com.nss.simplexweb.user.model.User;

@Controller
@RequestMapping(value={"/master/shipmentTermsMaster"})
public class ShipmentTermsMaster {
	
	@Autowired
	private ShipmentTermsService shipmentTermsService;
	

	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView getShipmentTermsList(@SessionAttribute("USER") User user) {
		ModelAndView mav = new ModelAndView();
		mav
			.addObject(USER.USER.name(), new User())
			.addObject(SHIPMENT_TERMS.SHIPMENT_TERMS.name(), new ShipmentTerms())
			.addObject(SHIPMENT_TERMS.SHIPMENT_TERMS_LIST.name(), shipmentTermsService.getActiveShipmentTermsList())
			.setViewName("master/shipment-terms/shipment_terms_master");
		
		return mav;
	}
	
	@RequestMapping(value={"/saveNewShipmentTerm"}, method = RequestMethod.POST)
	public @ResponseBody String saveNewShipmentTerm(ShipmentTerms shipmentTerms, HttpServletRequest request) {
		User user = SessionUtility.getUserFromSession(request);
		shipmentTerms.setCreatedBy(user);
		return shipmentTermsService.saveNewShipmentTerm(shipmentTerms);
	}
	
	@RequestMapping(value= {"/getShipmentTermDetailsById"}, method = RequestMethod.GET)
	public @ResponseBody ShipmentTerms getShipmentTermDetailsById(Long shipmentTermId) {
		return shipmentTermsService.getShipmentTermDetailsById(shipmentTermId);
	}
	
	@RequestMapping(value= {"/deleteShipmentTermDetailsById"}, method = RequestMethod.GET)
	public @ResponseBody ShipmentTerms deleteShipmentTermDetailsById(Long shipmentTermId) {
		return shipmentTermsService.deleteShipmentTermDetailsById(shipmentTermId);
	}
}
