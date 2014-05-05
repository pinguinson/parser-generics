package functions;

import functions.calculable.Calculable;

public class Const<T extends Calculable<T>> implements Expression3<T> {
    private T value;

    public Const(T value){
        this.value = value;
    }

    public T evaluate(T x, T y, T z){
        return value;
    }
}
