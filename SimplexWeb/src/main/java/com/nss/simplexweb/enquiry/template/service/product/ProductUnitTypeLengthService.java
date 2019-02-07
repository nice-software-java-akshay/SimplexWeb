package com.nss.simplexweb.enquiry.template.service.product;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nss.simplexweb.enquiry.template.model.product.ProductUnitTypeLength;
import com.nss.simplexweb.enquiry.template.repository.product.ProductUnitTypeLengthRepository;

@Service("productUnitTypeLengthService")
public class ProductUnitTypeLengthService {

	@Autowired
	private ProductUnitTypeLengthRepository productUnitTypeLengthRepository;
	
	public ProductUnitTypeLengthService(ProductUnitTypeLengthRepository productUnitTypeLengthRepository) {
		// TODO Auto-generated constructor stub
		this.productUnitTypeLengthRepository = productUnitTypeLengthRepository;
	}
	
	public List<ProductUnitTypeLength> getProductUnitTypeLengthList() {
		// TODO Auto-generated method stub
		return productUnitTypeLengthRepository.findAll();
	}
}
