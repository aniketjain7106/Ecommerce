package com.cg.product.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.cg.product.dto.ProductRequest;
//import com.cg.product.dto.ProductResponse;
import com.cg.product.model.Product;
import com.cg.product.service.ProductService;

import java.util.List;

@RestController
@RequestMapping("/product")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class ProductController {
    
	private final ProductService productService;

    @PostMapping
//    @ResponseStatus(HttpStatus.CREATED)
    public void createProduct(@RequestBody ProductRequest productRequest) {
        productService.createProduct(productRequest);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }
    
    @GetMapping (value = "/view/{id}")
    public ResponseEntity<Product> getOneProductById(@PathVariable ("id") String id){
        Product product =  productService.getProductById(id);
        if(product != null) {
        	return new ResponseEntity<Product>(
        			product,
        			HttpStatus.OK);
        }
        return new ResponseEntity<Product>(
        		HttpStatus.NOT_FOUND);
    }
    
    @PostMapping(value = "{id}/increase/{quantity}")
    public ResponseEntity<Product> increaseQuantity(@PathVariable ("id") String id,@PathVariable("quantity") int quantitiy){
        Product product = productService.increaseQuantity(quantitiy,id);
        if(product != null) {
        	return new ResponseEntity<Product>(
        			product,
        			HttpStatus.OK);
        }
        return new ResponseEntity<Product>(
        		HttpStatus.NOT_FOUND);
    }
    
    @PostMapping(value = "{id}/reduce/{quantity}")
    public ResponseEntity<Product> reduceQuantity(@PathVariable ("id") String id,@PathVariable("quantity") int quantitiy){
        Product product = productService.reduceQuantity(quantitiy,id);
        if(product != null) {
        	return new ResponseEntity<Product>(
        			product,
        			HttpStatus.OK);
        }
        return new ResponseEntity<Product>(
        		HttpStatus.NOT_FOUND);
    }
    
    @DeleteMapping(value = "/{id}")
    public void deleteProduct(@PathVariable ("id") String id) {
    	productService.deleteProduct(id);
    }
    
    @PostMapping(value = "{id}/price/{price}")
    public ResponseEntity<Product> updatePrice(@PathVariable ("id") String id,@PathVariable("price") int price){
        Product product = productService.updatePrice(id,price);
        if(product != null) {
        	return new ResponseEntity<Product>(
        			product,
        			HttpStatus.OK);
        }
        return new ResponseEntity<Product>(
        		HttpStatus.NOT_FOUND);
    }
    
    
    @PostMapping(value = "/update/{id}")
    public ResponseEntity<Product> update(@RequestBody ProductRequest productRequest,@PathVariable ("id") String id){
        Product product = productService.update(productRequest, id);
        if(product != null) {
            return new ResponseEntity<Product>(
                    product,
                    HttpStatus.OK);
        }
        return new ResponseEntity<Product>(
                HttpStatus.NOT_FOUND);
    }
    
}
