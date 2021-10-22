package com.csci4050.movie.api.Models;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path="api/v1/customer")
public class CustomerController {
    
    private final CustomerService customerService;

    @Autowired
    public CustomerController (CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping
	public List<User> getUsers() {
        return customerService.getUsers();
	}

    @PostMapping
    public void RegisterNewCustomer(@RequestBody Customer customer) {
        System.out.println(customer);
        //customerService.addNewCustomer(customer);
    }
}
