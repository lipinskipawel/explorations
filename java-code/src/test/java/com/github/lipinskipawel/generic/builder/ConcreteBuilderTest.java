package com.github.lipinskipawel.generic.builder;

import org.junit.jupiter.api.Test;

final class ConcreteBuilderTest {

    @Test
    void shouldTestBuilder() {
        final var concrete = new ConcreteBuilder.Builder()
                .integerValue(12)
                .abstractText("working as expected") // method from AbstractBuilder can be accessed after
                // methods from concrete class
                .build();

        concrete.printConcrete();
    }
}
