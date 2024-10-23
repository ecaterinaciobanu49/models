package com.models.models.repositories;

import com.models.models.allModels.Account;
import org.springframework.data.repository.CrudRepository;

public interface AccountRepository extends CrudRepository<Account, Long> {
    Account save(Account account);
}
