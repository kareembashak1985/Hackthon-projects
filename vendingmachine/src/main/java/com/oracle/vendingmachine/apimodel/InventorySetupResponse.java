package com.oracle.vendingmachine.apimodel;

public class InventorySetupResponse extends BaseApiModel {
	//Holds success/failure 
	private String status;
	private String message;
	
	
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
	@Override
	public String toString() {
		return "InitialCoinInventoryResponse [status=" + status + ", message=" + message + "]";
	}
	
	
	
	
}
