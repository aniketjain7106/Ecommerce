package com.cg.product.exception;

import lombok.Getter;

@Getter
public class ProductNotFoundException extends RuntimeException {
    
    private String productId;

    public String getProductId() {
        return productId;
    }

    public ProductNotFoundException(String message, String productId) {
        super(message);
        this.productId = productId;
    }

}
