
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ParkingFloor {
    private final int floorNumber;
    private final List<ParkingSpot> parkingSpots;
    private final ParkingStrategy parkingStrategy;

    public ParkingFloor(int floorNumber) {
        this.floorNumber = floorNumber;
        this.parkingSpots = new ArrayList<>();
        this.parkingStrategy = new NearestParkingStrategy(); // Default strategy
    }

    public ParkingFloor(int floorNumber, ParkingStrategy parkingStrategy) {
        this.floorNumber = floorNumber;
        this.parkingSpots = new ArrayList<>();
        this.parkingStrategy = parkingStrategy;
    }

    public int getFloorNumber() {
        return floorNumber;
    }

    public List<ParkingSpot> getParkingSpots() {
        return parkingSpots;
    }

    public void addParkingSpot(ParkingSpot spot) {
        parkingSpots.add(spot);
    }

    public synchronized Optional<ParkingSpot> findAvailableSpot(VehicleType vehicleType) {
        return parkingStrategy.findParkingSpot(this, vehicleType);
    }
}