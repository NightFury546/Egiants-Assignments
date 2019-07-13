package com.vendingmachine.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.IntStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vendingmachine.configure.CoinNotFoundException;
import com.vendingmachine.entity.Coin;
import com.vendingmachine.entity.Machine;
import com.vendingmachine.repository.CoinRepository;

@Service
public class CoinService {
	
	@Autowired
	CoinRepository coinRepository;
	
	@Autowired
	MachineService machineService;
	
	/**
	 * Loads the default coins on loading
	 */
	

    /**
     * Get list of all coins in the system
     *
     * @param machineId the machine name
     * @return the list of the coins
     */
	public Collection<Coin> getAvailableCoins(String machineId){	
		Machine machine = machineService.getMachine(machineId);
		return machine.getCoinsList();
	}
	
	public Machine addCoinInMachine(String machineId,Coin coin){	

		machineService.isMachineExists(machineId);		
		Machine machine = machineService.getMachine(machineId);

		final boolean[] coinFound = {false};

		// Increase the amount of coins if the machine already has seen that coin
		machine.getCoinsList().forEach(machineCoin -> {
			if (coin.value == machineCoin.value) {
				machineCoin.amount += coin.amount;	
				this.coinRepository.save(machineCoin);
				coinFound[0] = true; 
			}
		});

		// Else add the coin to the repository
		if (!coinFound[0] && IntStream.of(Coin.POSSIBLE_VALUES).anyMatch(x -> x == coin.value)) {
			this.coinRepository.saveAndFlush(new Coin(machine, coin.value, coin.amount));
			coinFound[0] = true;
		}

		if (coinFound[0]) {
			machine.currentAmount += coin.value;
		}else{
			throw new CoinNotFoundException(coin.getValue());
		}	

		return machineService.saveAndFlush(machine);
	}
	
	
	public List<Coin> refundUnusedAmount(String machineId){
		
		machineService.isMachineExists(machineId);        

        final int[] refundTotal = new int[1];        
        Machine machine = machineService.getMachine(machineId);
        refundTotal[0] = machine.currentAmount;     

        List<Coin> refundCoins = new ArrayList<>();

        for (int value : Coin.POSSIBLE_VALUES) {
        	machine.getCoinsList().forEach(coin -> {
                if (value == coin.value && coin.amount > 0 && coin.value <= refundTotal[0]) {
                    double max_coins = Math.min(Math.floor(refundTotal[0] / coin.value), coin.amount);
                    coin.amount -= max_coins;
                    this.coinRepository.saveAndFlush(coin);
                    refundCoins.add(new Coin(coin.value, (int) max_coins));
                    refundTotal[0] -= (int) max_coins * coin.value;
                }
            });
        }

        return refundCoins;	
		
	}
	
	
	public Coin getSpecificCoin(String machineId,int coinValue){

		  Machine machine = machineService.getMachine(machineId);
		
        final Coin[] foundCoin = new Coin[1];
        machine.getCoinsList().forEach((Coin coin) -> {
            if (coin.value == coinValue) {
                foundCoin[0] = coin;
            }
        });
        if (foundCoin[0] != null) {
            return foundCoin[0];
        } else {
            throw new CoinNotFoundException(coinValue);
        }
    
	}	

}
