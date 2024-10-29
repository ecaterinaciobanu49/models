package com.models.models.repositories;

import com.models.models.allModels.Account;
import com.models.models.allModels.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    Transaction save(Transaction transaction);

    @Query("FROM Transaction AS t LEFT JOIN t.account AS acc WHERE acc.accountId = ?1")
    List<Transaction> findAllTransactionsByAccountId(Long accountId);

    Transaction findByTransactionId(Long transactionId);
}
