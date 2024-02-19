package com.github.lipinskipawel.memoryModel;

import org.junit.jupiter.api.Test;

import java.util.concurrent.TimeUnit;

final class StopBackgroundThreadTest {

    private static boolean stopRequest = false;
    private static volatile int some = 0;

    @Test
    void shouldStopBackgroundThread() throws InterruptedException {
        final Thread backgroundThread = new Thread(() -> {
            int i = 0;
            while (!stopRequest) {
                i++;
                if (some == 1)
                    System.out.println("Background thread read the value");
            }
            System.out.println("Background thread is done");
        });

        backgroundThread.start();

        TimeUnit.SECONDS.sleep(1);
        stopRequest = true;
        some = 1;
    }
}
