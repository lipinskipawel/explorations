package com.github.lipinskipawel.concurrency.locks.tryLock;

import com.github.lipinskipawel.concurrency.locks.Account;
import org.assertj.core.api.WithAssertions;
import org.junit.jupiter.api.Test;

import static java.util.concurrent.TimeUnit.SECONDS;

class TransferMoneyTest implements WithAssertions {

    private final TransferMoney tryLock = new TransferMoney();

    @Test
    void transferMoney() throws InterruptedException {
        final var from = new Account();
        final var to = new Account();

        tryLock.transferMoney(from, to, 60, 1, SECONDS);

        assertThat(from.getBalance()).isEqualTo(40);
        assertThat(to.getBalance()).isEqualTo(160);
    }
}
