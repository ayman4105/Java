package banksystem;

import banksystem.models.BankAccount;
import banksystem.services.BankService;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        BankService bankService = new BankService(); // Create bank service object
        Scanner scanner = new Scanner(System.in); // Create scanner to read user input

        while (true) { // Keep main menu running
            System.out.println("\n===== Welcome to the Bank System =====");
            System.out.println("1. Create Account");
            System.out.println("2. Login");
            System.out.println("3. Exit");

            int choice = readValidInt(scanner, "Choose an option: "); // Read valid menu choice

            switch (choice) {
                case 1:
                    String accountNumber = readDigitsOnly(scanner, "Enter account number: ");
                    String holderName = readLettersOnly(scanner, "Enter holder name: "); // Read holder name
                    String password = readDigitsOnly(scanner, "Enter password: "); // Read password
                    double initialBalance = readPositiveDouble(scanner, "Enter initial balance: "); // Read valid initial balance

                    boolean created = bankService.createAccount(accountNumber, holderName, password, initialBalance); // Create account

                    if (created) {
                        System.out.println("Account created successfully!");
                    } else {
                        System.out.println("Account creation failed. Account number already exists.");
                    }
                    break;

                case 2:
                    String loginAccountNumber = readDigitsOnly(scanner, "Enter account number: "); // Read login account number
                    String loginPassword = readDigitsOnly(scanner, "Enter password: "); // Read login password

                    BankAccount account = bankService.login(loginAccountNumber, loginPassword); // Try login

                    if (account != null) {
                        System.out.println("Login successful!");
                        showAccountMenu(scanner, bankService, account); // Open account menu after login
                    } else {
                        System.out.println("Login failed. Invalid account number or password.");
                    }
                    break;

                case 3:
                    System.out.println("Exiting...");
                    scanner.close(); // Close scanner
                    return; // End program

                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    private static void showAccountMenu(Scanner scanner, BankService bankService, BankAccount account) {
        boolean loggedIn = true; // Control account menu loop

        while (loggedIn) { // Keep account menu running until logout
            System.out.println("\n===== Account Menu =====");
            System.out.println("1. Deposit Money");
            System.out.println("2. Withdraw Money");
            System.out.println("3. Transfer Money");
            System.out.println("4. Check Balance");
            System.out.println("5. View Account Information");
            System.out.println("6. Logout");

            int choice = readValidInt(scanner, "Choose an option: "); // Read valid account menu choice

            switch (choice) {
                case 1:
                    double depositAmount = readAmountGreaterThanZero(scanner, "Enter deposit amount: "); // Read valid deposit amount

                    if (bankService.deposit(account, depositAmount)) {
                        System.out.println("Deposit successful.");
                    } else {
                        System.out.println("Deposit failed. Amount must be greater than zero.");
                    }
                    break;

                case 2:
                    double withdrawAmount = readAmountGreaterThanZero(scanner, "Enter withdraw amount: "); // Read valid withdraw amount

                    if (bankService.withdraw(account, withdrawAmount)) {
                        System.out.println("Withdraw successful.");
                    } else {
                        System.out.println("Withdraw failed. Invalid amount or insufficient balance.");
                    }
                    break;

                case 3:
                    String targetAccountNumber = readDigitsOnly(scanner, "Enter target account number: "); // Read target account number
                    double transferAmount = readAmountGreaterThanZero(scanner, "Enter transfer amount: "); // Read valid transfer amount

                    if (bankService.transfer(account, targetAccountNumber, transferAmount)) {
                        System.out.println("Transfer successful.");
                    } else {
                        System.out.println("Transfer failed. Invalid target account, invalid amount, or insufficient balance.");
                    }
                    break;

                case 4:
                    double balance = bankService.checkBalance(account); // Get current balance
                    System.out.printf("Current Balance: %.2f\n", balance);
                    break;

                case 5:
                    bankService.viewAccountDetails(account); // Print account details
                    break;

                case 6:
                    System.out.println("Logged out successfully.");
                    loggedIn = false; // Exit account menu
                    break;

                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    private static int readValidInt(Scanner scanner, String message) {
        System.out.print(message); // Print message to user

        while (!scanner.hasNextInt()) { // Check if input is not integer
            System.out.println("Invalid input. Please enter a number."); // Print error
            scanner.next(); // Remove invalid input from buffer
            System.out.print(message); // Ask again
        }

        int value = scanner.nextInt(); // Read integer value
        scanner.nextLine(); // Consume newline after nextInt()

        return value; // Return valid integer
    }

    private static String readNonEmptyString(Scanner scanner, String message) {
        System.out.print(message); // Print message to user
        String value = scanner.nextLine(); // Read full line

        while (value.trim().isEmpty()) { // Check if input is empty or spaces only
            System.out.println("Invalid input. Value cannot be empty."); // Print error
            System.out.print(message); // Ask again
            value = scanner.nextLine(); // Read again
        }

        return value; // Return valid string
    }

    private static String readDigitsOnly(Scanner scanner, String message) {
        System.out.print(message); // Print message to user
        String value = scanner.nextLine().trim(); // Read full line and trim whitespace

        while (!value.matches("\\d+")) { // Check if input contains only digits
            System.out.println("Invalid input. Please enter digits only."); // Print error
            System.out.print(message); // Ask again
            value = scanner.nextLine().trim(); // Read again
        }

        return value; // Return valid digit string
    }

    private static String readLettersOnly(Scanner scanner, String message) {
        System.out.print(message); // Print message to user
        String value = scanner.nextLine().trim(); // Read full line and trim whitespace

        while (!value.matches("[a-zA-Z ]+") || value.isEmpty()) { // Check if input contains only letters and spaces
            System.out.println("Invalid input. Please enter letters only."); // Print error
            System.out.print(message); // Ask again
            value = scanner.nextLine().trim(); // Read again
        }

        return value; // Return valid letter string
    }

    private static double readPositiveDouble(Scanner scanner, String message) {
        System.out.print(message); // Print message to user

        while (!scanner.hasNextDouble()) { // Check if input is not double
            System.out.println("Invalid input. Please enter a valid number."); // Print error
            scanner.next(); // Remove invalid input from buffer
            System.out.print(message); // Ask again
        }

        double value = scanner.nextDouble(); // Read double value
        scanner.nextLine(); // Consume newline after nextDouble()

        while (value < 0) { // Check if value is negative
            System.out.println("Invalid input. Value cannot be negative."); // Print error
            System.out.print(message); // Ask again

            while (!scanner.hasNextDouble()) { // Check again if input is not double
                System.out.println("Invalid input. Please enter a valid number."); // Print error
                scanner.next(); // Remove invalid input from buffer
                System.out.print(message); // Ask again
            }

            value = scanner.nextDouble(); // Read double value again
            scanner.nextLine(); // Consume newline after nextDouble()
        }

        return value; // Return valid double
    }

    private static double readAmountGreaterThanZero(Scanner scanner, String message) {
        double amount = readPositiveDouble(scanner, message); // Read positive double or zero

        while (amount == 0) { // Amount cannot be zero
            System.out.println("Invalid input. Amount must be greater than zero."); // Print error
            amount = readPositiveDouble(scanner, message); // Ask again
        }

        return amount; // Return valid amount
    }
}