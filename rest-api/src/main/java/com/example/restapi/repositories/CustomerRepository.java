package com.example.restapi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.restapi.domain.Customer;

/**
 * Customer repository class for database interaction
 */
@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

    /**
     * Getting customer by customer Id
     * @param customerId
     * @return
     */
    Customer getCustomerByCustomerId(Long customerId);
}
