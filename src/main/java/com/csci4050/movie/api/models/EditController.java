package com.csci4050.movie.api.models;

import java.util.Optional;

import com.csci4050.movie.api.sendEmail;
import com.csci4050.movie.api.customer.CustomerRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

public class EditController {

    @Autowired
    private com.csci4050.movie.api.customer.CustomerService customerService;

    @Autowired
    public com.csci4050.movie.api.Repo.CustomerRepository customerRepository;

    @GetMapping(value = "/editProfile")
    public void editCustomer(@RequestBody Customer customer) {
        //public ResponseEntity<Customer> editCustomer(@RequestBody Customer customer) throws Exception {
        String email = customer.getEmail();
        Customer resultCustomer = customerRepository.findCustomerByEmail(email).get();
        ResponseEntity.status(HttpStatus.ACCEPTED).body(resultCustomer.getEmail()); 
        ResponseEntity.status(HttpStatus.ACCEPTED).body(resultCustomer.getFirstName());
        ResponseEntity.status(HttpStatus.ACCEPTED).body(resultCustomer.getLastName());
        ResponseEntity.status(HttpStatus.ACCEPTED).body(resultCustomer.getStreet());
        ResponseEntity.status(HttpStatus.ACCEPTED).body(resultCustomer.getCity());
        ResponseEntity.status(HttpStatus.ACCEPTED).body(resultCustomer.getState());
        ResponseEntity.status(HttpStatus.ACCEPTED).body(resultCustomer.getZip());
    } // editCustomer

    @Autowired
    private sendEmail sendEmail;

    @PostMapping(value = "/edit")
    public void editConfirmation(@RequestBody Customer customer) { 
        
        String email = customer.getEmail();
        Customer resultCustomer = customerRepository.findCustomerByEmail(email).get();

       

        if(!resultCustomer.getFirstName().equals(customer.getFirstName()) && !customer.getFirstName().equals(null)){
            resultCustomer.setFirstName(customer.getFirstName());
        } // if
        if(!resultCustomer.getLastName().equals(customer.getLastName()) && !customer.getLastName().equals(null)){
            resultCustomer.setLastName(customer.getLastName());
        } // if
        if(resultCustomer.getPassword().equals(customer.getPassword())){
            if (customer.getPassword().equals(customer.getPassword())){ //.getConfirmedPassword())){
                resultCustomer.setPassword(customer.getPassword());
            } // if
            else{
                System.out.println("***** Passwords do not match... Your password was not updated *****");
            } // else
        } // if
        else{
            System.out.println("***** Incorrect Password... Your password was not updated *****");
        } // else
         if(!resultCustomer.getBStreet().equals(customer.getBStreet()) && !customer.getBStreet().equals(null)){
            resultCustomer.setBStreet(customer.getBStreet());
        } // if
        if(!resultCustomer.getCard().equals(customer.getCard()) && !customer.getCard().equals(null)){
            resultCustomer.setCard(customer.getCard());
        } // if
        if(!resultCustomer.getPromotion().equals(customer.getPromotion()) && !customer.getPromotion().equals(null)){
            resultCustomer.setPromotion(customer.getPromotion());
        } // if
       if(!resultCustomer.getBCity().equals(customer.getBCity()) && !customer.getBCity().equals(null)){
            resultCustomer.setBCity(customer.getBCity());
        } // if
        if(!resultCustomer.getBState().equals(customer.getBState()) && !customer.getBState().equals(null)){
            resultCustomer.setBState(customer.getBState());
        } // if
        if(resultCustomer.getBZip() != (customer.getBZip()) && customer.getBZip()== 0){
            resultCustomer.setBZip(customer.getBZip());
        } // if
    
        sendEmail.sendEditEmail(customer);
    } // editConfirmation

}
