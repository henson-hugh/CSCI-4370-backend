package com.csci4050.movie.api.customer;

import com.csci4050.movie.api.EmailSenderService;
import com.csci4050.movie.api.model.Customer;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/customers")
public class CustomerController {
    @Autowired
    private CustomerService customerService;

    @Autowired
    private EmailSenderService emailService;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping
    public List<Customer> getAllCustomers() {
        return customerService.getAllCustomers();
    }

//    @GetMapping(value = "/{id}")
//    @ResponseBody
//    @CrossOrigin(origins = "http://localhost:4200")
//    public Customer getCustomerById(@PathVariable("id") int id) {
//        Optional<Customer> resultCustomer = customerService.getCustomerById(id);
//        if (resultCustomer.equals(Optional.empty())) {
//            System.out.println("**************** No Customer by id " + id + " **************");
//            return new Customer();
//        } else {
//            return resultCustomer.get();
//        }
//    }

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

    @GetMapping(value = "/{id}")
    @ResponseBody
    @CrossOrigin(origins = "http://localhost:4200")
    public ResponseEntity<Customer> sendEdit(@PathVariable("id") int cid) {

        return ResponseEntity.status(HttpStatus.ACCEPTED).body(customerService.getCustomerById(cid).get());

    }// editCustomer

    @PostMapping(value = "/save")
    @CrossOrigin(origins = "http://localhost:4200")
    public ResponseEntity<Customer> receiveEdit(@RequestBody Customer customer) {
        System.out.println(customer.getCid());
        customerService.updateCustomer(customer.getCid(), customer);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(customer);
    }

    @PostMapping(value = "/verifyPass")
    @CrossOrigin(origins = "http://localhost:4200")
    public ResponseEntity<Customer> verifyOldPassword(@RequestBody Customer customer) {

        String email = customer.getEmail();
        String password = customer.getPassword();
        Optional<Customer> resultCustomer = customerService.getCustomerByEmail(email);
        Customer match = resultCustomer.get();

        //System.out.println("******" + password + "************");
        if (resultCustomer.equals(Optional.empty())) {
            System.out.println("**************** Invalid credentials **************");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(customer);
        } else if (passwordEncoder.matches(password, match.getPassword())) { // check password with encoded password
            return ResponseEntity.status(HttpStatus.ACCEPTED)
                    .body(match);
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(customer);
        }
    }
}
