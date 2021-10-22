package com.csci4050.movie.api.Models;

import com.csci4050.movie.api.Repo.*;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class AdminConfig {

    @Bean
    CommandLineRunner adminRunner(AdminRepository adminRepo) {
        return args -> {
            User Philip = new Admin(
                "Philip", "password", UserStatus.active
            );
            User Henson = new Admin(
                "Henson", "password", UserStatus.active
            );
            User Nick = new Admin(
                "Nick", "password", UserStatus.active
            );
            User Travis = new Admin(
                "Travis", "password", UserStatus.active
            );
            User Zach = new Admin(
                "Zach", "password", UserStatus.active
            );
            adminRepo.saveAll(List.of(Philip, Henson, Nick, Travis, Zach));
        };
    }
    
}
