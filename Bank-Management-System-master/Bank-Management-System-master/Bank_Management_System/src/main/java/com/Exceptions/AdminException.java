package com.Exceptions;

public class AdminException extends RuntimeException{
	String msg;
	public AdminException(String msg) {
		this.msg=msg;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}
	
}
