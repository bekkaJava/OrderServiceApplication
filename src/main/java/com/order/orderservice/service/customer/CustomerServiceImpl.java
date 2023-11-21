package com.order.orderservice.service.customer;

import com.order.orderservice.dto.customer.CustomerDTO;
import com.order.orderservice.dto.customer.CustomerDTOMapper;
import com.order.orderservice.exception.CustomerNotFoundException;
import com.order.orderservice.model.Address;
import com.order.orderservice.model.Customer;
import com.order.orderservice.repository.CustomerRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.util.Objects.nonNull;

@Service
@AllArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;

    private final CustomerDTOMapper customerDTOMapper;

    @Override
    public CustomerDTO findCustomerById(Long customerId) {
        return customerRepository.findById(customerId)
                .map(customerDTOMapper)
                .orElseThrow(() -> new CustomerNotFoundException("Customer with id %d not found", customerId));
    }

    @Override
    public List<CustomerDTO> findAllCustomer() {

        return customerRepository.findAll()
                .stream()
                .map(customerDTOMapper)
                .toList();
    }

    @Override
    public void saveCustomer(Customer customer) {
        customerRepository.save(customer);

    }

    @Override
    public void deleteCustomerById(Long customerId) {

        if (!customerRepository.existsById(customerId)) {
            throw new CustomerNotFoundException("Customer with id %d not found", customerId);
        }

        customerRepository.deleteById(customerId);

    }

    @Override
    public CustomerDTO updateCustomer(Long customerId, Customer customer) {

        Customer updateCustomer = customerRepository.findById(customerId)
                .orElseThrow(() -> new CustomerNotFoundException("Customer with id %d not found", customerId));


        if (nonNull(customer.getFirstName()) && !customer.getFirstName().isEmpty()) {
            updateCustomer.setFirstName(customer.getFirstName());
        }

        if (nonNull(customer.getLastName()) && !customer.getLastName().isEmpty()) {
            updateCustomer.setLastName(customer.getLastName());
        }

        if (nonNull(customer.getEmail()) && !customer.getEmail().isEmpty()) {
            updateCustomer.setEmail(customer.getEmail());
        }

        Address customerAddress = customer.getAddress();

        if (nonNull(customerAddress)) {
            if (nonNull(customerAddress.getCity()) && !customerAddress.getCity().isEmpty()) {
                updateCustomer.getAddress().setCity(customerAddress.getCity());

                if (nonNull(customerAddress.getStreetAddress()) && !customerAddress.getStreetAddress().isEmpty()) {
                    updateCustomer.getAddress().setStreetAddress(customerAddress.getStreetAddress());

                    if (nonNull(customerAddress.getState()) && !customerAddress.getState().isEmpty()) {
                        updateCustomer.getAddress().setState(customerAddress.getState());

                        if (nonNull(customerAddress.getPostalCode()) && !customerAddress.getPostalCode().isEmpty()) {
                            updateCustomer.getAddress().setPostalCode(customerAddress.getPostalCode());
                        }
                    }
                }
            }
        }

        customerRepository.save(updateCustomer);
        return customerDTOMapper.apply(updateCustomer);
    }

}