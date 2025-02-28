package com.ecommerce.sb_ecom.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ecommerce.sb_ecom.model.CategoryModel;
import com.ecommerce.sb_ecom.model.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, String>{

	List<Product> findByCategoryModelOrderByPriceAsc(CategoryModel categoryModel);

	List<Product> findByProductNameLikeIgnoreCase(String keyword);
	
	Product findByProductName(String productName);

}
