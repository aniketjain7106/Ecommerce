package com.cg.order.Exception;

import lombok.Getter;

@Getter
public class InventoryInsufficientException extends RuntimeException {

    private String itemId;
    private long currentQuantity;
    private long requestQuantity;

    public String getItemId() {
		return itemId;
	}

	public long getCurrentQuantity() {
		return currentQuantity;
	}

	public long getRequestQuantity() {
		return requestQuantity;
	}

	public InventoryInsufficientException(String message, String itemId, long currentQuantity, long requestQuantity) {
        super(message);
        this.itemId = itemId;
        this.currentQuantity = currentQuantity;
        this.requestQuantity = requestQuantity;
    }
}
