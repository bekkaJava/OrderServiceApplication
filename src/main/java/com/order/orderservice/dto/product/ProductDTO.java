package com.order.orderservice.dto.product;

public record ProductDTO(String name,
                         String description,
                         Double price,
                         String categoryName) {
}
