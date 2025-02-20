package com.ecommerce.sb_ecom.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.ecommerce.sb_ecom.Utility.IdGeneratorUtil;
import com.ecommerce.sb_ecom.exception.APIException;
import com.ecommerce.sb_ecom.exception.ResourceNotFoundException;
import com.ecommerce.sb_ecom.model.CategoryModel;
import com.ecommerce.sb_ecom.payload.CategoryDTO;
import com.ecommerce.sb_ecom.payload.CategoryResponse;
import com.ecommerce.sb_ecom.repository.CategoryRepository;

@Service
public class CategoryServiceImpl implements ICategoryService{
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	@Autowired
	private ModelMapper modelMapper;
	
	IdGeneratorUtil idGeneratorUtil;
	
	@Override
	public CategoryResponse getAllCategories(Integer pageNumber, Integer pageSize, String sortBy, String sortOrder) {
		
		Sort sortByAndOrder = sortOrder.equals("asc") ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
		
		Pageable pageDetails = PageRequest.of(pageNumber, pageSize, sortByAndOrder);
		Page<CategoryModel> categoryPage = categoryRepository.findAll(pageDetails);
		
		List<CategoryModel> categories = categoryPage.getContent();

		if (categories == null || categories.isEmpty()) {
			throw new APIException("No category created till now.");
		}

		List<CategoryDTO> categoryDTOs = categories.stream()
				.map(category -> modelMapper.map(category, CategoryDTO.class)).collect(Collectors.toList());
		
		CategoryResponse categoryResponse = new CategoryResponse();
		categoryResponse.setContent(categoryDTOs);
		categoryResponse.setPageNumber(categoryPage.getNumber());
		categoryResponse.setPageSize(categoryPage.getSize());
		categoryResponse.setTotalElements(categoryPage.getTotalElements());
		categoryResponse.setTotalPage(categoryPage.getTotalPages());
		categoryResponse.setLastPgae(categoryPage.isLast());
		
		return categoryResponse;
	}

	@Override
	public CategoryDTO createCategory(CategoryDTO categoryDTO) {
		CategoryModel category = modelMapper.map(categoryDTO, CategoryModel.class);
		CategoryModel returnedCategory = categoryRepository.findByCategoryName(category.getCategoryName());
		if(returnedCategory != null) {
			throw new APIException("Category with the name "+category.getCategoryName()+ " already exist !!!");
		}
		String cid = idGeneratorUtil.generateId();
		category.setCategoryId(cid);
		CategoryModel savedCategory = categoryRepository.save(category);
		CategoryDTO savedCategoryDTO = modelMapper.map(savedCategory, CategoryDTO.class);
		
		return savedCategoryDTO;
	}

	@Override
	public CategoryDTO deleteCategory(String categoryId) {
		Optional<CategoryModel> savedCategoryOptional = categoryRepository.findById(categoryId);
		CategoryModel savedCategory = savedCategoryOptional
				.orElseThrow(()->new ResourceNotFoundException("Category","categoryId",categoryId));
		categoryRepository.delete(savedCategory);
		
		return modelMapper.map(savedCategory, CategoryDTO.class);
	}

	@Override
	public CategoryDTO updateCategory(CategoryDTO categoryDto, String categoryId) {
		Optional<CategoryModel> savedCategoryOptional = categoryRepository.findById(categoryId);
		CategoryModel categoryModel = modelMapper.map(categoryDto, CategoryModel.class);
		CategoryModel returnedCategory = savedCategoryOptional
				.orElseThrow(()->new ResourceNotFoundException("Category","categoryId",categoryId));
		
		returnedCategory.setCategoryName(categoryDto.getCategoryName());
		CategoryModel savedCategory = categoryRepository.save(returnedCategory);
		CategoryDTO savedCategoryDTO = modelMapper.map(savedCategory, CategoryDTO.class);
		
		return savedCategoryDTO;
	}
}
