package com.shefat;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

import com.shefat.account.BankAccount;
import com.shefat.account.SalaryAccount;
import com.shefat.account.SavingsAccount;

// Controller class which is accessed by the user to start the application
public class App {
	
	/**
	 * main(String[] args) method to start the application
	 * @param args is used to receive console arguments
	 */
	public static void main(String[] args) {
		System.out.println("*************WELCOME TO SHEFAT BANK SYSTEM******************************");
		System.out.println();
		Scanner scan = new Scanner(System.in);
		Bank bank = new Bank();
		while (true) {
			System.out.println("Press 1 to Create New Account");
			System.out.println("Press 2 to Display All Accounts");
			System.out.println("Press 3 to Update Account");
			System.out.println("Press 4 to Delete Account");
			System.out.println("Press 5 to Deposit an amount in an Account");
			System.out.println("Press 6 to Withdraw an amount from an Account");
			System.out.println("Press 7 to Search for an Account");
			System.out.println("Press 8 to Exit");
			System.out.print("Enter any number from 1 to 8: ");
			int opSelection = scan.nextInt();

			try {
				if (opSelection == 1) {
					accountCreationOperation(bank);// to call account creation operation method
				} else if (opSelection == 2) {
					displayAllAccountOperation(bank);// to call display all accounts information operation method
				} else if (opSelection == 3) {
					accountUpdateOperation(bank);// to call update account operation method
				} else if (opSelection == 4) {
					accountDeleteOperation(bank);// to call delete account operation method
				} else if (opSelection == 5) {
					depositOperation(bank);// to call deposit amount operation method
				} else if (opSelection == 6) {
					withdrawOperation(bank);// to call withdraw amount operation method
				} else if (opSelection == 7) {
					accountSearchOperation(bank);// to call search account operation method
				} else if (opSelection == 8) {
					break;// to call exit the system
				}
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}

			System.out.println("Do you want to continue? [yes/no]: ");
			String isExit = scan.next();
			if (isExit.equalsIgnoreCase("no")) {
				break;
			}
		}
		System.out.println("\nShefat Bank System Closed...");
		scan.close();

	}

	/**
	 * accountCreationOperation(Bank bank) method is used for creating new account
	 * this method in turn calls methods from Bank class to create specific accounts
	 * @param bank receives Bank object passed from main method while calling this function
	 */
	private static void accountCreationOperation(Bank bank) {

		// get the input from the users
		Scanner scanner = new Scanner(System.in);
		System.out.print(
				"\tEnter 1 for Current Account \n\tEnter 2 for Savings Account \n\tEnter 3 for Salary Account: ");
		int type = scanner.nextInt();
		System.out.print("\tEnter account-holder name: ");
		String accountHolderName = scanner.next();
		System.out.print("\tEnter deposit (minimum $500): ");
		double deposit = scanner.nextDouble();

		// Constraint: minimum amount $500 required to open any bank account
		if (deposit < 500.00) {
			throw new RuntimeException(" Account creation failed! \n Required to deposit a minimum amount of $500");
		}

		long accountNo = new Random().nextLong(10000);
		LocalDateTime creationDate = LocalDateTime.now();

		String message = null;
		// Specific account creation method is called from Bank object based on account type
		if (type == 1) {
			message = bank.createCurrentAccount(accountNo, creationDate, accountHolderName, deposit);
		} else if (type == 2) {
			System.out.print("\tEnter interest rate: ");
			double interestRate = scanner.nextDouble();
			message = bank.createSavingsAccount(accountNo, creationDate, accountHolderName, deposit, interestRate);
		} else if (type == 3) {
			System.out.print("\tEnter company name you currently work at: ");
			String companyName = scanner.next();
			message = bank.createSalaryAccount(accountNo, creationDate, accountHolderName, deposit, companyName);
		} else
			throw new RuntimeException(
					" Account creation failed! \n Please enter 1 or 2 or 3 for correct account type!");

		//Printing the success or failure message of account creation feature
		if (message != null)
			System.out.println(message);
		else
			System.out.println("\tAccount creation failed!");
		// scanner.close();
	}

	/**
	 * displayAllAccountOperation(Bank bank) method is used for displaying information of all the accounts that are created
	 * 
	 * @param bank receives Bank object passed from main method while calling this function
	 */
	private static void displayAllAccountOperation(Bank bank) {
		//Retrieved list of bank accounts that are created
		List<BankAccount> bankAccountList = bank.getBankAccountList();
		
		//Prints the bank account information if accounts are present in the bankAccountList
		if (bankAccountList.size() > 0) {
			System.out.println("\tBank Account Information List");
			System.out.println("----------------------------------");

			for (BankAccount bankAccount : bankAccountList) {
				System.out.println("\tAccount-Type: " + bankAccount.getAccountType());
				System.out.println("\tAccountNo: " + bankAccount.getAccountNumber());
				System.out.println("\tAccount-Holder-Name: " + bankAccount.getAccountHolderName());
				System.out.println("\tAccount-Creation-Date: " + bankAccount.getAccountCreationDate());
				System.out.println("\tBalanceAmt: " + bankAccount.getBalance());
				
				// Prints specific information of SavingsAccount and SalaryAccount
				if (bankAccount.getAccountType().equalsIgnoreCase("Savings"))
					System.out.println("\tInterest-Rate: " + ((SavingsAccount) bankAccount).getInterestRate());
				else if (bankAccount.getAccountType().equalsIgnoreCase("Salary"))
					System.out.println("\tCompany: " + ((SalaryAccount) bankAccount).getCompanyName());
				System.out.println("----------------------------------------------------------------");
			}
		} else
			System.out.println("No account is created to show!");
	}

