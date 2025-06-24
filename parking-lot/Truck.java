public class Truck extends Vehicle {
    private static final double DEFAULT_WEIGHT = 5000d;

    public Truck(String license) {
        super(license, VehicleType.TRUCK, DEFAULT_WEIGHT);
    }

    public Truck(String license, double weight) {
        super(license, VehicleType.TRUCK, weight);
    }
}