package com.csci4050.movie.api.customer;

import com.csci4050.movie.api.model.Customer;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerRepository extends CrudRepository<Customer, Long> {
    Optional<Customer> findByEmail(String email);
    Optional<Customer> findByEmailAndPassword(String email, String password);
}
