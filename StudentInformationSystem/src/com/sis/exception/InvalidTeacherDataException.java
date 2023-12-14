package com.sis.exception;


public class InvalidTeacherDataException extends Exception {

	public InvalidTeacherDataException(){
		System.out.println("from InvalidTeacherDataException constructor");
	}
	
	public String toString() {
		return "Invalid Teacher Data Exception";
	}
}
