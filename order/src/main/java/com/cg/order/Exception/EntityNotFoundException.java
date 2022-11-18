package com.cg.order.Exception;

import lombok.Getter;

@Getter
public class EntityNotFoundException extends RuntimeException {

    private String itemId;

    public String getItemId() {
		return itemId;
	}

	public EntityNotFoundException(String message, String itemId) {
        super(message);
        this.itemId = itemId;
    }
}
