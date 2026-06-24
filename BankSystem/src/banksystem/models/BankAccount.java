package banksystem.models;

public class BankAccount {

    private String accountNumber; 
    private String holderName; 
    private String password; 
    private double balance; 

    public BankAccount(String accountNumber, String holderName, String password, double balance) {
        this.accountNumber = accountNumber; 
        this.holderName = holderName; 
        this.password = password; 
        this.balance = balance; 
    }

    public String getAccountNumber() {
        return accountNumber; 
    }

    public String getHolderName() {
        return holderName; 
    }

    public String getPassword() {
        return password; 
    }

    public double getBalance() {
        return balance; 
    }

    public void setBalance(double balance) {
        this.balance = balance; 
    }
}