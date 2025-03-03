package com;

import java.time.LocalDate;
import java.util.Scanner;

import com.DAO.BankUserDAO;
import com.DAO.BankUserDAOImp;

import com_service.AdminService;
import com_service.AdminServiceImp;
import com_service.BankService;
import com_service.BankServiceImp;
/**
 * Hello world!
 * pom-project object model
 *				
 */
public class App
{
    public static void main( String[] args )
    {
    	AdminService as=new AdminServiceImp();
    	BankUserDAO budDAO=new BankUserDAOImp();
    	BankService bs=new BankServiceImp();
    	Scanner sc=new Scanner(System.in);
    	bs.forsleep("-----Welcome to Bank-----");
        boolean start=true;
    	while(start) {
    		
	        System.out.println("Enter\n1.User Login\n2.Admin Login\n3.User Registration");
	        switch(sc.nextInt()) {
	        case 1:
	        	bs.forsleep("User Login");
	        	bs.userLogin();
	           	break;
	        case 2:
	        	bs.forsleep("Admin Login");
	        	as.adminLogin();
	        	break;
	        case 3:
	        	bs.forsleep("User Registration");
	        	bs.userRegistration();
	        	break;
	        	
	        default:
	        	System.out.println("Invalid Option");
	        	break;
	        }
	        System.out.println("DO you want to continue\nYES or NO");
	        if(sc.next().equalsIgnoreCase("no")) {
	        	bs.forsleep("-----Thank You-----");
	        	start=false;
	        }
	        
    	}
    }
}
