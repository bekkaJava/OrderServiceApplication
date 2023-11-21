package com.order.orderservice.service.order;

import com.order.orderservice.dto.order.OrderDTO;
import com.order.orderservice.dto.orderitem.OrderItemDTO;
import com.order.orderservice.model.Order;

import java.util.List;

public interface OrderService {

    OrderDTO findOrderById(Long orderId);

    OrderDTO createOrder(Long customerId, List<OrderItemDTO> orderItems);

    List<OrderDTO> findAllOrdersByCustomerId(Long customerId);
}
