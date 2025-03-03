	package com.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.Exceptions.BankUserException;
import com.Model.BankUserDetails;
/*
 * id, name, email_id, aadhar_No, mobile_no, pan_No, address, gender, amount, pin, account_No, status
 */
public class BankUserDAOImp implements BankUserDAO {
	  private static final String url="jdbc:mysql://localhost:3306/tec_63?user=root&password=12345";
	  private static final String insert="insert into bank_user_details(name, email_id, aadhar_No, mobile_no, pan_No, address, gender, amount, status)values(?,?,?,?,?,?,?,?,?)";
	  private static final String select="select * from bank_user_details where email_id=? and pin=?";
	  private static final String select_all="select * from bank_user_details";
	  private static final String update="update bank_user_details set pin=?,account_No=?,status=? where aadhar_No=?" ;
	  private static final String user_login="select * from bank_user_details where (email_id=? or account_No=?) and pin=?";
	  private static final String update_amt="update bank_user_details set amount=? where pin=?";
	  
	  @Override
		public List<BankUserDetails> getAllUserDetails() {
			try {
				Connection c=DriverManager.getConnection(url);
				Statement ps=c.createStatement();
				ResultSet rs=ps.executeQuery(select_all);
				List<BankUserDetails> l=new ArrayList<BankUserDetails>();
				if(rs.isBeforeFirst()) {
					while(rs.next()) {
						BankUserDetails bud=new BankUserDetails();
						bud.setName(rs.getString("name"));
						bud.setEmail(rs.getString("email_id"));
						bud.setAadharNo(rs.getLong("aadhar_No"));
						bud.setMobileNo(rs.getLong("mobile_no"));
						bud.setPanNo(rs.getString("pan_no"));
						bud.setStatus(rs.getString("status"));
						l.add(bud);
					}
					return l;
				}else
					return null;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return null;
			}
			
		}
	  
	  @Override
		public void insertBankDetails(BankUserDetails bud) {
			try {
				Connection c=DriverManager.getConnection(url);
				PreparedStatement ps=c.prepareStatement(insert);
				ps.setString(1,bud.getName());
				ps.setString(2,bud.getEmail());
				ps.setLong(3,bud.getAadharNo());
				ps.setLong(4,bud.getMobileNo());
				ps.setString(5,bud.getPanNo());
				ps.setString(6,bud.getAddress());
				ps.setString(7,bud.getGender());
				ps.setDouble(8,bud.getAmount());
				ps.setString(9,"pending");
				int r=ps.executeUpdate();
			}
			catch(SQLException e) {
				System.out.println(e);
			}
		}
	  /*
		 * id, name, email_id, aadhar_No, mobile_no, pan_No, address, gender, amount, pin, account_No, status
		 */
	
	@Override
	public void getUserDetailsUsingEmailIdandPin(String email, int pin) {
		try {
			Connection c=DriverManager.getConnection(url);
			PreparedStatement ps=c.prepareStatement(select);
			ps.setString(1,email);
			ps.setInt(2, pin);
			ResultSet rs=ps.executeQuery();
			System.out.println("id\tname\t email_id\t aadhar_No\t mobile_no\t pan_No\t address\t gender\t amount\t pin\t account_No\t status");
			while(rs.next()) {
					System.out.println(rs.getInt(1)+"\t"+rs.getString(2)+"\t"+rs.getString(3)+"\t"+rs.getLong(4)+"\t"+rs.getLong(5)+"\t"+rs.getString(6)+"\t"+rs.getString(7)
					+"\t"+rs.getString(8)+"\t"+rs.getString(9)+"\t"+rs.getDouble(10)+"\t"+rs.getInt(11)+"\t"+rs.getString(12));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

	

	@Override
	public int updatePinandAccountUsingAadhar(int pin, int accountNo, long aadharNo) {
		try {
			Connection c=DriverManager.getConnection(url);
			PreparedStatement ps=c.prepareStatement(update);
			ps.setInt(1,pin);
			ps.setInt(2, accountNo);
			ps.setString(3,"Accepted");
			ps.setLong(4, aadharNo);
			return ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return 0;
		}
		
	}

	@Override
	public BankUserDetails getUserDetailsUsingEmailORAccountNoAndPin(String ea, int pin) {
		try {
			Connection c=DriverManager.getConnection(url);
			PreparedStatement ps=c.prepareStatement(user_login);
			ps.setString(1, ea);
			ps.setString(2,ea);
			ps.setInt(3, pin);
			ResultSet rs=ps.executeQuery();
			if(rs.next()) {
				BankUserDetails bud=new BankUserDetails();
				bud.setAmount(rs.getDouble("amount"));
				bud.setPin(rs.getInt("pin"));
				return bud;
			}
			return null;
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public int updateAmount(int pin, double balanceAmt) {
		try {
			Connection c = DriverManager.getConnection(url);
				PreparedStatement ps=c.prepareStatement(update_amt);
				ps.setDouble(1,balanceAmt);
				ps.setInt(2, pin);
				return ps.executeUpdate();
				}
		catch (SQLException e) {
				e.printStackTrace();
				return 0;
			}
			
			
		
	}
}
