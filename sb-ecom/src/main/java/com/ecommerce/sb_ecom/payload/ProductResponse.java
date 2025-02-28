package com.ecommerce.sb_ecom.payload;

import java.util.List;

public class ProductResponse {
	
	private List<ProductDTO> productContent;
	private Integer pageNumber;
	private Integer pageSize;
	private Integer totalPage;
	private Long totalElements;
	private boolean lastPgae;
	
	public ProductResponse() {
		// TODO Auto-generated constructor stub
	}

	public ProductResponse(List<ProductDTO> productContent, Integer pageNumber, Integer pageSize, Integer totalPage,
			Long totalElements, boolean lastPgae) {
		super();
		this.productContent = productContent;
		this.pageNumber = pageNumber;
		this.pageSize = pageSize;
		this.totalPage = totalPage;
		this.totalElements = totalElements;
		this.lastPgae = lastPgae;
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


	public List<ProductDTO> getProductContent() {
		return productContent;
	}

	public void setProductContent(List<ProductDTO> productContent) {
		this.productContent = productContent;
	}

}
