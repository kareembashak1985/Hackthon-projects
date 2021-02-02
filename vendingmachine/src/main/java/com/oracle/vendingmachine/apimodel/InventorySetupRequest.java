package com.oracle.vendingmachine.apimodel;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;

import com.oracle.vendingmachine.model.CoinInventory;

public class InventorySetupRequest {
	// request to send   coin type and quantity 
	@NotEmpty(message = "inventory setup field required")
	private List<@Valid CoinInventory> inventorySetup;

	public List<CoinInventory> getInventorySetup() {
		return inventorySetup;
	}

	public void setInventorySetup(List<CoinInventory> inventorySetup) {
		this.inventorySetup = inventorySetup;
	}

	
	
}
