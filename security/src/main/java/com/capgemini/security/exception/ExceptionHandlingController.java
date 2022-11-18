package com.capgemini.security.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.capgemini.security.payload.CommonResponse;


@ControllerAdvice
public class ExceptionHandlingController extends ResponseEntityExceptionHandler {
    
    @ExceptionHandler(Universal.class)
    public ResponseEntity<CommonResponse> universal(Universal ex) {
        CommonResponse response = new CommonResponse();
        response.setCode(HttpStatus.NOT_FOUND.value());
        response.setMessage(ex.getMessage());
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

}
