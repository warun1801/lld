
import java.util.concurrent.*;

public class Main {
    public static void main(String[] args) {
        UserService userService = new UserService();

        ExecutorService executor = Executors.newFixedThreadPool(1);
        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(5);

        Long startTime = System.currentTimeMillis();

        Runnable r = () -> {
            System.out.println("Client 1: " + Thread.currentThread().getName() + "-- " + userService.serveRequest("client1"));
        };
        scheduledExecutorService.scheduleAtFixedRate(r, 0, 50, TimeUnit.MILLISECONDS);
        executor.shutdown();

        try {
            executor.awaitTermination(Long.MAX_VALUE, TimeUnit.MILLISECONDS);
            Long endTime = System.currentTimeMillis();
            System.out.println("total time " + (endTime - startTime));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}