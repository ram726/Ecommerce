package com.ecommerce.sb_ecom.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.ecommerce.sb_ecom.payload.ProductDTO;
import com.ecommerce.sb_ecom.payload.ProductResponse;
import com.ecommerce.sb_ecom.service.IProductService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api")
public class ProductController {
	
	@Autowired
	private IProductService productService;
	
	@PostMapping("/admin/categories/{categoryId}/product")
	public ResponseEntity<ProductDTO> addProduct(@Valid @RequestBody ProductDTO productDTO, @PathVariable String categoryId){
		ProductDTO savedproductDTO = productService.addProduct(productDTO,categoryId);
		return new ResponseEntity<ProductDTO>(savedproductDTO, HttpStatus.OK);
	}
	
	@GetMapping("/public/products")
	public ResponseEntity<ProductResponse> getAllProducts(
			@RequestParam(value = "pageNumber", required = false, defaultValue = "0") int pageNumber,
			@RequestParam(value = "pageSize", required = false, defaultValue = "10") int pageSize,
	        @RequestParam(value = "sortBy", required = false, defaultValue = "productTimeStamp") String sortBy,
	        @RequestParam(value = "sortDir", required = false, defaultValue = "asc") String sortDir)
	       {
		ProductResponse productResponse = productService.getAllProducts(pageNumber, pageSize, sortBy, sortDir);
		return new ResponseEntity<ProductResponse>(productResponse, HttpStatus.OK);
	}
	
	@GetMapping("/public/categories/{categoryId}/products")
	public ResponseEntity<ProductResponse> getProductByCategory(@PathVariable String categoryId){
		ProductResponse productResponse = productService.searchByCategory(categoryId);
		return new ResponseEntity<ProductResponse>(productResponse, HttpStatus.OK);
	}
	
	@GetMapping("/public/products/{keyword}")
	public ResponseEntity<ProductResponse> getProductByKeyword(@PathVariable String keyword){
		ProductResponse productResponse = productService.searchProductByKeyword(keyword);
		return new ResponseEntity<ProductResponse>(productResponse, HttpStatus.OK);
	}
	
	@PutMapping("/admin/products/{productId}")
	public ResponseEntity<ProductDTO> updateProduct(@Valid @RequestBody ProductDTO productDTO,
													@PathVariable String productId){
		ProductDTO updatedProductDTO = productService.updateProduct(productId, productDTO);
		return new ResponseEntity<ProductDTO>(updatedProductDTO, HttpStatus.OK);
	}
	
	@DeleteMapping("/admin/products/{productId}")
	public ResponseEntity<ProductDTO> deleteProduct(@PathVariable String productId){
		ProductDTO updatedProductDTO = productService.deleteProduct(productId);
		return new ResponseEntity<ProductDTO>(updatedProductDTO, HttpStatus.OK);
	}
	
	@PutMapping("/admin/products/{productId}/image")
	ResponseEntity<ProductDTO> updateProductImage(@PathVariable String productId, 
			                                      @RequestParam("image") MultipartFile image)  
			                                      throws IOException {
		ProductDTO updatedProductDTO = productService.updateProductImage(productId, image);
		return new ResponseEntity<ProductDTO>(updatedProductDTO, HttpStatus.OK);
	}

}
