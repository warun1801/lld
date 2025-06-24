public class TokenBucketAlgo implements RateLimiterAlgo {
    public final long bucketSize;
    public final long refillRate;
    private long currentBucketSize;
    private long lastRefillTimeStamp;

    public TokenBucketAlgo(long bucketSize, long refillRate) {
        this.bucketSize = bucketSize;
        this.refillRate = refillRate;
        this.currentBucketSize = bucketSize;
        this.lastRefillTimeStamp = System.nanoTime();
    }

    private void refill() {
        long now = System.nanoTime();

        double tokensToAdd = (now - lastRefillTimeStamp) * refillRate / 1e9;
        System.out.println("Before refill: " + currentBucketSize + "\n");
        currentBucketSize = (long) Math.min(currentBucketSize + tokensToAdd, this.bucketSize);
        System.out.println("After refill: " + currentBucketSize + "\n");
        lastRefillTimeStamp = now;
    }

    @Override
    public synchronized boolean allowRequest() {
        refill();

        if (currentBucketSize >= 1) {
            currentBucketSize -= 1;
            return true;
        }
        return false;
    }

}