package com.ecommerce.sb_ecom.payload;


public class ProductDTO {
	
	private String productId;
	private String productName;
	private String image;
	private Integer quantity;
	private double price;
	private double discount;
	private double specialPrice;
	private Long productTimeStamp;
	
	public Long getProductTimeStamp() {
		return productTimeStamp;
	}

	public void setProductTimeStamp(Long productTimeStamp) {
		this.productTimeStamp = productTimeStamp;
	}

	public ProductDTO() {
		// TODO Auto-generated constructor stub
	}

	public ProductDTO(String productId, String productName, String image, Integer quantity, double price,
			double discount, double specialPrice, Long productTimeStamp) {
		super();
		this.productId = productId;
		this.productName = productName;
		this.image = image;
		this.quantity = quantity;
		this.price = price;
		this.discount = discount;
		this.specialPrice = specialPrice;
		this.productTimeStamp = productTimeStamp;
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

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
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

	public double getDiscount() {
		return discount;
	}

	public void setDiscount(double discount) {
		this.discount = discount;
	}

	public double getSpecialPrice() {
		return specialPrice;
	}

	public void setSpecialPrice(double specialPrice) {
		this.specialPrice = specialPrice;
	}
	
	

}
