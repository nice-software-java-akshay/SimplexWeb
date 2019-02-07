package com.nss.simplexweb.enquiry.template.repository.product;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nss.simplexweb.enquiry.template.model.product.ProductSurfaceType;

@Repository("productSurfaceTypeRepository")
public interface ProductSurfaceTypeRepository extends JpaRepository<ProductSurfaceType, Long>{

}
