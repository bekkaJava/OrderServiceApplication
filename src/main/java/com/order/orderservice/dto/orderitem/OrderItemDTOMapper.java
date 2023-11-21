package com.order.orderservice.dto.orderitem;

import com.order.orderservice.model.OrderItem;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class OrderItemDTOMapper implements Function<OrderItem, OrderItemDTO> {
    @Override
    public OrderItemDTO apply(OrderItem orderItem) {

        return new OrderItemDTO(orderItem.getProductId(),
                orderItem.getPrice(),
                orderItem.getQuantity());
    }
}
