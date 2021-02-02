package com.oracle.vendingmachine.model;

import java.util.HashMap;
import java.util.Map;

/**
 * This model checks the coin availability in 'COIN_INVENTORY' before with drawl from 'COIN_INVENTORY'
 * 
 * 
 * @author kareem
 *
 */
public class CoinsAvailabilityStatus {

	/**
	 *  coin value accumulator
	 */
	private Integer coinAccumulator= new Integer(0);
	
	/**
	 * Holds coin types and quantity 
	 * e.g.  {1pound= 10,2pound=15,5pound=5}
	 */
	private  Map<Coin,Integer> coinDemonination = new HashMap<Coin,Integer>();
	
	public Integer getCoinAccumulator() {
		return coinAccumulator;
	}

	public void setCoinAccumulator(Integer coinAccumulator) {
		this.coinAccumulator = coinAccumulator;
	}

	
	
	public Map<Coin, Integer> getCoinDemonination() {
		return coinDemonination;
	}

	public void setCoinDemonination(Map<Coin, Integer> coinDemonination) {
		this.coinDemonination = coinDemonination;
	}


}
