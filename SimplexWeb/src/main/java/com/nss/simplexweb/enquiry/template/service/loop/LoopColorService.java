package com.nss.simplexweb.enquiry.template.service.loop;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nss.simplexweb.enquiry.template.model.loop.LoopColor;
import com.nss.simplexweb.enquiry.template.repository.loop.LoopColorRepository;

@Service("loopColorService")
public class LoopColorService {

	@Autowired
	private LoopColorRepository loopColorRepository;
	
	public LoopColorService(LoopColorRepository loopColorRepository) {
		// TODO Auto-generated constructor stub
		this.loopColorRepository = loopColorRepository;
	}
	
	public List<LoopColor> getLoopColorList() {
		// TODO Auto-generated method stub
		return loopColorRepository.findAll();
	}
}
