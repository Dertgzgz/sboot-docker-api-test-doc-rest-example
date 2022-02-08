package com.example.restapi.api.v1.model;

import java.util.List;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Vendor list data transfer object model
 */
@ApiModel(value = "Vendor List", description = "vendor list")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class VendorListDTO {

    public List<VendorDTO> vendors;
}
