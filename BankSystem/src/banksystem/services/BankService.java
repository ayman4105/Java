package banksystem.services;
import banksystem.models.BankAccount;
import java.util.HashMap;

public class BankService {
    private HashMap<String, BankAccount> accounts;

    public BankService() {
        this.accounts = new HashMap<>();
    }

    public boolean createAccount(String accountNumber, String holderName, String password, double initialBalance) {
        if (accounts.containsKey(accountNumber)) {
            return false; 
        }
        BankAccount newAccount = new BankAccount(accountNumber, holderName, password, initialBalance);
        accounts.put(accountNumber, newAccount);
        return true; 
    }

    public BankAccount login(String accountNumber, String password) {
        BankAccount account = accounts.get(accountNumber);
        if (account != null && account.getPassword().equals(password)) {
            return account; 
        }
        return null; 
    }

    public boolean deposit(BankAccount account, double amount) {
        if (amount <= 0) {
            return false; 
        }
        account.setBalance(account.getBalance() + amount);
        return true; 
    }

    public boolean withdraw(BankAccount account, double amount) {
        if (amount <= 0 || amount > account.getBalance()) {
            return false; 
        }
        account.setBalance(account.getBalance() - amount);
        return true; 
    }

    public double checkBalance(BankAccount account) {
        return account.getBalance(); 
    }

    public boolean transfer(BankAccount fromAccount, String targetAccountNumber, double amount) {

        BankAccount targetAccount = accounts.get(targetAccountNumber); // Get target account by account number

        if (targetAccount == null) { // Check if target account does not exist
            return false;
        }

        if (!withdraw(fromAccount, amount)) { // Try to withdraw money from source account
            return false;
        }

        deposit(targetAccount, amount); // Deposit money into target account

        return true;

    }

    public HashMap<String, BankAccount> getAccounts() {
        return accounts;
    }

    public void viewAccountDetails(BankAccount account) {
        System.out.println("\n===== Account Details =====");
        System.out.println("Account Number: " + account.getAccountNumber());
        System.out.println("Holder Name: " + account.getHolderName());
        System.out.printf("Balance: %.2f\n", account.getBalance());
    }
    
}
