public class ThrottleRule {
    public long bucketSize;
    public long refillRate;

    public ThrottleRule(long bucketSize, long refillRate) {
        this.bucketSize = bucketSize;
        this.refillRate = refillRate;
    }

    public ThrottleRule() {
        // Default constructor
        this.bucketSize = 10; // Default bucket size
        this.refillRate = 10;  // Default refill rate
    }

    public long getBucketSize() {
        return bucketSize;
    }

    public long getRefillRate() {
        return refillRate;
    }
}