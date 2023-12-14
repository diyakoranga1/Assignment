package com.sis.exception;



public class StudentNotFoundException extends Exception {

	public StudentNotFoundException(){
		System.out.println("from StudentNotFoundException constructor");
	}
	
	public String toString() {
		return "Student NotFound Exception";
	}
}
