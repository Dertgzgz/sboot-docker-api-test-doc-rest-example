package com.example.restapi.api.v1.model;


import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Customer data transfer object model
 */
@ApiModel(value = "Customer", description = "customer")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerDTO {

    @ApiModelProperty(value = "First Name", required = true, allowEmptyValue = false, position = 0)
    @NotBlank
    @Size(min = 1, max = 255)
    private String firstName;


    @ApiModelProperty(value = "Last Name", required = true, allowEmptyValue = false, position = 1)
    @NotBlank
    @Size(min = 1, max = 255)
    private String lastName;

    @ApiModelProperty(value = "Only available in response", readOnly = true, position = 2)
    private String customerUrl;
}
