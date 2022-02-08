package com.example.restapi.api.v1.mapper;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.example.restapi.api.v1.model.VendorDTO;
import com.example.restapi.domain.Vendor;


class VendorMapperTest {

    public static final String FRUITS = "Fruits";
    public static final String SHOPS = "Shops";
    VendorMapper vendorMapper = VendorMapper.INSTANCE;

    @BeforeEach
    public static void setUp() throws Exception {
    }

    @Test
    void vendorToVendorDTO() {

        // Given
        Vendor vendor = new Vendor();
        vendor.setVendorName(FRUITS);
        vendor.setVendorId(1L);

        // When
        VendorDTO vendorDTO = vendorMapper.vendorToVendorDTO(vendor);

        // Then
        assertEquals(vendorDTO.getVendorName(), FRUITS);
    }

    @Test
    void vendorDTOToVendor() {

        // Given
        VendorDTO vendorDTO = new VendorDTO();
        vendorDTO.setVendorName(SHOPS);

        // When
        Vendor vendor = vendorMapper.vendorDTOToVendor(vendorDTO);

        // Then
        assertEquals(vendor.getVendorName(), SHOPS);

    }
}