package com.sis.exception;

public class InvalidCourseDataException extends Exception {
	 public InvalidCourseDataException() {
	        System.out.println("from Invalid Course Data Exception constructor");
	    }
	    public String toString() {
	    	return "Invalid Course Data";
	    }
}