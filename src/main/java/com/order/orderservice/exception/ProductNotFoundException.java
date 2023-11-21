package com.order.orderservice.exception;

public class ProductNotFoundException extends RuntimeException{

    public ProductNotFoundException(String message, Object... args) {
        super(String.format(message, args));
    }
}
