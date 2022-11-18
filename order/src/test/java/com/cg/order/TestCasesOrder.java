package com.cg.order;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.security.PublicKey;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.cg.order.model.Order;
import com.cg.order.model.repository.OrderRepository;
import com.cg.order.service.OrderService;


@SpringBootTest(classes = {TestCasesOrder.class})
public class TestCasesOrder {
    
    @Mock
    OrderRepository orderRepository;
    
    @InjectMocks
    OrderService orderService;
    
    public List<Order> myorder;
    
    @Test
    public void test_getAllOrders() {
        List<Order> myorder = new ArrayList<Order>();
        myorder.add(new Order("1","3","aniket1",1,"12/10/2022",20,"qwerty"));
        myorder.add(new Order("2","3","aniket2",1,"12/10/2022",20,"qwerty"));
        when(orderRepository.findAll()).thenReturn(myorder);
        assertEquals(2, orderService.getAllOrders().size());
    }
    
    @Test
    public void test_deleteOrderbyid() {
        Order order = new Order("1","3","aniket1",1,"12/10/2022",20,"qwerty");
        when(orderRepository.getOrderById("1")).thenReturn(order);
        orderService.deleteOrder("1");
        verify(orderRepository,times(1)).deleteById("1");
    }

}
