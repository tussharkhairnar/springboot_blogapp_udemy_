package com.services.blogrestapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BlogRestApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(BlogRestApiApplication.class, args);
		System.out.println("Stared blog application");
	}

}
