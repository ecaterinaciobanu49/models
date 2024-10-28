package com.models.models.repositories;

import com.models.models.allModels.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, Long> {
    Account save(Account account);
}
