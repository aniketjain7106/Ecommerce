package com.cg.product.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.cg.product.model.Product;

@Repository
public interface ProductRepository extends MongoRepository<Product, String> {

	Product getProductById(String id);
//	Product reduceQuantity(int quantity,String id);
//	Product increaseQuantity(int quantity,String id);

}