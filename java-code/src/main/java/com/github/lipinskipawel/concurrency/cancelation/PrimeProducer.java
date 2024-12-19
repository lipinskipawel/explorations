package com.github.lipinskipawel.concurrency.cancelation;

import java.math.BigInteger;
import java.util.concurrent.BlockingQueue;

import static java.math.BigInteger.ONE;
import static java.util.Objects.requireNonNull;

final class PrimeProducer extends Thread {
    private final BlockingQueue<BigInteger> queue;

    PrimeProducer(BlockingQueue<BigInteger> queue) {
        this.queue = requireNonNull(queue);
    }

    @Override
    public void run() {
        try {
            BigInteger prime = ONE;
            queue.put(prime);
            while (!Thread.currentThread().isInterrupted()) {
                System.out.println(prime);
                queue.put(prime = prime.nextProbablePrime());
            }
        } catch (InterruptedException consumed) {
            // Allow thread to exit
        }
    }

    void cancel() {
        interrupt();
    }
}
