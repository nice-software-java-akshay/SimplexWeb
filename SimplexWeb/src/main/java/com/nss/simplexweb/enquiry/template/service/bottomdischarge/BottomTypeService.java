package com.nss.simplexweb.enquiry.template.service.bottomdischarge;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nss.simplexweb.enquiry.template.model.bottomdischarge.BottomType;
import com.nss.simplexweb.enquiry.template.repository.bottomdischarge.BottomTypeRepository;

@Service("bottomTypeService")
public class BottomTypeService {

	@Autowired
	private BottomTypeRepository bottomTypeRepository;
	
	public BottomTypeService(BottomTypeRepository bottomTypeRepository) {
		// TODO Auto-generated constructor stub
		this.bottomTypeRepository = bottomTypeRepository;
	}
	
	public List<BottomType> getBottomTypeList() {
		// TODO Auto-generated method stub
		return bottomTypeRepository.findAll();
	}
}
