package com.models.models.controllers;

import com.models.models.allModels.Customer;
import com.models.models.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class MainController {

    @Autowired
    private CustomerRepository customerRepository;

    @GetMapping("/allCustomers")
    List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

}
