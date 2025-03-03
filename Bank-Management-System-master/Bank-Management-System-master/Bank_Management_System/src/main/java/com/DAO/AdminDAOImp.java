package com.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class AdminDAOImp implements AdminDAO{

	public static String url="jdbc:mysql://localhost:3306/tec_63?user=root&password=12345";
	public static String select="select * from admin where admin_email=? and admin_password=?";
	@Override
	public boolean getAdminDetailsUsingEmailAndPassword(String email, String pass) {
		try {
			Connection c=DriverManager.getConnection(url);
			PreparedStatement ps=c.prepareStatement(select);
			ps.setString(1,email);
			ps.setString(2, pass);
			ResultSet rs=ps.executeQuery();
			if(rs.next()) 
				return true;
			return false;
			}
		catch(SQLException e){
			e.printStackTrace();
			return false;
		}
	}

}
