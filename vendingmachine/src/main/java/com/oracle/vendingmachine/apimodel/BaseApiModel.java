package com.oracle.vendingmachine.apimodel;

public abstract class  BaseApiModel {
	//Error details about failure cases
	private ErrorDetails errorDetails;

	public ErrorDetails getErrorDetails() {
		return errorDetails;
	}

	public void setErrorDetails(ErrorDetails errorDetails) {
		this.errorDetails = errorDetails;
	}
	

}
