package com.github.lipinskipawel.concurrency.locks;

public interface TriConsumer<A, B, C> {

    void accept(A a, B b, C c);
}
