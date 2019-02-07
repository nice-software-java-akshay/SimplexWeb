package com.nss.simplexweb.enquiry.template.repository.product;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nss.simplexweb.enquiry.template.model.product.ProductType;

@Repository("productTypeRepository")
public interface ProductTypeRepository extends JpaRepository<ProductType, Long>{

	ArrayList<ProductType> findAll();
}
