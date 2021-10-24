package com.csci4050.movie.api;

import com.csci4050.movie.api.model.Customer;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    public Optional<Customer> getCustomerById(Long id) {
        return customerRepository.findById(id);
    }

    public List<Customer> getAllCustomers() {
        return (List<Customer>) customerRepository.findAll();
    };

    public Customer createCustomer(Customer customer) {
        return customerRepository.save(customer);
    }

    public Customer updateCustomer(Long id, Customer updatedCustomer) {
        Customer customer = customerRepository.findById(id).get();
        customer.setFirstName(updatedCustomer.getFirstName());
        customer.setLastName(updatedCustomer.getLastName());
        customer.setPassword(updatedCustomer.getPassword());
        customer.setAddress(updatedCustomer.getAddress());
        return customerRepository.save(customer);
    }

    public void deleteCustomer(Long id) {
        customerRepository.delete(customerRepository.findById(id).get());
    }
}
