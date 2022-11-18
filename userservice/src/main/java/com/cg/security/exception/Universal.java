package com.cg.security.exception;

import lombok.Getter;

@Getter
public class Universal extends RuntimeException{
    
    public Universal(String message) {
        super(message);
    }

}
