package com.oracle.vendingmachine.apimodel;

import java.util.List;

import com.oracle.vendingmachine.model.CoinInventory;

public class DispenseCoinResponse extends BaseApiModel {
	// dispense coin details coin type and quantity.
	private List<CoinInventory>  dispenseCoinsDetails;
	//Additional details about the coin dispense transaction
	private String message;
	//Status success/failure
	private String status;
	
	

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public List<CoinInventory> getDispenseCoinDetails() {
		return dispenseCoinsDetails;
	}

	public void setDispenseCoinDetails(List<CoinInventory> dispenseCoinDetails) {
		this.dispenseCoinsDetails = dispenseCoinDetails;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	@Override
	public String toString() {
		return "DispenseCoinResponse [dispenseCoinDetails=" + dispenseCoinsDetails + ", message=" + message + ", status="
				+ status + "]";
	}

	

	

	

	
	

}
