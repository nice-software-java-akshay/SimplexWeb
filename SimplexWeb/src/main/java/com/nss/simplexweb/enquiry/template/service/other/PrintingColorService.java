package com.nss.simplexweb.enquiry.template.service.other;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nss.simplexweb.enquiry.template.model.other.PrintingColor;
import com.nss.simplexweb.enquiry.template.repository.other.PrintingColorRepository;


@Service("printingColorService")
public class PrintingColorService {

	@Autowired
	private PrintingColorRepository printingColorRepository;
	
	public PrintingColorService(PrintingColorRepository printingColorRepository) {
		// TODO Auto-generated constructor stub
		this.printingColorRepository = printingColorRepository;
	}
	
	public List<PrintingColor> getPrintingColorList() {
		// TODO Auto-generated method stub
		return printingColorRepository.findAll();
	}
}
