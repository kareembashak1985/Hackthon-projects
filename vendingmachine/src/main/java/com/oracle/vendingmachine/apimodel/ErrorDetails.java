package com.oracle.vendingmachine.apimodel;

/**
 * 
 * @author kareem
 *
 */
public class ErrorDetails {
	
	private String timestamp;
    private Integer status;
    private String error;
    private String message;   
    private String detail;
    
    
	public String getDetail() {
		return detail;
	}
	public void setDetail(String detail) {
		this.detail = detail;
	}
	public String getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public String getError() {
		return error;
	}
	public void setError(String error) {
		this.error = error;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
	
	@Override
	public String toString() {
		return "ErrorDetails [timestamp=" + timestamp + ", status=" + status + ", error=" + error + ", message="
				+ message + ", detail=" + detail + "]";
	}
    
    
    
}
