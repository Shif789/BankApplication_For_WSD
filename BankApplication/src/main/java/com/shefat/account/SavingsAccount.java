package com.shefat.account;

import java.time.LocalDateTime;

//SavingsAccount is an implementation of BankAccount class and inherited BankAccount properties
public class SavingsAccount extends BankAccount {

	//Special field of SavingsAccount class
	private Double interestRate;

	// Getters and Setters for the above field to allow its access by outside classes
	public Double getInterestRate() {
		return interestRate;
	}

	public void setInterestRate(Double interestRate) {
		this.interestRate = interestRate;
	}

	/**
	 * getBalance() method of BankAccount(super class) is overriden here to add interest amount calculated using interestRate field
	 * return modified balance amount with added interest amount
	 */
	@Override
	public Double getBalance() {
		int accountCreationYear=getAccountCreationDate().getYear();
		int currentYear=LocalDateTime.now().getYear();
		int time=currentYear-accountCreationYear;
		double interestAmt=(super.getBalance()*time*this.interestRate)/100;
		
		return super.getBalance()+interestAmt;
	}

	@Override
	public String toString() {
		return "SavingsAccount [accountType=" + getAccountType() + ", accountNumber=" + getAccountNumber()
				+ ", accountCreationDate=" + getAccountCreationDate() + ", accountHolderName=" + getAccountHolderName()
				+ ", balance=" + this.getBalance() + ", interestRate=" + interestRate + "]";
	}
}
