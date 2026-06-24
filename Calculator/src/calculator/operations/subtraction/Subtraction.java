package calculator.operations.subtraction;
import calculator.interfaces.Operation;

public class Subtraction implements Operation {
    @Override
    public double calc(double a, double b){
        return a - b;
    }
}