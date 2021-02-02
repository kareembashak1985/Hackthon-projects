package com.oracle.vendingmachine.apimodel;

import java.util.List;

import com.oracle.vendingmachine.model.CoinInventory;

public class CoinInventoryStatusResponse extends BaseApiModel {
	// Holds Coin inventory
	private List<CoinInventory> coinInventory;
	// Additional details about the coin availability
	private String message;

	// Status success/failure
	private String status;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public List<CoinInventory> getCoinInventory() {
		return coinInventory;
	}

	public void setCoinInventory(List<CoinInventory> coinInventory) {
		this.coinInventory = coinInventory;
	}

	@Override
	public String toString() {
		return "CoinInventoryStatusResponse [coinInventory=" + coinInventory + ", status=" + status + "]";
	}

}
