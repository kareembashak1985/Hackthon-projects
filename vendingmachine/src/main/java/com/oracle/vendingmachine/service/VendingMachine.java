package com.oracle.vendingmachine.service;

import com.oracle.vendingmachine.apimodel.CoinInventoryStatusResponse;
import com.oracle.vendingmachine.apimodel.DispenseCoinResponse;
import com.oracle.vendingmachine.apimodel.InventorySetupRequest;
import com.oracle.vendingmachine.apimodel.InventorySetupResponse;
import com.oracle.vendingmachine.apimodel.RegisterCoinsRequest;
import com.oracle.vendingmachine.apimodel.RegisterCoinsResponse;

public interface VendingMachine {

	public CoinInventoryStatusResponse inventoryStatus();

	public InventorySetupResponse inventorySetup(InventorySetupRequest req);

	public RegisterCoinsResponse registerCoins(RegisterCoinsRequest req);

	public DispenseCoinResponse dispenceCoins(Integer value);
}
