package com.cg.order.controller;

import lombok.RequiredArgsConstructor;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import com.cg.order.dto.OrderRequest;
import com.cg.order.dto.OrderResponse;
import com.cg.order.model.Order;
import com.cg.order.model.Product;
import com.cg.order.service.OrderService;

@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class OrderController {
	
	@Autowired
    private RestTemplate restTemplate;
	
	private final OrderService orderService;
	
	@GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public List<Order> getAllOrders() {
        return orderService.getAllOrders();
    }
	
    @ResponseStatus(HttpStatus.CREATED)
	@PostMapping()
    public void createOrder(@RequestBody OrderRequest orderRequest) {
        orderService.createOrder(orderRequest);
    }
	
	@GetMapping("/products")
    public Product[] getProduct(){
    	Product[] products = restTemplate.getForObject("http://localhost:8812/product", Product[].class);
//    	System.out.println(products);
    	return products;   	
    }
	
	@GetMapping("/{username}")
	public List<OrderResponse> getOrdersByUsername(@PathVariable("username") String username ) {
		return orderService.getOrdersByUsername(username);
//		return order;
	}
	
	@DeleteMapping(value = "/{id}")
    public void deleteProduct(@PathVariable ("id") String orderId) {
        orderService.deleteOrder(orderId);
    }
	
	@GetMapping (value = "/view/{id}")
    public ResponseEntity<Order> getOrderById(@PathVariable ("id") String id){
        Order order =  orderService.getOrderById(id);
        if(order != null) {
            return new ResponseEntity<Order>(
                    order,
                    HttpStatus.OK);
        }
        return new ResponseEntity<Order>(
                HttpStatus.NOT_FOUND);
    }
	
	

}
