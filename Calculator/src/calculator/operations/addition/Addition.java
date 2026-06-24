package calculator.operations.addition;
import calculator.interfaces.Operation;

public class Addition implements Operation {
    @Override
    public double calc(double a, double b){
        return a + b;
    }
}