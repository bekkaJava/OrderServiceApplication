package com.order.orderservice.handler;

import com.order.orderservice.exception.CategoryNotFoundException;
import com.order.orderservice.exception.CustomerNotFoundException;
import com.order.orderservice.exception.OrderNotFoundException;
import com.order.orderservice.exception.ProductNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@ControllerAdvice
public class EntityRestExceptionHandler {


    @ExceptionHandler(CategoryNotFoundException.class)
    public ResponseEntity<EntityErrorResponse> questionNotFound(CategoryNotFoundException e) {

        EntityErrorResponse error = new EntityErrorResponse(
                e.getMessage(),
                NOT_FOUND,
                System.currentTimeMillis());

        return new ResponseEntity<>(error, NOT_FOUND);
    }


    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<EntityErrorResponse> questionNotFound(ProductNotFoundException e) {

        EntityErrorResponse error = new EntityErrorResponse(
                e.getMessage(),
                NOT_FOUND,
                System.currentTimeMillis());

        return new ResponseEntity<>(error, NOT_FOUND);
    }


    @ExceptionHandler(CustomerNotFoundException.class)
    public ResponseEntity<EntityErrorResponse> questionNotFound(CustomerNotFoundException e) {

        EntityErrorResponse error = new EntityErrorResponse(
                e.getMessage(),
                NOT_FOUND,
                System.currentTimeMillis());

        return new ResponseEntity<>(error, NOT_FOUND);
    }


    @ExceptionHandler(OrderNotFoundException.class)
    public ResponseEntity<EntityErrorResponse> questionNotFound(OrderNotFoundException e) {

        EntityErrorResponse error = new EntityErrorResponse(
                e.getMessage(),
                NOT_FOUND,
                System.currentTimeMillis());

        return new ResponseEntity<>(error, NOT_FOUND);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<EntityErrorResponse> exception(Exception e) {

        EntityErrorResponse error = new EntityErrorResponse(
                e.getMessage(),
                INTERNAL_SERVER_ERROR,
                System.currentTimeMillis());

        return new ResponseEntity<>(error, INTERNAL_SERVER_ERROR);

    }

}
