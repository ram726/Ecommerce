 package com.ecommerce.sb_ecom.service;

import com.ecommerce.sb_ecom.payload.CategoryDTO;
import com.ecommerce.sb_ecom.payload.CategoryResponse;

public interface ICategoryService {
	
	public CategoryResponse getAllCategories(Integer pageNumber, Integer pageSize, String sortBy, String sortOrder);
	public CategoryDTO createCategory(CategoryDTO CategoryDTO);
	public CategoryDTO deleteCategory(String categoryId);
	public CategoryDTO updateCategory(CategoryDTO category, String categoryId);
	

}
