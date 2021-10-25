package com.csci4050.movie.api.registration;

import com.csci4050.movie.api.customer.CustomerService;
import com.csci4050.movie.api.model.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class RegistrationController {

    @Autowired
    private CustomerService customerService;

    @PostMapping(value = "/register")
    public ResponseEntity<Customer> registerCustomer(@RequestBody Customer customer) throws Exception {
        String email = customer.getEmail();
            Optional<Customer> resultCustomer = customerService.getCustomerByEmail(email);
            if (resultCustomer != null) {
//                System.out.println("****************User with: " + email + " already exists.**************");
//                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
//                        .body(customer);
                //throw new Exception("User with: " + email + " already exists.");
            }

        return ResponseEntity.status(HttpStatus.ACCEPTED)
                .body(customerService.saveCustomer(customer));
    }
}
