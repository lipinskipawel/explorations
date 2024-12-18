package com.github.lipinskipawel.concurrency.locks;

import java.util.concurrent.locks.ReentrantLock;

public final class Account {

    private int amount;
    public ReentrantLock lock;

    public Account() {
        this.amount = 100;
        this.lock = new ReentrantLock();
    }

    public int getBalance() {
        return amount;
    }

    public void debit(int amount) {
        this.amount -= amount;
    }

    public void credit(int amount) {
        this.amount += amount;
    }
}
