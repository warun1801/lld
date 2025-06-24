
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

public class SlidingWindowAlgo implements RateLimiterAlgo {
    long bucketSize;
    long timeWindowInSecs;
    Queue<Long> timeQueue;

    public SlidingWindowAlgo(long bucketSize, long timeWindowInSecs) {
        this.bucketSize = bucketSize;
        this.timeWindowInSecs = timeWindowInSecs;
        this.timeQueue = new ConcurrentLinkedQueue<>();
    }

    public void checkAndUpdateTimeWindow(Long currentTime) {
        if (timeQueue.isEmpty()) {
            return;
        }

        long timeDiff = (currentTime - timeQueue.peek()) / 1000;
        System.out.println("Time difference: " + timeDiff);
        while (!timeQueue.isEmpty() && timeDiff >= timeWindowInSecs) {
            timeQueue.poll();
            System.out.println("Queue peek time: " + timeQueue.peek());
            if (!timeQueue.isEmpty()) {
                timeDiff = (currentTime - timeQueue.peek()) / 1000;
            }
        }
    }

    @Override
    public synchronized boolean allowRequest() {
        Long currentTime = System.currentTimeMillis();
        checkAndUpdateTimeWindow(currentTime);
        if (timeQueue.size() < bucketSize) {
            timeQueue.offer(currentTime);
            return true;
        }
        return false;
    }

}