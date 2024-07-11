package com.swiftbuy.product.repository;



import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import com.swiftbuy.admin.model.ProductDetails;
import com.swiftbuy.admin.model.ProductDetails.ProductStatus;


public interface ProductRepository  extends CrudRepository<ProductDetails, Long> {
	 Page<ProductDetails> findByProductStatus(ProductStatus active, Pageable pageable);
	    Page<ProductDetails> findByProductNameContainingIgnoreCase(String searchTerm, Pageable pageable);
	  List<ProductDetails> findByProductStatus(ProductStatus active);
	  	}
