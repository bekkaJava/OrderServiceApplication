package com.order.orderservice.controller;

import com.order.orderservice.dto.order.OrderDTO;
import com.order.orderservice.dto.orderitem.OrderItemDTO;
import com.order.orderservice.service.order.OrderService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping("api/v3/order")
@AllArgsConstructor
public class OrderController {

    private final OrderService orderService;


    @GetMapping("/{orderId}")
    public ResponseEntity<OrderDTO> findOrderById(@PathVariable Long orderId) {

        return ok(orderService.findOrderById(orderId));

    }


    @GetMapping("/findAllOrdersByCustomerId/{customerId}")

    public ResponseEntity<List<OrderDTO>> findAllOrdersByCustomerId(@PathVariable Long customerId) {

        return ok(orderService.findAllOrdersByCustomerId(customerId));
    }


    @PostMapping("/createOrder/{customerId}")

    public ResponseEntity<OrderDTO> createOrder(@PathVariable Long customerId,
                                                @RequestBody List<OrderItemDTO> orderItems) {

        return new ResponseEntity<>(orderService.createOrder(customerId, orderItems), CREATED);

    }

}
