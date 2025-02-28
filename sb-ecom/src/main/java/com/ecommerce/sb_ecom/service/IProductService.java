package com.ecommerce.sb_ecom.service;

import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;

import com.ecommerce.sb_ecom.payload.ProductDTO;
import com.ecommerce.sb_ecom.payload.ProductResponse;

public interface IProductService {
	
	public ProductDTO addProduct(ProductDTO productDTO, String categoryId);

	public ProductResponse getAllProducts(int pageNumber, int pageSize, String sortBy, String sortDir);

	public ProductResponse searchByCategory(String categoryId);

	public ProductResponse searchProductByKeyword(String keyword);

	public ProductDTO updateProduct(String productId, ProductDTO productDTO);

	public ProductDTO deleteProduct(String productId);

	public ProductDTO updateProductImage(String productId, MultipartFile image) throws IOException;

}
