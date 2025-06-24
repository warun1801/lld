public class ParkingSpot {
    private final Integer lotNumber;
    private final VehicleType vehicleType;
    private boolean isOccupied;
    private Vehicle parkedVehicle;

    public ParkingSpot(Integer lotNumber, VehicleType vehicleType) {
        this.lotNumber = lotNumber;
        this.vehicleType = vehicleType;
        this.isOccupied = false;
    }

    public Integer getLotNumber() {
        return lotNumber;
    }

    public VehicleType getVehicleType() {
        return vehicleType;
    }

    public boolean isOccupied() {
        return isOccupied;
    }

    public Vehicle getParkedVehicle() {
        return parkedVehicle;
    }

    public boolean park(Vehicle vehicle) {
        if (isOccupied || vehicle.getVehicleType() != vehicleType) {
            return false; // Spot is occupied or vehicle type does not match
        }
        this.parkedVehicle = vehicle;
        this.isOccupied = true;
        return true; // Successfully parked the vehicle
    }

    public boolean unpark() {
        if (!isOccupied) {
            System.out.println("Parking spot is already empty.");
            return false; // Spot is already empty
        }
        this.parkedVehicle = null;
        this.isOccupied = false;
        return true; // Successfully unparked the vehicle
    }
}