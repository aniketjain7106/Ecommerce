package com.cg.order.dto;

import java.math.BigDecimal;
import java.util.Date;

import org.springframework.data.annotation.Id;

import lombok.*;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderRequest {
	
	@Id
    private String id;
    private String itemId;
    private int quantity;
    private String createAt;
    private int price;
    private String userId;
    private String address;
}
