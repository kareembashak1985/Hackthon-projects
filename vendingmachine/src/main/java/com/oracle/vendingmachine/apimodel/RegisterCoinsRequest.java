package com.oracle.vendingmachine.apimodel;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;

import com.oracle.vendingmachine.model.CoinInventory;

public class RegisterCoinsRequest {
	// Register coins that user send
	@NotEmpty(message = "register coins details field required")
	private List<@Valid CoinInventory> registerCoinsDetails;

	public List<CoinInventory> getRegisterCoinsDetails() {
		return registerCoinsDetails;
	}

	public void setRegisterCoinsDetails(List<CoinInventory> registerCoinsDetails) {
		this.registerCoinsDetails = registerCoinsDetails;
	}
}
