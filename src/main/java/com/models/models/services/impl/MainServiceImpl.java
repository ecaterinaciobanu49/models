package com.models.models.services.impl;

import com.models.models.allModels.*;
import com.models.models.repositories.*;
import com.models.models.services.MainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MainServiceImpl implements MainService {

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

    @Override
    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    @Override
    public Customer createNewCustomer(Customer customer) {
        return customerRepository.save(customer);
    }

    @Override
    public Customer getCostumerBySubjectCode(String subjectCode) {
        return customerRepository.findBySubjectCode(subjectCode);
    }

    @Override
    public Customer editCustomer(String subjectCode, String newEmail) {
        Customer customer = customerRepository.findBySubjectCode(subjectCode);
        customer.setEmail(newEmail);
        return customerRepository.save(customer);
    }

    @Override
    public void deleteCostumer(String subjectCode) {
        Customer customerToDelete = customerRepository.findBySubjectCode(subjectCode);
        customerRepository.deleteById(customerToDelete.getCustomerId());
    }

    @Override
    public Account addNewAccount(Account account) {
        return accountRepository.save(account);
    }

    @Override
    public List<Account> getAllAccountsBySubjectCode(String subjectCode) {
        Customer customer = customerRepository.findBySubjectCode(subjectCode);
        return accountRepository.findAllAccountsByCustomerId(customer.getCustomerId());
    }

    @Override
    public Account getAccountByAccountNumber(String accountNumber) {
        return accountRepository.findByAccountNumber(accountNumber);
    }

    @Override
    public Account editAccount(String accountNumber, Double balance) {
        Account account = accountRepository.findByAccountNumber(accountNumber);
        account.setBalance(balance);
        return accountRepository.save(account);
    }

    @Override
    public void closeAccount(String accountNumber) {
        Account account = accountRepository.findByAccountNumber(accountNumber);
        account.setStatus(Status.CLOSED);
        accountRepository.save(account);
    }

    @Override
    public Transaction addNewTransaction(Transaction transaction) {
        return transactionRepository.save(transaction);
    }

    @Override
    public List<Transaction> getAllTransactionByAccountId(Long accountId) {
        return transactionRepository.findAllTransactionsByAccountId(accountId);
    }

    @Override
    public Transaction getTransactionById(Long transactionId) {
        return transactionRepository.findByTransactionId(transactionId);
    }

    @Override
    public Loan addNewLoan(Loan loan) {
        return loanRepository.save(loan);
    }


    @Override
    public List<Loan> retrieveLoansForACustumer(String subjectCode) {
        Customer customer = customerRepository.findBySubjectCode(subjectCode);
        return loanRepository.findAllByCostumerId(customer.getCustomerId());
    }
    @Override
    public Loan getLoanById(Long loanId) {
        return loanRepository.findByLoanId(loanId);
    }

    @Override
    public Loan updateOutstandingAmount(Long loanId, Double balance) {
        Loan loan = loanRepository.findByLoanId(loanId);
        double currentAmount = loan.getOutstandingAmount();
        loan.setOutstandingAmount(currentAmount - balance);
        return loanRepository.save(loan);
    }

    @Override
    public void closeLoan(Long loanId) {
        Loan loan = loanRepository.findByLoanId(loanId);
        loan.setStatus(LoanStatus.CLOSED);
        loanRepository.save(loan);
    }

    @Override
    public Card addNewCard(Card card) {
        return cardRepository.save(card);
    }

    @Override
    public List<Card> getCardsBySubjectCode(String subjectCode) {
        Customer customer = customerRepository.findBySubjectCode(subjectCode);
        return cardRepository.findAllCardsByCustomerId(customer.getCustomerId());
    }

    @Override
    public Card getCardById(Long cardId) {
        return cardRepository.findByCardId(cardId);
    }


}
