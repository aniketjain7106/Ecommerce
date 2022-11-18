package com.cg.product.service;

import java.util.List;
import java.util.Optional;

import com.cg.product.dto.ProductRequest;
import com.cg.product.dto.ProductResponse;
//import com.cg.product.dto.ProductResponse;
import com.cg.product.exception.ProductNotFoundException;
import com.cg.product.exception.Universal;
import com.cg.product.model.Product;
import com.cg.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.data.annotation.Id;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
@Service
@RequiredArgsConstructor
@Slf4j
public class ProductService {
    
	private final ProductRepository productRepository;

    public void createProduct(ProductRequest productRequest) {
        if (productRequest.getId()==null || productRequest.getName()==null || productRequest.getPrice()==0 
                || productRequest.getQuantity()==0) {
            throw new Universal("Enter all details");
        }
        else if(productRequest.getId().equals("0")) {
            throw new Universal("Id cant be zero");
        }
        else {
            Optional<Product> product2 = Optional.ofNullable(productRepository.getProductById(productRequest.getId()));
            if (product2.isEmpty()) {
                Product product = Product.builder()
                        .id(productRequest.getId())
                        .name(productRequest.getName())
                        .description(productRequest.getDescription())
                        .price(productRequest.getPrice())
                        .quantity(productRequest.getQuantity())
                        .build();
        
                productRepository.save(product);
                log.info("Product {} is saved", product.getId());
//                return ResponseEntity.ok("Product is created");
            }
            else {
                throw new Universal("Id already exist");
            
            }
        }
    }

//    public List<ProductResponse> getAllProducts() {
//        List<Product> products = productRepository.findAll();
//        return products.stream().map(this::mapToProductResponse).toList();
//    }
    
    public List<Product> getAllProducts() {
        List<Product> products = productRepository.findAll();
        return products;
    }

    private ProductResponse mapToProductResponse(Product product) {
        return ProductResponse.builder()
                .id(product.getId())
                .name(product.getName())
                .description(product.getDescription()) 
                .price(product.getPrice())
                .quantity(product.getQuantity())
                .build();
    }
    
    public Product getProductById(String id) {
    	List<Product> products = productRepository.findAll();
		for (Product product : products) {
			if (id.equals(product.getId())) {
				return product;}
		}
		throw new ProductNotFoundException("Product Invalid ", id);
    }
    
    public Product increaseQuantity(int quantity,String id) {
    	Product product=productRepository.getProductById(id);
    	int x = product.getQuantity()+quantity;
    	Product product2 = Product.builder().id(product.getId()).name(product.getName()).description(product.getDescription()).price(product.getPrice()).quantity(x).build();
    	productRepository.save(product2);
    	return product2;
    }
    
    public void deleteProduct(String id) {
        Product product = productRepository.getProductById(id);
        if (product==null){
            throw new ProductNotFoundException("Product Invalid ", id);
        }
    	productRepository.deleteById(id);
	}
    
    public Product reduceQuantity(int quantity,String id) {
    	Product product=productRepository.getProductById(id);
    	int x = product.getQuantity()-quantity;
    	Product product2 = Product.builder().id(product.getId()).name(product.getName()).description(product.getDescription()).price(product.getPrice()).quantity(x).build();
    	productRepository.save(product2);
    	return product2;
    }
    
    public Product updatePrice(String id,int price) {
    	Product product=productRepository.getProductById(id);
    	Product product2 = Product.builder().id(product.getId()).name(product.getName()).description(product.getDescription()).price(price).quantity(product.getQuantity()).build();
    	productRepository.save(product2);
    	return product2;
    }
    
    public Product update(ProductRequest productRequest,String id) {
        if (productRequest.getName()==null || productRequest.getPrice()==0 
                || productRequest.getQuantity()==0) {
            throw new Universal("Enter all details");
        }
        Product product = Product.builder()
        		.id(id)
                .name(productRequest.getName())
                .description(productRequest.getDescription())
                .price(productRequest.getPrice())
                .quantity(productRequest.getQuantity())
                .build();
        productRepository.save(product);
        return product;
    }
    
}
