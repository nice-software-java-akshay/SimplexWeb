package com.nss.simplexweb.enquiry.template.service.loop;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nss.simplexweb.enquiry.template.model.loop.LoopType;
import com.nss.simplexweb.enquiry.template.repository.loop.LoopTypeRepository;

@Service("loopTypeService")
public class LoopTypeService {

	@Autowired
	private LoopTypeRepository loopTypeRepository;
	
	public LoopTypeService(LoopTypeRepository loopTypeRepository) {
		// TODO Auto-generated constructor stub
		this.loopTypeRepository = loopTypeRepository;
	}
	
	public List<LoopType> getLoopTypeList() {
		// TODO Auto-generated method stub
		return loopTypeRepository.findAll();
	}
}
