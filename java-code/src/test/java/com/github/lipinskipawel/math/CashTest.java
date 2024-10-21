package com.github.lipinskipawel.math;

import org.assertj.core.api.WithAssertions;
import org.junit.jupiter.api.Test;

import static com.github.lipinskipawel.math.Cash.Currency.USD;
import static com.github.lipinskipawel.math.Cash.cash;

final class CashTest implements WithAssertions {

    @Test
    void multiply_correctly() {
        var volume = 300;
        var pricePerShare = cash(100, USD);

        var assetValue = pricePerShare.multiply(volume);

        assertThat(assetValue).isEqualTo(cash(30_000, USD));
    }
}
