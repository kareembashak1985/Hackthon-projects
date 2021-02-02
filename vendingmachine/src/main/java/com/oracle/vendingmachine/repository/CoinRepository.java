package com.oracle.vendingmachine.repository;

import static com.oracle.vendingmachine.model.Coin.FIVEPOUND;
import static com.oracle.vendingmachine.model.Coin.ONEPOUND;
import static com.oracle.vendingmachine.model.Coin.TWOPOUND;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.oracle.vendingmachine.model.Coin;
import com.oracle.vendingmachine.service.VendingMachineServiceImpl;

@Repository
public class CoinRepository {

	public static final Logger log = LoggerFactory.getLogger(VendingMachineServiceImpl.class);

	public static ConcurrentHashMap<Coin, Integer> CoinInventory = new ConcurrentHashMap<Coin, Integer>();
	
	@PostConstruct
	public void initialSetup() {
		//TODO Initial default value for demo, Required to remove the default value before live.
		CoinInventory.put(ONEPOUND, 100);
		CoinInventory.put(TWOPOUND, 300);
		CoinInventory.put(FIVEPOUND, 500);
	}

	/**
	 * Function  to withdrawal coins from the COIN_INVENTORY
	 * @param withDrawalCoinsMap
	 * @return
	 */
	public Boolean withDrawalCoins(Map<Coin, Integer> withDrawalCoinsMap) {
		log.info("With draw coins details -> " + withDrawalCoinsMap);
		Boolean status = Boolean.FALSE;
		try {

			withDrawalCoinsMap.entrySet().forEach(e -> {
				// withdrawal the coins
				CoinInventory.put(e.getKey(), CoinInventory.get(e.getKey()) - e.getValue());
			});
			status = Boolean.TRUE;
		} catch (Exception e) {
			log.error("withdrwal coin operation failed -> " + e.getMessage());
		}
		log.info("Coin inventory status after withdrawl coins -> " + CoinRepository.CoinInventory);
		return status;
	}
	
	
	/**
	 * Function to register the coins deposited by the user in COIN_INVENTORY
	 * @param registerCoins
	 * @return
	 */
	public Boolean registerCoins(Map<Coin, Integer> registerCoins) {
		log.info("Register coins details -> " + registerCoins);
		Boolean status = Boolean.FALSE;
		try {

			registerCoins.entrySet().forEach(e -> {
				if(CoinInventory.containsKey(e.getKey())) {
					//update current denominations
					CoinInventory.put(e.getKey(), CoinInventory.get(e.getKey()) + e.getValue());
				} else {
					//Register new denominations
					CoinInventory.put(e.getKey(), e.getValue());
				}
			});
			status = Boolean.TRUE;
		} catch (Exception e) {
			log.error("Register coin operation failed -> " + e.getMessage());
		}
		log.info("Coin inventory status after withdrawl coins -> " + CoinRepository.CoinInventory);
		return status;
	}


}
