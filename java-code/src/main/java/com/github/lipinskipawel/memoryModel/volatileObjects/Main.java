package com.github.lipinskipawel.memoryModel.volatileObjects;

final class Main {

    private static volatile FlagObject flag = new FlagObject(false);

    public static void main(String[] args) throws InterruptedException {

        final Thread background = new Thread(() -> {
            System.out.println("Background thread has been started.");
            while (flag.isNotDone()) {
                someImportantWork();
            }
            System.out.println("Background thread has finishes job.");
        });

        background.start();
        Thread.sleep(1000);
        flag = new FlagObject(true);
    }

    private static void someImportantWork() {
        int some = 4;
        // System.out.println("Working");
    }
}
