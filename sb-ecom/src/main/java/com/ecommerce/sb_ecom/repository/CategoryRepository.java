package com.ecommerce.sb_ecom.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ecommerce.sb_ecom.model.CategoryModel;

public interface CategoryRepository extends JpaRepository<CategoryModel,String>{

	CategoryModel findByCategoryName(String categoryName);

}
