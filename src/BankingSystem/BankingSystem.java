package BankingSystem;

import java.util.HashMap;
import java.util.Scanner;


public class BankingSystem {

    public class BankAccount {
        private String accountNumber;
        private String accountHolderName;
        private double balance;

        public BankAccount(String accountNumber, String accountHolderName, double initialBalance) {
            this.accountNumber = accountNumber;
            this.accountHolderName = accountHolderName;
            this.balance = initialBalance;
        }

        public String getAccountNumber() {
            return accountNumber;
        }

        public String getAccountHolderName() {
            return accountHolderName;
        }

        public double getBalance() {
            return balance;
        }

        public void deposit(double amount) {
            if (amount > 0) {
                balance += amount;
                System.out.println("Deposit successful. New balance: " + balance);
            } else {
                System.out.println("Invalid deposit amount.");
            }
        }

        public void withdraw(double amount) {
            if (amount > 0 && amount <= balance) {
                balance -= amount;
                System.out.println("Withdrawal successful. New balance: " + balance);
            } else {
                System.out.println("Invalid withdrawal amount or insufficient balance.");
            }
        }

        public void transfer(BankAccount recipient, double amount) {
            if (amount > 0 && amount <= balance) {
                this.withdraw(amount);
                recipient.deposit(amount);
                System.out.println("Transfer successful.");
            } else {
                System.out.println("Invalid transfer amount or insufficient balance.");
            }
        }
    }

    private HashMap<String, BankAccount> accounts;

    public BankingSystem() {
        accounts = new HashMap<>();
    }

    public void createAccount(String accountNumber, String accountHolderName, double initialBalance) {
        if (!accounts.containsKey(accountNumber)) {
            BankAccount newAccount = new BankAccount(accountNumber, accountHolderName, initialBalance);
            accounts.put(accountNumber, newAccount);
            System.out.println("Account created successfully.");
        } else {
            System.out.println("Account number already exists.");
        }
    }

    public BankAccount getAccount(String accountNumber) {
        return accounts.get(accountNumber);
    }

    public void showMenu() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("\nBanking System Menu:");
            System.out.println("1. Create a new account");
            System.out.println("2. Deposit funds");
            System.out.println("3. Withdraw funds");
            System.out.println("4. Check account balance");
            System.out.println("5. Transfer funds");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();  // Consume newline

            switch (choice) {
                case 1:
                    System.out.print("Enter account number: ");
                    String accNumber = scanner.nextLine();
                    System.out.print("Enter account holder name: ");
                    String accHolder = scanner.nextLine();
                   System.out.print("Enter initial balance: ");
                    double initBalance = scanner.nextDouble();
                    createAccount(accNumber, accHolder, initBalance);
                    break;
                case 2:
                    System.out.print("Enter account number: ");
                    accNumber = scanner.nextLine();
                    System.out.print("Enter amount to deposit: ");
                    double depositAmount = scanner.nextDouble();
                    BankAccount acc = getAccount(accNumber);
                    if (acc != null) {
                        acc.deposit(depositAmount);
                    } else {
                        System.out.println("Account not found.");
                    }
                    break;
                case 3:
                    System.out.print("Enter account number: ");
                    accNumber = scanner.nextLine();
                    System.out.print("Enter amount to withdraw: ");
                    double withdrawAmount = scanner.nextDouble();
                    acc = getAccount(accNumber);
                    if (acc != null) {
                        acc.withdraw(withdrawAmount);
                    } else {
                        System.out.println("Account not found.");
                    }
                    break;
                case 4:
                    System.out.print("Enter account number: ");
                    accNumber = scanner.nextLine();
                    acc = getAccount(accNumber);
                    if (acc != null) {
                        System.out.println("Account balance: " + acc.getBalance());
                    } else {
                        System.out.println("Account not found.");
                    }
                    break;
                case 5:
                    System.out.print("Enter your account number: ");
                    String fromAccNumber = scanner.nextLine();
                    System.out.print("Enter recipient account number: ");
                    String toAccNumber = scanner.nextLine();
                    System.out.print("Enter amount to transfer: ");
                    double transferAmount = scanner.nextDouble();
                    BankAccount fromAcc = getAccount(fromAccNumber);
                    BankAccount toAcc = getAccount(toAccNumber);
                    if (fromAcc != null && toAcc != null) {
                        fromAcc.transfer(toAcc, transferAmount);
                    } else {
                        System.out.println("Invalid account number(s).");
                    }
                    break;
                case 6:
                    System.out.println("Exiting system.");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    public static void main(String[] args) {
        BankingSystem bankingSystem = new BankingSystem();
        bankingSystem.showMenu();
    }
}
