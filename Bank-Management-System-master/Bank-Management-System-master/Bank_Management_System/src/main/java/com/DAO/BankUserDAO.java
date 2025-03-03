package com.DAO;

import java.util.List;

import com.Model.BankUserDetails;

public interface BankUserDAO {
	void insertBankDetails(BankUserDetails bud);
	void getUserDetailsUsingEmailIdandPin(String email,int pin);
	List<BankUserDetails> getAllUserDetails();
	int updatePinandAccountUsingAadhar(int pin,int accountNo,long aadharNo);
	BankUserDetails getUserDetailsUsingEmailORAccountNoAndPin(String ea,int pin);
	int updateAmount(int pin,double balanceAmt);
}