package com.order.orderservice.controller;

import com.order.orderservice.dto.customer.CustomerDTO;
import com.order.orderservice.model.Customer;
import com.order.orderservice.service.customer.CustomerService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.http.HttpStatus.ACCEPTED;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.ResponseEntity.noContent;
import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping("api/v4/customer")
@AllArgsConstructor
public class CustomerController {

    private final CustomerService customerService;


    @GetMapping("/{customerId}")
    public ResponseEntity<CustomerDTO> findCustomerById(@PathVariable Long customerId) {

        return ok(customerService.findCustomerById(customerId));

    }

    @GetMapping("/")
    public ResponseEntity<List<CustomerDTO>> findAllCustomer() {

        return ok(customerService.findAllCustomer());
    }


    @PostMapping("/")
    public ResponseEntity<Void> createCustomer(@RequestBody Customer customer) {

        customerService.saveCustomer(customer);

        return new ResponseEntity<>(CREATED);
    }


    @PutMapping("/{customerId}")
    public ResponseEntity<CustomerDTO> updateCustomer(@PathVariable Long customerId,
                                                      @RequestBody Customer customer) {

        return new ResponseEntity<>(customerService.updateCustomer(customerId, customer), ACCEPTED);
    }


    @DeleteMapping("/{customerID}")
    public ResponseEntity<Void> deleteCustomerById(@PathVariable Long customerID) {

        customerService.deleteCustomerById(customerID);

        return noContent().build();
    }
}
