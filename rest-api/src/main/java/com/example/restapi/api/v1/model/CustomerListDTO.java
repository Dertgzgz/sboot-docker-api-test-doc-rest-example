package com.example.restapi.api.v1.model;

import java.util.List;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Customer list data transfer object model
 */
@ApiModel(value = "Customer List", description = "customer list")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerListDTO {

    private List<CustomerDTO> customers;
}
