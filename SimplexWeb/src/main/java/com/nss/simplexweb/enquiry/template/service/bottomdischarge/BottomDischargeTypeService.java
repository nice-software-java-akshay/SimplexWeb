package com.nss.simplexweb.enquiry.template.service.bottomdischarge;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nss.simplexweb.enquiry.template.model.bottomdischarge.BottomDischargeType;
import com.nss.simplexweb.enquiry.template.repository.bottomdischarge.BottomDischargeTypeRepository;

@Service("bottomDischargeTypeService")
public class BottomDischargeTypeService {

	@Autowired
	private BottomDischargeTypeRepository bottomDischargeTypeRepository;
	
	public BottomDischargeTypeService(BottomDischargeTypeRepository bottomDischargeTypeRepository) {
		// TODO Auto-generated constructor stub  
		this.bottomDischargeTypeRepository = bottomDischargeTypeRepository;
	}
	
	public  List<BottomDischargeType> getBottomDischargeTypeList() {
		// TODO Auto-generated method stub
		return bottomDischargeTypeRepository.findAll();
	}
}
