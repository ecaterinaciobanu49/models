package com.models.models.controllers;

import com.models.models.allModels.Account;
import com.models.models.allModels.Customer;
import com.models.models.repositories.AccountRepository;
import com.models.models.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class MainController {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private AccountRepository accountRepository;

    @GetMapping("/allCustomers")
    List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    @PostMapping("/customer")
    Customer createNewCustomer(@RequestBody Customer customer) {
        return customerRepository.save(customer);
    }

    @GetMapping("/findCustomerByLastNameAndFirstName")
    Customer getCostumerByLastNameAndFirstName(@RequestBody String lastName, @RequestBody String firstName) {
        return customerRepository.findByLastnameAndFirstname(lastName, firstName);
    }

    @GetMapping("/findCustomerBySubjectCode/{subjectCode}")
    Customer getCostumerBySubjectCode(@PathVariable String subjectCode) {
        return customerRepository.findBySubjectCode(subjectCode);
    }


    //TODO edit this method
    @PutMapping("editCustomer/{subjectCode}")
    Customer editCustomer(@PathVariable String subjectCode, @RequestBody Customer editedCustomer) {
        return customerRepository.save(editedCustomer);
    }

    @DeleteMapping("/deleteCustumer/{subjectCode}")
    void deleteCostumer(@PathVariable String subjectCode) {
        Customer customerToDelete = customerRepository.findBySubjectCode(subjectCode);
        customerRepository.deleteById(customerToDelete.getCustomerId());
    }

    @PostMapping("/addNewAccount")
    Account addNewAccount(@RequestBody Account account) {
        return accountRepository.save(account);
    }

    @PostMapping("/getAccountsBySubjcetCode/{subjectCode}")
    List<Account> getAllAccountsBySubjectCode(@PathVariable String subjectCode) {
        Customer customer = customerRepository.findBySubjectCode(subjectCode);
        return accountRepository.findAllAccountsByCustomerId(customer.getCustomerId());
    }
}
