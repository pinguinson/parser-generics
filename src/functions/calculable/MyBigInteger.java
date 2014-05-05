package functions.calculable;

import java.math.BigInteger;

public class MyBigInteger implements Calculable<MyBigInteger> {
    BigInteger a;
    public MyBigInteger (BigInteger a) {
        this.a = a;
    }
    @Override
    public MyBigInteger add(MyBigInteger b) {
        return new MyBigInteger(a.add(b.a));
    }
    @Override
    public MyBigInteger sub(MyBigInteger b) {
        return new MyBigInteger(a.subtract(b.a));
    }
    @Override
    public MyBigInteger mul(MyBigInteger b) {
        return new MyBigInteger(a.multiply(b.a));
    }
    @Override
    public MyBigInteger div(MyBigInteger b) {
        return new MyBigInteger(a.divide(b.a));
    }
    @Override
    public MyBigInteger mod(MyBigInteger b) {
        return new MyBigInteger(a.mod(b.a));
    }
    @Override
    public MyBigInteger not() {
        return new MyBigInteger(a.not());
    }
    @Override
    public MyBigInteger neg() {
        return new MyBigInteger(a.negate());
    }
    @Override
    public MyBigInteger abs() {
        return new MyBigInteger(a.abs());
    }
    @Override
    public MyBigInteger parse(String s) {
        return new MyBigInteger(new BigInteger(s));
    }
    @Override
    public String value() {
        return a.toString();
    }
}
