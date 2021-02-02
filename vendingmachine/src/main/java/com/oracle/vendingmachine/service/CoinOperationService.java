package com.oracle.vendingmachine.service;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.oracle.vendingmachine.model.Coin;
import com.oracle.vendingmachine.model.CoinsAvailabilityStatus;
import com.oracle.vendingmachine.repository.CoinRepository;

@Service
public class CoinOperationService {
	
	public static final Logger log = LoggerFactory.getLogger(VendingMachineServiceImpl.class);
	
	@Autowired private CoinRepository coinRepo;
	
	/**
	 * This Function calculate the minimum coins required , if change found than 
	 * with drawl coins from CoinInventory and return coins to customer 
	 * 
	 * @param inputValue - Customer provided input value(money)
	 * @return   - returns available coins and quantity 
	 */
	public synchronized Map<Coin,Integer>  minCoins(Integer inputValue) {
		CoinsAvailabilityStatus cas = new CoinsAvailabilityStatus();
		try {
			if(inputValue==0) {				
				return cas.getCoinDemonination();
			}
			
			List<Coin> coins = CoinRepository.CoinInventory.keySet().stream().collect(Collectors.toList());
			Collections.sort(coins, Collections.reverseOrder());		
			
			for(Coin coin : coins) {			
				if(coin.getCoinValue() <=inputValue) {				
					Integer quotient = (inputValue-cas.getCoinAccumulator())/ coin.getCoinValue();				
					if(quotient<=CoinRepository.CoinInventory.get(coin)) {
						cas.getCoinDemonination().put(coin,  quotient);
						cas.setCoinAccumulator(cas.getCoinAccumulator()+quotient*coin.getCoinValue());
					} else {
						cas.getCoinDemonination().put(coin,  CoinRepository.CoinInventory.get(coin));
						cas.setCoinAccumulator(cas.getCoinAccumulator()+CoinRepository.CoinInventory.get(coin)*coin.getCoinValue());
					}
					if(cas.getCoinAccumulator().intValue() ==inputValue.intValue()) {					
						break;
					}
				} 
			}
			
			// if required coins not found in vending machine, reset the 'CoinAvailabiltyStatus'
			if(cas.getCoinAccumulator()<inputValue ) {
				cas= new CoinsAvailabilityStatus();
			}
			//if customer provided change  matches with CoinAccumulator, proceed for withDrawCoins
			if(cas.getCoinAccumulator() ==inputValue) {
				coinRepo.withDrawalCoins(cas.getCoinDemonination());
			}
		} catch(Exception e) {
			log.error("Error encounter while calculating the minimum coins required for the given value "+ e.getMessage() );
		}
		return cas.getCoinDemonination();
		
	}

}
