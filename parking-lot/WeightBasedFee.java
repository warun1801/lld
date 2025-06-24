
import java.util.Map;

public class WeightBasedFee implements FeeStrategy {
    private static final double DEFAULT_WEIGHT_FEE_PER_KR_PER_HR = 0.01; // Default fee per kg
    private final Map<VehicleType, Double> weightFee;

    public WeightBasedFee(Map<VehicleType, Double> weightFee) {
        this.weightFee = weightFee;
    }

    @Override
    public double calculateFee(Ticket ticket) {
        double weight = ticket.getVehicle().getWeight();
        double hours = ticket.getDuration() / 3600.0; // Convert duration to hours
        return weightFee.getOrDefault(ticket.getVehicle().getVehicleType(), DEFAULT_WEIGHT_FEE_PER_KR_PER_HR) * weight * hours;
    }
}