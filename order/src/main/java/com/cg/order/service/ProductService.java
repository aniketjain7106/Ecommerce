package com.cg.order.service;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.cg.order.model.Product;


@Service
//@FeignClient(name="${service.inventory}", path="${api.inventory.item}")
@FeignClient(name="product-service",url = "http://localhost:8812/product")
public interface ProductService {

  @GetMapping("view/{id}")
  Product getProductById(@PathVariable("id") String id);

  @GetMapping
  List<Product> getItems();
  
  @PostMapping("{id}/reduce/{quantity}")
  Product reduceQuantity(@PathVariable("id") String id,@PathVariable("quantity") long quantity);
}	
