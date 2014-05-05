package functions;

import functions.calculable.*;

public abstract class MonadicOperation<T extends Calculable<T>> implements Expression3<T> {
    public Expression3<T> a;

    public MonadicOperation(Expression3<T> a){
        this.a = a;
    }

    @Override
    public abstract T evaluate(T x, T y, T z);
}
