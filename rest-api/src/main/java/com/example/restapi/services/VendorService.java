package com.example.restapi.services;


import java.util.List;

import com.example.restapi.api.v1.model.VendorDTO;

/**
 * Vendor Interface
 */
public interface VendorService {

    // Get all vendors
    List<VendorDTO> getVendors();

    // Get vendor by id
    VendorDTO getVendorById(Long vendorId);

    // Create new vendor
    VendorDTO createVendor(VendorDTO vendorDTO);

    // Put vendor
    VendorDTO updateVendor(Long vendorId, VendorDTO vendorDTO);

    // Patch customer
    VendorDTO patchVendor(Long vendorId, VendorDTO vendorDTO);

    // Delete vendor
    void deleteVendorById(Long vendorId);
}
