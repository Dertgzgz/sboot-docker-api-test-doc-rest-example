package com.example.restapi.api.v1.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.example.restapi.api.v1.model.VendorDTO;
import com.example.restapi.domain.Vendor;

/**
 * Data transfer object mapper using MapStruct
 */
@Mapper
public interface VendorMapper {


    VendorMapper INSTANCE = Mappers.getMapper(VendorMapper.class);

    VendorDTO vendorToVendorDTO(Vendor vendor);
    Vendor vendorDTOToVendor(VendorDTO vendorDTO);
}
