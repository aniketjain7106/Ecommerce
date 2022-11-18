package com.cg.order.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.cg.order.controller.CommonResponse;

@ControllerAdvice
public class ExceptionHandlingController extends ResponseEntityExceptionHandler {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<CommonResponse> entityNotFound(EntityNotFoundException ex) {
        CommonResponse response = new CommonResponse();
        response.setCode(HttpStatus.NOT_FOUND.value());
        response.setMessage(ex.getMessage() + ", itemId: " + ex.getItemId());

        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(InventoryInsufficientException.class)
    public ResponseEntity<CommonResponse> inventoryInsufficient(InventoryInsufficientException ex) {
        CommonResponse response = new CommonResponse();
        response.setCode(HttpStatus.NOT_ACCEPTABLE.value());
        response.setMessage(ex.getMessage() + ", itemId: " + ex.getItemId() + ", current quantity: " +
                ex.getCurrentQuantity() + ", request quantity: " + ex.getRequestQuantity());

        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }
    
    @ExceptionHandler(Universal.class)
    public ResponseEntity<CommonResponse> universal(Universal ex) {
        CommonResponse response = new CommonResponse();
        response.setCode(HttpStatus.NOT_FOUND.value());
        response.setMessage(ex.getMessage());
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }
}
