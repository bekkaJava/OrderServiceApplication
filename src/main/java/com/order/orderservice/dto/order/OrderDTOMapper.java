package com.order.orderservice.dto.order;

import com.order.orderservice.dto.orderitem.OrderItemDTOMapper;
import com.order.orderservice.model.Order;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.function.Function;
@Service
@AllArgsConstructor
public class OrderDTOMapper implements Function<Order, OrderDTO> {

    private final OrderItemDTOMapper orderItemDTOMapper;

    @Override
    public OrderDTO apply(Order order) {

        return new OrderDTO(order.getCustomer().getId(),
                order.getOrderDate(),
                order.getOrderItems()
                        .stream()
                        .map(orderItemDTOMapper)
                        .toList());
    }
}
