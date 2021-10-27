package com.csci4050.movie.api.customer;

import com.csci4050.movie.api.CodeGenerator;
import com.csci4050.movie.api.customer.CustomerRepository;
import com.csci4050.movie.api.model.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private CodeGenerator codeGenerator;

    public Optional<Customer> getCustomerById(Long id) {
        return customerRepository.findById(id);
    }

    public Optional<Customer> getCustomerByEmail(String email) {
        if (customerRepository.findByEmail(email) == null) {
            return Optional.empty();
        } else {
            return customerRepository.findByEmail(email);
        }
    }

    public Optional<Customer> getCustomerByEmailAndPassword(String email, String password) {
        if (customerRepository.findByEmailAndPassword(email, password) == null) {
            return Optional.empty();
        } else {
            return customerRepository.findByEmailAndPassword(email, password);
        }
    }

    public List<Customer> getAllCustomers() {
        return (List<Customer>) customerRepository.findAll();
    };

    public Optional<Customer> saveCustomer(Customer customer) {
        Optional<Customer> customerByEmail = customerRepository.findByEmail(customer.getEmail());
        if (customerByEmail.isPresent()) {
            return customerByEmail;
        } else {
            Customer registeredCustomer = customer;
            registeredCustomer.setActive(false);
            registeredCustomer.setType("customer");
            registeredCustomer.setVerificationCode(codeGenerator.generateVerificationCode());
            //Encoding
            registeredCustomer.setPassword(passwordEncoder.encode(customer.getPassword()));

            if (customer.getPaymentCard() != null) {
                registeredCustomer.setPaymentCard(passwordEncoder.encode(customer.getPaymentCard()));
            }
            customerRepository.save(registeredCustomer);
            return Optional.empty();
        }
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
        customerRepository.deleteById(id);
    }
}
