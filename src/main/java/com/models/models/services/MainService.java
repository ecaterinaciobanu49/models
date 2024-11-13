package com.models.models.services;

import com.models.models.allModels.*;

import java.util.List;

public interface MainService {
    List<Customer> getAllCustomers(int steps);

    Customer createNewCustomer(Customer customer, int steps);

    Customer getCostumerBySubjectCode(String subjectCode, int steps);

    Customer editCustomer(String subjectCode, String newEmail, int steps);

    void deleteCostumer(String subjectCode, int steps);

    Account addNewAccount(Account account, int steps);

    List<Account> getAllAccountsBySubjectCode(String subjectCode, int steps);

    Account getAccountByAccountNumber(String accountNumber, int steps);

    Account editAccount(String accountNumber, Double balance, int steps);

    void closeAccount(String accountNumber, int steps);

    Transaction addNewTransaction(Transaction transaction, int steps);

    List<Transaction> getAllTransactionByAccountId(Long accountId, int steps);

    Transaction getTransactionById(Long transactionId, int steps);

    Loan addNewLoan(Loan loan, int steps);

    List<Loan> retrieveLoansForACustumer(String subjectCode, int steps);

    Loan getLoanById(Long loanId, int steps);

    Loan updateOutstandingAmount(Long loanId, Double balance, int steps);

    void closeLoan(Long loanId, int steps);

    Card addNewCard(Card card, int steps);

    List<Card> getCardsBySubjectCode(String subjectCode, int steps);

    Card getCardById(Long cardId, int steps);
}
