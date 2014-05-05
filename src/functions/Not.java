package functions;

import functions.calculable.*;

public class Not<T extends Calculable<T>> extends MonadicOperation<T> {

    public Not(Expression3 a) {
        super(a);
    }

    @Override
    public T evaluate(T x, T y, T z) {
        return a.evaluate(x,y,z).not();
    }
}
