package com_service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import com.DAO.BankStatementDAO;
import com.DAO.BankStatementDAOImp;
import com.DAO.BankUserDAO;
import com.DAO.BankUserDAOImp;
import com.Exceptions.BankUserException;
import com.Model.BankStatement;
import com.Model.BankUserDetails;

public class BankServiceImp implements BankService{
	BankUserDetails login_person;
	BankStatementService bss=new BankStatementServiceImp();
	BankStatement bst=new BankStatement();
	Scanner s=new Scanner(System.in);
	BankUserDAO budDAO=new BankUserDAOImp();
//	BankStatementDAO bsDAO=new BankStatementDAOImp();
	
	@Override
	public void userRegistration() {
		List<BankUserDetails>l=budDAO.getAllUserDetails();
		BankUserDetails bud=new BankUserDetails();
		
//		USERNAME
		boolean status=true;
			while(status) {
				try {
					System.out.println("Enter Your Name");
					String name=s.nextLine();
					int c=0;
					for(int i=0;i<name.length();i++) {
						if(!Character.isAlphabetic(name.charAt(i))&&name.charAt(i)!=' '&&name.charAt(i)!='.') {
							c++;
						}
					}
					System.out.println(c);
					if(c==0) {
						int nc=0;
						for(BankUserDetails bud2:l) {
							if(bud2.getName().equalsIgnoreCase(name)) {
								nc++;
							}
							if(nc>0) {
								throw new BankUserException("name already exist");
							}
							else {
								bud.setName(name);
								status=false;
							}
						}
					}
					else {
						throw new BankUserException("Invalid name");
						}
				}
				catch(BankUserException e) {
					System.out.println(e.getMsg());
				}
			}
//			EMAILID
			status=true;
			while(status) {
				try {
					System.out.println("Enter Your Emailid");
					String emailid=s.next();
					int ec=10;
					String s1[]=emailid.split("@");
					if(s1.length==2) {
						if(s1[1].equals("gmail.com"))
							for(int i=0;i<s1[0].length();i++) 
								if(Character.isAlphabetic(s1[0].charAt(i))||Character.isDigit(s1[0].charAt(i)))
									ec++;
						if(ec==emailid.length()) {
							long emc=l.stream().filter(user->user.getEmail().equals(emailid)).count();
							if(emc==0) {
								bud.setEmail(emailid);
								status=false;
							}else throw new BankUserException("Email Already Exist");
						}else throw new BankUserException("Invalid format");
					}else throw new BankUserException("Invalid Email ID");
				}
				catch(BankUserException e)
				{
					System.out.println(e.getMsg());
				}
			}
//			AADHARNO
			status=true;
			while(status) {
				try {
					System.out.println("Enter Your Aadhar Number");
					long aadharNo=s.nextLong();
					long t=aadharNo;
					int c=0;
					while(t>0) {
						t/=10;
						c++;}
					if(c==12)
					{
						long ac=l.stream().filter(bankuser ->bankuser.getAadharNo()==aadharNo).count();
						if(ac==0) {
							bud.setAadharNo(aadharNo);
							status=false;
						}
						else {
							throw new BankUserException("Aadhar already exist");
						}
					}else throw new BankUserException("Aadhar should contain 12digits");
				}
				catch(BankUserException e) {
					System.out.println(e.getMsg());
				}catch(InputMismatchException b) {
					System.out.print("Aadhar should only contain digits\n");
					s.next();
				}
			}
//			MOBILENO
			status=true;
			while(status) {
				try {
					System.out.println("Enter Your Mobile Number");
					long mobileNo=s.nextLong();
					if(mobileNo>=6000000000l && mobileNo<=9999999999l) {
						long mn=l.stream().filter(user->user.getMobileNo()==mobileNo).count();
						if(mn==0) {
							status=false;
							bud.setMobileNo(mobileNo);
						}
						else {
							throw new BankUserException("Mobile no exist");
						}
					}
					else {
						throw new BankUserException("Invalid Mobile No");
					}
				}
				catch(BankUserException e) {
					System.out.println(e.getMsg());
				}catch(InputMismatchException b) {
					System.out.print("Number should only contain digits\n");
					s=new Scanner(System.in);
				}
			}
//			PANNO
			status=true;
			while(status) {
				try {
					System.out.println("Enter Your PAN Number");
					String panNo=s.next().toUpperCase();
					if(panNo.length()==10) {
						int a=0,d=0,last=0;
						for(int i=0;i<panNo.length()/2;i++) {
							if(Character.isAlphabetic(panNo.charAt(i)))
								a++;
						}
						for(int i=panNo.length()/2;i<panNo.length()-1;i++) {
							if(Character.isDigit(panNo.charAt(i)))
								d++;
						}
						if(Character.isAlphabetic(panNo.charAt(panNo.length()-1)))
							last++;
						System.out.println(a+"\n"+d+"\n"+last);
						if(a==5&&d==4&&last==1) {
							long pc=l.stream().filter(user->user.getPanNo().equals(panNo)).count();
							if(pc==0) {
								bud.setPanNo(panNo);
								status=false;
							}else throw new BankUserException("PAN already exist");
						}else throw new BankUserException("Invalid Format");
					}else throw new BankUserException("PAN should contain 10scharacters");
					
				}catch(BankUserException e) {
					System.out.println(e.getMsg());
				}
			}
				
			System.out.println("Enter Your Address");
			String address=s.next();
			bud.setAddress(address);
//			GENDER
			status=true;
			while(status) {
				try {
					System.out.println("Enter Your Gender");
					String gender=s.next();
					if(gender.equalsIgnoreCase("male")||gender.equalsIgnoreCase("female")) {
						bud.setGender(gender);
						status=false;
					}else throw new BankUserException("Enter male or female");
				}
				catch (BankUserException e) {
					System.out.println(e.getMsg());
				}
			}
			System.out.println("Enter Your Amount");
			Double amount=s.nextDouble();	
			bud.setAmount(amount);
			budDAO.insertBankDetails(bud);
		}
	@Override
	public void forsleep(String value) {
		for(int i=0;i<value.length();i++) {
			System.out.print(value.charAt(i));
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		System.out.println();
	}
	@Override
	public void userLogin() {
		boolean status=true;
		while(status) {
			try {
				System.out.println("Enter EmailID or Account Number");
				String ea=s.next();
				System.out.println("Enter PIN :");
				int pin=s.nextInt();
				login_person=budDAO.getUserDetailsUsingEmailORAccountNoAndPin(ea, pin);
				if(login_person!=null) {
					status=false;
					System.out.println("Enter\n1.For Credit\n2.For Debit\n3.Check balance"
							+ "\n4.Change PIN\n5.Check Statement\n6.Request to Delete Account");
					switch (s.nextInt()) {
					case 1:
						credit(login_person.getAmount(),pin);
						break;
					case 2:
						System.out.println("Debit");
						debit(login_person.getAmount(),pin);
						break;
					case 3:
						System.out.println("Check Balance");
						break;
					case 4:
						System.out.println("Change PIN");
						break;
					case 5:
						System.out.println("Change Statment");
						break;
					case 6:
						System.out.println("Remove Account");
						break;
					default:
						System.out.println("Invalid choice");
						break;
					}
				}else throw new BankUserException("Enter Valid Credentials");
			}catch(BankUserException e) {
				System.out.println(e.getMsg());
			}catch(InputMismatchException i) {
				System.out.println("Input Mismatch");
				s=new Scanner(System.in);
				}
		}
		
		
	}
	public void debit(double bankAmt,int pin) {
		boolean status=true;
		while(status) {
			try {
				System.out.println("Enter your Amount");
				double amt=s.nextDouble();
				if(amt>=0) {
					if(bankAmt>=amt) {
						double balanceAmt=bankAmt-amt;
						int res=budDAO.updateAmount(pin, balanceAmt);
						if(res>0) {
							BankStatement bst=new BankStatement();
							bst.setTransamount(amt);
							bst.setBalanceamount(balanceAmt);
							bst.setDateoftrans(LocalDate.now());
							bst.setTimeoftrans(LocalTime.now());
							bst.setTranstype("Debit");
							bst.setUserId(login_person.getId());
							int statementdetails=bss.bankStatementDetails(bst);
							if(statementdetails>0) {
								System.out.println("Amount Debited");
								bankAmt=balanceAmt;							
							}else 
								System.out.println("Server Error 404");
						}else
							throw new BankUserException("Invalid Amount");
						}else
							throw new BankUserException("Nullllllll");
					}
				}
					catch(BankUserException e) {
						System.out.println(e.getMsg());
					}
					System.out.println("DO you want to continue\nYES or NO");
					if(s.next().equalsIgnoreCase("no")) {
						status=false;
						System.out.println("Exited");
					}
		}
	}
	public void credit(double bankAmt,int pin) {
		boolean status=true;
		while(status) {
			try {
				System.out.println("Enter your Amount");
				double amt=s.nextDouble();
				if(amt>0) {
				double balanceAmt=bankAmt+amt;
				int res=budDAO.updateAmount(pin, balanceAmt);
					if(res>0) {
						BankStatement bst=new BankStatement();
						bst.setTransamount(amt);
						bst.setBalanceamount(balanceAmt);
						bst.setDateoftrans(LocalDate.now());
						bst.setTimeoftrans(LocalTime.now());
						bst.setTranstype("Credit");
						bst.setUserId(login_person.getId());
						int statementdetails=bss.bankStatementDetails(bst);
						if(statementdetails>0) {
							System.out.println("Amount Credited");
							bankAmt=balanceAmt;
						}
						System.out.println(bst.getBalanceamount());
						status=false;
					}else System.out.println("Server Error");
				}else throw new BankUserException("Invalid amount");
			}
			catch(BankUserException e) {
				System.out.println(e.getMsg());
			}
		}
	}
	@Override
	public void checkBalance(double bankAmt) {
		// TODO Auto-generated method stub
		
	}

}
