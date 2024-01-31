package com.shefat.account;

// CurrentAccountclass is an implementation of BankAccount class and inherited BankAccount properties
public class CurrentAccount extends BankAccount {

	//Special field of CurrentAccount class
	private boolean limitedTransaction = false;

	//Getter method of above field
	public boolean isLimitedTransaction() {
		return limitedTransaction;
	}

	@Override
	public String toString() {
		return "CurrentAccount [accountType=" + getAccountType() + ", accountNumber=" + getAccountNumber()
				+ ", accountCreationDate=" + getAccountCreationDate() + ", accountHolderName=" + getAccountHolderName()
				+ ", balance=" + getBalance() + ", limitedTransaction=" + limitedTransaction + "]";
	}

}
