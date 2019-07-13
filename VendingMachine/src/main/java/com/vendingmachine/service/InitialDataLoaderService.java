package com.vendingmachine.service;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;

import com.vendingmachine.entity.Coin;
import com.vendingmachine.entity.Machine;
import com.vendingmachine.entity.Product;
import com.vendingmachine.repository.CoinRepository;
import com.vendingmachine.repository.MachineRepository;
import com.vendingmachine.repository.ProductRepository;

@Service
public class InitialDataLoaderService {
	
	@Autowired
	MachineRepository machineRepository;
	
	@Autowired
	CoinRepository coinRepository;
	
	@Autowired
	ProductRepository productRepository;
	
	public CommandLineRunner loadinitialData(){
		
		return (evt) -> Arrays.asList("MachineOne,MachineTwo".split(","))
					.forEach(
						machineName -> {
							// Create the new machine
							Machine machine = machineRepository.save(new Machine(machineName, 1000));

							// Add 2 default products
							productRepository.save(new Product(machine,"Thumsup", 120,10));
							productRepository.save(new Product(machine,"Mazza", 300, 5));
							productRepository.save(new Product(machine,"Appy Fizz", 300, 5));

							// Add some cash float to the machine
							coinRepository.save(new Coin(machine, 50, 6));
							coinRepository.save(new Coin(machine, 100, 2));
							coinRepository.save(new Coin(machine, 20, 4));
							coinRepository.save(new Coin(machine, 1, 10));
						});
		
	}
	
	

}
