package calculator.operations.division;
import calculator.interfaces.Operation;

public class Division implements Operation {
    @Override
    public double calc(double a, double b){
        try{
            if(b == 0){
                throw new ArithmeticException("Division by zero is not allowed.");
            }
            return a/b;
        }
        catch(Exception e){
            System.out.println("Error: " + e.getMessage());
            return 0;
        }
    }
}