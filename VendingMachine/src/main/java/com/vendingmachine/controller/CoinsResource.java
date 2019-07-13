package com.vendingmachine.controller;


import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vendingmachine.entity.Coin;
import com.vendingmachine.entity.Machine;
import com.vendingmachine.service.CoinService;
import com.vendingmachine.service.MachineService;
import com.vendingmachine.service.ProductService;


@RestController
@RequestMapping("/{machineId}/coins")
public class CoinsResource {

	@Autowired
	MachineService machineService;
	
	@Autowired
    CoinService coinService;	
	
	@Autowired
	ProductService productService;
	
    @GetMapping   
    ResponseEntity<Collection<Coin>> getCoins(@PathVariable String machineId) {             
        return ResponseEntity.ok(this.coinService.getAvailableCoins(machineId));        
    }

    /**
     * Add a new coin into the vending machine
     *
     * @param machineId the vending machine name
     * @param coin      the coin you want to add
     * @return the machine that the coin was added
     */
    @PostMapping   
    ResponseEntity<Machine> addCoin(@PathVariable String machineId, @RequestBody Coin coin) {
    	coinService.addCoinInMachine(machineId, coin);
    	Machine coinAddedToMachine = coinService.addCoinInMachine(machineId, coin);
    	return ResponseEntity.ok(coinAddedToMachine);        
    }

    /**
     * Return all the money that has not been spent in the system
     *
     * @param machineId machine name
     * @return the list of coins that have been returned
     */
    @GetMapping("/unused")
    @RequestMapping(produces={MediaType.APPLICATION_JSON_VALUE})
    ResponseEntity<List<Coin>> refundUnUsedAmount(@PathVariable String machineId) {    	
    	return ResponseEntity.ok(coinService.refundUnusedAmount(machineId));   	
    }

    /**
     * Get a specific coin
     *
     * @param machineId machine name
     * @param coinValue coin value
     * @return the details of coins in that vending machine
     */
    @GetMapping("/{coinValue}")   
    ResponseEntity<Coin> getCoin(@PathVariable String machineId, @PathVariable int coinValue) {
      return ResponseEntity.ok(coinService.getSpecificCoin(machineId, coinValue)); 
    }

   
}
