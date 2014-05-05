package functions;

import functions.calculable.*;

public abstract class BinaryOperation<T extends Calculable<T>> implements Expression3<T> {
    private Expression3<T> a, b;

    public BinaryOperation(Expression3<T> a, Expression3<T> b) {
        this.a = a;
        this.b = b;
    }

    @Override
    public T evaluate(T x, T y, T z) {
        return perform(a.evaluate(x, y, z), b.evaluate(x, y, z));
    }

    protected abstract T perform(T arg1, T arg2);
}