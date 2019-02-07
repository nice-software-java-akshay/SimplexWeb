package com.nss.simplexweb.enquiry.template.service.loop;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nss.simplexweb.enquiry.template.model.loop.LoopMaterial;
import com.nss.simplexweb.enquiry.template.repository.loop.LoopMaterialRepository;

@Service("loopMaterialService")
public class LoopMaterialService {

	@Autowired
	private LoopMaterialRepository loopMaterialRepository;
	
	public LoopMaterialService(LoopMaterialRepository loopMaterialRepository) {
		// TODO Auto-generated constructor stub
		this.loopMaterialRepository = loopMaterialRepository;
	}
	
	public List<LoopMaterial> getLoopMaterialList() {
		// TODO Auto-generated method stub
		return loopMaterialRepository.findAll();
	}
}
