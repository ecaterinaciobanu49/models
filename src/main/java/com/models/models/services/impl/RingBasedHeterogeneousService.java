package com.models.models.services.impl;

import com.models.models.allModels.*;
import com.models.models.repositories.*;
import com.models.models.services.MainService;
import com.models.models.services.RequestSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RingBasedHeterogeneousService implements MainService {
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
    public List<Customer> getAllCustomers( int steps) {
        return customerRepository.findAll();
    }

    //TODO change
    @Override
    public Customer createNewCustomer(Customer customer, int steps) {
        if(steps == 0) {
            return customerRepository.save(customer);
        } else {
            steps--;
//            RequestSender.
        }

        return null;
    }

    @Override
    public Customer getCostumerBySubjectCode(String subjectCode, int steps) {
        return customerRepository.findBySubjectCode(subjectCode);
    }

    @Override
    public Customer editCustomer(String subjectCode, String newEmail, int steps) {
        Customer customer = customerRepository.findBySubjectCode(subjectCode);
        customer.setEmail(newEmail);
        return customerRepository.save(customer);
    }

    @Override
    public void deleteCostumer(String subjectCode, int steps) {
        Customer customerToDelete = customerRepository.findBySubjectCode(subjectCode);
        customerRepository.deleteById(customerToDelete.getCustomerId());
    }

    @Override
    public Account addNewAccount(Account account, int steps) {
        return accountRepository.save(account);
    }

    @Override
    public List<Account> getAllAccountsBySubjectCode(String subjectCode, int steps) {
        Customer customer = customerRepository.findBySubjectCode(subjectCode);
        return accountRepository.findAllAccountsByCustomerId(customer.getCustomerId());
    }

    @Override
    public Account getAccountByAccountNumber(String accountNumber, int steps) {
        return accountRepository.findByAccountNumber(accountNumber);
    }

    @Override
    public Account editAccount(String accountNumber, Double balance, int steps) {
        Account account = accountRepository.findByAccountNumber(accountNumber);
        account.setBalance(balance);
        return accountRepository.save(account);
    }

    @Override
    public void closeAccount(String accountNumber, int steps) {
        Account account = accountRepository.findByAccountNumber(accountNumber);
        account.setStatus(Status.CLOSED);
        accountRepository.save(account);
    }

    @Override
    public Transaction addNewTransaction(Transaction transaction, int steps) {
        return transactionRepository.save(transaction);
    }

    @Override
    public List<Transaction> getAllTransactionByAccountId(Long accountId, int steps) {
        return transactionRepository.findAllTransactionsByAccountId(accountId);
    }

    @Override
    public Transaction getTransactionById(Long transactionId, int steps) {
        return transactionRepository.findByTransactionId(transactionId);
    }

    @Override
    public Loan addNewLoan(Loan loan, int steps) {
        return loanRepository.save(loan);
    }


    @Override
    public List<Loan> retrieveLoansForACustumer(String subjectCode, int steps) {
        Customer customer = customerRepository.findBySubjectCode(subjectCode);
        return loanRepository.findAllByCostumerId(customer.getCustomerId());
    }
    @Override
    public Loan getLoanById(Long loanId, int steps) {
        return loanRepository.findByLoanId(loanId);
    }

    @Override
    public Loan updateOutstandingAmount(Long loanId, Double balance, int steps) {
        Loan loan = loanRepository.findByLoanId(loanId);
        double currentAmount = loan.getOutstandingAmount();
        loan.setOutstandingAmount(currentAmount - balance);
        return loanRepository.save(loan);
    }

    @Override
    public void closeLoan(Long loanId, int steps) {
        Loan loan = loanRepository.findByLoanId(loanId);
        loan.setStatus(LoanStatus.CLOSED);
        loanRepository.save(loan);
    }

    @Override
    public Card addNewCard(Card card, int steps) {
        return cardRepository.save(card);
    }

    @Override
    public List<Card> getCardsBySubjectCode(String subjectCode, int steps) {
        Customer customer = customerRepository.findBySubjectCode(subjectCode);
        return cardRepository.findAllCardsByCustomerId(customer.getCustomerId());
    }

    @Override
    public Card getCardById(Long cardId, int steps) {
        return cardRepository.findByCardId(cardId);
    }
}
