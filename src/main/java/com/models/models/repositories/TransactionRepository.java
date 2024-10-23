package com.models.models.repositories;

import com.models.models.allModels.Transaction;
import org.springframework.data.repository.CrudRepository;

public interface TransactionRepository extends CrudRepository<Transaction, Long> {
    Transaction save(Transaction transaction);
}
