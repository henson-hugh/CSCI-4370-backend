package com.csci4050.movie.api.customer;

import com.csci4050.movie.api.customer.CustomerRepository;
import com.csci4050.movie.api.model.Customer;
import net.bytebuddy.utility.RandomString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

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

    public Customer registerCustomer(Customer customer) {
        Customer registeredCustomer = customer;
        registeredCustomer.setPassword(passwordEncoder.encode(customer.getPassword()));
        registeredCustomer.setPaymentCard(passwordEncoder.encode(customer.getPaymentCard()));
        System.out.println("***********" + registeredCustomer.getPaymentCard());
        return customerRepository.save(registeredCustomer);
    }

    public Customer updateCustomer(int id, Customer updatedCustomer) {
        if (customerRepository.findById(id).isPresent()) {
            Customer customer = customerRepository.findById(id).get();
            customer.setFirstName(updatedCustomer.getFirstName());
            customer.setLastName(updatedCustomer.getLastName());
            customer.setPassword(updatedCustomer.getPassword());
            customer.setAddress(updatedCustomer.getAddress());
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
