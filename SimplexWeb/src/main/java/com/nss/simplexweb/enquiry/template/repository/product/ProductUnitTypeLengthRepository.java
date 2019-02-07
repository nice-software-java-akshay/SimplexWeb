package com.nss.simplexweb.enquiry.template.repository.product;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nss.simplexweb.enquiry.template.model.product.ProductUnitTypeLength;

@Repository("productUnitTypeLengthRepository")
public interface ProductUnitTypeLengthRepository extends JpaRepository<ProductUnitTypeLength, Long>{

}
