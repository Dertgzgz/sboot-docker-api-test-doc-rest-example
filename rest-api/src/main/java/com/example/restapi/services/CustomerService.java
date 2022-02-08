package com.example.restapi.services;

import java.util.List;

import com.example.restapi.api.v1.model.CustomerDTO;

/**
 * Customer Interface
 */
public interface CustomerService {

    // Get all customers
    List<CustomerDTO> getCustomers();

    // Get customer by id
    CustomerDTO getCustomerById(Long customerId);

    // Create new customer
    CustomerDTO createCustomer(CustomerDTO customerDTO);

    // Put customer
    CustomerDTO updateCustomer(Long customerId, CustomerDTO customerDTO);

    // Patch customer
    CustomerDTO patchCustomer(Long customerId, CustomerDTO customerDTO);

    void deleteCustomerById(Long customerId);
}
