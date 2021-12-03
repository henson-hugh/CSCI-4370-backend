package com.csci4050.movie.api.customer;

import com.csci4050.movie.api.model.Booking;
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
    private JavaMailSender mailSender;

    public Optional<Customer> getCustomerById(int id) {
        return customerRepository.findById(id);
    }

    public List<Customer> getAllCustomersWithPromo() {
        return customerRepository.findAllByGetPromo(true);
    }

    public Optional<Customer> getCustomerByUserid(int uid){ return customerRepository.findByUserid(uid); }


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
        customerRepository.save(customer);
        return Optional.empty();
    }

    public Customer updateCustomer(int id, Customer updatedCustomer) {
        if (customerRepository.findById(id).isPresent()) {
            Customer customer = customerRepository.findById(id).get();
            customer.setFirstName(updatedCustomer.getFirstName());
            if (updatedCustomer.getLastName() != null && !updatedCustomer.getLastName().isEmpty()) {
                customer.setLastName(updatedCustomer.getLastName());
            }
            if (updatedCustomer.getStreet() != null && !updatedCustomer.getStreet().isEmpty()) {
                customer.setStreet(updatedCustomer.getStreet());
            }
            if (updatedCustomer.getCity() != null && !updatedCustomer.getCity().isEmpty()) {
                customer.setCity(updatedCustomer.getCity());
            }
            if (updatedCustomer.getState() != null && !updatedCustomer.getState().isEmpty()) {
                customer.setState(updatedCustomer.getState());
            }
            if (updatedCustomer.getZip() != null && !updatedCustomer.getZip().isEmpty()) {
                customer.setZip(updatedCustomer.getZip());
            }
            if (updatedCustomer.getPhone() != null && !updatedCustomer.getPhone().isEmpty()) {
                customer.setPhone(updatedCustomer.getPhone());
            }
            customer.setGetPromo(updatedCustomer.isGetPromo());

            return customerRepository.save(customer);
        } else {
            return new Customer();
        }
    }

    public Customer verifyCustomer(int cid) {
        Customer customer = customerRepository.findById(cid).get();
        customer.setActive(true);
        customer.setVerified(true);
        return customerRepository.save(customer);
    }

    public void deleteCustomer(int id) {
        customerRepository.deleteById(id);
    }

    public Customer suspendCustomer(int cid, boolean suspend) {
        Customer customer = customerRepository.findById(cid).get();
        customer.setSuspend(suspend);
        return customerRepository.save(customer);
    }

    public Optional<Customer> editCustomer(int cid, Customer customer) {
        Customer updatedCustomer = customer;
        updatedCustomer.setCid(cid);
        updatedCustomer.setUserid(customerRepository.findById(cid).get().getUserid());
        updatedCustomer.setFirstName(customer.getFirstName());
        updatedCustomer.setLastName(customer.getLastName());
        updatedCustomer.setPhone(customer.getPhone());
        if (customer.getStreet() != null) {
            updatedCustomer.setStreet(customer.getStreet());
        }
        if (customer.getCity() != null) {
            updatedCustomer.setCity(customer.getCity());
        }
        if (customer.getState() != null) {
            updatedCustomer.setState(customer.getState());
        }
        if (customer.getZip() != null) {
            updatedCustomer.setZip(customer.getZip());
        }
        updatedCustomer.setActive(customer.isActive());
        updatedCustomer.setVerified(customer.isVerified());
        updatedCustomer.setGetPromo(customer.isGetPromo());
        return Optional.of(customerRepository.save(updatedCustomer));
    }


}
