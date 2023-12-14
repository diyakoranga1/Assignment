package com.sis.exception;

public class CourseNotFoundException extends Exception {

	public CourseNotFoundException(){
		System.out.println("from course not found constructor");
	}
	
	public String toString() {
		return "course not found Exception";
	}
}

