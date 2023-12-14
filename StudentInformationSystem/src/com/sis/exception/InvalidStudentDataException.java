package com.sis.exception;


public class InvalidStudentDataException extends Exception {

	public InvalidStudentDataException(){
		System.out.println("from InvalidStudentDataException constructor");
	}
	
	public String toString() {
		return "Invalid Student Data Exception";
	}
}