	/**
	 * accountSearchOperation(Bank bank) method is used for searching a particular account that is already created
	 * 
	 * @param bank receives Bank object passed from main method while calling this function
	 */
	private static void accountSearchOperation(Bank bank) {
		//Retrieved list of bank accounts that are created
		List<BankAccount> bankAccountList = bank.getBankAccountList();
		
		// Searches and prints the bank account information if accounts are present in the bankAccountList
		if (bankAccountList.size() > 0) {
			// get the input from the users
			Scanner scanner = new Scanner(System.in);
			System.out.print("\tEnter account number to search: ");
			long accNo = scanner.nextLong();
			
			for (BankAccount bankAccount : bankAccountList) {
				// checks if the desired bank account is found
				if (bankAccount.getAccountNumber() == accNo) {
					//prints the bank account information of the desired account
					System.out.println("\tBank Account Information");
					System.out.println("--------------------------------------------------------------");
					System.out.println("\tAccount-Type: " + bankAccount.getAccountType());
					System.out.println("\tAccountNo: " + bankAccount.getAccountNumber());
					System.out.println("\tAccount-Holder-Name: " + bankAccount.getAccountHolderName());
					System.out.println("\tAccount-Creation-Date: " + bankAccount.getAccountCreationDate());
					System.out.println("\tBalanceAmt: " + bankAccount.getBalance());
					
					// Prints specific information of SavingsAccount and SalaryAccount
					if (bankAccount.getAccountType().equalsIgnoreCase("Savings"))
						System.out.println("\tInterest-Rate: " + ((SavingsAccount) bankAccount).getInterestRate());
					else if (bankAccount.getAccountType().equalsIgnoreCase("Salary"))
						System.out.println("\tCompany: " + ((SalaryAccount) bankAccount).getCompanyName());

					return;
				}
			}
			System.out.println("Account not found with accountNo.: " + accNo);
		} else
			System.out.println("No account created!");

	}

	/**
	 * accountDeleteOperation(Bank bank) method is used for deleting a particular account that is already created
	 * 
	 * @param bank receives Bank object passed from main method while calling this function
	 */
	private static void accountDeleteOperation(Bank bank) {
		//Retrieved list of bank accounts that are created
		List<BankAccount> bankAccountList = bank.getBankAccountList();
		
		// Searches and deletes the bank account if accounts are present in the bankAccountList
		if (bankAccountList.size() > 0) {
			// get the input from the users
			Scanner scanner = new Scanner(System.in);
			System.out.print("\tEnter account number to delete: ");
			long accNo = scanner.nextLong();
			
			for (BankAccount bankAccount : bankAccountList) {
				// checks if the required account is found for deletion
				if (bankAccount.getAccountNumber() == accNo) {
					// deletes the account
					boolean removed = bank.getBankAccountList().remove(bankAccount);
					if (removed)
						System.out.println("Account removed with account number: " + accNo);
					return;
				}
			}
			System.out.println("Account not found with accountNo.: " + accNo + " for deletion");
		} else
			System.out.println("No account created!");

	}

	/**
	 * depositOperation(Bank bank) method is used for depositing an amount in a particular account that is already created
	 * 
	 * @param bank receives Bank object passed from main method while calling this function
	 */
	private static void depositOperation(Bank bank) {
		//Retrieved list of bank accounts that are created
		List<BankAccount> bankAccountList = bank.getBankAccountList();
		
		// Searches the bank account information if accounts are present in the bankAccountList
		if (bankAccountList.size() > 0) {

			// get the input from the users
			Scanner scanner = new Scanner(System.in);
			System.out.print("\tEnter account number to deposit an amount: ");
			long accNo = scanner.nextLong();

			// searches in the list
			for (BankAccount bankAccount : bankAccountList) {
				// checks if the desired account is found
				if (bankAccount.getAccountNumber() == accNo) {
					// takes the deposit amount from user
					System.out.print("\tEnter an amount to deposit: ");
					double depositAmt = scanner.nextDouble();
					
					//adds the deposit amount to the existing balance
					bankAccount.setBalance(bankAccount.getBalance() + depositAmt);
					System.out.println("Successfully deposited amount: " + depositAmt
							+ " in account with account number: " + accNo);
					return;
				}
			}

			System.out.println("Account not found with accountNo.: " + accNo + " to deposit");

		} else
			System.out.println("No account created!");

	}

