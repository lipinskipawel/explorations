package com.github.lipinskipawel.concurrency.cancelation;

import org.assertj.core.api.WithAssertions;
import org.junit.jupiter.api.Test;

import java.math.BigInteger;
import java.util.concurrent.ArrayBlockingQueue;

class PrimeProducerTest implements WithAssertions {

    @Test
    void cancel_blocked_thread() throws InterruptedException {
        final var bigIntegers = new ArrayBlockingQueue<BigInteger>(4);
        final var primeProducer = new PrimeProducer(bigIntegers);

        primeProducer.start();
        Thread.sleep(100);
        primeProducer.cancel();

        assertThat(bigIntegers.stream().map(BigInteger::intValue).toList()).containsExactly(1, 2, 3, 5);
    }

    @Test
    void interrupt_blocked_thread() throws InterruptedException {
        final var bigIntegers = new ArrayBlockingQueue<BigInteger>(4);
        final var primeProducer = new PrimeProducer(bigIntegers);

        primeProducer.start();
        Thread.sleep(100);
        primeProducer.interrupt();

        assertThat(bigIntegers.stream().map(BigInteger::intValue).toList()).containsExactly(1, 2, 3, 5);
    }
}
