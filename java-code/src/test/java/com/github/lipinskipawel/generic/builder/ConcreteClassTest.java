package com.github.lipinskipawel.generic.builder;

import org.junit.jupiter.api.Test;

import static com.github.lipinskipawel.generic.abstractExample.ConcreteClass.Builder.conreteBuilder;
import static java.util.UUID.randomUUID;

final class ConcreteClassTest {

    @Test
    void shouldBuild() {
        final var concreteClass = conreteBuilder()
            .precision(12.2)
            .abstractText("hello world")
            .uuid(randomUUID())
            .build();

        concreteClass.printConcreteClass();
    }
}
