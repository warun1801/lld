import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class ParkingLot {
    private static ParkingLot instance;
    private final Map<String, Ticket> activeTickets = new ConcurrentHashMap<>();
    private final List<ParkingFloor> parkingFloors = new ArrayList<>();
    private FeeStrategy feeStrategy = new VehicleBasedFee(); // Default fee strategy

    private ParkingLot() {}

    public static synchronized ParkingLot getInstance() {
        if (instance == null) {
            instance = new ParkingLot();
        }
        return instance;
    }

    public void addParkingFloor(ParkingFloor floor) {
        parkingFloors.add(floor);
    }

    public void setFeeStrategy(FeeStrategy feeStrategy) {
        this.feeStrategy = feeStrategy;
    }

    public synchronized Ticket parkVehicle(Vehicle vehicle) {
        for (ParkingFloor floor: parkingFloors) {
            Optional<ParkingSpot> availableSpot = floor.findAvailableSpot(vehicle.getVehicleType());
            if (availableSpot.isPresent()) {
                ParkingSpot spot = availableSpot.get();
                if (spot.park(vehicle)) {
                    String ticketId = UUID.randomUUID().toString();
                    Ticket ticket = new Ticket(ticketId, vehicle, spot);
                    activeTickets.put(ticketId, ticket);
                    return ticket;
                }
            }
        }
        throw new IllegalStateException("No available parking spot for vehicle type: " + vehicle.getVehicleType());
    }

    public synchronized double unparkVehicle(String ticketId) {
        Ticket ticket = activeTickets.remove(ticketId);
        if (ticket == null) {
            throw new IllegalArgumentException("Invalid ticket ID: " + ticketId);
        }
        ticket.getParkingSpot().unpark();

        ticket.setExitTime();
        return feeStrategy.calculateFee(ticket);
    }

}