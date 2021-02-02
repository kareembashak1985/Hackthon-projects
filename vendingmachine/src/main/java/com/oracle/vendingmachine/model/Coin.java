package com.oracle.vendingmachine.model;

/**
 * it holds supported denominations
 * 
 * @author kareem
 *
 */
public enum Coin {

	ONEPOUND("ONEPOUND",1), TWOPOUND("TWOPOUND",2), FIVEPOUND("FIVEPOUND",5);

	private int coinValue;
	private String coinName;

	Coin(String coinName, int coinValue) {
		this.coinName = coinName;
		this.coinValue = coinValue;
	}

	public int getCoinValue() {
		return this.coinValue;
	}
	
	public String getCoinName() {
		return this.coinName;
	}	
	

}
