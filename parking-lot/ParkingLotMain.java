public class ParkingLotMain {
    public static void main(String[] args) throws InterruptedException {
        ParkingLot parkingLot = ParkingLot.getInstance();

        // Create some vehicles
        Vehicle car1 = new Car("CAR123");
        Vehicle bike1 = new Bike("BIKE456");
        Vehicle truck1 = new Truck("TRUCK789");

        // Add parking floors
        ParkingFloor floor1 = new ParkingFloor(1);
        ParkingFloor floor2 = new ParkingFloor(2);
        parkingLot.addParkingFloor(floor1);
        parkingLot.addParkingFloor(floor2);

        for (int i = 0; i < 5; i++) {
            floor1.addParkingSpot(new ParkingSpot(i, VehicleType.CAR));
            floor1.addParkingSpot(new ParkingSpot(i, VehicleType.BIKE));
            floor1.addParkingSpot(new ParkingSpot(i, VehicleType.TRUCK));

            floor2.addParkingSpot(new ParkingSpot(i + 5, VehicleType.CAR));
            floor2.addParkingSpot(new ParkingSpot(i + 5, VehicleType.BIKE));
            floor2.addParkingSpot(new ParkingSpot(i + 5, VehicleType.TRUCK));
        }

        // Park vehicles
        Ticket ticket1 = parkingLot.parkVehicle(car1);
        Ticket ticket2 = parkingLot.parkVehicle(bike1);
        Ticket ticket3 = parkingLot.parkVehicle(truck1);

        // Print parked tickets
        System.out.println("Parked Tickets:");
        System.out.println(ticket1);
        System.out.println(ticket2);
        System.out.println(ticket3);

        // Unpark a vehicle
        Thread.sleep(1000); // Simulate some time passing
        double fee = parkingLot.unparkVehicle(ticket1.getTicketId());
        System.out.println("Unparked Vehicle Fee: " + fee);
    }
}