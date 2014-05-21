package functions.calculable;

public class MyDouble implements Calculable<MyDouble> {
    Double a;
    public MyDouble (Double a) {
        this.a = a;
    }
    @Override
    public MyDouble add(MyDouble b) {
        return new MyDouble(a + b.a);
    }
    @Override
    public MyDouble sub(MyDouble b) {
        return new MyDouble(a - b.a);
    }
    @Override
    public MyDouble mul(MyDouble b) {
        return new MyDouble(a * b.a);
    }
    @Override
    public MyDouble div(MyDouble b) {
        return new MyDouble(a / b.a);
    }
    @Override
    public MyDouble mod(MyDouble b) {
        return new MyDouble(a % b.a);
    }
    @Override
    public MyDouble not() {
        return new MyDouble(a);
    }
    @Override
    public MyDouble neg() {
        return new MyDouble(-a);
    }
    @Override
    public MyDouble abs() {
        return new MyDouble(Math.abs(a));
    }
    @Override
    public MyDouble parse(String s) {
        return new MyDouble(Double.parseDouble(s));
    }
    @Override
    public MyDouble parse(int val) {
        return new MyDouble((double) val);
    }
    @Override
    public String value() {
        return a.toString();
    }
}
