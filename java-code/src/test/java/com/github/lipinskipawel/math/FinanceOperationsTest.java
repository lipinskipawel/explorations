package com.github.lipinskipawel.math;

import org.assertj.core.api.WithAssertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static com.github.lipinskipawel.math.FinanceOperations.bigDecimalSubtract;
import static com.github.lipinskipawel.math.FinanceOperations.doubleSubtract;
import static com.github.lipinskipawel.math.FinanceOperations.floatSubtract;
import static com.github.lipinskipawel.math.FinanceOperations.intSubtract;

class FinanceOperationsTest implements WithAssertions {

    @Test
    void float_subtract() {
        var result = floatSubtract(1.03f, 0.42f);

        assertThat(result).isEqualTo(0.61f);
    }

    @Test
    void double_subtract() {
        var result = doubleSubtract(1.03, 0.42);

        assertThat(result).isEqualTo(0.6100000000000001);
    }

    @Test
    void big_decimal_subtract() {
        var result = bigDecimalSubtract(1.03, 0.42);

        assertThat(result).isEqualTo(new BigDecimal("0.61"));
    }

    @Test
    void int_subtract() {
        var result = intSubtract(103, 42);

        assertThat(result).isEqualTo(61);
    }
}
