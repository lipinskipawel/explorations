package com.github.lipinskipawel.proxy;

import org.junit.jupiter.api.Test;

final class ProxyTest {

    @Test
    void shouldProxy() {
        final var proxy = InterfaceProxy.proxy(new SurroundingCode.ExpensiveOps());
        proxy.compute(12);
    }
}
