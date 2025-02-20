package com.ecommerce.sb_ecom.model;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity(name="categories")
public class CategoryModel {
	
	@Id
	private String categoryId;
	@NotBlank
	@Size(min=2, message="Category name must contain 2 character")
	private String categoryName;
	@Column(nullable = false, updatable = false)
	private Long categoryCreationTime;
	
    // Auto-set timestamp before persisting to DB
    @PrePersist
    protected void onCreate() {
    	 this.categoryCreationTime = System.currentTimeMillis(); // Store timestamp as long (milliseconds)
    }

	
	public String getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId;
	}
	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	public CategoryModel(String categoryId, String categoryName) {
		super();
		this.categoryId = categoryId;
		this.categoryName = categoryName;
	}
	public CategoryModel() {
		 
	}
	public CategoryModel(String categoryId, String categoryName, Long  categoryCreationTime) {
		super();
		this.categoryId = categoryId;
		this.categoryName = categoryName;
		this.categoryCreationTime = categoryCreationTime;
	}
	public Long  getCategoryCreationTime() {
		return categoryCreationTime;
	}
	public void setCategoryCreationTime(Long  categoryCreationTime) {
		this.categoryCreationTime = categoryCreationTime;
	}
}
