import functions.*;
import functions.calculable.*;

public class ExpressionParser {

    private static String current;
    private static Lexer lex;

    private static class Lexer {
        private final String s;
        private int cur;

        Lexer(String s) {
            this.s = s;
            cur = 0;
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

    private static <T extends Calculable<T>> Expression3<T> parseValue(T type) {
        if (current.charAt(0) >= '0' && current.charAt(0) <= '9') {
            return new Const<>(type.parse(current));
        } else if (current.charAt(0) >= 'x' && current.charAt(0) <= 'z') {
            return new Variable<>(current);
        } else if (current.equals("(")) {
            return parseExpr(type);
        } else {
            assert false;
            return new Const<>(type.parse("0"));
        }
    }

    private static <T extends Calculable<T>> Expression3<T> parseMultiplier(T type) {
        current = lex.next();
        if (current.equals("")) {
            assert false;
            return new Const<>(type.parse("0"));
        }
        switch (current) {
            case "-":
                return new Negate<>(parseMultiplier(type));
            case "~":
                return new Not<>(parseMultiplier(type));
            case "#":
                return new Abs<>(parseMultiplier(type));
        }
        return parseValue(type);
    }

    private static <T extends Calculable<T>> Expression3<T> parseSum(T type) {
        Expression3<T> left = parseMultiplier(type);
        while (true) {
            current = lex.next();
            if (!current.equals("*") && !current.equals("/") && !current.equals("%")) {
                return left;
            }
            switch (current.charAt(0)) {
                case '*': {
                    left = new Multiply<>(left, parseMultiplier(type));
                    break;
                }
                case '/': {
                    left = new Divide<>(left, parseMultiplier(type));
                    break;
                }
                case '%': {
                    left = new Mod<>(left, parseMultiplier(type));
                    break;
                }
            }
        }
    }

    private static <T extends Calculable<T>> Expression3<T> parseExpr(T type) {
        Expression3<T> left = parseSum(type);
        while (true) {
            if (current.equals(")") || current.equals("")) {
                return left;
            }
            switch (current.charAt(0)) {
                case '+': {
                    left = new Add<>(left, parseSum(type));
                    break;
                }
                case '-': {
                    left = new Subtract<>(left, parseSum(type));
                    break;
                }
            }
        }
    }

    public static <T extends Calculable<T>> Expression3<T> parse(String s, T type) {
        s = s.replaceAll("\\s", "");
        s = s.replaceAll("mod", "%");
        s = s.replaceAll("abs", "#");
        lex = new Lexer(s);
        return parseExpr(type);
    }
}