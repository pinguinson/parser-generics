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
                Expression3<MyInteger> ex;
                MyInteger i = new MyInteger(1);
                ex = ExpressionParser.parse(expression, i);
                for (int x = -100; x <= 100; x++) {
                    for (int y = -100; y <= 100; y++) {
                        try {
                            ans = ex.evaluate(new MyInteger(x), new MyInteger(y), new MyInteger(0));
                            System.out.println(ans.value());
                        } catch (Exception e) {
                            System.out.println("error");
                        }
                    }
                }
                break;
            }
            case "-bi": {
                Expression3<MyBigInteger> ex;
                MyBigInteger bi = new MyBigInteger(BigInteger.ONE);
                ex = ExpressionParser.parse(expression, bi);
                for (long x = -100; x <= 100; x++) {
                    for (long y = -100; y <= 100; y++) {
                        try {
                            ans = ex.evaluate(new MyBigInteger(BigInteger.valueOf(x)), new MyBigInteger(BigInteger.valueOf(y)), new MyBigInteger(BigInteger.ZERO));
                            System.out.println(ans.value());
                        } catch (Exception e) {
                            System.out.println("error");
                        }
                    }
                }
                break;
            }
            case "-d": {
                Expression3<MyDouble> ex;
                MyDouble d = new MyDouble(1.0);
                ex = ExpressionParser.parse(expression, d);
                for (double x = -100.0; x <= 100.0; x += 1.0) {
                    for (double y = -100.0; y <= 100.0; y += 1.0) {
                        ans = ex.evaluate(new MyDouble(x), new MyDouble(y), new MyDouble(0.0));
                        System.out.println(ans.value());
                    }
                }
                break;
            }
        }
    }
}
