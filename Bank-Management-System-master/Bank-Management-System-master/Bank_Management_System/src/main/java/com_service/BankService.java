package com_service;

public interface BankService {
   void forsleep(String value);
   void userRegistration();
   void userLogin();
   void debit(double bankAmt,int pin);
   void credit(double bankAmt,int pin);
   void checkBalance(double bankAmt);
}
