package com.example.restapi.schedulers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.example.restapi.repositories.CustomerRepository;
import com.example.restapi.repositories.VendorRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class ScheduledTasks {



    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private VendorRepository vendorRepository;

    /**
     * Method for deleting all record from all the tables
     * every 15 minutes
     */
    @Scheduled(fixedRate = 900000)
    public void databaseCleanUp() {

        log.info("Clean up initiated");

        customerRepository.deleteAll();
        vendorRepository.deleteAll();

        log.info("Customers Deleted => " + customerRepository.findAll().size());
        log.info("Vendors Deleted => " + vendorRepository.findAll().size());
    }
}
