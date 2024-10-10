package com.github.lipinskipawel.math;

import java.math.BigDecimal;

final class FinanceOperations {

    public static float floatSubtract(double lhs, double rhs) {
        return (float) (lhs - rhs);
    }

    public static double doubleSubtract(double lhs, double rhs) {
        return lhs - rhs;
    }

    public static BigDecimal bigDecimalSubtract(double lhs, double rhs) {
        return new BigDecimal(String.valueOf(lhs)).subtract(new BigDecimal(String.valueOf(rhs)));
    }

    public static int intSubtract(double lhs, double rhs) {
        return (int) lhs - (int) rhs;
    }
}
