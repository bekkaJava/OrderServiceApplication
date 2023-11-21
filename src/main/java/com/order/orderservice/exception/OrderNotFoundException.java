package com.order.orderservice.exception;

public class OrderNotFoundException extends RuntimeException{
    public OrderNotFoundException(String message, Object... args) {
        super(String.format(message, args));
    }
}
