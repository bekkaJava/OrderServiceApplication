package com.order.orderservice.dto.orderitem;

public record OrderItemDTO(
        Long productId,
        Double price,
        Integer quantity

) {
}
