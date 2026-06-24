package calculator;
import java.util.Scanner;
import java.util.HashMap;
import calculator.interfaces.Operation;
import calculator.operations.addition.Addition;
import calculator.operations.subtraction.Subtraction;
import calculator.operations.multiplication.Multiplication;
import calculator.operations.division.Division;

public class Main {
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        HashMap<String, Operation> operations = new HashMap<>();
        operations.put("+", new Addition());
        operations.put("-", new Subtraction());
        operations.put("*", new Multiplication());
        operations.put("/", new Division());

        try {
            System.out.println("Enter first number:");
            double num1 = scanner.nextDouble();

            System.out.println("Enter operation (+, -, *, /):");
            String op = scanner.next();

            System.out.println("Enter second number:");
            double num2 = scanner.nextDouble();

            Operation operation = operations.get(op);

            if (operation != null) {
                double result = operation.calc(num1, num2);
                System.out.println("Result: " + result);
            } else {
                System.out.println("Invalid operation.");
            }

        } 
        catch (Exception e) {
            System.out.println("Invalid input. Please enter valid numbers and operations.");
            System.out.println("Error: " + e.getMessage());
        }

        scanner.close();
    }
}



