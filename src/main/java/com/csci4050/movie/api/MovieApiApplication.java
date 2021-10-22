package com.csci4050.movie.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
@EnableAutoConfiguration
public class MovieApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(MovieApiApplication.class, args);
	}
}
