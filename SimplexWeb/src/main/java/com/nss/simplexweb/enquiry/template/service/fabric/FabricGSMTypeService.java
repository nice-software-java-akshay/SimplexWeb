package com.nss.simplexweb.enquiry.template.service.fabric;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nss.simplexweb.enquiry.template.model.fabric.FabricGSMType;
import com.nss.simplexweb.enquiry.template.repository.fabric.FabricGSMTypeRepository;

@Service("fabricGSMTypeService")
public class FabricGSMTypeService {

	@Autowired
	private FabricGSMTypeRepository fabricGSMTypeRepository;
	
	public FabricGSMTypeService(FabricGSMTypeRepository fabricGSMTypeRepository) {
		// TODO Auto-generated constructor stub
		this.fabricGSMTypeRepository = fabricGSMTypeRepository;
	}
	
	public List<FabricGSMType> getFabricGSMTypeList() {
		// TODO Auto-generated method stub
		return fabricGSMTypeRepository.findAll();
	}
}
