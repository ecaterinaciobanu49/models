package com.models.models.repositories;

import com.models.models.allModels.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
    Customer save(Customer customer);


    @Query("select c from Customer c where c.subjectCode = ?1")
    Customer findBySubjectCode(String subjectCode);
}
