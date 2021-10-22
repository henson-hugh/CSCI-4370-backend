package com.csci4050.movie.api;

import com.csci4050.movie.api.user.User.Status;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import com.csci4050.movie.api.user.*;

@SpringBootApplication
@RestController
public class MovieApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(MovieApiApplication.class, args);
	}

	@GetMapping
	public List<User> test() {
		return List.of(
			new User(0123L, "jason1233", "Test1234#", Status.active),
			new Customer(0124L, "testid", "testpass", Status.active, "James", "Bond", "123456789", "jbond@gmail.com")
		);
	}

}
