package com.ecommerce.sb_ecom.service;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.ecommerce.sb_ecom.Utility.IdGeneratorUtil;
import com.ecommerce.sb_ecom.exception.APIException;
import com.ecommerce.sb_ecom.exception.ResourceNotFoundException;
import com.ecommerce.sb_ecom.model.CategoryModel;
import com.ecommerce.sb_ecom.model.Product;
import com.ecommerce.sb_ecom.payload.ProductDTO;
import com.ecommerce.sb_ecom.payload.ProductResponse;
import com.ecommerce.sb_ecom.repository.CategoryRepository;
import com.ecommerce.sb_ecom.repository.ProductRepository;

@Service
public class ProductServiceImpl implements IProductService {

	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private CategoryRepository categoryRepository;

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	IFileService fileService;

	@Value("${project.image}")
	private String path;

	IdGeneratorUtil idGeneratorUtil;

	public ProductDTO addProduct(ProductDTO productDTO, String categoryId) {

		Product existingProduct = productRepository.findByProductName(productDTO.getProductName());
		if (existingProduct != null) {
			throw new APIException("Product with the name " + productDTO.getProductName() + " already exist !!!");
		}

		CategoryModel categoryModel = categoryRepository.findById(categoryId)
				.orElseThrow(() -> new ResourceNotFoundException("Category", "categoryId", categoryId));

		Product product = modelMapper.map(productDTO, Product.class);

		product.setProductId(idGeneratorUtil.generateId());
		product.setCategoryModel(categoryModel);
		product.setImage("default.png");

		double specialPrice = product.getPrice() - ((product.getDiscount() * 0.01) * product.getPrice());
		product.setSpecialPrice(specialPrice);

		Product savedProduct = productRepository.save(product);

		return modelMapper.map(savedProduct, ProductDTO.class);
	}

	public ProductResponse getAllProducts(int pageNumber, int pageSize, String sortBy, String sortDir) {
		
		Sort sortByAndOrder = sortDir.equals("asc") ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
		Pageable pageDetails = PageRequest.of(pageNumber, pageSize, sortByAndOrder);
		
		Page<Product> productsPage = productRepository.findAll(pageDetails);
		List<Product> products = productsPage.getContent();

		if (products.size() == 0 || products == null || products.isEmpty()) {
			throw new APIException("There are no products available !!!");
		}

		List<ProductDTO> productDTOs = products.stream().map(product -> modelMapper.map(product, ProductDTO.class))
				.collect(Collectors.toList());

		ProductResponse productResponse = new ProductResponse();
		productResponse.setProductContent(productDTOs);
		productResponse.setPageNumber(productsPage.getNumber());
		productResponse.setPageSize(productsPage.getSize());
		productResponse.setTotalElements(productsPage.getTotalElements());
		productResponse.setTotalPage(productsPage.getTotalPages());
		productResponse.setLastPgae(productsPage.isLast());

		return productResponse;
	}

	public ProductResponse searchByCategory(String categoryId) {

		CategoryModel categoryModel = categoryRepository.findById(categoryId)
				.orElseThrow(() -> new ResourceNotFoundException("Category", "categoryId", categoryId));

		List<Product> products = productRepository.findByCategoryModelOrderByPriceAsc(categoryModel);

		if (products.size() == 0 || products == null || products.isEmpty()) {
			throw new APIException("There are no products available !!!");
		}

		List<ProductDTO> productDTOs = products.stream().map(product -> modelMapper.map(product, ProductDTO.class))
				.collect(Collectors.toList());

		ProductResponse productResponse = new ProductResponse();
		productResponse.setProductContent(productDTOs);

		return productResponse;
	}

	public ProductResponse searchProductByKeyword(String keyword) {

		List<Product> products = productRepository.findByProductNameLikeIgnoreCase('%' + keyword + '%');

		if (products.size() == 0 || products == null || products.isEmpty()) {
			throw new APIException("There are no products available !!!");
		}

		List<ProductDTO> productDTOs = products.stream().map(product -> modelMapper.map(product, ProductDTO.class))
				.collect(Collectors.toList());

		ProductResponse productResponse = new ProductResponse();
		productResponse.setProductContent(productDTOs);

		return productResponse;
	}

	public ProductDTO updateProduct(String productId, ProductDTO productDTO) {

		Product savedProduct = productRepository.findById(productId)
				.orElseThrow(() -> new ResourceNotFoundException("Product", "productId", productId));

		Product product = modelMapper.map(productDTO, Product.class);
		savedProduct.setProductName(product.getProductName());
		savedProduct.setDescription(product.getDescription());
		savedProduct.setQuantity(product.getQuantity());
		savedProduct.setPrice(product.getPrice());
		savedProduct.setDiscount(product.getDiscount());

		double specialPrice = product.getPrice() - ((product.getDiscount() * 0.01) * product.getPrice());
		savedProduct.setSpecialPrice(specialPrice);

		Product updatedProduct = productRepository.save(savedProduct);

		return modelMapper.map(updatedProduct, ProductDTO.class);
	}

	public ProductDTO deleteProduct(String productId) {

		Product savedProduct = productRepository.findById(productId)
				.orElseThrow(() -> new ResourceNotFoundException("Product", "productId", productId));

		productRepository.delete(savedProduct);
		return modelMapper.map(savedProduct, ProductDTO.class);
	}

	public ProductDTO updateProductImage(String productId, MultipartFile image) throws IOException {
		String fileName = null;
		Product savedProduct = productRepository.findById(productId)
				.orElseThrow(() -> new ResourceNotFoundException("Product", "productId", productId));
		if (image != null && !image.isEmpty()) {
			fileName = fileService.uploadImage(path, image);
		}
		savedProduct.setImage(fileName);
		Product updatedProduct = productRepository.save(savedProduct);
		return modelMapper.map(updatedProduct, ProductDTO.class);
	}

}
