package com.csci4050.movie.api.registration;

import com.csci4050.movie.api.EmailSenderService;
import com.csci4050.movie.api.customer.CustomerService;
import com.csci4050.movie.api.model.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.net.URI;
import java.util.Optional;

@RestController
public class RegistrationController {

    @Autowired
    private CustomerService customerService;

    @Autowired
    private EmailSenderService emailService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping(value = "/register")
    @CrossOrigin(origins = "http://localhost:4200")
    public ResponseEntity<Customer> registerCustomer(@RequestBody Customer customer) throws Exception {
        Optional<Customer> customerByEmail = customerService.saveCustomer(customer);
        if (!customerByEmail.isPresent()) {
            emailService.sendConfirmationEmail(customer, customer.getEmail());

            return ResponseEntity.status(HttpStatus.ACCEPTED)
                    .body(customer);
        } else {
            System.out.println("****************User with: " + customer.getEmail() + " already exists.**************");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(customer);
        }

    }

    @RequestMapping(value = "/verify", method = {RequestMethod.GET, RequestMethod.POST})
    @CrossOrigin(origins = "http://localhost:4200")
    public ResponseEntity<Void> verifyAccount(@RequestParam("code") String vcode, @RequestParam("id") int cid) {
        Customer customer = customerService.getCustomerById(cid).get();
        if (customer.getVcode().equals(vcode)) {
            customer.setActive(true);
            customer.setVcode("Verified");
            customerService.verifyCustomer(cid, customer);

            return ResponseEntity.status(HttpStatus.FOUND).location(URI.create("http://localhost:4200/email-confirmed")).build();

        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping(value = "/login")
    @CrossOrigin(origins = "http://localhost:4200")
    public ResponseEntity<Customer> loginCustomer(@RequestBody Customer customer, HttpServletRequest request, HttpSession session) {
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
            session.invalidate();
            HttpSession newSession = request.getSession();
            System.out.println(match.getCid());
            return ResponseEntity.status(HttpStatus.ACCEPTED)
                    .body(match);
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(customer);
        }
    }

    @PostMapping(value = "/forgotPass")
    @CrossOrigin(origins = "http://localhost:4200")
    public ResponseEntity<String> forgotPassword(@RequestBody String email) {
        if (customerService.getCustomerByEmail(email).isPresent()) {
            Customer customer = customerService.getCustomerByEmail(email).get();
            emailService.sendForgotEmail(email, customer);
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(email);
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(email);
        }
    }

    @RequestMapping(value = "/reset", method = {RequestMethod.GET, RequestMethod.POST})
    @CrossOrigin(origins = "http://localhost:4200")
    public ResponseEntity<Void> verifyAccount(@RequestParam("id") int cid) {
        return ResponseEntity.status(HttpStatus.FOUND).location(URI.create("http://localhost:4200/home")).build();
    }
}
