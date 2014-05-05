package functions;

import functions.calculable.*;

public class Variable<T extends Calculable<T>> implements Expression3<T> {
    String s;
    public Variable(String var) {
        this.s = var;
    }

    @Override
    public T evaluate(T x, T y, T z) {
        if (s.equals("x")) {
            return x;
        }
        if (s.equals("y")) {
            return y;
        }
        return z;
    }
}
