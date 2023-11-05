package com.example.electric;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class ElectricApplication {

	public static void main(String[] args) {
		SpringApplication.run(ElectricApplication.class, args);
	}

}
