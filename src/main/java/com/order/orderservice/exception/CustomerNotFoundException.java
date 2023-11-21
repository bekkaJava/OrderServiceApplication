package com.order.orderservice.exception;

public class CustomerNotFoundException extends RuntimeException {
    public CustomerNotFoundException(String message, Object... args) {
        super(String.format(message, args));
    }
}
