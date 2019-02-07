package com.nss.simplexweb.enquiry.template.service.topfilling;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nss.simplexweb.enquiry.template.model.topfilling.TopFillingType;
import com.nss.simplexweb.enquiry.template.repository.topfilling.TopFillingTypeRepository;

@Service("topFillingTypeService")
public class TopFillingTypeService {
	
	@Autowired
	private TopFillingTypeRepository topFillingTypeRepository;
	
	public TopFillingTypeService(TopFillingTypeRepository topFillingTypeRepository) {
		// TODO Auto-generated constructor stub
		this.topFillingTypeRepository = topFillingTypeRepository;
	}
	
	public List<TopFillingType> getTopFillingTypeList() {
		// TODO Auto-generated method stub
		return topFillingTypeRepository.findAll();
	}

}
