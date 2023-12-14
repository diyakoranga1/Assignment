package com.sis.exception;


public class TeacherNotFoundException extends Exception {

	public TeacherNotFoundException(){
		System.out.println("from TeacherNotFoundException constructor");
	}
	
	public String toString() {
		return " Teacher Not Found Exception";
	}
}
