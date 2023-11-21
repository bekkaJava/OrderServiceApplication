package com.order.orderservice.exception;

public class CategoryNotFoundException extends RuntimeException {

    public CategoryNotFoundException(String message, Object... args) {
        super(String.format(message, args));
    }
}
