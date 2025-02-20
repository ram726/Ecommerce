package com.ecommerce.sb_ecom.payload;

public class CategoryDTO {
	private String categoryId;
	private String categoryName;
	private Long categoryCreationTime;
	public CategoryDTO() {
		// TODO Auto-generated constructor stub
	}
	public CategoryDTO(String categoryId, String categoryName, Long categoryCreationTime) {
		super();
		this.categoryId = categoryId;
		this.categoryName = categoryName;
		this.categoryCreationTime = categoryCreationTime;
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
	public Long getCategoryCreationTime() {
		return categoryCreationTime;
	}
	public void setCategoryCreationTime(Long categoryCreationTime) {
		this.categoryCreationTime = categoryCreationTime;
	}
	
	
	
}
