public class UserService {
    RateLimiterService rateLimiterService;

    public UserService() {
        // Constructor logic if needed
        this.rateLimiterService = RateLimiterService.getInstance();
    }

    public String serveRequest(String clientId) {
        if (this.rateLimiterService.isRateLimitedRequestAllowedForUser(clientId)) {
            return "Request Accepted";
        } else {
            return "Rate Limit Reached. Please Try again later";
        }
    }
}