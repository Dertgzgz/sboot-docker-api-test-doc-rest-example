package com.example.restapi.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.example.restapi.api.v1.mapper.CustomerMapper;
import com.example.restapi.api.v1.model.CustomerDTO;
import com.example.restapi.bootstrap.Bootstrap;
import com.example.restapi.domain.Customer;
import com.example.restapi.repositories.CategoryRepository;
import com.example.restapi.repositories.CustomerRepository;
import com.example.restapi.repositories.VendorRepository;

@DataJpaTest
class CustomerServiceImplIT {

	@Autowired
	CustomerRepository customerRepository;

	@Autowired
	CategoryRepository categoryRepository;

	@Autowired
	VendorRepository vendorRepository;

	private CustomerService customerService;

	@BeforeAll
	public void setUp() throws Exception {

		// Load the default data set for testing
		Bootstrap bootstrap = new Bootstrap(categoryRepository, customerRepository, vendorRepository);
		bootstrap.run();

		customerService = new CustomerServiceImpl(customerRepository, CustomerMapper.INSTANCE);
	}

	@Test
	void testPatchCustomer() throws Exception {

		Long customerId = getCustomerId();

		Customer savedCustomer = customerRepository.getCustomerByCustomerId(customerId);

		CustomerDTO customerDTO = new CustomerDTO();
		customerDTO.setFirstName("TestUpdatedName");

		customerService.patchCustomer(customerId, customerDTO);

		Customer getUpdatedCustomer = customerRepository.getCustomerByCustomerId(customerId);

		assertNotNull(getUpdatedCustomer);
		assertEquals("TestUpdatedName",getUpdatedCustomer.getFirstName());

	}

	/**
	 * Get first customer Id for testing
	 * 
	 * @return
	 */
	private Long getCustomerId() {

		List<Customer> customers = customerRepository.findAll();

		return customers.get(0).getCustomerId();
	}

}
