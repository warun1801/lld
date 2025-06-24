public class Ticket {
    private final String ticketId;
    private final Vehicle vehicle;
    private final ParkingSpot parkingSpot;
    private final long entryTime;
    private Long exitTime;

    public Ticket(String ticketId, Vehicle vehicle, ParkingSpot parkingSpot) {
        this.ticketId = ticketId;
        this.vehicle = vehicle;
        this.parkingSpot = parkingSpot;
        this.entryTime = System.currentTimeMillis();
    }

    public String getTicketId() {
        return ticketId;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public ParkingSpot getParkingSpot() {
        return parkingSpot;
    }

    public long getEntryTime() {
        return entryTime;
    }

    public Long getExitTime() {
        return exitTime;
    }

    public void setExitTime() {
        this.exitTime = System.currentTimeMillis();
    }

    public Long getDuration() {
        if (exitTime == null) {
            throw new IllegalStateException("Vehicle with this ticket has not been exited yet");
        }
        System.out.println("Duration for ticket " + ticketId + ": " + (exitTime - entryTime) + " ms");
        return exitTime - entryTime; // Duration in milliseconds
    }

    @Override
    public String toString() {
        return "Ticket{" +
                "ticketId='" + ticketId + '\'' +
                ", vehicle=" + vehicle +
                ", parkingSpot=" + parkingSpot +
                ", entryTime=" + entryTime +
                ", exitTime=" + exitTime +
                '}';
    }
}