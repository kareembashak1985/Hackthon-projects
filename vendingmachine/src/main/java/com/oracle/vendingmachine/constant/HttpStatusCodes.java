package com.oracle.vendingmachine.constant;

public enum HttpStatusCodes {
	STATUS_500(500), STATUS_400(400), STATUS_401(401),STATUS_404(404);

	private int code;

	HttpStatusCodes(int code){
		this.code=code;
	}

	public int getHttpCode() {
		return code;
	}

}
