package com.nss.simplexweb.enquiry.template.service.product;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nss.simplexweb.enquiry.template.model.product.ProductSurfaceType;
import com.nss.simplexweb.enquiry.template.repository.product.ProductSurfaceTypeRepository;

@Service("productSurfaceTypeService")
public class ProductSurfaceTypeService {
	
	@Autowired
	private ProductSurfaceTypeRepository productSurfaceTypeRepository;

	public ProductSurfaceTypeService(ProductSurfaceTypeRepository productSurfaceTypeRepository) {
		// TODO Auto-generated constructor stub
		this.productSurfaceTypeRepository = productSurfaceTypeRepository;
	}
	
	public List<ProductSurfaceType> getProductSurfaceTypeList() {
		// TODO Auto-generated method stub
		return productSurfaceTypeRepository.findAll();
	}
	
}
