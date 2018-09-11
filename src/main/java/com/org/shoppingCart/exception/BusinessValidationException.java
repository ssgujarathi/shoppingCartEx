package com.org.shoppingCart.exception;

public class BusinessValidationException extends Exception {

	private static final long serialVersionUID = 1L;

	public BusinessValidationException() {
		
	}
	
	public BusinessValidationException(String pMessage) {
		super(pMessage);
	}
	
	public BusinessValidationException(Throwable pCause) {
		super(pCause);
	}
	
	public BusinessValidationException(String pMessage, Throwable pCause) {
		super(pMessage, pCause);
	}
}
