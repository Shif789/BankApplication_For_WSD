package com.shefat.account;

import java.time.LocalDateTime;

// BankAccount class is made abstract to prevent instance creation of this class
public abstract class BankAccount {

	// Fields of BankAccount class are made private to provide abstraction
	private String accountType;
	private Long accountNumber;
	private LocalDateTime accountCreationDate;
	private String accountHolderName;
	private Double balance;

	// Getters and Setters for the above fields to allow other classes to access the fields
	public String getAccountType() {
		return accountType;
	}

	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}

	public Long getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(Long accountNumber) {
		this.accountNumber = accountNumber;
	}

	public LocalDateTime getAccountCreationDate() {
		return accountCreationDate;
	}

	public void setAccountCreationDate(LocalDateTime accountCreationDate) {
		this.accountCreationDate = accountCreationDate;
	}

	public String getAccountHolderName() {
		return accountHolderName;
	}

	public void setAccountHolderName(String accountHolderName) {
		this.accountHolderName = accountHolderName;
	}

	public Double getBalance() {
		return balance;
	}

	public void setBalance(Double balance) {
		this.balance = balance;
	}

	// toString() method is overridden to display the object details rather than object hashcode
	@Override
	public String toString() {
		return "BankAccount [accountType=" + accountType + ", accountNumber=" + accountNumber + ", accountCreationDate="
				+ accountCreationDate + ", accountHolderName=" + accountHolderName + ", balance=" + balance + "]";
	}

}
