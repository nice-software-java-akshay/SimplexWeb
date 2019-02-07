package com.nss.simplexweb.enquiry.template.service.fabric;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nss.simplexweb.enquiry.template.model.fabric.FabricType;
import com.nss.simplexweb.enquiry.template.repository.fabric.FabricTypeRepositoy;

@Service("fabricTypeService")
public class FabricTypeService {

	@Autowired
	private FabricTypeRepositoy fabricTypeRepositoy;
	
	public FabricTypeService(FabricTypeRepositoy fabricTypeRepositoy) {
		// TODO Auto-generated constructor stub
		this.fabricTypeRepositoy = fabricTypeRepositoy;
	}
	
	public List<FabricType> getFabricTypeList() {
		// TODO Auto-generated method stub
		return fabricTypeRepositoy.findAll();
	}
}
