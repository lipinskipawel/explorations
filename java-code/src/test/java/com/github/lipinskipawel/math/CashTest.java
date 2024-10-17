package com.github.lipinskipawel.math;

import org.assertj.core.api.WithAssertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static com.github.lipinskipawel.math.Cash.Currency.JPY;
import static com.github.lipinskipawel.math.Cash.Currency.PLN;
import static com.github.lipinskipawel.math.Cash.Currency.USD;
import static com.github.lipinskipawel.math.Cash.cash;
import static com.github.lipinskipawel.math.CurrencyPair.currencyPair;

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

    @Test
    void exchange_using_baseCurrency_from_currency_pair() {
        var cash = cash(100, USD);
        var plnUsd = currencyPair(PLN, BigDecimal.valueOf(25, 2), USD);

        var assetValue = cash.exchange(plnUsd);

        assertThat(assetValue).isEqualTo(cash("25.00", PLN));
    }

    @Test
    void exchange_using_quotedCurrency_from_currency_pair() {
        var cash = cash(100, USD);
        var usdPln = currencyPair(USD, BigDecimal.valueOf(4), PLN);

        var assetValue = cash.exchange(usdPln);

        assertThat(assetValue).isEqualTo(cash("25.00", PLN));
    }

    @Test
    void throws_when_currency_pair_does_not_match_cash() {
        var cash = cash(100, JPY);
        var usdPln = currencyPair(USD, BigDecimal.valueOf(4), PLN);

        var runtimeException = catchRuntimeException(() -> cash.exchange(usdPln));

        assertThat(runtimeException)
            .hasMessage("Cash currency [JPY] does not match currency pair [CurrencyPair{baseCurrency=USD, quote=4, quotedCurrency=PLN}]");
    }
}
