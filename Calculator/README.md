# Java OOP Calculator

## Core Idea

This project is a simple calculator written in Java using **OOP concepts**.

Instead of writing all operations inside `Main.java` using `switch` or many `if` statements, each operation has its own class.

```text
Operation interface
        |
        | implements
        v
Addition / Subtraction / Multiplication / Division
        |
        v
Main uses them through HashMap
```

---

## Project Structure

```text
Calculator/
└── src/
    └── calculator/
        ├── Main.java
        ├── interfaces/
        │   └── Operation.java
        └── operations/
            ├── addition/
            │   └── Addition.java
            ├── subtraction/
            │   └── Subtraction.java
            ├── multiplication/
            │   └── Multiplication.java
            └── division/
                └── Division.java
```

---

## OOP Concepts Used

### 1. Interface

We created an interface called `Operation`.

It contains one function:

```java
public interface Operation {
    double calc(double a, double b);
}
```

The interface does not calculate anything by itself.
It only says:

```text
Any operation class must have a calc(a, b) function.
```

---

### 2. Implements

Each operation class implements the `Operation` interface.

Example idea:

```text
Addition implements Operation
Subtraction implements Operation
Multiplication implements Operation
Division implements Operation
```

This means every operation class must write its own version of:

```java
double calc(double a, double b)
```

---

### 3. Polymorphism

In `Main.java`, we do not care if the object is `Addition`, `Subtraction`, or `Division`.

We deal with all of them as one type:

```java
Operation operation;
```

This is called **Polymorphism**.

Simple meaning:

```text
Different classes, same interface, same function name, different behavior.
```

Example:

```text
Addition.calc(a, b)       -> a + b
Subtraction.calc(a, b)    -> a - b
Multiplication.calc(a, b) -> a * b
Division.calc(a, b)       -> a / b
```

---

## Why We Used OOP Here

Without OOP, `Main.java` would contain all logic using `switch` or many `if` statements.

With OOP:

```text
Main.java only controls the flow.
Each operation class handles its own logic.
```

Benefits:

```text
- Cleaner code
- Easier to add new operations
- Each class has one clear responsibility
- Main does not need to know how each operation works internally
```

Example: if we want to add Power operation later:

```text
1. Create Power.java
2. Make it implements Operation
3. Add it to the HashMap
```

No need to rewrite the whole program.

---

## HashMap Idea

We used `HashMap` instead of `switch`.

The map connects each operator symbol with its operation object.

```text
Key      Value
-------------------------
"+"      Addition object
"-"      Subtraction object
"*"      Multiplication object
"/"      Division object
```

In Java:

```java
HashMap<String, Operation> operations = new HashMap<>();
```

Meaning:

```text
Key type   = String
Value type = Operation
```

So when the user enters `+`, we get the matching object:

```java
Operation operation = operations.get(op);
```

Then we call:

```java
double result = operation.calc(num1, num2);
```

Main does not need to know which class is running.
It only knows that the object has `calc()` because it implements `Operation`.

---

## Division by Zero Note

For `double` numbers in Java:

```java
10.0 / 0.0
```

does not throw an exception.
It returns:

```text
Infinity
```

So division class should manually check:

```text
if b == 0
    print error
else
    return a / b
```

---

## Compile and Run

From the project root folder:

```bash
javac -d out $(find src -name "*.java")
```

This compiles all `.java` files and puts the compiled `.class` files inside `out/`.

Run the program:

```bash
java -cp out calculator.Main
```

---

## Expected Flow

```text
Enter first number:
10
Enter operation (+, -, *, /):
+
Enter second number:
5
Result: 15.0
```

---

## Quick Reminder

```text
interface  -> defines required function
implements -> class promises to write that function
@Override  -> confirms we are implementing interface function
HashMap    -> replaces switch by mapping operator to object
calc()     -> common function used by all operations
```
