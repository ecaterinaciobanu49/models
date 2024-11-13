package com.models.models.services.impl;

import com.models.models.allModels.*;
import com.models.models.repositories.*;
import com.models.models.services.MainService;
import com.models.models.services.RequestSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.awt.geom.QuadCurve2D;
import java.lang.reflect.Array;
import java.util.*;

@Service
public class MasterSlaveImpl implements MainService {
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

    private final List<String> slaves = Arrays.asList("8082", "8021");

    private Queue<Command> commands = new LinkedList<Command>();

    private Thread thread;

    public MasterSlaveImpl() {
        thread = new Thread(() -> {
            synchronized (this) {
                Command command = commands.poll();
                command.execute();
            }
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });

        thread.start();
    }

    @Override
    public List<Customer> getAllCustomers( int steps) {
        return customerRepository.findAll();
    }

    @Override
    public Customer createNewCustomer(Customer customer, int steps) {
        synchronized (this) {
            Command command = new Command() {
                @Override
                public void execute() {
                    for (int i = 0; i < slaves.size(); i++) {
                        RequestSender.createNewCustomer(customer, slaves.get(i));
                    }
                }
            };
            commands.offer(command);
        }
        return customerRepository.save(customer);
    }

    @Override
    public Customer getCostumerBySubjectCode(String subjectCode, int steps) {
        return customerRepository.findBySubjectCode(subjectCode);
    }

    @Override
    public Customer editCustomer(String subjectCode, String newEmail, int steps) {
        synchronized (this) {
            Command command = new Command() {
                @Override
                public void execute() {
                    for (String port : slaves) {
                        RequestSender.editCustomerEmail(subjectCode, newEmail, port);
                    }
                }
            };
            commands.offer(command);
        }
        Customer customer = customerRepository.findBySubjectCode(subjectCode);
        customer.setEmail(newEmail);
        return customerRepository.save(customer);
    }

    @Override
    public void deleteCostumer(String subjectCode, int steps) {
        synchronized (this) {
            Command command = new Command() {
                @Override
                public void execute() {
                    for (String port : slaves) {
                        RequestSender.deleteCustomer(subjectCode, port);
                    }
                }
            };
            commands.offer(command);
        }
        Customer customerToDelete = customerRepository.findBySubjectCode(subjectCode);
        customerRepository.deleteById(customerToDelete.getCustomerId());
    }

    @Override
    public Account addNewAccount(Account account, int steps) {
        synchronized (this) {
            Command command = new Command() {
                @Override
                public void execute() {
                    for (String port : slaves) {
                        RequestSender.addNewAccount(account, port);
                    }
                }
            };
            commands.offer(command);
        }
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
        synchronized (this) {
            Command command = new Command() {
                @Override
                public void execute() {
                    for (String port : slaves) {
                        RequestSender.updateAccountBalance(accountNumber, balance, port);
                    }
                }
            };
            commands.offer(command);
        }
        Account account = accountRepository.findByAccountNumber(accountNumber);
        account.setBalance(balance);
        return accountRepository.save(account);
    }

    @Override
    public void closeAccount(String accountNumber, int steps) {
        synchronized (this) {
            Command command = new Command() {
                @Override
                public void execute() {
                    for (String port : slaves) {
                        RequestSender.closeAccount(accountNumber, port);
                    }
                }
            };
            commands.offer(command);
        }
        Account account = accountRepository.findByAccountNumber(accountNumber);
        account.setStatus(Status.CLOSED);
        accountRepository.save(account);
    }

    @Override
    public Transaction addNewTransaction(Transaction transaction, int steps) {
        synchronized (this) {
            Command command = new Command() {
                @Override
                public void execute() {
                    for (String port : slaves) {
                        RequestSender.addNewTransaction(transaction, port);
                    }
                }
            };
            commands.offer(command);
        }
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
        synchronized (this) {
            Command command = new Command() {
                @Override
                public void execute() {
                    for (String port : slaves) {
                        RequestSender.addNewLoan(loan, port);
                    }
                }
            };
            commands.offer(command);
        }
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
        synchronized (this) {
            Command command = new Command() {
                @Override
                public void execute() {
                    for (String port : slaves) {
                        RequestSender.updateOutstandingAmount(loanId, balance, port);
                    }
                }
            };
            commands.offer(command);
        }
        Loan loan = loanRepository.findByLoanId(loanId);
        double currentAmount = loan.getOutstandingAmount();
        loan.setOutstandingAmount(currentAmount - balance);
        return loanRepository.save(loan);
    }

    @Override
    public void closeLoan(Long loanId, int steps) {
        synchronized (this) {
            Command command = new Command() {
                @Override
                public void execute() {
                    for (String port : slaves) {
                        RequestSender.closeLoan(loanId, port);
                    }
                }
            };
            commands.offer(command);
        }
        Loan loan = loanRepository.findByLoanId(loanId);
        loan.setStatus(LoanStatus.CLOSED);
        loanRepository.save(loan);
    }

    @Override
    public Card addNewCard(Card card, int steps) {
        synchronized (this) {
            Command command = new Command() {
                @Override
                public void execute() {
                    for (String port : slaves) {
                        RequestSender.addNewCard(card, port);
                    }
                }
            };
            commands.offer(command);
        }
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
