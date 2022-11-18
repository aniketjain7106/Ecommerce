package com.cg.order.Exception;

import lombok.Getter;

@Getter
public class Universal extends RuntimeException{
    
    public Universal(String message) {
        super(message);
    }

}
