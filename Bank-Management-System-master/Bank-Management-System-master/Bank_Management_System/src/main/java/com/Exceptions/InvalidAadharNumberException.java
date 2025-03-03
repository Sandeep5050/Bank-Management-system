package com.Exceptions;

public class InvalidAadharNumberException extends RuntimeException{

	@Override
	public String toString() {
		return getClass()+"InvalidAadharNumberException Aadhar Number should contain 12 digits";
	}
	

}
