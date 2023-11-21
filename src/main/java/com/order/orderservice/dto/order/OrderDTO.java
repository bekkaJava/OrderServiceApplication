package com.order.orderservice.dto.order;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.order.orderservice.dto.orderitem.OrderItemDTO;

import java.time.LocalDateTime;
import java.util.List;

public record OrderDTO(
        Long customerId,
        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
        LocalDateTime orderDate,
        List<OrderItemDTO> orderItems

) {
}
