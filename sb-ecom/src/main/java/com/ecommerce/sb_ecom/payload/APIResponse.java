package com.ecommerce.sb_ecom.payload;

public class APIResponse {
	
	public String message;
	private boolean status;
	
	public APIResponse() {
		// TODO Auto-generated constructor stub
	}

	public APIResponse(String message, boolean status) {
		super();
		this.message = message;
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}
	

}
