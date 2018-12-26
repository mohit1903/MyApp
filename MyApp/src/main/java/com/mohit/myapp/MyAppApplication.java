package com.mohit.myapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
@EnableAutoConfiguration(exclude = {SecurityAutoConfiguration.class}) 
public class MyAppApplication {

	public static void main(String[] args) {
		ApplicationContext context=SpringApplication.run(MyAppApplication.class, args);
		
	}
}
