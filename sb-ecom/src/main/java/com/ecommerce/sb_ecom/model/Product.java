package com.ecommerce.sb_ecom.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
public class Product {
	
	@Id
	private String productId;
	
	@NotBlank
	@Size(min=3, message="Product name must contain 3 character")
	private String productName;
	
	@Size(min=8, message="Product description must contain 8 character")
	private String description;
	private String image;
	private Integer quantity;
	private double price;
	private double discount;
	private double specialPrice;
	
	@Column(nullable = false, updatable = false)
	private Long productTimeStamp;
	
	@ManyToOne
	@JoinColumn(name = "category_Id")
	private CategoryModel categoryModel;
	
    // Auto-set timestamp before persisting to DB
    @PrePersist
    protected void onCreate() {
    	 this.productTimeStamp = System.currentTimeMillis(); // Store timestamp as long (milliseconds)
    }
    
	public Long getProductTimeStamp() {
	 	return productTimeStamp;
	}

	public void setProductTimeStamp(Long productTimeStamp) {
		this.productTimeStamp = productTimeStamp;
	}

	public Product() {
		// TODO Auto-generated constructor stub
	}

	public Product(String productId, String productName, String description, String image, Integer quantity, double price, double discount,
			double specialPrice, CategoryModel categoryModel, Long productTimeStamp) {
		super();
		this.productId = productId;
		this.productName = productName;
		this.description = description;
		this.image = image;
		this.quantity = quantity;
		this.price = price;
		this.price = discount;
		this.specialPrice = specialPrice;
		this.categoryModel = categoryModel;
		this.productTimeStamp = productTimeStamp;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public double getSpecialPrice() {
		return specialPrice;
	}

	public void setSpecialPrice(double specialPrice) {
		this.specialPrice = specialPrice;
	}

	public CategoryModel getCategoryModel() {
		return categoryModel;
	}

	public void setCategoryModel(CategoryModel categoryModel) {
		this.categoryModel = categoryModel;
	}

	public double getDiscount() {
		return discount;
	}

	public void setDiscount(double discount) {
		this.discount = discount;
	}
	
	

}
