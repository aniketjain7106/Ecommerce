package com.cg.order.dto;

import java.util.Date;

import org.springframework.data.annotation.Id;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderResponse {
	
	@Id
    private String id;
    private String itemId;
    private int quantity;
    private String createAt;
    private int price;
    private String userId;
    private String address;
    
}
