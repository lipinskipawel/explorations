package com.github.lipinskipawel;

import java.util.concurrent.Callable;
import java.util.function.Consumer;

// https://www.baeldung.com/java-lambda-exceptions
public final class Functionals {

    public static <T> Consumer<T> unchecked(Callable<T> call) {
        try {
            return (Consumer<T>) call.call();
        } catch (Exception e) {
            throw new RuntimeException("Exception: ", e);
        }
    }
}
