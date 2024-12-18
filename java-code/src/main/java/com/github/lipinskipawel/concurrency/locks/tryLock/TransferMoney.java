package com.github.lipinskipawel.concurrency.locks.tryLock;

import com.github.lipinskipawel.concurrency.locks.Account;

import java.util.concurrent.TimeUnit;

import static java.lang.System.nanoTime;
import static java.util.concurrent.ThreadLocalRandom.current;
import static java.util.concurrent.TimeUnit.NANOSECONDS;

final class TransferMoney {

    /**
     * Try locking account.
     *
     * @param fromAccount
     * @param toAccount
     * @param amount
     * @param timeout
     * @param unit
     * @throws InterruptedException
     */
    void transferMoney(final Account fromAccount,
                       final Account toAccount,
                       final int amount,
                       long timeout,
                       TimeUnit unit) throws InterruptedException {
        long fixedDelay = unit.toNanos(timeout);
        long randMod = current().nextInt(0, 10);
        long stopTime = nanoTime() + unit.toNanos(timeout);

        while (true) {
            if (fromAccount.lock.tryLock()) {
                try {
                    if (toAccount.lock.tryLock()) {
                        try {
                            if (fromAccount.getBalance() < amount) {
                                throw new RuntimeException();
                            } else {
                                fromAccount.debit(amount);
                                toAccount.credit(amount);
                                return;
                            }
                        } finally {
                            toAccount.lock.unlock();
                        }
                    }
                } finally {
                    fromAccount.lock.unlock();
                }
            }
            if (nanoTime() < stopTime) {
                return;
            }
            NANOSECONDS.sleep(fixedDelay + current().nextLong() % randMod);
        }
    }
}
