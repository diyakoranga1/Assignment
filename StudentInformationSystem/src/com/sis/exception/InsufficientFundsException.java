package com.sis.exception;


public class InsufficientFundsException extends Exception {

	public InsufficientFundsException(){
		System.out.println("from InsufficientFundsException constructor");
	}
	
	public String toString() {
		return " Insufficient Funds Exception";
	}
}
