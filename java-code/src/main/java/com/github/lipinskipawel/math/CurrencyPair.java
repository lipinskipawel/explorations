package com.github.lipinskipawel.math;

import java.math.BigDecimal;

import static com.github.lipinskipawel.math.Cash.Currency;
import static java.util.Objects.requireNonNull;

final class CurrencyPair {
    private final Currency baseCurrency;
    private final BigDecimal quote;
    private final Currency quotedCurrency;

    private CurrencyPair(Currency baseCurrency, BigDecimal quote, Currency quoteCurrency) {
        this.baseCurrency = requireNonNull(baseCurrency);
        this.quote = requireNonNull(quote);
        this.quotedCurrency = requireNonNull(quoteCurrency);
    }

    static CurrencyPair currencyPair(Currency baseCurrency, BigDecimal quote, Currency quoteCurrency) {
        return new CurrencyPair(baseCurrency, quote, quoteCurrency);
    }

    CurrencyPair inverted() {
        return new CurrencyPair(quotedCurrency, BigDecimal.ONE.divide(quote), baseCurrency);
    }

    Currency baseCurrency() {
        return baseCurrency;
    }

    BigDecimal quote() {
        return quote;
    }

    Currency quotedCurrency() {
        return quotedCurrency;
    }

    @Override
    public String toString() {
        return "CurrencyPair{" +
            "baseCurrency=" + baseCurrency +
            ", quote=" + quote +
            ", quotedCurrency=" + quotedCurrency +
            '}';
    }
}
