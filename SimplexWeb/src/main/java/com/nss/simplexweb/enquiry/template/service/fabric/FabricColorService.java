package com.nss.simplexweb.enquiry.template.service.fabric;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nss.simplexweb.enquiry.template.model.fabric.FabricColor;
import com.nss.simplexweb.enquiry.template.repository.fabric.FabricColorRepository;

@Service("fabricColorService")
public class FabricColorService {

	@Autowired
	private FabricColorRepository fabricColorRepository;
	
	public FabricColorService(FabricColorRepository fabricColorRepository) {
		// TODO Auto-generated constructor stub
		this.fabricColorRepository = fabricColorRepository;
	}
	
	public List<FabricColor> getFabricColorList() {
		// TODO Auto-generated method stub
		return fabricColorRepository.findAll();
	}
}
