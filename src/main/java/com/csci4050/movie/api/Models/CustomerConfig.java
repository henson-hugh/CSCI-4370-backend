package com.csci4050.movie.api.Models;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

import com.csci4050.movie.api.Repo.*;


@Configuration
public class CustomerConfig {
    
    @Bean
    CommandLineRunner customerRunner(CustomerRepository userRepo) {
        return args -> {
            User Jason = new Customer(
                "jborne", "JB1234", UserStatus.active,
                "Jason", "Borne", "1234567890", "jborne@gmail.com"
            );
            User Michael = new Customer(
                "VSauce", "MS1234", UserStatus.active,
                "Michael", "Stevens", "1112223334", "vsauce@gmail.com",
                "420 Vsauce Road", "New York", "NY", 12345 
            );
            User Linda = new Customer(
                "lkore", "lk1234", UserStatus.inactive,
                "Linda", "Kore", "9998887776", "lindak@gmail.com",
                1111222233334444L, "01/25", "69 Yeehaw St", "Atlanta", "GA", 98765
            );
            User Gibralter = new Customer(
                "GibbyMain", "GM1337", UserStatus.active,
                "Gibralter", "Main", "9999999999", "gibbyrocks@gmail.com",
                "3660 Olympus Road", "Apex", "NC", 13337,
                1234123412341234L, "69/69", "3660 Olympus Road", "Apex", "NC", 13337
            );
            userRepo.saveAll(List.of(Jason, Michael, Linda, Gibralter));
        };
    }

    
    
}
