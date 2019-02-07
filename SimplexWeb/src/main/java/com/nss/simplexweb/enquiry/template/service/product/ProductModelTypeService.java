package com.nss.simplexweb.enquiry.template.service.product;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nss.simplexweb.enquiry.template.model.product.ProductModelType;
import com.nss.simplexweb.enquiry.template.repository.product.ProductModelTypeRepository;

@Service("productModelTypeService")
public class ProductModelTypeService {

	@Autowired
	private ProductModelTypeRepository productModelTypeRepository;
	
	public ProductModelTypeService(ProductModelTypeRepository productModelTypeRepository) {
		this.productModelTypeRepository = productModelTypeRepository;
	}
	
	public List<ProductModelType> getProductModelTypeList() {
		// TODO Auto-generated method stub
		return productModelTypeRepository.findAll();
	}
}
