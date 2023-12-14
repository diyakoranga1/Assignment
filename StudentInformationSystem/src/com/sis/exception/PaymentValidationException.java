package com.sis.exception;

public class PaymentValidationException extends Exception {

	public PaymentValidationException(){
		System.out.println("from PaymentValidationException constructor");
	}
	
	public String toString() {
		return "Payment ValidationException";
	}
}
