package com.sis.exception;



public class InvalidEnrollmentDataException extends Exception {

	public InvalidEnrollmentDataException(){
		System.out.println("from InvalidEnrollmentDataException constructor");
	}
	
	public String toString() {
		return "Invalid Enrollment DataException";
	}
}
