package com.github.lipinskipawel.typeSpecific;

import org.assertj.core.api.WithAssertions;
import org.junit.jupiter.api.Test;

final class ExtendedEnumTest implements WithAssertions {

    @Test
    void should() {
        final var service = new PriceService(() -> 2d);

        final var price1 = service.computePrice(Movie.Type.REGULAR, 2);
        final var price2 = service.computePrice(Movie.Type.NEW_RELEASE, 2);
        final var price3 = service.computePrice(Movie.Type.CHILDREN, 2);

        assertThat(price1).isEqualTo(3);
        assertThat(price2).isEqualTo(4);
        assertThat(price3).isEqualTo(5);
    }
}
