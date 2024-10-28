package com.models.models.repositories;

import com.models.models.allModels.Loan;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LoanRepository extends JpaRepository<Loan, Long> {
    Loan save(Loan loan);
}
