package com.nss.simplexweb.enquiry.template.repository.product;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nss.simplexweb.enquiry.template.model.product.ProductModelType;

@Repository("productModelTypeRepository")
public interface ProductModelTypeRepository extends JpaRepository<ProductModelType, Long>{

}
