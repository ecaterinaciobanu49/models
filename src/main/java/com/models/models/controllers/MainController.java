package com.models.models.controllers;

import com.models.models.allModels.Account;
import com.models.models.allModels.Customer;
import com.models.models.allModels.Status;
import com.models.models.repositories.AccountRepository;
import com.models.models.repositories.CustomerRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
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

    @GetMapping("/getAnAccountByAccountNumber")
    Account getAccountByAccountNumber(@RequestBody String accountNumber) {
        return accountRepository.findByAccountNumber(accountNumber);
    }


    //TODO edit method
    @PutMapping("/editAccount/{accountNumber}")
    Account editAccount(@PathVariable String accountNumber, @RequestBody Account editedAccount) {
        Account account = accountRepository.findByAccountNumber(accountNumber);
        BeanUtils.copyProperties(account, editedAccount);

        return accountRepository.save(account);
    }

    @PutMapping("/closeAccount/{accountNumber}")
    void closeAccount(@PathVariable String accountNumber) {
        Account account = accountRepository.findByAccountNumber(accountNumber);
        account.setStatus(Status.CLOSED);
        accountRepository.save(account);
    }


}
