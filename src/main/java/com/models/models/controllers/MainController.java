package com.models.models.controllers;

import com.models.models.allModels.*;
import com.models.models.repositories.*;
import com.models.models.services.MainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class MainController {

    @Autowired
    private MainService mainService;

    @GetMapping("/allCustomers")
    List<Customer> getAllCustomers() {
        return mainService.getAllCustomers();
    }

    @PostMapping("/customer")
    Customer createNewCustomer(@RequestBody Customer customer) {
        return mainService.createNewCustomer(customer);
    }

    @GetMapping("/findCustomerBySubjectCode/{subjectCode}")
    Customer getCostumerBySubjectCode(@PathVariable String subjectCode) {
        return mainService.getCostumerBySubjectCode(subjectCode);
    }



    @PutMapping("editCustomerEmail/{subjectCode}")
    Customer editCustomer(@PathVariable String subjectCode, @RequestBody String newEmail) {
        return mainService.editCustomer(subjectCode, newEmail);
    }

    @DeleteMapping("/deleteCustumer/{subjectCode}")
    void deleteCostumer(@PathVariable String subjectCode) {
        mainService.deleteCostumer(subjectCode);
    }

    @PostMapping("/addNewAccount")
    Account addNewAccount(@RequestBody Account account) {
        return mainService.addNewAccount(account);
    }

    @GetMapping("/getAccountsBySubjcetCode/{subjectCode}")
    List<Account> getAllAccountsBySubjectCode(@PathVariable String subjectCode) {
        return mainService.getAllAccountsBySubjectCode(subjectCode);
    }

    @GetMapping("/getAnAccountByAccountNumber/{accountNumber}")
    Account getAccountByAccountNumber(@PathVariable String accountNumber) {
        return mainService.getAccountByAccountNumber(accountNumber);
    }


    @PutMapping("/updateAccountBalance/{accountNumber}")
    Account editAccount(@PathVariable String accountNumber, @RequestBody Double balance) {
        return mainService.editAccount(accountNumber, balance);
    }

    @PutMapping("/closeAccount/{accountNumber}")
    void closeAccount(@PathVariable String accountNumber) {
        mainService.closeAccount(accountNumber);
    }

    @PostMapping("/addTransaction")
    Transaction addNewTransaction(@RequestBody Transaction transaction) {
        return mainService.addNewTransaction(transaction);
    }

    @GetMapping("/getAllTransactionByAccountId/{accountId}")
    List<Transaction> getAllTransactionByAccountId(@PathVariable Long accountId) {
        return mainService.getAllTransactionByAccountId(accountId);
    }

    @GetMapping("/getTransactionByTransactionId/{transactionId}")
    Transaction getTransactionById(@PathVariable Long transactionId) {
        return mainService.getTransactionById(transactionId);
    }

    @PostMapping("/createNewLoan")
    Loan addNewLoan(@RequestBody Loan loan) {
        return mainService.addNewLoan(loan);
    }

    @GetMapping("/retrieveLoansForACustumer/{subjectCode}")
    List<Loan> retrieveLoansForACustumer(@PathVariable String subjectCode) {
        return mainService.retrieveLoansForACustumer(subjectCode);
    }

    @GetMapping("/getLoanById/{loanId}")
    Loan getLoanById(@PathVariable Long loanId) {
        return mainService.getLoanById(loanId);
    }

    @PutMapping("/updateAmount/{loanId}")
    Loan updateOutstandingAmount(@PathVariable Long loanId, @RequestBody Double balance) {
        return mainService.updateOutstandingAmount(loanId, balance);
    }

    @PutMapping("/closeLoan/{loanId}")
    void closeLoan(@PathVariable Long loanId) {
        mainService.closeLoan(loanId);
    }

    @PostMapping("/createNewCard")
    Card addNewCard(@RequestBody Card card) {
        return mainService.addNewCard(card);
    }

    @GetMapping("/getCardsBySubjectCode/{subjectCode}")
    List<Card> getCardsBySubjectCode(@PathVariable String subjectCode) {
        return  mainService.getCardsBySubjectCode(subjectCode);
    }

    @GetMapping("/cards/{cardId}")
    Card getCardById(@PathVariable Long cardId) {
        return mainService.getCardById(cardId);
    }

}
