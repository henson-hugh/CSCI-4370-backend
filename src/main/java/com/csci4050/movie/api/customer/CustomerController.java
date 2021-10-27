package com.csci4050.movie.api.customer;

import com.csci4050.movie.api.model.Customer;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/customers")
public class CustomerController {
    @Autowired
    private CustomerService customerService;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping
    public List<Customer> getAllCustomers() {
        return customerService.getAllCustomers();
    }

    @GetMapping(value = "/{id}")
    @ResponseBody
    @CrossOrigin(origins = "http://localhost:4200")
    public Customer getCustomerById(@PathVariable("id") int id) {
        Optional<Customer> resultCustomer = customerService.getCustomerById(23);
        if (resultCustomer.equals(Optional.empty())) {
            System.out.println("**************** No Customer by id " + id + " **************");
            return new Customer();
        } else {
            return resultCustomer.get();
        }
    }

    @GetMapping(value = "/email/{email}")
    @ResponseBody
    @CrossOrigin(origins = "http://localhost:4200")
    public Customer getCustomerById(@PathVariable("email") String email) {
        Optional<Customer> resultCustomer = customerService.getCustomerByEmail(email);
        if (resultCustomer.equals(Optional.empty())) {
            System.out.println("**************** No Customer by email " + email + " **************");
            return new Customer();
        } else {
            return resultCustomer.get();
        }
    }

    @PostMapping(value = "/name/{lastName}+{firstName}")
    @CrossOrigin(origins = "http://localhost:4200")
    public ResponseEntity<Customer> updateCustomer(@PathVariable("lastName") String lastName, @PathVariable("firstName") String firstName) {
        Optional<Customer> resultCustomer = customerService.getCustomerByLastNameAndFirstName(lastName, firstName);
        if (resultCustomer.equals(Optional.empty())) {
            System.out.println("**************** No Customer by lastname and firstname " + lastName + " " + firstName + "*******");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(resultCustomer.get());
        } else {
            customerService.updateCustomerByName(lastName, firstName, resultCustomer.get());
            return ResponseEntity.status(HttpStatus.ACCEPTED)
                    .body(resultCustomer.get());
        }
    }
}