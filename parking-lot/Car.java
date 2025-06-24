public class Car extends Vehicle {
    private static final double DEFAULT_WEIGHT = 1000d;

    public Car(String license) {
        super(license, VehicleType.CAR, DEFAULT_WEIGHT);
    }

    public Car(String license, double weight) {
        super(license, VehicleType.CAR, weight);
    }
}