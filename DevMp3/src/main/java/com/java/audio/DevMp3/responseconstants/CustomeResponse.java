package com.java.audio.DevMp3.responseconstants;

public class CustomeResponse {
	private String statusMessage;
	private int statusCode;
	
	public void setStatusMessage(String statusMessage) {
		this.statusMessage = statusMessage;
	}
	
	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}

	public String getStatusMessage() {
		return statusMessage;
	}

	public int getStatusCode() {
		return statusCode;
	}
	
}
