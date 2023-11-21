package com.order.orderservice.dto.customer;

import com.order.orderservice.model.Address;

public record CustomerDTO(
        String firstName,
        String lastName,
        String email,
        Address address) {
}
