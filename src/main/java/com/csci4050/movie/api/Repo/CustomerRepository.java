package com.csci4050.movie.api.Repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.csci4050.movie.api.models.*;

@Repository
public interface CustomerRepository extends UserRepository {
    
    //@Query("SELECT c FROM Customer c WHERE c.email = ?1")
    Optional<Customer> findCustomerByEmail(String email);
}