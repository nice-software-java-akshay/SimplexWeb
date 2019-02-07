package com.nss.simplexweb.enquiry.template.service.product;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nss.simplexweb.enquiry.template.model.product.ProductSFtype;
import com.nss.simplexweb.enquiry.template.repository.product.ProductSFtypeRepository;

@Service("productSFtypeService")
public class ProductSFtypeService {

	@Autowired
	private ProductSFtypeRepository ProductSFtypeRepository;
	
	public ProductSFtypeService(ProductSFtypeRepository ProductSFtypeRepository) {
		// TODO Auto-generated constructor stub
		this.ProductSFtypeRepository = ProductSFtypeRepository;
	}
	
	public List<ProductSFtype> getProductSFTypeList() {
		// TODO Auto-generated method stub
		return ProductSFtypeRepository.findAll();
	}
}
