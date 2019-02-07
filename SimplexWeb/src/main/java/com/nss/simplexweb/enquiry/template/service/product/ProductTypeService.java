package com.nss.simplexweb.enquiry.template.service.product;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nss.simplexweb.enquiry.template.model.product.ProductType;
import com.nss.simplexweb.enquiry.template.repository.product.ProductTypeRepository;

@Service("productTypeService")
public class ProductTypeService {

	@Autowired
	private ProductTypeRepository productTypeRepository;
	
	
	@Autowired
	public ProductTypeService (
		ProductTypeRepository productTypeRepository
		
	) {
		this.productTypeRepository = productTypeRepository;
	}
	
	//Product Type List
	public List<ProductType> getProductTypeList() {
		// TODO Auto-generated method stub
		return productTypeRepository.findAll();
	}
}
