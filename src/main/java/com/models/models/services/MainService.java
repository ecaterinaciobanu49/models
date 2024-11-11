package com.models.models.services;

import com.models.models.allModels.*;

import java.util.List;

public interface MainService {
    List<Customer> getAllCustomers();

    Customer createNewCustomer(Customer customer);

    Customer getCostumerBySubjectCode(String subjectCode);

    Customer editCustomer(String subjectCode, String newEmail);

    void deleteCostumer(String subjectCode);

    Account addNewAccount(Account account);

    List<Account> getAllAccountsBySubjectCode(String subjectCode);

    Account getAccountByAccountNumber(String accountNumber);

    Account editAccount(String accountNumber, Double balance);

    void closeAccount(String accountNumber);

    Transaction addNewTransaction(Transaction transaction);

    List<Transaction> getAllTransactionByAccountId(Long accountId);

    Transaction getTransactionById(Long transactionId);

    Loan addNewLoan(Loan loan);

    List<Loan> retrieveLoansForACustumer(String subjectCode);

    Loan getLoanById(Long loanId);

    Loan updateOutstandingAmount(Long loanId, Double balance);

    void closeLoan(Long loanId);

    Card addNewCard(Card card);

    List<Card> getCardsBySubjectCode(String subjectCode);

    Card getCardById(Long cardId);
}
