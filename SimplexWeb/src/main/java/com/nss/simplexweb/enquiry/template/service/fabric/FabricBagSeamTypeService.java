package com.nss.simplexweb.enquiry.template.service.fabric;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nss.simplexweb.enquiry.template.model.fabric.FabricBagSeamType;
import com.nss.simplexweb.enquiry.template.repository.fabric.FabricBagSeamTypeRepository;

@Service("fabricBagSeamTypeService")
public class FabricBagSeamTypeService {

	@Autowired
	private FabricBagSeamTypeRepository fabricBagSeamTypeRepository;
	
	public FabricBagSeamTypeService(FabricBagSeamTypeRepository fabricBagSeamTypeRepository) {
		// TODO Auto-generated constructor stub
		this.fabricBagSeamTypeRepository = fabricBagSeamTypeRepository;
	}
	
	public List<FabricBagSeamType> getFabricBagSeamTypeList() {
		// TODO Auto-generated method stub
		return fabricBagSeamTypeRepository.findAll();
	}
}
