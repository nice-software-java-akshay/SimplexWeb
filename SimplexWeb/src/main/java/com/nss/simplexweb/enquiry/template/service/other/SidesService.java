package com.nss.simplexweb.enquiry.template.service.other;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nss.simplexweb.enquiry.template.model.other.Sides;
import com.nss.simplexweb.enquiry.template.repository.other.SidesRepository;

@Service("sidesService")
public class SidesService {

	@Autowired
	private SidesRepository sidesRepository;
	
	public SidesService(SidesRepository sidesRepository) {
		// TODO Auto-generated constructor stub
		this.sidesRepository = sidesRepository;
	}
	
	public List<Sides> getSidesList() {
		// TODO Auto-generated method stub
		return sidesRepository.findAll();
	}
}
