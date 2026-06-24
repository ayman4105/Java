# Bank System V1 - Quick README

## Core Idea

This is a simple **Java OOP Banking System**.

The system allows the user to:

```text
Create a new bank account
Login using account number and password
Deposit money
Withdraw money
Transfer money to another account
Check account balance
View account information
Logout
```

This version is an **in-memory system**.

```text
Accounts are stored in RAM using HashMap.
When the program closes, the accounts are lost.
No file storage is used in this version.
```

---

## Project Structure

```text
BankSystem/
└── src/
    └── banksystem/
        ├── Main.java
        ├── models/
        │   └── BankAccount.java
        ├── services/
        │   └── BankService.java
        └── storage/
            └── FileManager.java
```

`FileManager.java` exists, but we are not using it in V1.

---

## App Flow

```text
User
 |
 v
Main Menu
 |
 | create account / login / exit
 v
BankService
 |
 | manages accounts
 v
HashMap<String, BankAccount>
 |
 | account number -> account object
 v
BankAccount
```

After login:

```text
Login success
 |
 v
Account Menu
 |
 | deposit
 | withdraw
 | transfer
 | check balance
 | view info
 | logout
```

---

## Main Classes

## 1. BankAccount

`BankAccount` is the model class.

It represents one bank account.

It stores:

```text
accountNumber
holderName
password
balance
```

The fields are `private`, so other classes cannot access them directly.

This is called **Encapsulation**.

```text
private balance
```

Other classes should not change the balance directly.

They should use controlled methods like:

```text
getBalance()
setBalance()
```

---

## 2. Constructor and this

The constructor receives account data and stores it inside the object.

```text
this.accountNumber = accountNumber
this.holderName    = holderName
this.password      = password
this.balance       = balance
```

Meaning:

```text
this.accountNumber -> field inside the current object
accountNumber      -> parameter from the constructor
```

`this` always refers to the current object.

---

## 3. BankService

`BankService` contains the banking logic.

It is responsible for:

```text
createAccount()
login()
deposit()
withdraw()
transfer()
checkBalance()
viewAccountDetails()
```

Why use a service class?

```text
BankAccount stores data.
BankService handles logic.
Main handles user input and menus.
```

This keeps the code clean.

---

## HashMap Usage

The system stores accounts using:

```text
HashMap<String, BankAccount>
```

Meaning:

```text
Key   = account number
Value = BankAccount object
```

Example:

```text
"1" -> Account object for Ayman
"2" -> Account object for Ali
```

Why HashMap?

Because it makes account search easy and fast.

```text
accounts.get("1")
```

This returns the account object with account number `1`.

---

## Main Menu

```text
1. Create Account
2. Login
3. Exit
```

### Create Account

The app asks for:

```text
account number
holder name
password
initial balance
```

Then it calls:

```text
bankService.createAccount(...)
```

If the account number already exists, creation fails.

### Login

The app asks for:

```text
account number
password
```

Then it calls:

```text
bankService.login(...)
```

If login succeeds, it opens the account menu.

---

## Account Menu

```text
1. Deposit Money
2. Withdraw Money
3. Transfer Money
4. Check Balance
5. View Account Information
6. Logout
```

---

## Operation Logic

### Deposit

```text
if amount <= 0
    fail

balance = balance + amount
success
```

### Withdraw

```text
if amount <= 0
    fail

if amount > balance
    fail

balance = balance - amount
success
```

### Transfer

```text
find target account by account number

if target account does not exist
    fail

withdraw money from current account

if withdraw fails
    fail

deposit money into target account
success
```

Important idea:

```text
transfer() reuses withdraw() and deposit()
```

So we do not repeat the same logic many times.

---

## Input Validation

### Menu Choice

Must be an integer.

```text
Invalid: abc
Valid: 1
```

### Account Number

Must be digits only.

```text
Invalid: aa, 12ab
Valid: 1, 1002, 00123
```

We store account number as `String`, not `int`, because account numbers may start with zero.

### Holder Name

Must be letters and spaces only.

```text
Invalid: 11, Ayman123
Valid: Ayman, Ayman Mohamed
```

### Password

Must not be empty.

It can contain letters, numbers, or symbols.

### Balance and Amounts

Initial balance can be zero or positive.

Deposit, withdraw, and transfer amounts must be greater than zero.

```text
Invalid: -10, 0, abc
Valid: 50, 100.5
```

---

## Why We Used OOP

Without OOP, all code would be inside `Main.java`, and the project would become messy.

With OOP:

```text
BankAccount.java -> stores account data
BankService.java -> handles banking logic
Main.java        -> handles user input and menus
```

This makes the project easier to read, debug, and extend later.

---

## Compile

From the project root:

```bash
javac -d out $(find src -name "*.java")
```

Meaning:

```text
javac              -> Java compiler
-d out             -> put compiled .class files inside out/
find src -name ... -> compile all Java files inside src/
```

---

## Run

```bash
java -cp out banksystem.Main
```

Meaning:

```text
java             -> run Java program
-cp out          -> use out/ as the classpath
banksystem.Main  -> run Main class inside banksystem package
```

---

## Example Test

```text
1. Create account 1 with balance 100
2. Create account 2 with balance 50
3. Login to account 1
4. Deposit 50
5. Check balance -> 150
6. Withdraw 20
7. Check balance -> 130
8. Transfer 30 to account 2
9. Check balance account 1 -> 100
10. Logout
11. Login to account 2
12. Check balance -> 80
```

---

## Quick Revision

```text
Class          -> blueprint for objects
Object         -> real instance from a class
Constructor    -> runs when creating an object
this           -> current object
private        -> protects data inside class
getter         -> reads private data safely
setter         -> updates private data safely
Service class  -> handles logic
HashMap        -> stores key/value pairs
Scanner        -> reads user input
Validation     -> prevents wrong input
```

---

## Current Version

```text
Bank System V1
Type: In-Memory
Storage: HashMap only
File saving: Not used yet
Threading: Not used yet
```
