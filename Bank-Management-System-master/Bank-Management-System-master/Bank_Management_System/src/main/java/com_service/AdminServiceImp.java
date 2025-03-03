package com_service;

import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.stream.Collectors;

import com.DAO.AdminDAO;
import com.DAO.AdminDAOImp;
import com.DAO.BankUserDAO;
import com.DAO.BankUserDAOImp;
import com.Exceptions.AdminException;
import com.Model.BankUserDetails;

public class AdminServiceImp implements AdminService{
	AdminDAO adD=new AdminDAOImp();
	BankUserDAO budDAO=new BankUserDAOImp();
	Scanner sc=new Scanner(System.in);
	@Override
	public void adminLogin() {
		boolean status=true;
		while(status) {
			try {
				System.out.println("Enter Email Id: ");
				String email=sc.next();
				System.out.println("Enter Password : ");
				String pass=sc.next();
				if(adD.getAdminDetailsUsingEmailAndPassword(email, pass)) {
					status=false;
					System.out.println("Enter 1.Get All User Details\n2.Get All Account Request Details");
					switch (sc.nextInt()) {
					case 1:
						System.out.println("Get all user details");
						allAccountDetails();
						break;
					case 2:
						System.out.println("Get all account request details");
						allAccountRequestDetails();
						break;
					default:
						break;
					}
				}
				else {
					throw new AdminException("Invalid Email and Password");
				}
			}catch(AdminException e) {
				System.out.println(e.getMsg());
			}
		}
	}
	@Override
	public void allAccountDetails() {
		List<BankUserDetails> l=budDAO.getAllUserDetails();
		l.forEach((user)->{
			int i=l.indexOf(user)+1;
			System.out.println("S.No : "+i);
			System.out.println("Name: "+user.getName());
			System.out.println("Email id: "+user.getEmail());
			System.out.println("Aadhar No: "+user.getAadharNo());
			System.out.println("Mobile No : "+user.getMobileNo());
		});
		
	}
	@Override
	public void allAccountRequestDetails() {
		List<BankUserDetails>l=budDAO.getAllUserDetails();
		List<BankUserDetails>pending=l.stream()
				.filter(user->user.getStatus().equalsIgnoreCase("Pending"))
				.collect(Collectors.toList());
		pending.forEach((user)->{
			int i=pending.indexOf(user)+1;
			System.out.println("S. No : "+i);
			System.out.println("UserName: "+user.getName());
			System.out.println("EmailId: "+user.getEmail());
			System.out.println("Aadhar No: "+user.getAadharNo());
			System.out.println("Mobile No: "+user.getMobileNo());
			System.out.println("Status: "+user.getStatus());
		});
		System.out.println("Enter S.No to select user");
		BankUserDetails currentUser=pending.get(sc.nextInt()-1);
		System.out.println(currentUser);
		System.out.println("Enter\n1.To accept 2.To reject");
		switch (sc.nextInt()) {
		case 1:
			System.out.println("Accepted");
			acceptRequest(currentUser.getAadharNo());
			break;
		case 2:
			System.out.println("Rejected");
			break;
		default:
			System.out.println("No data found/Invalid Input");
			break;
		}
	}
	@Override
	public void acceptRequest(long aadharNo) {
		Random r=new Random();
		int pin=r.nextInt(10000);
		if(pin<1000)
			pin+=1000;
		int accountNo=r.nextInt(1000000);
		if(accountNo<1000000)
			accountNo+=1000000;
		int res=budDAO.updatePinandAccountUsingAadhar(pin, accountNo, aadharNo);
		if(res!=0) {
			System.out.println("Account accepted successfully\nAccount No : "+accountNo+"\nPIN : "+pin);
		}else System.out.println("Server 404 Not Found");
	}
}
