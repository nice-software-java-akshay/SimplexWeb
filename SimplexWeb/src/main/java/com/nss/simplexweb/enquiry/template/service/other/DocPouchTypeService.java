package com.nss.simplexweb.enquiry.template.service.other;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nss.simplexweb.enquiry.template.model.other.DocPouchType;
import com.nss.simplexweb.enquiry.template.repository.other.DocPouchTypeRepository;

@Service("docPouchTypeService")
public class DocPouchTypeService {
	
	@Autowired
	private DocPouchTypeRepository docPouchTypeRepository;
	
	public DocPouchTypeService(DocPouchTypeRepository docPouchTypeRepository) {
		// TODO Auto-generated constructor stub
		this.docPouchTypeRepository = docPouchTypeRepository;
	}
	
	public List<DocPouchType> getDcoPouchTypeList() {
		// TODO Auto-generated method stub
		return docPouchTypeRepository.findAll();
	}
}
