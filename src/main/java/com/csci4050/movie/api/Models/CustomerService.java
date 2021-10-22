package com.csci4050.movie.api.Models;

import java.util.List;
import java.util.Optional;

import com.csci4050.movie.api.Repo.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {

	private final CustomerRepository customerRepository;

	@Autowired
	public CustomerService (CustomerRepository customerRepository) {
		this.customerRepository = customerRepository;
	}

    public List<User> getUsers() {
		return customerRepository.findAll();
	}
    
	public void addNewCustomer(Customer customer) {
		Optional<Customer> customerByEmail = customerRepository.findCustomerByEmail(customer.getEmail());
		/*if (customerByEmail.isPresent()) {
			throw new IllegalStateException("Email is taken");
		}*/

		customerRepository.save(customer);
		//System.out.println(customer);
	}
}
