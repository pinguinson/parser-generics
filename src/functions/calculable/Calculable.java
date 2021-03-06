package functions.calculable;

public interface Calculable<T> {
    public T add(T a);
    public T sub(T a);
    public T mul(T a);
    public T div(T a);
    public T mod(T a);
    public T not();
    public T neg();
    public T abs();
    public T parse(String s);
    public T parse(int val);
    public String value();
}
