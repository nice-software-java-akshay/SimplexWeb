package com.nss.simplexweb.enquiry.template.service.other;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nss.simplexweb.enquiry.template.model.other.LinerType;
import com.nss.simplexweb.enquiry.template.repository.other.LinerTypeRepository;


@Service("linerTypeService")
public class LinerTypeService {

	@Autowired
	private LinerTypeRepository linerTypeRepository;

	public LinerTypeService(LinerTypeRepository linerTypeRepository) {
		// TODO Auto-generated constructor stub
		this.linerTypeRepository = linerTypeRepository;
	}
	
	public List<LinerType> getLinerTypeList() {
		// TODO Auto-generated method stub
		return linerTypeRepository.findAll();
	}
}