	/**
	 * withdrawOperation(Bank bank) method is used for withdrawing an amount from a particular account that is already created
	 * 
	 * @param bank receives Bank object passed from main method while calling this function
	 */
	private static void withdrawOperation(Bank bank) {
		//Retrieved list of bank accounts that are created
		List<BankAccount> bankAccountList = bank.getBankAccountList();
		
		// Searches the bank account information if accounts are present in the bankAccountList
		if (bankAccountList.size() > 0) {

			// get the input from the users
			Scanner scanner = new Scanner(System.in);
			System.out.print("\tEnter account number to withdraw an amount: ");
			long accNo = scanner.nextLong();

			//searches in the list
			for (BankAccount bankAccount : bankAccountList) {
				//checks if the desired account is found
				if (bankAccount.getAccountNumber() == accNo) {
					// takes the withdraw amount from the user
					System.out.print("\tEnter an amount to withdraw: ");
					double withdrawAmt = scanner.nextDouble();

					Double currentBalance = bankAccount.getBalance();
					Double balanceAfterWithdrawal = currentBalance - withdrawAmt;
					
					// Constraint: checks if the withdrawing amount is within the limit 
					if (balanceAfterWithdrawal < 500.00) {
						throw new RuntimeException(
								" Withdraw operation failed! \n Insufficient balance \n Please enter a valid withdraw amount so that your account balance shouldn't be less than $500");
					}
					
					// performs the withdrawing operation
					bankAccount.setBalance(balanceAfterWithdrawal);
					System.out.println("Successfully withdrawn amount: " + withdrawAmt
							+ " in account with account number: " + accNo);
					return;
				}
			}

			System.out.println("Account not found with accountNo.: " + accNo + " to withdraw");

		} else
			System.out.println("No account created!");

	}

	/**
	 * accountUpdateOperation(Bank bank) method is used for updating a particular account information that is already created
	 * 
	 * @param bank receives Bank object passed from main method while calling this function
	 */
	private static void accountUpdateOperation(Bank bank) {
		//Retrieved list of bank accounts that are created
		List<BankAccount> bankAccountList = bank.getBankAccountList();
		
		// Searches the bank account information if accounts are present in the bankAccountList
		if (bankAccountList.size() > 0) {

			// get the input from the users
			Scanner scanner = new Scanner(System.in);
			System.out.print("\tEnter account number to update: ");
			long accNo = scanner.nextLong();

			//searches in the list
			for (BankAccount bankAccount : bankAccountList) {
				//checks if the desired account is found
				if (bankAccount.getAccountNumber() == accNo) {
					// displays the current output and takes the new input from the user
					System.out.print("\tCurrent Account holder name is: " + bankAccount.getAccountHolderName());
					System.out.println("\tDo you want to update the name? (yes/no) ");
					String updateChoice = scanner.next();
					if (updateChoice.trim().equalsIgnoreCase("yes")) {
						System.out.println("\tEnter new Account holder name: ");
						String newAccountHolderName = scanner.next();

						// performs updation
						bankAccount.setAccountHolderName(newAccountHolderName);

						System.out.println("Successfully updated account holder name: " + newAccountHolderName
								+ " in account with account number: " + accNo);
					}

					// performs updation for specific accounts fields
					if (bankAccount.getAccountType().equalsIgnoreCase("Savings")) {
						System.out.print("\tCurrent Savings Account interest rate is: "
								+ ((SavingsAccount) bankAccount).getInterestRate());
						System.out.println("\tDo you want to update the interest rate? (yes/no) ");
						updateChoice = scanner.next();
						if (updateChoice.trim().equalsIgnoreCase("yes")) {
							System.out.println("\tEnter new interest rate: ");
							Double newInterestRate = scanner.nextDouble();

							((SavingsAccount) bankAccount).setInterestRate(newInterestRate);

							System.out.println("Successfully updated interest rate: " + newInterestRate
									+ " in account with account number: " + accNo);
						}
					} else if (bankAccount.getAccountType().equalsIgnoreCase("Salary")) {
						System.out.print("\tCurrent company you are working at is: "
								+ ((SalaryAccount) bankAccount).getCompanyName());
						System.out.println("\tDo you want to update the company name? (yes/no) ");
						updateChoice = scanner.next();
						if (updateChoice.trim().equalsIgnoreCase("yes")) {
							System.out.println("\tEnter new company name: ");
							String newCompanyName = scanner.next();

							((SalaryAccount) bankAccount).setCompanyName(newCompanyName);

							System.out.println("Successfully updated company name: " + newCompanyName
									+ " in account with account number: " + accNo);
						}
					}

					return;
				}
			}

			System.out.println("Account not found with accountNo.: " + accNo + " to update");

		} else
			System.out.println("No account created!");

	}
}
