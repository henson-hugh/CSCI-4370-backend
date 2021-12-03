package com.csci4050.movie.api.customer;

import com.csci4050.movie.api.EmailSenderService;
import com.csci4050.movie.api.booking.BookingDto;
import com.csci4050.movie.api.booking.BookingService;
import com.csci4050.movie.api.customer.CustomerService;
import com.csci4050.movie.api.model.Booking;
import com.csci4050.movie.api.MovieCard;
import com.csci4050.movie.api.customer.CustomerService;
import com.csci4050.movie.api.model.*;
import com.csci4050.movie.api.paymentCard.PaymentCardDto;
import com.csci4050.movie.api.paymentCard.PaymentCardService;
import com.csci4050.movie.api.model.Customer;
import com.csci4050.movie.api.model.User;
import com.csci4050.movie.api.model.Verification;
import com.csci4050.movie.api.showing.ShowingDto;
import com.csci4050.movie.api.user.UserDto;
import com.csci4050.movie.api.user.UserService;
import com.csci4050.movie.api.verification.VerificationService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.net.URI;
import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/customer")
public class CustomerController {
    @Autowired
    private CustomerService customerService;

    @Autowired
    private EmailSenderService emailService;

    @Autowired
    private PaymentCardService paymentCardService;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserService userService;

    @Autowired
    private VerificationService verificationService;

    @Autowired
    private BookingService bookingService;

    @GetMapping
    public List<Customer> getAllCustomers() {
        return customerService.getAllCustomers();
    }

    // Register
    @PostMapping(value = "/register")
    @CrossOrigin(origins = "http://localhost:4200")
    public ResponseEntity<CustomerDto> registerCustomer(@RequestBody CustomerDto customerDto) throws Exception {
        Customer customer = modelMapper.map(customerDto, Customer.class);

        // save customer
        customerService.saveCustomer(customer);

        // generate verification code
        String code = verificationService.generateVerificationCode(customer);

        // send email
        String email = userService.getUserById(customer.getUserid()).get().getEmail();
        emailService.sendRegistrationEmail(customer, email, code);

        return ResponseEntity.status(HttpStatus.ACCEPTED)
                .body(modelMapper.map(customer, CustomerDto.class));
    }

    // Verify Account
    @RequestMapping(value = "/verify", method = {RequestMethod.GET, RequestMethod.POST})
    @CrossOrigin(origins = "http://localhost:4200")
    public ResponseEntity<Void> verifyAccount(@RequestParam("code") String vcode, @RequestParam("id") int cid) {

        Customer customer = customerService.getCustomerById(cid).get();

        // check verification code
        Optional<Verification> verification = verificationService.getVerificationByCode(vcode);
        if (verification.isPresent()) { // checks if verification matches
            customerService.verifyCustomer(customer.getCid());

            System.out.println("cid" + cid);

            // remove vcode from repository
            verificationService.verifyCustomer(cid);

            // redirect to email confirmed page
            return ResponseEntity.status(HttpStatus.FOUND).location(URI.create("http://localhost:4200/email-confirmed")).build();
        }

        return ResponseEntity.notFound().build();
    }

    @GetMapping(value = "/{id}")
    @ResponseBody
    @CrossOrigin(origins = "http://localhost:4200")
    public ResponseEntity<Object> sendEdit(@PathVariable("id") int cid) {
        Map<String, Object> map = new HashMap<String, Object>();
        Customer customer = customerService.getCustomerById(cid).get();
        map.put("customer", customer);
        map.put("uid", customer.getUserid());

        return new ResponseEntity<Object>(map, HttpStatus.ACCEPTED);
    }// editCustomer

    @PostMapping(value = "/save")
    @CrossOrigin(origins = "http://localhost:4200")
    public ResponseEntity<CustomerDto> receiveEdit(@RequestBody CustomerDto customerdto) {
        Customer customer = modelMapper.map(customerdto, Customer.class);
        customerService.updateCustomer(customer.getCid(), customer);
        //emailService.sendEditEmail(customer);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(modelMapper.map(customer, CustomerDto.class));
    }

    @PostMapping(value = "/payment/add")
    @CrossOrigin(origins = "http://localhost:4200")
    public ResponseEntity<PaymentCardDto> addPaymentCard(@RequestBody PaymentCardDto paymentCardDto) {
        PaymentCard paymentCard = modelMapper.map(paymentCardDto, PaymentCard.class);
        paymentCardService.savePaymentCard(paymentCard);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(paymentCardDto);
    }

    @PostMapping(value = "/payment/retrieve")
    @CrossOrigin(origins = "http://localhost:4200")
    public ResponseEntity<Object> getPaymentCards(@RequestBody CustomerDto customerdto) {
        Customer customer = modelMapper.map(customerdto, Customer.class);
        List<PaymentCard> paymentCards = paymentCardService.getAllPaymentCards(customer);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(paymentCards);
    }

    @PostMapping(value = "/payment/retrieve/first")
    @CrossOrigin(origins = "http://localhost:4200")
    public ResponseEntity<Object> getPaymentCard(@RequestBody CustomerDto customerdto) {
        Customer customer = modelMapper.map(customerdto, Customer.class);
        Optional<PaymentCard> paymentCard = paymentCardService.getPaymentCards(customer);
        if (paymentCard.isPresent()) {
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(paymentCard);
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(paymentCard);
    }
  
    @PostMapping(value = "/payment/edit")
    @CrossOrigin(origins = "http://localhost:4200")
    public ResponseEntity<PaymentCardDto> editPaymentCard(@RequestBody PaymentCardDto paymentCardDto) {
        PaymentCard paymentCard = modelMapper.map(paymentCardDto, PaymentCard.class);
        paymentCardService.savePaymentCard(paymentCard);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(modelMapper.map(paymentCard, PaymentCardDto.class));
    }
  
    @PostMapping(value = "/booking/history")
    @CrossOrigin(origins = "http://localhost:4200")
    List<Booking> bookingHistory(@RequestBody CustomerDto customerDto) {
        Customer customer = modelMapper.map(customerDto, Customer.class);
        return bookingService.getBookingHistory(customer);
    }
}
