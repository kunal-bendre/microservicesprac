package com.microservpractice.microservicesprac;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class MicroservicespracApplication {

	public static void main(String[] args) {
		ApplicationContext applicationContext = 
				SpringApplication.run(MicroservicespracApplication.class, args);
	}

}
