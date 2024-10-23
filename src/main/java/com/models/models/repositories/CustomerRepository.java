package com.models.models.repositories;

import com.models.models.allModels.Customer;
import org.springframework.data.repository.CrudRepository;

public interface CustomerRepository extends CrudRepository<Customer, Long> {
    Customer save(Customer customer);
}
