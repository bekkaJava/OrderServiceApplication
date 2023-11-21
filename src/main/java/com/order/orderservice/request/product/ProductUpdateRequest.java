package com.order.orderservice.request.product;

import lombok.Getter;

@Getter
public class ProductUpdateRequest {

    private String productName;

    private String description;

    private Double price;
}
