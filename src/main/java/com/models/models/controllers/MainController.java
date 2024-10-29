package com.models.models.controllers;

import com.models.models.allModels.*;
import com.models.models.repositories.*;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class MainController {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private LoanRepository loanRepository;

    @Autowired
    private CardRepository cardRepository;

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



    @PutMapping("editCustomerEmail/{subjectCode}")
    Customer editCustomer(@PathVariable String subjectCode, @RequestBody String newEmail) {
        Customer customer = customerRepository.findBySubjectCode(subjectCode);
        customer.setEmail(newEmail);
        return customerRepository.save(customer);
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


    @PutMapping("/updateAccountBalance/{accountNumber}")
    Account editAccount(@PathVariable String accountNumber, @RequestBody Double balance) {
        Account account = accountRepository.findByAccountNumber(accountNumber);
        account.setBalance(balance);

        return accountRepository.save(account);
    }

    @PutMapping("/closeAccount/{accountNumber}")
    void closeAccount(@PathVariable String accountNumber) {
        Account account = accountRepository.findByAccountNumber(accountNumber);
        account.setStatus(Status.CLOSED);
        accountRepository.save(account);
    }

    @PostMapping("/addTransaction")
    Transaction addNewTransaction(@RequestBody Transaction transaction) {
        return transactionRepository.save(transaction);
    }

    @GetMapping("/getAllTransactionByAccountId/{accountId}")
    List<Transaction> getAllTransactionByAccountId(@PathVariable Long accountId) {
        return transactionRepository.findAllTransactionsByAccountId(accountId);
    }

    @GetMapping("/getTransactionByTransactionId/{transactionId}")
    Transaction getTransactionById(@PathVariable Long transactionId) {
        return transactionRepository.findByTransactionId(transactionId);
    }

    @PostMapping("/createNewLoan")
    Loan addNewLoan(@RequestBody Loan loan) {
        return loanRepository.save(loan);
    }

    @GetMapping("/retrieveLoansForACustumer/{subjectCode}")
    List<Loan> retrieveLoansForACustumer(@PathVariable String subjectCode) {
        Customer customer = customerRepository.findBySubjectCode(subjectCode);
        return loanRepository.findAllByCostumerId(customer.getCustomerId());
    }

    @GetMapping("/getLoanById/{loanId}")
    Loan getLoanById(@PathVariable Long loanId) {
        return loanRepository.findByLoanId(loanId);
    }

    @PutMapping("/updateAmount/{loanId}")
    Loan updateOutstandingAmount(@PathVariable Long loanId, @RequestBody Double balance) {
        Loan loan = loanRepository.findByLoanId(loanId);
        double currentAmount = loan.getOutstandingAmount();
        loan.setOutstandingAmount(currentAmount - balance);

        return loanRepository.save(loan);
    }

    @PutMapping("/closeLoan/{loanId}")
    void closeLoan(@PathVariable Long loanId) {
        Loan loan = loanRepository.findByLoanId(loanId);
        loan.setStatus(LoanStatus.CLOSED);
        loanRepository.save(loan);
    }

    @PostMapping("/createNewCard")
    Card addNewCard(@RequestBody Card card) {
        return cardRepository.save(card);
    }

    @GetMapping("/getCardsBySubjectCode/{subjectCode}")
    List<Card> getCardsBySubjectCode(@PathVariable String subjectCode) {
        Customer customer = customerRepository.findBySubjectCode(subjectCode);

        return  cardRepository.findAllCardsByCustomerId(customer.getCustomerId());
    }

    @GetMapping("/cards/{cardId}")
    Card getCardById(@PathVariable Long cardId) {
        return cardRepository.findByCardId(cardId);
    }

}
