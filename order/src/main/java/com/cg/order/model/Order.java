package com.cg.order.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document(collection = "order")
public class Order {

    @Id
    private String id;
    private String itemId;
    private String userId;
    private int quantity;
    private String createAt;
    private int price;
    private String address;
    
}

