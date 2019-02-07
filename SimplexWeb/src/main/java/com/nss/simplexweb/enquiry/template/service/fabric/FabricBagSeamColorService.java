package com.nss.simplexweb.enquiry.template.service.fabric;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nss.simplexweb.enquiry.template.model.fabric.FabricBagSeamColor;
import com.nss.simplexweb.enquiry.template.repository.fabric.FabricBagSeamColorRepository;

@Service("fabricBagSeamColorService")
public class FabricBagSeamColorService {

	@Autowired
	private FabricBagSeamColorRepository fabricBagSeamColorRepository;
	
	public FabricBagSeamColorService(FabricBagSeamColorRepository fabricBagSeamColorRepository) {
		// TODO Auto-generated constructor stub
		this.fabricBagSeamColorRepository = fabricBagSeamColorRepository;
	}
	
	public List<FabricBagSeamColor> getFabricBagSeamColorList() {
		// TODO Auto-generated method stub
		return fabricBagSeamColorRepository.findAll();
	}
}
