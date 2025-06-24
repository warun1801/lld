import java.util.HashMap;
import java.util.Map;

public class RateLimiterService {
    Map<String, RateLimiterAlgo> clientRateLimiters;
    ThrottleRuleService throttleRuleService;
    // This is for making the service singleton to avoid rehashing
    private static volatile RateLimiterService instance;

    private RateLimiterService() {
        this.throttleRuleService = ThrottleRuleService.getInstance();
        this.clientRateLimiters = new HashMap<>();
    }

    
    public static RateLimiterService getInstance() {
        RateLimiterService instanceVar = RateLimiterService.instance;
        if (instanceVar == null) {
            // Double-checked locking to ensure thread safety
            // and to avoid unnecessary synchronization after the instance is initialized
            synchronized (RateLimiterService.class) {
                instanceVar = RateLimiterService.instance;
                if (instanceVar == null) {
                    RateLimiterService.instance = instanceVar = new RateLimiterService();
                }
            }
        }
        return instanceVar;
    }

    private void createUserIfAbsent(String userId) {
        if (throttleRuleService.getRule(userId) == null) {
            // Create a default throttle rule if it doesn't exist
            ThrottleRule defaultRule = new ThrottleRule(); // Assuming a default constructor exists
            throttleRuleService.createRule(userId, defaultRule);
        }

        if (!clientRateLimiters.containsKey(userId)) {
            // Create a new rate limiter for the user
            ThrottleRule rule = throttleRuleService.getRule(userId);
            RateLimiterAlgo rateLimiter = new TokenBucketAlgo(rule.getBucketSize(), rule.getRefillRate());
            clientRateLimiters.put(userId, rateLimiter);
        }
    }


    public boolean isRateLimitedRequestAllowedForUser(String userId) {
        createUserIfAbsent(userId);
        return clientRateLimiters.get(userId).allowRequest();
    }



}