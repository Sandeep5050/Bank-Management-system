package com.Exceptions;

public class InvalidMobileNumberException extends RuntimeException{

	public InvalidMobileNumberException(String s) {
		System.out.println(s);
	}

	@Override
	public String toString() {
		return getClass() +"InvalidMobileNumberException Mobile Number should contain 10 digits";
	}
	
}
