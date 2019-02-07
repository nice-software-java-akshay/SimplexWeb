package com.nss.simplexweb.enquiry.template.service.loop;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nss.simplexweb.enquiry.template.model.loop.LoopSewingType;
import com.nss.simplexweb.enquiry.template.repository.loop.LoopSewingTypeRepository;

@Service("loopSewingTypeService")
public class LoopSewingTypeService {
	
	@Autowired
	private LoopSewingTypeRepository loopSewingTypeRepository;
	
	public LoopSewingTypeService(LoopSewingTypeRepository loopSewingTypeRepository) {
		// TODO Auto-generated constructor stub
		this.loopSewingTypeRepository = loopSewingTypeRepository;
	}

	
	public List<LoopSewingType> getLoopSewingTypeList() {
		// TODO Auto-generated method stub
		return loopSewingTypeRepository.findAll();
	}
}
