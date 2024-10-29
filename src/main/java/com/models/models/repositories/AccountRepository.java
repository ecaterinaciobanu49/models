package com.models.models.repositories;

import com.models.models.allModels.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AccountRepository extends JpaRepository<Account, Long> {
    Account save(Account account);

    @Query("FROM Account AS acc LEFT JOIN acc.customer AS c WHERE c.customerId = ?1")
    List<Account> findAllAccountsByCustomerId(Long customerId);

    @Query("select a from Account a where a.accountNumber = ?1")
    Account findByAccountNumber(String accountNumber);
}
