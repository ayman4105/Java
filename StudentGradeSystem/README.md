# Student Grade System - Quick README

## Core Idea

This is a simple Java OOP application that reads student data, stores it inside a `Student` object, calculates the average grade, and prints the final letter grade.

The goal of this task is to practice:

- Classes and objects
- Constructor and `this`
- Encapsulation using `private` fields and getters
- Separating data from logic
- Input validation using `Scanner`
- Compile and run Java packages from terminal

---

## Project Structure

```text
StudentGradeSystem/
└── src/
    └── studentgradesystem/
        ├── Main.java
        ├── models/
        │   └── Student.java
        └── services/
            └── GradeService.java
```

---

## App Flow

```text
User Input
   |
   v
Main.java
   |
   | creates
   v
Student object
   |
   | sends grades to
   v
GradeService
   |
   | calculates
   v
Average + Letter Grade
   |
   v
Print Result
```

---

## Main Classes

### 1. `Student`

`Student` represents one student.

It stores:

```text
name
id
grades
```

The fields are `private`, so other classes cannot access them directly.

Example concept:

```text
private name   -> accessed using getName()
private id     -> accessed using getId()
private grades -> accessed using getGrades()
```

This is called **Encapsulation**.

---

### 2. Constructor and `this`

Inside `Student`:

```java
public Student(String name, int id, double[] grades)
```

This constructor runs when we create a new student object.

The idea:

```text
this.name   = name;
this.id     = id;
this.grades = grades;
```

Meaning:

```text
this.name   -> field inside the current object
name        -> parameter coming from the constructor
```

So when we write:

```java
Student student = new Student("Ayman", 20, grades);
```

The object will store:

```text
name   = Ayman
id     = 20
grades = the grades array
```

---

### 3. `GradeService`

`GradeService` contains the calculation logic.

It is responsible for:

```text
calculateAverage()
determineLetterGrade()
```

Why separate it from `Student`?

Because `Student` should only store data, while `GradeService` should handle calculations.

This follows the idea of **Single Responsibility Principle**:

```text
Each class should have one clear job.
```

---

## Grade Logic

### Average

```text
average = sum of grades / number of grades
```

Example:

```text
20 + 80 + 60 = 160
160 / 3 = 53.33
```

### Letter Grade

Example grading system:

```text
90 or more -> A
80 or more -> B
70 or more -> C
50 or more -> D
less than 50 -> F
```

---

## Input Validation

The app validates user input before accepting it.

### Student Name

The name must contain letters and spaces only.

Invalid:

```text
50
Ayman123
```

Valid:

```text
Ayman
Ayman Mohamed
```

### Student ID

The ID must be an integer.

Invalid:

```text
abc
10.5
```

Valid:

```text
20
```

### Number of Grades

The number of grades must be a positive integer.

Invalid:

```text
0
-3
abc
```

Valid:

```text
3
```

### Grade Value

Each grade must be a number between `0` and `100`.

Invalid:

```text
-10
150
abc
```

Valid:

```text
85
53.5
```

---

## Why We Used OOP

Without OOP, all code would be inside `Main.java`, which makes the program messy.

With OOP:

```text
Student.java      -> stores student data
GradeService.java -> handles grade calculations
Main.java         -> handles user input and program flow
```

This makes the code cleaner and easier to extend later.

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
java -cp out studentgradesystem.Main
```

Meaning:

```text
-cp out                  -> use out/ as the classpath
studentgradesystem.Main  -> run the Main class inside this package
```

---

## Example Run

```text
Enter student name: Ayman
Enter student ID: 20
Enter number of grades: 3
Enter grade 1: 20
Enter grade 2: 80
Enter grade 3: 60

===== Student Result =====
Student Name: Ayman
Student ID: 20
Average Grade: 53.33
Letter Grade: D
```

---

## Quick Revision

```text
Class        -> blueprint for objects
Object       -> real instance from a class
Constructor  -> runs when creating an object
this         -> current object
private      -> protects data inside class
getter       -> reads private data safely
Service      -> class that handles logic/calculations
Scanner      -> reads user input
Validation   -> prevents wrong input
```
