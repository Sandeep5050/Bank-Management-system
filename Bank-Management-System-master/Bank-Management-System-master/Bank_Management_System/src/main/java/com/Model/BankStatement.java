package com.Model;

import java.time.LocalDate;
import java.time.LocalTime;

/*
 * transaction_id,
 * transaction_amount,
 * balance_amount,
 * date_of_transaction,
 * time_of_transaction,
 * transaction_type,
 * user_id
 */
public class BankStatement {
	private int transId;
	private double transamount;
	private double balanceamount;
	private LocalDate dateoftrans;
	private LocalTime timeoftrans;
	private String transtype;
	private int userId;
	
	public BankStatement() {}
	public BankStatement(int transId, double transamount, double balanceamount, LocalDate dateoftrans,
			LocalTime timeoftrans, String transtype, int userId) {
		super();
		this.transId = transId;
		this.transamount = transamount;
		this.balanceamount = balanceamount;
		this.dateoftrans = dateoftrans;
		this.timeoftrans = timeoftrans;
		this.transtype = transtype;
		this.userId = userId;
	}
	public int getTransId() {
		return transId;
	}
	public void setTransId(int transId) {
		this.transId = transId;
	}
	public double getTransamount() {
		return transamount;
	}
	public void setTransamount(double transamount) {
		this.transamount = transamount;
	}
	public double getBalanceamount() {
		return balanceamount;
	}
	public void setBalanceamount(double balanceamount) {
		this.balanceamount = balanceamount;
	}
	public LocalDate getDateoftrans() {
		return dateoftrans;
	}
	public void setDateoftrans(LocalDate dateoftrans) {
		this.dateoftrans = dateoftrans;
	}
	public LocalTime getTimeoftrans() {
		return timeoftrans;
	}
	public void setTimeoftrans(LocalTime timeoftrans) {
		this.timeoftrans = timeoftrans;
	}
	public String getTranstype() {
		return transtype;
	}
	public void setTranstype(String transtype) {
		this.transtype = transtype;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	@Override
	public String toString() {
		return "BankStatement [transId=" + transId + ", transamount=" + transamount + ", balanceamount=" + balanceamount
				+ ", dateoftrans=" + dateoftrans + ", timeoftrans=" + timeoftrans + ", transtype=" + transtype
				+ ", userId=" + userId + "]";
	}
	
}
