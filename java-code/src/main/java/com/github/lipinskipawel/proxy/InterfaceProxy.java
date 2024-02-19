package com.github.lipinskipawel.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;

public class InterfaceProxy {
    public static SurroundingCode.IExpensiveOps proxy(final SurroundingCode.ExpensiveOps realImplementation) {
        final InvocationHandler invocationHandler = new InvocationHandler() {

            private final Map<List<Object>, Object> cache = new ConcurrentHashMap<>();

            private List<Object> getCacheKey(final Method method, Object... args) {
                final List<Object> list = new ArrayList<>();
                list.add(method.getName());
                list.addAll(Arrays.asList(args));
                return list;
            }

            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                final var key = getCacheKey(method, args);
                System.out.println("Intercepted: " + method.getName());
                return cache.computeIfAbsent(key, rethrowFunction(unused -> method.invoke(realImplementation, args)));
            }
        };

        return (SurroundingCode.IExpensiveOps) Proxy.newProxyInstance(InterfaceProxy.class.getClassLoader(),
                new Class<?>[]{SurroundingCode.IExpensiveOps.class},
                invocationHandler);

    }

    static <T, R, E extends Exception> Function<T, R> rethrowFunction(SurroundingCode.Function_WithExceptions<T, R, E> function) throws E {
        return t -> {
            try {
                return function.apply(t);
            } catch (Exception exception) {
                throw new RuntimeException(exception);
            }
        };
    }
}
