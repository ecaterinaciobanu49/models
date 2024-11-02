package com.models.models.repositories;

import com.models.models.allModels.Loan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LoanRepository extends JpaRepository<Loan, Long> {
    Loan save(Loan loan);

    @Query("FROM Loan AS l LEFT JOIN l.customer AS c WHERE c.customerId = ?1")
    List<Loan> findAllByCostumerId(Long customerId);

    Loan findByLoanId(Long loanId);
}
