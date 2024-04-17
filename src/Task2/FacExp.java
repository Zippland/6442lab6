public class FacExp extends Exp {

    Exp coefficient;

    FacExp(Exp coefficient) {
        this.coefficient = coefficient;
    }

    @Override
    public String show() {
        return "(" + coefficient.show() + "!)";
    }

    @Override
    public int evaluate() {
        int coefficientValue = coefficient.evaluate();
        if (coefficientValue < 0) {
            throw new ArithmeticException("Cannot calculate factorial of the given number less than zero: " + coefficientValue);
        } else {
            int factorialResult=1;
            for(int i=coefficientValue;i>0;i--){
                factorialResult *= i;
            }
            return factorialResult;
        }
    }
}
