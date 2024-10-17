package com.github.lipinskipawel.math;

import java.math.BigDecimal;
import java.util.Objects;

final class Cash {
    enum Currency {
        USD,
        PLN,
        JPY
    }

    private final BigDecimal amount;
    private final Currency currency;

    private Cash(BigDecimal amount, Currency currency) {
        this.amount = amount;
        this.currency = currency;
    }

    public static Cash cash(int amount, Currency currency) {
        return new Cash(BigDecimal.valueOf(amount), currency);
    }

    public static Cash cash(String amount, Currency currency) {
        return new Cash(new BigDecimal(amount), currency);
    }

    public Cash multiply(int multiplier) {
        return new Cash(this.amount.multiply(BigDecimal.valueOf(multiplier)), this.currency);
    }

    public Cash exchange(BigDecimal exchangeRate, Currency currency) {
        return new Cash(this.amount.multiply(exchangeRate), currency);
    }

    public Cash exchange(CurrencyPair currencyPair) {
        if (currency == currencyPair.baseCurrency()) {
            final var inverted = currencyPair.inverted();
            return new Cash(this.amount.multiply(inverted.quote()), inverted.baseCurrency());
        }
        if (currency == currencyPair.quotedCurrency()) {
            return new Cash(this.amount.multiply(currencyPair.quote()), currencyPair.baseCurrency());
        }
        throw new RuntimeException("Cash currency [%s] does not match currency pair [%s]".formatted(currency, currencyPair));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cash cash = (Cash) o;
        return Objects.equals(amount, cash.amount) && currency == cash.currency;
    }

    @Override
    public int hashCode() {
        return Objects.hash(amount, currency);
    }

    @Override
    public String toString() {
        return "Cash{" +
            "amount=" + amount +
            ", currency=" + currency +
            '}';
    }
}
