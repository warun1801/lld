public class Bike extends Vehicle {
    private static final double DEFAULT_WEIGHT = 100d;

    public Bike(String license) {
        super(license, VehicleType.BIKE, DEFAULT_WEIGHT);
    }

    public Bike(String license, double weight) {
        super(license, VehicleType.BIKE, weight);
    }
}