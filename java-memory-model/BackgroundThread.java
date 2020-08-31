import java.util.concurrent.TimeUnit;

public final class BackgroundThread {

    private static boolean stopRequest = false;
    private static volatile int some = 0;


    public static void main(String[] args) throws InterruptedException {

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
