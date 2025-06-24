
import java.util.Optional;

public class NearestParkingStrategy implements ParkingStrategy {

    @Override
    public Optional<ParkingSpot> findParkingSpot(ParkingFloor floor, VehicleType vehicleType) {
        return floor.getParkingSpots().stream()
                .filter(spot -> spot.getVehicleType() == vehicleType && !spot.isOccupied())
                .findFirst();
    }
}