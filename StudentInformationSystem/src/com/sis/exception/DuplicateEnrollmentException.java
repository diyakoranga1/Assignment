package com.sis.exception;


public class DuplicateEnrollmentException extends Exception {

	public DuplicateEnrollmentException(){
		System.out.println("from DuplicateEnrollmentException constructor");
	}
	
	public String toString() {
		return "Duplicate Enrollment Exception";
	}
}
