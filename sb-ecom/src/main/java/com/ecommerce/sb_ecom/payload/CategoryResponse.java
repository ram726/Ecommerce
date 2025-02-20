package com.ecommerce.sb_ecom.payload;

import java.util.List;

public class CategoryResponse {
	
	private List<CategoryDTO> categoryContent;
	private Integer pageNumber;
	private Integer pageSize;
	private Integer totalPage;
	private Long totalElements;
	private boolean lastPgae;
	public CategoryResponse() {
		// TODO Auto-generated constructor stub
	}
	public CategoryResponse(List<CategoryDTO> categoryContent) {
		super();
		this.categoryContent = categoryContent;
	}
	public List<CategoryDTO> getContent() {
		return categoryContent;
	}
	public void setContent(List<CategoryDTO> categoryContent) {
		this.categoryContent = categoryContent;
	}
	public Integer getPageNumber() {
		return pageNumber;
	}
	public void setPageNumber(Integer pageNumber) {
		this.pageNumber = pageNumber;
	}
	public Integer getPageSize() {
		return pageSize;
	}
	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}
	public Integer getTotalPage() {
		return totalPage;
	}
	public void setTotalPage(Integer totalPage) {
		this.totalPage = totalPage;
	}
	public Long getTotalElements() {
		return totalElements;
	}
	public void setTotalElements(Long totalElements) {
		this.totalElements = totalElements;
	}
	public boolean isLastPgae() {
		return lastPgae;
	}
	public void setLastPgae(boolean lastPgae) {
		this.lastPgae = lastPgae;
	}
	
	

}
