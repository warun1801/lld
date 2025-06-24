public abstract class Vehicle {
    protected String licensePlate;
    protected VehicleType vehicleType;
    protected double weight;

    public Vehicle(String licensePlate, VehicleType vehicleType) {
        this.licensePlate = licensePlate;
        this.vehicleType = vehicleType;
        this.weight = 0d;
    }

    public Vehicle(String licensePlate, VehicleType vehicleType, double weight) {
        this.licensePlate = licensePlate;
        this.vehicleType = vehicleType;
        this.weight = weight;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public VehicleType getVehicleType() {
        return vehicleType;
    }

    public double getWeight() {
        return weight;
    }

    @Override
    public String toString() {
        return "Vehicle{" +
                "licensePlate='" + licensePlate + '\'' +
                ", vehicleType=" + vehicleType +
                ", weight=" + weight +
                '}';
    }
}