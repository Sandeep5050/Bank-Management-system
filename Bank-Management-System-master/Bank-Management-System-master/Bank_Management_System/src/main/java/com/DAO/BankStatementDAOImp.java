package com.DAO;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;

import com.Model.BankStatement;
import com.Model.BankUserDetails;

public class BankStatementDAOImp implements BankStatementDAO{
	
	private static final String url="jdbc:mysql://localhost:3306/tec_63?user=root&password=12345";
	private static final String insert="insert into bank_statement "
			+ "( transaction_amount, balance_amount, date_of_transaction, time_of_transaction, transaction_type, user_id) "
			+ "values (?,?,?,?,?,?)"; 
	
	@Override
	public int insertBankStatement(BankStatement bs) {
		try {
			Connection c=DriverManager.getConnection(url);
			PreparedStatement ps=c.prepareStatement(insert);
			ps.setDouble(1,bs.getTransamount());
			ps.setDouble(2,bs.getBalanceamount());
			ps.setDate(3, Date.valueOf(bs.getDateoftrans()));
			ps.setTime(4, Time.valueOf(bs.getTimeoftrans()));
			ps.setString(5, bs.getTranstype());
			ps.setInt(6, bs.getUserId());
			return ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return 0;
		}
		
	}

	private BankUserDAO BankUserDAOImp() {
		// TODO Auto-generated method stub
		return null;
	}

}
