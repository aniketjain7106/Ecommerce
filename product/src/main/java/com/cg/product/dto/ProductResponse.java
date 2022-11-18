package com.cg.product.dto;

import java.math.BigDecimal;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductResponse {
	private String id;
    private String name;
    private String description;
    private int price;
    private int quantity;
}