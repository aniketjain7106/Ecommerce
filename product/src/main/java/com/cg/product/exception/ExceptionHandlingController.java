package com.cg.product.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.cg.product.controller.CommonResponse;

@ControllerAdvice
public class ExceptionHandlingController extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<CommonResponse> entityNotFound(ProductNotFoundException ex) {
        CommonResponse response = new CommonResponse();
        response.setCode(HttpStatus.NOT_FOUND.value());
        response.setMessage(ex.getMessage() + ", productId: " + ex.getProductId());
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

//    @ExceptionHandler(InventoryInsufficientException.class)
//    public ResponseEntity<CommonResponse> inventoryInsufficient(InventoryInsufficientException ex) {
//        CommonResponse response = new CommonResponse();
//        response.setCode(HttpStatus.NOT_ACCEPTABLE.value());
//        response.setMessage(ex.getMessage() + ", itemId: " + ex.getItemId() + ", current quantity: " +
//                ex.getCurrentQuantity() + ", request quantity: " + ex.getRequestQuantity());
//
//        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
//    }
    
    @ExceptionHandler(Universal.class)
    public ResponseEntity<CommonResponse> universal(Universal ex) {
        CommonResponse response = new CommonResponse();
        response.setCode(HttpStatus.NOT_FOUND.value());
        response.setMessage(ex.getMessage());
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

}
