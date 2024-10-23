package com.models.models.repositories;

import com.models.models.allModels.Loan;
import org.springframework.data.repository.CrudRepository;

public interface LoanRepository extends CrudRepository<Loan, Long> {
    Loan save(Loan loan);
}
