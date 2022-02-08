package com.example.restapi.controllers.v1;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.example.restapi.api.v1.model.CustomerDTO;
import com.example.restapi.exceptions.RestResponseEntityExceptionHandler;
import com.example.restapi.services.CustomerService;
import com.fasterxml.jackson.databind.ObjectMapper;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class CustomerControllerTest {

	@Mock
	private CustomerService customerService;

	@InjectMocks
	private CustomerController customerController;

	private MockMvc mockMvc;

	@BeforeAll
	public void setUp() throws Exception {
		MockitoAnnotations.openMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(customerController)
				.setControllerAdvice(new RestResponseEntityExceptionHandler()).build();
	}

	@Test
	void testGetCustomers() throws Exception {

		// Given
		CustomerDTO customerDTOOne = new CustomerDTO();
		CustomerDTO customerDTOTwo = new CustomerDTO();

		List<CustomerDTO> customerDTOList = new ArrayList<CustomerDTO>();
		customerDTOList.add(customerDTOOne);
		customerDTOList.add(customerDTOTwo);

		// When
		when(customerService.getCustomers()).thenReturn(customerDTOList);

		// Then
		mockMvc.perform(get("/api/v1/customers/").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
				.andExpect(jsonPath("$.customers", hasSize(2)));
	}

	@Test
	void testGetCustomerById() throws Exception {

		// Given
		CustomerDTO customerDTO = new CustomerDTO();
		customerDTO.setFirstName("first");
		customerDTO.setLastName("last");

		// When
		when(customerService.getCustomerById(anyLong())).thenReturn(customerDTO);

		mockMvc.perform(get("/api/v1/customers/" + 1L).contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andExpect(jsonPath("$.firstName", equalTo("first")));

	}

	@Test
	void testCreateCustomer() throws Exception {

		// Given
		CustomerDTO customerDTO = new CustomerDTO();
		customerDTO.setFirstName("Rob");
		customerDTO.setLastName("Smith");

		CustomerDTO saveCustomer = new CustomerDTO();
		saveCustomer.setLastName(customerDTO.getLastName());
		saveCustomer.setFirstName(customerDTO.getFirstName());
		saveCustomer.setCustomerUrl("/api/v1/customers/1");

		// When
		when(customerService.createCustomer(customerDTO)).thenReturn(saveCustomer);

		// Then
		mockMvc.perform(post("/api/v1/customers/").contentType(MediaType.APPLICATION_JSON)
				.content(new ObjectMapper().writeValueAsString(customerDTO))).andExpect(status().isCreated())
				.andExpect(jsonPath("$.firstName", equalTo("Rob")))
				.andExpect(jsonPath("$.customerUrl", equalTo("/api/v1/customers/1")));

	}

	@Test
	void testUpdateCustomer() throws Exception {

		// Given
		CustomerDTO customerDTO = new CustomerDTO();
		customerDTO.setFirstName("Rob");
		customerDTO.setLastName("Smith");

		CustomerDTO saveCustomer = new CustomerDTO();
		saveCustomer.setLastName(customerDTO.getLastName());
		saveCustomer.setFirstName(customerDTO.getFirstName());
		saveCustomer.setCustomerUrl("/api/v1/customers/1");

		// When
		when(customerService.updateCustomer(anyLong(), ArgumentMatchers.any(CustomerDTO.class)))
				.thenReturn(saveCustomer);

		// Then
		mockMvc.perform(put("/api/v1/customers/1").contentType(MediaType.APPLICATION_JSON)
				.content(new ObjectMapper().writeValueAsString(customerDTO))).andExpect(status().isOk())
				.andExpect(jsonPath("$.firstName", equalTo("Rob")))
				.andExpect(jsonPath("$.customerUrl", equalTo("/api/v1/customers/1")));

	}

	@Test
	public void testDeleteCustomer() throws Exception {

		mockMvc.perform(delete("/api/v1/customers/1").contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());

		verify(customerService).deleteCustomerById(anyLong());

	}
}