import functions.Expression3;
import functions.calculable.Calculable;
import functions.calculable.MyBigInteger;
import functions.calculable.MyDouble;
import functions.calculable.MyInteger;

import java.math.BigInteger;

public class GenericParser {

    public static void main(String[] args) {
        Calculable ans;
        String type = args[0];
        String expression = args[1];
        switch (type) {
            case "-i": {
                MyInteger i = new MyInteger(1);
                testType(i, expression);
                break;
            }
            case "-bi": {
                MyBigInteger bi = new MyBigInteger(BigInteger.ONE);
                testType(bi, expression);
                break;
            }
            case "-d": {
                MyDouble d = new MyDouble(1.0);
                testType(d, expression);
                break;
            }
        }
    }

    public static <T extends Calculable<T>> void testType(T type, String expression) {
        Expression3<T> ex = ExpressionParser.parse(expression, type);
        for (int x = -100; x <= 100; x++) {
            for (int y = -100; y <= 100; y++) {
                try {
                    System.out.println(ex.evaluate(type.parse(x), type.parse(y), type.parse(0)).value());
                } catch (Exception e) {
                    System.out.println("error");
                }
            }

        }
    }
}
