package com.example.restapi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.restapi.domain.Vendor;

/**
 * Vendor repository class for database interaction
 */
@Repository
public interface VendorRepository extends JpaRepository<Vendor, Long> {

}
