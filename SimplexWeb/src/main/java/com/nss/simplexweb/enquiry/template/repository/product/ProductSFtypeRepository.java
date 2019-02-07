package com.nss.simplexweb.enquiry.template.repository.product;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nss.simplexweb.enquiry.template.model.product.ProductSFtype;

@Repository("productSFtypeRepository")
public interface ProductSFtypeRepository extends JpaRepository<ProductSFtype, Long>{

}
