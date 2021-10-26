package com.csci4050.movie.api.customer;

import com.csci4050.movie.api.model.Customer;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("customers")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping
    public List<CustomerDto> getAllCustomers() {
        return customerService.getAllCustomers().stream().map(customer ->
                modelMapper.map(customer, CustomerDto.class)).collect(Collectors.toList());
    }

    @GetMapping(value = "/{id}")
    @ResponseBody
    public ResponseEntity<CustomerDto> getCustomer(@PathVariable("id") Long id) {
        Customer customer = customerService.getCustomerById(id).get();
        CustomerDto customerDto = modelMapper.map(customer, CustomerDto.class);
        return ResponseEntity.ok().body(customerDto);
    }

}
