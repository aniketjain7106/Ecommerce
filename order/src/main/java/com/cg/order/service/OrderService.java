package com.cg.order.service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.cg.order.dto.OrderRequest;
import com.cg.order.dto.OrderResponse;
import com.cg.order.Exception.EntityNotFoundException;
import com.cg.order.Exception.InventoryInsufficientException;
import com.cg.order.Exception.Universal;
import com.cg.order.model.Order;
import com.cg.order.model.Product;
import com.cg.order.model.Users;
import com.cg.order.model.repository.OrderRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrderService {

	@Autowired
    private RestTemplate restTemplate;
	
	@Autowired
	private ProductService productService;
	
	@Autowired
	private UserService userService;
	
	private final OrderRepository orderRepository;
	
	public void createOrder(OrderRequest orderRequest) {
        isQuantitySufficient(orderRequest);
    }

    public void isQuantitySufficient(OrderRequest orderRequest) {
    	Product product = productService.getProductById(orderRequest.getItemId());
    	Users user = restTemplate.getForObject("http://localhost:8814/users/"+orderRequest.getUserId(), Users.class);
        if(product == null) {
        	
            throw new EntityNotFoundException("Item not found", orderRequest.getItemId());
        }

        if(product.getQuantity() < orderRequest.getQuantity()) {
            throw new InventoryInsufficientException("Item inventory insufficient", orderRequest.getItemId(),
            		product.getQuantity(), orderRequest.getQuantity());
        }
        else{
        	Product product2 = productService.reduceQuantity(orderRequest.getItemId(), orderRequest.getQuantity());
        	orderRequest.setId(UUID.randomUUID().toString());
        	Date date = Calendar.getInstance().getTime();
        	DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        	String today = formatter.format(date);
        	orderRequest.setCreateAt(today);
        	Order order = Order.builder()
        	        .id(orderRequest.getId())
//        	        .orderId(orderRequest.setId(UUID.randomUUID().toString())));
            		.itemId(orderRequest.getItemId())
            		.quantity(orderRequest.getQuantity())
            		.createAt(orderRequest.getCreateAt())
            		.price(product.getPrice()*orderRequest.getQuantity())
            		.userId(orderRequest.getUserId())
            		.address(orderRequest.getAddress())
                    .build();
    		orderRepository.insert(order);
    		log.info("order{} is saved", order.getId());
        }
    }
	
	public List<Order> getAllOrders() {
        List<Order> orders = orderRepository.findAll();
//        return orders.stream().map(this::mapToProductResponse).toList();
        return orders;
    }
	
	private OrderResponse mapToProductResponse(Order order) {
        return OrderResponse.builder()
        		.id(order.getId())
        		.itemId(order.getItemId())
        		.quantity(order.getQuantity())
        		.createAt(order.getCreateAt())
        		.price(order.getPrice())
        		.userId(order.getUserId())
        		.address(order.getAddress())
                .build();
    }
	
	public List<OrderResponse> getOrdersByUsername(String username){
		List<Order> orders = orderRepository.findAll();
		List<Order> order = new ArrayList<>(); 
		for (Order order2 :orders) {
			if (order2.getUserId().equals(username)) {
				order.add(order2);
			}
		}
		return order.stream().map(this::mapToProductResponse).toList();
	}
	
	public void deleteOrder(String id) {
	    Order order = orderRepository.getOrderById(id);
        if (order==null){
            throw new Universal("Order Invalid");
        }
        orderRepository.deleteById(id);
    }

    public Order getOrderById(String id) {
        List<Order> orders = orderRepository.findAll();
        for (Order order : orders) {
            if (id.equals(order.getId())) {
                return order;}
        }
        throw new Universal("Order Invalid ");
    }
}