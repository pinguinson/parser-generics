package functions;

import functions.calculable.*;

public class Subtract <T extends Calculable<T>> extends BinaryOperation<T> {
    public Subtract(Expression3 a, Expression3 b){
        super(a, b);
    }

    @Override
    protected T perform(T arg1, T arg2) {
        return arg1.sub(arg2);
    }
}