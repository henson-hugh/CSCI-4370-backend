package com.csci4050.movie.api.admin;

import com.csci4050.movie.api.customer.CustomerRepository;
import com.csci4050.movie.api.model.Admin;
import com.csci4050.movie.api.model.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AdminService {

    @Autowired
    private AdminRepository adminRepository;

    private CustomerRepository customerRepository;

    public Optional<Admin> getAdminByUid(int uid) {
        return adminRepository.findByUserid(uid);
    }



}
