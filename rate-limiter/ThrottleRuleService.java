import java.util.HashMap;
import java.util.Map;

public class ThrottleRuleService {
    Map<String, ThrottleRule> clientThrottleRules;
    private static volatile ThrottleRuleService instance;

    private ThrottleRuleService() {
        clientThrottleRules = new HashMap<>();
    }

    // This is for making the service singleton to avoid rehashing
    public static ThrottleRuleService getInstance() {
        ThrottleRuleService instanceVar = ThrottleRuleService.instance;
        if (instanceVar == null) {
            // Double-checked locking to ensure thread safety
            // and to avoid unnecessary synchronization after the instance is initialized
            synchronized (ThrottleRuleService.class) {
                instanceVar = ThrottleRuleService.instance;
                if (instanceVar == null) {
                    ThrottleRuleService.instance = instanceVar = new ThrottleRuleService();
                }
            }
        }
        return instanceVar;
    }

    public void createRule(String clientId, ThrottleRule throttleRule) {
        clientThrottleRules.put(clientId, throttleRule);
    }

    public ThrottleRule getRule(String clientId) {
        if (!clientThrottleRules.containsKey(clientId)) {
            return null;
        }
        return clientThrottleRules.get(clientId);
    }

}