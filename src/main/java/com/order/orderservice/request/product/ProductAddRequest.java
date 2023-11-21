package com.order.orderservice.request.product;

import lombok.Getter;

@Getter
public class ProductAddRequest {

    private String productName;

    private String description;

    private Double price;

    private Long categoryId;
}
