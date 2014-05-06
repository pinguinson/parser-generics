package functions.calculable;

public class MyInteger implements Calculable<MyInteger> {
    private Integer a;
    public MyInteger (Integer a) {
        this.a = a;
    }
    @Override
    public MyInteger add(MyInteger b) {
        return new MyInteger(a + b.a);
    }
    @Override
    public MyInteger sub(MyInteger b) {
        return new MyInteger(a - b.a);
    }
    @Override
    public MyInteger mul(MyInteger b) {
        return new MyInteger(a * b.a);
    }
    @Override
    public MyInteger div(MyInteger b) {
        return new MyInteger(a / b.a);
    }
    @Override
    public MyInteger mod(MyInteger b) {
        return new MyInteger(a % b.a);
    }
    @Override
    public MyInteger not() {
        return new MyInteger(~a);
    }
    @Override
    public MyInteger neg() {
        return new MyInteger(-a);
    }
    @Override
    public MyInteger abs() {
        return new MyInteger(Math.abs(a));
    }
    @Override
    public MyInteger parse(String s) {
        if (s.equals("2147483648")) {
            return new MyInteger(Integer.MIN_VALUE);
        }
        return new MyInteger(Integer.parseInt(s));
    }
    @Override
    public String value() {
        return a.toString();
    }
}
