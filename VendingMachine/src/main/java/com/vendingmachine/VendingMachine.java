package com.vendingmachine;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.vendingmachine.service.InitialDataLoaderService;

@SpringBootApplication
public class VendingMachine {
	

	public static void main(String[] args) {
		SpringApplication.run(VendingMachine.class, args);
	}

	@Bean
	CommandLineRunner init(InitialDataLoaderService dataLoaderService) {		
		return dataLoaderService.loadinitialData();		
	}	
	
}
