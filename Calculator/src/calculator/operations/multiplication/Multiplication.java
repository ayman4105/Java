package calculator.operations.multiplication;
import calculator.interfaces.Operation;

public class Multiplication implements Operation {
    @Override
    public double calc(double a, double b){
        return a * b;
    }
}