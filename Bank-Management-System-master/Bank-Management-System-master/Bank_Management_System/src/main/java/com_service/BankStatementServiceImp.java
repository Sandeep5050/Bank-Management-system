package com_service;

import com.DAO.BankStatementDAO;
import com.DAO.BankStatementDAOImp;
import com.Model.BankStatement;


public class BankStatementServiceImp implements BankStatementService{

	BankStatementDAO bsDAO=new BankStatementDAOImp();
	
	@Override
	public int bankStatementDetails(BankStatement bs) {
		return bsDAO.insertBankStatement(bs);
	}
}
