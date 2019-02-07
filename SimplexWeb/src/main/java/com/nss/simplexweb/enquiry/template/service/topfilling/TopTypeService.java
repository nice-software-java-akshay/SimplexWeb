package com.nss.simplexweb.enquiry.template.service.topfilling;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nss.simplexweb.enquiry.template.model.topfilling.TopType;
import com.nss.simplexweb.enquiry.template.repository.topfilling.TopTypeRepository;

@Service("topTypeService")
public class TopTypeService {

	@Autowired
	private TopTypeRepository topTypeRepository;
	
	public TopTypeService(TopTypeRepository topTypeRepository) {
		// TODO Auto-generated constructor stub
		this.topTypeRepository = topTypeRepository;
	}
	
	public List<TopType> getTopTypeList() {
		// TODO Auto-generated method stub
		return topTypeRepository.findAll();
	}
}
