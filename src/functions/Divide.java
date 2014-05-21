package functions;

import functions.calculable.*;

public class Divide <T extends Calculable<T>> extends BinaryOperation<T> {
    public Divide(Expression3<T> a, Expression3<T> b){
        super(a, b);
    }

    @Override
    protected T perform(T arg1, T arg2) {
        return arg1.div(arg2);
    }
}