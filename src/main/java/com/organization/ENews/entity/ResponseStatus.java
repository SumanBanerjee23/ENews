package com.organization.ENews.entity;

public class ResponseStatus {
	
	private String timestamp;
	private String errorMessage;
	private int reasonCode;
	public String getTimestamp() {
		return timestamp;
	}
	public ResponseStatus(){}
	
	public ResponseStatus(String timestamp, String errorMessage, int reasonCode) {
		
		this.timestamp = timestamp;
		this.errorMessage = errorMessage;
		this.reasonCode = reasonCode;
	}
	
	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}
	public String getErrorMessage() {
		return errorMessage;
	}
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	public int getReasonCode() {
		return reasonCode;
	}
	public void setReasonCode(int reasonCode) {
		this.reasonCode = reasonCode;
	}


}
