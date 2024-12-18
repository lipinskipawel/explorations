package com.github.lipinskipawel.concurrency.locks.order;

import com.github.lipinskipawel.concurrency.locks.Account;
import org.assertj.core.api.WithAssertions;
import org.junit.jupiter.api.Test;

class OrderLockTest implements WithAssertions {

    private final TransferMoney orderLock = new TransferMoney();

    @Test
    void transferMoney() {
        final var from = new Account();
        final var to = new Account();

        orderLock.transferMoney(from, to, 60);

        assertThat(from.getBalance()).isEqualTo(40);
        assertThat(to.getBalance()).isEqualTo(160);
    }
}
