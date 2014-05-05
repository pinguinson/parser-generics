package functions;
import functions.calculable.*;

public interface Expression3<T extends Calculable<T>> {
    public T evaluate(T x, T y, T z);
}
