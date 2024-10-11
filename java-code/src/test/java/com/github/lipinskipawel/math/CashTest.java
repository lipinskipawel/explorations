package com.github.lipinskipawel.math;

import org.assertj.core.api.WithAssertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static com.github.lipinskipawel.math.Cash.Currency.PLN;
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

    @Test
    void exchange_correctly() {
        var cash = cash(100, USD);
        var invertedExchangeRate = BigDecimal.valueOf(1).divide(BigDecimal.valueOf(4));

        var assetValue = cash.exchange(invertedExchangeRate, PLN);

        assertThat(assetValue).isEqualTo(cash("25.00", PLN));
    }
}
