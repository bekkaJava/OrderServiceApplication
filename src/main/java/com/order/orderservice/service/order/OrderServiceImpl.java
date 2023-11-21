package com.order.orderservice.service.order;

import com.order.orderservice.dto.order.OrderDTO;
import com.order.orderservice.dto.order.OrderDTOMapper;
import com.order.orderservice.dto.orderitem.OrderItemDTO;
import com.order.orderservice.exception.CustomerNotFoundException;
import com.order.orderservice.exception.OrderNotFoundException;
import com.order.orderservice.model.Customer;
import com.order.orderservice.model.Order;
import com.order.orderservice.model.OrderItem;
import com.order.orderservice.repository.CustomerRepository;
import com.order.orderservice.repository.OrderRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@AllArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;

    private final CustomerRepository customerRepository;

    private final OrderDTOMapper orderDTOMapper;

    @Override
    public OrderDTO findOrderById(Long orderId) {

        return orderRepository.findById(orderId)
                .map(orderDTOMapper)
                .orElseThrow(() -> new OrderNotFoundException("Order with id %d not found", orderId));
    }

    @Override
    @Transactional
    public OrderDTO createOrder(Long customerId, List<OrderItemDTO> orderItems) {

        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new CustomerNotFoundException("Customer with id %d not found", customerId));

        Order order = new Order();
        order.setOrderDate(LocalDateTime.now());

        for (OrderItemDTO orderItemDTO : orderItems) {

            OrderItem orderItem = OrderItem.builder()
                    .productId(orderItemDTO.productId())
                    .price(orderItemDTO.price())
                    .quantity(orderItemDTO.quantity())
                    .build();


            order.getOrderItems().add(orderItem);
        }

        customer.addOrder(order);
        orderRepository.save(order);

        return orderDTOMapper.apply(order);

    }

    @Override
    public List<OrderDTO> findAllOrdersByCustomerId(Long customerId) {

        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new CustomerNotFoundException("Customer with id %d not found", customerId));

        return customer.getOrders().stream()
                .map(orderDTOMapper)
                .toList();
    }
}
