package com.order.orderservice.service.customer;

import com.order.orderservice.dto.customer.CustomerDTO;
import com.order.orderservice.model.Customer;

import java.util.List;

public interface CustomerService {

    CustomerDTO findCustomerById(Long customerId);
    List<CustomerDTO> findAllCustomer();
    void saveCustomer(Customer customer);
    void deleteCustomerById(Long customerId);
    CustomerDTO updateCustomer(Long customerId, Customer customer);

}
