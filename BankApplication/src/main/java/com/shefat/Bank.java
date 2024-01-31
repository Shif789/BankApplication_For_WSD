package com.shefat;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.shefat.account.BankAccount;
import com.shefat.account.CurrentAccount;
import com.shefat.account.SalaryAccount;
import com.shefat.account.SavingsAccount;

public class Bank {

	// Bank class containing list of bank accounts
	private List<BankAccount> bankAccountList= new ArrayList<>();

	/**
	 *  method to access bankAccountList
	 *  returns bankAccountList
	 * */
	public List<BankAccount> getBankAccountList() {
		return bankAccountList;
	}

	/**
	 *  method to create CurrentAccount instance
	 *  returns String success or failure message
	 * */
	public String createCurrentAccount(Long accountNumber, LocalDateTime accountCreationDate,
			String accountHolderName, Double depositAmt) {

		// Empty CurrentAccount instance created
		CurrentAccount currentAccount = new CurrentAccount();
		
		// Injecting values to the CurrentAccount fields using setters
		currentAccount.setAccountType("Current");
		currentAccount.setAccountNumber(accountNumber);
		currentAccount.setAccountCreationDate(accountCreationDate);
		currentAccount.setAccountHolderName(accountHolderName);
		currentAccount.setBalance(depositAmt);
		
		// Adding the CurrentAccount instance to the bankAccountList
		bankAccountList.add(currentAccount);
		
		return currentAccount == null ? "Account creation failed"
				: currentAccount.getAccountType() + " Account successfully created with account no.: "
						+ currentAccount.getAccountNumber();
	}

	/**
	 *  method to create SavingsAccount instance
	 *  returns String success or failure message
	 * */
	public String createSavingsAccount(Long accountNumber, LocalDateTime accountCreationDate,
			String accountHolderName, Double depositAmt, Double interestRate) {

		// Empty SavingsAccount instance created
		SavingsAccount savingsAccount = new SavingsAccount();
		
		// Injecting values to the SavingsAccount fields using setters
		savingsAccount.setAccountType("Savings");
		savingsAccount.setAccountNumber(accountNumber);
		savingsAccount.setAccountCreationDate(accountCreationDate);
		savingsAccount.setAccountHolderName(accountHolderName);
		savingsAccount.setBalance(depositAmt);
		savingsAccount.setInterestRate(interestRate);
		
		// Adding the SavingsAccount instance to the bankAccountList
		bankAccountList.add(savingsAccount);
		
		return savingsAccount == null ? "Account creation failed"
				: savingsAccount.getAccountType() + " Account successfully created with account no.: "
						+ savingsAccount.getAccountNumber();
	}

	/**
	 *  method to create SalaryAccount instance
	 *  returns String success or failure message
	 * */
	public String createSalaryAccount(Long accountNumber, LocalDateTime accountCreationDate,
			String accountHolderName, Double depositAmt, String companyName) {

		// Empty SalaryAccount instance created
		SalaryAccount salaryAccount = new SalaryAccount();
		
		// Injecting values to the SalaryAccount fields using setters
		salaryAccount.setAccountType("Salary");
		salaryAccount.setAccountNumber(accountNumber);
		salaryAccount.setAccountCreationDate(accountCreationDate);
		salaryAccount.setAccountHolderName(accountHolderName);
		salaryAccount.setBalance(depositAmt);
		salaryAccount.setCompanyName(companyName);
		
		// Adding the SalaryAccount instance to the bankAccountList
		bankAccountList.add(salaryAccount);
		
		return salaryAccount == null ? "Account creation failed"
				: salaryAccount.getAccountType() + " Account successfully created with account no.: "
						+ salaryAccount.getAccountNumber();
	}
}
