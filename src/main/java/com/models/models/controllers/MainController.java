package com.models.models.controllers;

import com.models.models.allModels.*;
import com.models.models.services.MainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class MainController {

    @Qualifier("mainServiceImpl")
    @Autowired
    private MainService mainService;



    @GetMapping("/allCustomers")
    List<Customer> getAllCustomers(@RequestParam(value = "steps", defaultValue = "3") int steps) {
        return mainService.getAllCustomers(steps);
    }

    @PostMapping("/customer")
    Customer createNewCustomer(@RequestBody Customer customer ,@RequestParam(value = "steps", defaultValue = "3") int steps) {
        return mainService.createNewCustomer(customer, steps);
    }

    @GetMapping("/findCustomerBySubjectCode/{subjectCode}")
    Customer getCostumerBySubjectCode(@PathVariable String subjectCode ,@RequestParam(value = "steps", defaultValue = "3") int steps) {
        return mainService.getCostumerBySubjectCode(subjectCode, steps);
    }

    @PutMapping("editCustomerEmail/{subjectCode}")
    Customer editCustomer(@PathVariable String subjectCode, @RequestBody String newEmail ,@RequestParam(value = "steps", defaultValue = "3") int steps) {
        return mainService.editCustomer(subjectCode, newEmail, steps);
    }

    @DeleteMapping("/deleteCustumer/{subjectCode}")
    void deleteCostumer(@PathVariable String subjectCode ,@RequestParam(value = "steps", defaultValue = "3") int steps) {
        mainService.deleteCostumer(subjectCode, steps);
    }

    @PostMapping("/addNewAccount")
    Account addNewAccount(@RequestBody Account account ,@RequestParam(value = "steps", defaultValue = "3") int steps) {
        return mainService.addNewAccount(account, steps);
    }

    @GetMapping("/getAccountsBySubjcetCode/{subjectCode}")
    List<Account> getAllAccountsBySubjectCode(@PathVariable String subjectCode ,@RequestParam(value = "steps", defaultValue = "3") int steps) {
        return mainService.getAllAccountsBySubjectCode(subjectCode, steps);
    }

    @GetMapping("/getAnAccountByAccountNumber/{accountNumber}")
    Account getAccountByAccountNumber(@PathVariable String accountNumber ,@RequestParam(value = "steps", defaultValue = "3") int steps) {
        return mainService.getAccountByAccountNumber(accountNumber, steps);
    }


    @PutMapping("/updateAccountBalance/{accountNumber}")
    Account editAccount(@PathVariable String accountNumber, @RequestBody Double balance ,@RequestParam(value = "steps", defaultValue = "3") int steps) {
        return mainService.editAccount(accountNumber, balance, steps);
    }

    @PutMapping("/closeAccount/{accountNumber}")
    void closeAccount(@PathVariable String accountNumber ,@RequestParam(value = "steps", defaultValue = "3") int steps) {
        mainService.closeAccount(accountNumber, steps);
    }

    @PostMapping("/addTransaction")
    Transaction addNewTransaction(@RequestBody Transaction transaction ,@RequestParam(value = "steps", defaultValue = "3") int steps) {
        return mainService.addNewTransaction(transaction, steps);
    }

    @GetMapping("/getAllTransactionByAccountId/{accountId}")
    List<Transaction> getAllTransactionByAccountId(@PathVariable Long accountId ,@RequestParam(value = "steps", defaultValue = "3") int steps) {
        return mainService.getAllTransactionByAccountId(accountId, steps);
    }

    @GetMapping("/getTransactionByTransactionId/{transactionId}")
    Transaction getTransactionById(@PathVariable Long transactionId,@RequestParam(value = "steps", defaultValue = "3") int steps) {
        return mainService.getTransactionById(transactionId, steps);
    }

    @PostMapping("/createNewLoan")
    Loan addNewLoan(@RequestBody Loan loan ,@RequestParam(value = "steps", defaultValue = "3") int steps) {
        return mainService.addNewLoan(loan, steps);
    }

    @GetMapping("/retrieveLoansForACustumer/{subjectCode}")
    List<Loan> retrieveLoansForACustumer(@PathVariable String subjectCode ,@RequestParam(value = "steps", defaultValue = "3") int steps) {
        return mainService.retrieveLoansForACustumer(subjectCode, steps);
    }

    @GetMapping("/getLoanById/{loanId}")
    Loan getLoanById(@PathVariable Long loanId ,@RequestParam(value = "steps", defaultValue = "3") int steps) {
        return mainService.getLoanById(loanId, steps);
    }

    @PutMapping("/updateAmount/{loanId}")
    Loan updateOutstandingAmount(@PathVariable Long loanId, @RequestBody Double balance ,@RequestParam(value = "steps", defaultValue = "3") int steps) {
        return mainService.updateOutstandingAmount(loanId, balance, steps);
    }

    @PutMapping("/closeLoan/{loanId}")
    void closeLoan(@PathVariable Long loanId, @RequestParam(value = "steps", defaultValue = "3") int steps) {
        mainService.closeLoan(loanId, steps);
    }

    @PostMapping("/createNewCard")
    Card addNewCard(@RequestBody Card card ,@RequestParam(value = "steps", defaultValue = "3") int steps) {
        return mainService.addNewCard(card, steps);
    }

    @GetMapping("/getCardsBySubjectCode/{subjectCode}")
    List<Card> getCardsBySubjectCode(@PathVariable String subjectCode ,@RequestParam(value = "steps", defaultValue = "3") int steps) {
        return  mainService.getCardsBySubjectCode(subjectCode, steps);
    }

    @GetMapping("/cards/{cardId}")
    Card getCardById(@PathVariable Long cardId ,@RequestParam(value = "steps", defaultValue = "3") int steps) {
        return mainService.getCardById(cardId, steps);
    }

}
