package com.shefat.account;

//SalaryAccount is an implementation of BankAccount class and inherited BankAccount properties
public class SalaryAccount extends BankAccount {

	//Special field of SalaryAccount class
	private String companyName;

	// Getters and Setters for the above field to allow its access by outside classes
	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	@Override
	public String toString() {
		return "SalaryAccount [accountType=" + getAccountType() + ", accountNumber=" + getAccountNumber()
				+ ", accountCreationDate=" + getAccountCreationDate() + ", accountHolderName=" + getAccountHolderName()
				+ ", balance=" + getBalance() + ", companyName=" + companyName + "]";
	}
}
