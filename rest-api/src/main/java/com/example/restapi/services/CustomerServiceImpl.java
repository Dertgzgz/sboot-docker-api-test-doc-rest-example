package com.example.restapi.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.example.restapi.api.v1.mapper.CustomerMapper;
import com.example.restapi.api.v1.model.CustomerDTO;
import com.example.restapi.controllers.v1.CustomerController;
import com.example.restapi.domain.Customer;
import com.example.restapi.exceptions.ResourceNotFoundException;
import com.example.restapi.repositories.CustomerRepository;

/**
 * Customer service implementation class
 */
@Service
public class CustomerServiceImpl implements CustomerService {

    /**
     * Injecting dependencies
     */
    private CustomerRepository customerRepository;
    private CustomerMapper customerMapper;

    /**
     * Constructor
     * @param customerRepository
     * @param customerMapper
     */
    public CustomerServiceImpl(CustomerRepository customerRepository, CustomerMapper customerMapper) {
        this.customerRepository = customerRepository;
        this.customerMapper = customerMapper;
    }

    /**
     * Method implementation for getting all customers from the database
     * @return
     */
    @Override
    public List<CustomerDTO> getCustomers() {
        return customerRepository.findAll()
                .stream()
                .map(customer -> {
                    CustomerDTO customerDTO = customerMapper.customerToCustomerDTO(customer);
                    customerDTO.setCustomerUrl(getCustomerUrl(customer.getCustomerId()));
                    return customerDTO;
                })
                .collect(Collectors.toList());
    }


    /**
     * Method implementation for getting customer by provided customer Id
     * @param customerId
     * @return
     */
    @Override
    public CustomerDTO getCustomerById(Long customerId) {

        return customerRepository.findById(customerId)
                .map(customerMapper::customerToCustomerDTO)
                .map(customerDTO -> {
                    customerDTO.setCustomerUrl(getCustomerUrl(customerId));
                    return customerDTO;
                })
                .orElseThrow(ResourceNotFoundException::new);
    }

    /**
     * Method implementation for creating a new customer by provided customer post data
     * @param customerDTO
     * @return
     */
    @Override
    public CustomerDTO createCustomer(CustomerDTO customerDTO) {

        Customer customer = customerMapper.customerDTOToCustomer(customerDTO);

        return createOrUpdateCustomerHelper(customer);
    }

    /**
     * Helper method for create or update customer
     * @param customer
     * @return CustomerDTO
     */
    private CustomerDTO createOrUpdateCustomerHelper(Customer customer) {

        Customer saveCustomer = customerRepository.save(customer);

        CustomerDTO saveCustomerDTO = customerMapper.customerToCustomerDTO(saveCustomer);

        saveCustomerDTO.setCustomerUrl(getCustomerUrl(saveCustomer.getCustomerId()));

        return saveCustomerDTO;
    }

    /**
     * Method implementation for updating customer by customer Id
     * @param customerId
     * @param customerDTO
     * @return
     */
    @Override
    public CustomerDTO updateCustomer(Long customerId, CustomerDTO customerDTO) {

        Customer customer = customerMapper.customerDTOToCustomer(customerDTO);
        customer.setCustomerId(customerId);

        return createOrUpdateCustomerHelper(customer);
    }

    /**
     * Method implementation for partial updating customer by customer Id
     * @param customerId
     * @param customerDTO
     * @return
     */
    @Override
    public CustomerDTO patchCustomer(Long customerId, CustomerDTO customerDTO) {

        return customerRepository.findById(customerId).map(customer -> {

            if(customerDTO.getFirstName() != null) {
                customer.setFirstName(customerDTO.getFirstName());
            }

            if(customerDTO.getLastName() != null) {
                customer.setLastName(customerDTO.getLastName());
            }

            CustomerDTO saveCustomerDTO = customerMapper.customerToCustomerDTO(customerRepository.save(customer));
            saveCustomerDTO.setCustomerUrl(getCustomerUrl(customerId));
            return saveCustomerDTO;

        }).orElseThrow(ResourceNotFoundException::new);

    }

    /**
     * Method implementation for deleting customer by provided customer Id
     * @param customerId
     */
    @Override
    public void deleteCustomerById(Long customerId) {
        customerRepository.deleteById(customerId);
    }

    /**
     * Helper method for generating customer url
     * @param customerId
     * @return
     */
    private String getCustomerUrl(Long customerId) {

        return CustomerController.BASE_URL + "/" + customerId;
    }
}
