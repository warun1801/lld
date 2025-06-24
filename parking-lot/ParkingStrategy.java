
import java.util.Optional;

public interface ParkingStrategy {
    Optional<ParkingSpot> findParkingSpot(ParkingFloor floor, VehicleType vehicleType);
}