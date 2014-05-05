package functions;

import functions.calculable.*;

public class Negate<T extends Calculable<T>> extends MonadicOperation<T> {

    public Negate(Expression3 a) {
        super(a);
    }

    @Override
    public T evaluate(T x, T y, T z) {
        return a.evaluate(x,y,z).neg();
    }
}
