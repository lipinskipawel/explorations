package com.github.lipinskipawel.concurrency.locks.order;

import com.github.lipinskipawel.concurrency.locks.Account;
import com.github.lipinskipawel.concurrency.locks.TriConsumer;

import static java.lang.System.identityHashCode;

final class TransferMoney {

    private static final Object TIE_LOCK = new Object();
    private static final TriConsumer<Account, Account, Integer> TRANSFER = (fAccount, tAccount, amount) -> {
        if (fAccount.getBalance() < amount) {
            throw new RuntimeException("");
        } else {
            fAccount.debit(amount);
            tAccount.credit(amount);
        }
    };

    /**
     * In order to avoid deadlock we have to keep synchronizing on objects always in the same order. To achieve this
     * following code uses hash codes as a way to order objects. If objects have the same hash code then the tie_lock
     * must be acquired first.
     *
     * @param fromAccount
     * @param toAccount
     * @param amount
     */
    void transferMoney(final Account fromAccount,
                       final Account toAccount,
                       final int amount) {
        var fromHash = identityHashCode(fromAccount);
        var toHash = identityHashCode(toAccount);

        if (fromHash < toHash) {
            synchronized (fromAccount) {
                synchronized (toAccount) {
                    TRANSFER.accept(fromAccount, toAccount, amount);
                }
            }
        } else if (fromHash > toHash) {
            synchronized (toAccount) {
                synchronized (fromAccount) {
                    TRANSFER.accept(fromAccount, toAccount, amount);
                }
            }
        } else {
            synchronized (TIE_LOCK) {
                synchronized (fromAccount) {
                    synchronized (toAccount) {
                        TRANSFER.accept(fromAccount, toAccount, amount);
                    }
                }
            }
        }
    }
}
