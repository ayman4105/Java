package studentgradesystem;

import java.util.Scanner;
import studentgradesystem.models.Student;
import studentgradesystem.services.GradeService;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in); // Create scanner to read user input
        GradeService gradeService = new GradeService(); // Create service object for grade calculations

        String name = readValidName(scanner); // Read valid student name
        int id = readValidInt(scanner, "Enter student ID: "); // Read valid student ID
        int numGrades = readValidPositiveInt(scanner, "Enter number of grades: "); // Read valid number of grades

        double[] grades = new double[numGrades]; // Create array to store grades

        for (int i = 0; i < numGrades; i++) { // Loop to read all grades
            grades[i] = readValidGrade(scanner, "Enter grade " + (i + 1) + ": "); // Read valid grade
        }

        Student student = new Student(name, id, grades); // Create student object

        double average = gradeService.calculateAverage(student.getGrades()); // Calculate average grade
        String letterGrade = gradeService.determineLetterGrade(average); // Determine letter grade

        System.out.println("\n===== Student Result =====");
        System.out.println("Student Name: " + student.getName());
        System.out.println("Student ID: " + student.getId());
        System.out.printf("Average Grade: %.2f\n", average);
        System.out.println("Letter Grade: " + letterGrade);

        scanner.close(); // Close scanner
    }

    private static String readValidName(Scanner scanner) {
        System.out.print("Enter student name: "); // Ask user to enter name
        String name = scanner.nextLine(); // Read full line as name

        while (!name.matches("[a-zA-Z ]+")) { // Check if name contains only letters and spaces
            System.out.println("Invalid name. Please enter letters only."); // Print error message
            System.out.print("Enter student name: "); // Ask again
            name = scanner.nextLine(); // Read name again
        }

        return name; // Return valid name
    }

    private static int readValidInt(Scanner scanner, String message) {
        System.out.print(message); // Print input message

        while (!scanner.hasNextInt()) { // Check if input is not integer
            System.out.println("Invalid input. Please enter an integer number."); // Print error message
            scanner.next(); // Remove invalid input from scanner buffer
            System.out.print(message); // Ask again
        }

        return scanner.nextInt(); // Return valid integer
    }

    private static int readValidPositiveInt(Scanner scanner, String message) {
        int number = readValidInt(scanner, message); // Read integer number

        while (number <= 0) { // Check if number is zero or negative
            System.out.println("Invalid input. Number must be greater than zero."); // Print error message
            number = readValidInt(scanner, message); // Read again
        }

        return number; // Return valid positive integer
    }

    private static double readValidGrade(Scanner scanner, String message) {
        System.out.print(message); // Print input message

        while (!scanner.hasNextDouble()) { // Check if input is not double
            System.out.println("Invalid grade. Please enter a number."); // Print error message
            scanner.next(); // Remove invalid input from scanner buffer
            System.out.print(message); // Ask again
        }

        double grade = scanner.nextDouble(); // Read grade

        while (grade < 0 || grade > 100) { // Check grade range
            System.out.println("Invalid grade. Grade must be between 0 and 100."); // Print error message
            System.out.print(message); // Ask again

            while (!scanner.hasNextDouble()) { // Check again if input is not double
                System.out.println("Invalid grade. Please enter a number."); // Print error message
                scanner.next(); // Remove invalid input
                System.out.print(message); // Ask again
            }

            grade = scanner.nextDouble(); // Read grade again
        }

        return grade; // Return valid grade
    }
}