package com.ecommerce.sb_ecom.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.sb_ecom.config.AppConstants;
import com.ecommerce.sb_ecom.payload.CategoryDTO;
import com.ecommerce.sb_ecom.payload.CategoryResponse;
import com.ecommerce.sb_ecom.service.ICategoryService;

import jakarta.validation.Valid;

@RestController
public class CategoryController {
	
	@Autowired
	private ICategoryService categoryService;
	
	@GetMapping("api/public/categories")
	public ResponseEntity<CategoryResponse> getAllCategories(
			@RequestParam(name="pageNumber", defaultValue = AppConstants.PAGE_NUMBER) Integer pageNumber,
			@RequestParam(name="pageSize", defaultValue = AppConstants.PAGE_SIZE) Integer pageSize,
			@RequestParam(name="sortBy", defaultValue = AppConstants.SORT_CATEGORIES_BY) String sortBy,
			@RequestParam(name="sortOrder", defaultValue = AppConstants.SORT_DIR) String sortOrder
			){
		return new ResponseEntity<> (categoryService.getAllCategories(pageNumber, pageSize, sortBy, sortOrder), HttpStatus.OK);
	}
	
	@PostMapping("api/public/categories")
	public ResponseEntity<CategoryDTO> createCategory(@Valid @RequestBody CategoryDTO categoryDTO){
		CategoryDTO savedCategoryDTO = categoryService.createCategory(categoryDTO);
		return new ResponseEntity<CategoryDTO>(savedCategoryDTO , HttpStatus.CREATED);
	}
	
	@DeleteMapping("api/public/categories/{categoryId}")
	public ResponseEntity<CategoryDTO> deleteCategory(@PathVariable String categoryId) {
		CategoryDTO deletedCategoryDTO = categoryService.deleteCategory(categoryId);
			return new ResponseEntity<>(deletedCategoryDTO, HttpStatus.OK);
	}
	
	@PutMapping("api/public/categories/{categoryId}")
	public ResponseEntity<CategoryDTO> updateCategory(@Valid @RequestBody CategoryDTO categoryDTO,
			@PathVariable String categoryId) {
		CategoryDTO updatedCategory = categoryService.updateCategory(categoryDTO, categoryId);
		return new ResponseEntity<CategoryDTO>(updatedCategory, HttpStatus.OK);
	}

}
