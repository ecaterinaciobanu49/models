package com.models.models.repositories;

import com.models.models.allModels.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
    Customer save(Customer customer);
}
