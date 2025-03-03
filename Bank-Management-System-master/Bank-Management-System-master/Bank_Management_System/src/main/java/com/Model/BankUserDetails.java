package com.Model;

import java.util.Scanner;

import com.Exceptions.InvalidAadharNumberException;
import com.Exceptions.InvalidMobileNumberException;

public class BankUserDetails {

	private int id;
	private String name;
	private String email;
	private long aadharNo;
	private long mobileNo;
	private String panNo;
	private String address;
	private String gender;
	private double amount;
	private int pin;
	private int accountNo;
	private String status;
	Scanner s=new Scanner(System.in);
	
	@Override
	public String toString() {
		return "BankUserDetails [id=" + id + ", name=" + name + ", email=" + email + ", aadharNo=" + aadharNo
				+ ", mobileNo=" + mobileNo + ", panNo=" + panNo + ", address=" + address + ", gender=" + gender
				+ ", amount=" + amount + ", pin=" + pin + ", accountNo=" + accountNo + ", status=" + status + "]";
	}

	public BankUserDetails() {}
	
	public BankUserDetails(int id, String name, String email, long aadharNo, long mobileNo, String panNo,
			String address, String gender, double amount, int pin, int accountNo, String status) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.aadharNo = aadharNo;
		this.mobileNo = mobileNo;
		this.panNo = panNo;
		this.address = address;
		this.gender = gender;
		this.amount = amount;
		this.pin = pin;
		this.accountNo = accountNo;
		this.status = status;
	}

	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public long getAadharNo() {
		return aadharNo;
	}
	public void setAadharNo(long aadharNo) {
		this.aadharNo = aadharNo;
	}
			
	public long getMobileNo() {
		return mobileNo;
	}
	public void setMobileNo(long mobileNo) {
		this.mobileNo = mobileNo;
	}
	public String getPanNo() {
		return panNo;
	}
	public void setPanNo(String panNo) {
		this.panNo = panNo;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public int getPin() {
		return pin;
	}
	public void setPin(int pin) {
		this.pin = pin;
	}
	public int getAccountNo() {
		return accountNo;
	}
	public void setAccountNo(int accountNo) {
		this.accountNo = accountNo;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
}
