package com.cg.order.model.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.cg.order.model.Order;

public interface OrderRepository extends MongoRepository<Order, String>{
    
    Order getOrderById(String orderId);

}
