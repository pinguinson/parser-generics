import functions.*;
import functions.calculable.*;

import java.math.BigInteger;

public class GenericParser<T> {

    static private String current;
    static private Lexer lex;

    static private class Lexer {
        private final String s;
        private int cur;
        private Calculable ex;

        Lexer(String s, Calculable ex) {
            this.s = s;
            cur = 0;
            this.ex = ex;
        }

        String next() {
            if (cur == s.length()) {
                return "";
            }
            if (Character.isDigit(s.charAt(cur))) {
                int j = cur;
                while (j + 1 < s.length() && (Character.isDigit(s.charAt(j + 1)) || s.charAt(j+1) == '.')) {
                    j++;
                }
                if (j + 1 < s.length() && s.charAt(j+1) == 'E') {
                    j++;
                }
                if (j + 1 < s.length() && s.charAt(j+1) == '-' && s.charAt(j) == 'E') {
                    j++;
                }
                while (j + 1 < s.length() && Character.isDigit(s.charAt(j + 1))) {
                    j++;
                }
                int buff = cur;
                cur = j + 1;
                return s.substring(buff, j + 1);
            }
            cur++;
            return s.substring(cur - 1, cur);
        }
    }

    static private Expression3 parseValue() {
        if (current.charAt(0) >= '0' && current.charAt(0) <= '9') {
            try {
                return new Const(lex.ex.parse(current));
            } catch (NumberFormatException e) {
                return new Const(new MyInteger(Integer.MIN_VALUE));
            }
        } else if (current.charAt(0) >= 'x' && current.charAt(0) <= 'z') {
            return new Variable(current);
        } else if (current.equals("(")) {
            return parseExpr();
        } else {
            assert false;
            return new Const(lex.ex.parse("0"));
        }
    }

    static private Expression3 parseMultiplier() {
        current = lex.next();
        if (current.equals("")) {
            assert false;
            return new Const(lex.ex.parse("0"));
        }
        if (current.equals("-")) {
            return new Negate(parseMultiplier());
        } else if (current.equals("~")) {
            return new Not(parseMultiplier());
        } else if (current.equals("#")) {
            return new Abs(parseMultiplier());
        }
        return parseValue();
    }

    static private Expression3 parseSum() {
        Expression3 left = parseMultiplier();
        while (true) {
            current = lex.next();
            if (!current.equals("*") && !current.equals("/") && !current.equals("%")) {
                return left;
            }
            switch (current.charAt(0)) {
                case '*': {
                    left = new Multiply(left, parseMultiplier());
                    break;
                }
                case '/': {
                    left = new Divide(left, parseMultiplier());
                    break;
                }
                case '%': {
                    left = new Mod(left, parseMultiplier());
                    break;
                }
            }
        }
    }

    static private Expression3 parseExpr() {
        Expression3 left = parseSum();
        while (true) {
            if (current.equals(")") || current.equals("")) {
                return left;
            }
            switch (current.charAt(0)) {
                case '+': {
                    left = new Add(left, parseSum());
                    break;
                }
                case '-': {
                    left = new Subtract(left, parseSum());
                    break;
                }
            }
        }
    }

    static public Expression3 parse(String s, Calculable ex) {
        s = s.replaceAll("\\s", "");
        s = s.replaceAll("mod", "%");
        s = s.replaceAll("abs", "#");
        lex = new Lexer(s, ex);
        return parseExpr();
    }

    public static void main(String[] args) {
        Calculable ans;
        if (args[0].equals("-i")) {
            Expression3 ex;
            MyInteger i = new MyInteger(1);
            ex = parse(args[1], i);
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
        } else if (args[0].equals("-bi")) {
            Expression3 ex;
            MyBigInteger bi = new MyBigInteger(BigInteger.ONE);
            ex = parse(args[1], bi);
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
        } else {
            Expression3 ex;
            MyDouble d = new MyDouble(1.0);
            ex = parse(args[1], d);
            for (double x = -100.0; x <= 100.0; x += 1.0) {
                for (double y = -100.0; y <= 100.0; y += 1.0) {
                    ans = ex.evaluate(new MyDouble(x), new MyDouble(y), new MyDouble(0.0));
                    System.out.println(ans.value());
                }
            }
        }
    }
}