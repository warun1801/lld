
import java.util.Map;

public class VehicleBasedFee implements FeeStrategy {
    private final Map<VehicleType, Double> fixedFee;
    private static final double DEFAULT_BIKE_FEE = 10.0;
    private static final double DEFAULT_CAR_FEE = 20.0;
    private static final double DEFAULT_TRUCK_FEE = 30.0;
    private static final double DEFAULT_VEHICLE_FEE = 20.0;

    public VehicleBasedFee(Map<VehicleType, Double> config) {
        this.fixedFee = config;
    }

    public VehicleBasedFee(double carFee, double bikeFee, double truckFee) {
        this.fixedFee = Map.of(
                VehicleType.CAR, carFee,
                VehicleType.BIKE, bikeFee,
                VehicleType.TRUCK, truckFee
        );
    }

    public VehicleBasedFee() {
        this.fixedFee = Map.of(
                VehicleType.CAR, DEFAULT_CAR_FEE,
                VehicleType.BIKE, DEFAULT_BIKE_FEE,
                VehicleType.TRUCK, DEFAULT_TRUCK_FEE
        );
    }

    @Override
    public double calculateFee(Ticket ticket) {
        long duration = ticket.getDuration();
        long hours = duration / 3600 + 1;
        return fixedFee.getOrDefault(ticket.getVehicle().getVehicleType(), DEFAULT_VEHICLE_FEE) * hours;
    }
}
