package com.github.lipinskipawel.proxy;

final class Main {

    public static void main(String[] args) {
        final var proxy = InterfaceProxy.proxy(new ExpensiveOps());
        proxy.compute(12);
    }


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
