package com.oracle.vendingmachine.apimodel;

public class RegisterCoinsResponse extends BaseApiModel {
	// holds transaction success/failure
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
		return "RegisterCoinsResponse [status=" + status + ", message=" + message + "]";
	}
	
	
	
	

}
