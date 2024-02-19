package com.github.lipinskipawel.proxy;

public final class SurroundingCode {

    public interface Function_WithExceptions<T, R, E extends Exception> {
        R apply(T t) throws E;
    }

    public interface IExpensiveOps {
        int compute(final int number);
    }

    public static class ExpensiveOps implements IExpensiveOps {
        @Override
        public int compute(int number) {
            return number;
        }
    }
}
