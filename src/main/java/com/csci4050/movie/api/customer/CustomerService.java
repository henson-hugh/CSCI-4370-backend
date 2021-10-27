package com.csci4050.movie.api.customer;

import com.csci4050.movie.api.CodeGenerator;
import com.csci4050.movie.api.model.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
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

    @Autowired
    private JavaMailSender mailSender;

    public Optional<Customer> getCustomerById(int id) {
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

    public Optional<Customer> getCustomerByLastNameAndFirstName(String lastName, String firstName) {
        if (customerRepository.findByLastNameAndFirstName(lastName, firstName) == null) {
            return Optional.empty();
        } else {
            return customerRepository.findByLastNameAndFirstName(lastName, firstName);
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
            //Encoding
            registeredCustomer.setPassword(passwordEncoder.encode(customer.getPassword()));

            if (customer.getPaymentCard() != null) {
                registeredCustomer.setPaymentCard(passwordEncoder.encode(customer.getPaymentCard()));
            }
            //Verification
            registeredCustomer.setVcode(codeGenerator.generateVerificationCode());

            customerRepository.save(registeredCustomer);
            return Optional.empty();
        }
    }

    public Customer updateCustomer(int id, Customer updatedCustomer) {
        if (customerRepository.findById(id).isPresent()) {
            Customer customer = customerRepository.findById(id).get();
            customer.setFirstName(updatedCustomer.getFirstName());
            customer.setLastName(updatedCustomer.getLastName());
            customer.setPassword(updatedCustomer.getPassword());
            customer.setAddress(updatedCustomer.getAddress());
            customer.setVcode(updatedCustomer.getVcode());
            customer.setActive(updatedCustomer.isActive());
            return customerRepository.save(customer);
        } else {
            return new Customer();
        }
    }

    public Customer verifyCustomer(int id, Customer verifiedCustomer) {
        if (customerRepository.findById(id).isPresent()) {
            Customer customer = customerRepository.findById(id).get();
            customer.setVcode(verifiedCustomer.getVcode());
            customer.setActive(verifiedCustomer.isActive());
            return customerRepository.save(customer);
        } else {
            return new Customer();
        }
    }

    public Customer updateCustomerByName(String lastName, String firstName, Customer updatedCustomer) {
        if (customerRepository.findByLastNameAndFirstName(firstName, lastName).isPresent()) {
            Customer customer = customerRepository.findByLastNameAndFirstName(firstName, lastName).get();
            customer.setFirstName(updatedCustomer.getFirstName());
            customer.setLastName(updatedCustomer.getLastName());
            customer.setPassword(updatedCustomer.getPassword());
            customer.setPassword(passwordEncoder.encode(updatedCustomer.getPassword()));
            customer.setPaymentCard(passwordEncoder.encode(updatedCustomer.getPaymentCard()));
            customer.setAddress(updatedCustomer.getAddress());
            return customerRepository.save(customer);
        } else {
            return new Customer();
        }
    }

    public void deleteCustomer(int id) {
        customerRepository.deleteById(id);
    }
}
