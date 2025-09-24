public class Ticket {
    private final Vehicle vehicle;
    private final ParkingSpot spot;
    private final long entryTime;
    private final boolean usingCharging;

    public Ticket(Vehicle vehicle, ParkingSpot spot, boolean usingCharging) {
        this.vehicle = vehicle;
        this.spot = spot;
        this.entryTime = System.currentTimeMillis();
        this.usingCharging = usingCharging;
    }

    public Vehicle getVehicle() { return vehicle; }
    public ParkingSpot getSpot() { return spot; }
    public long getEntryTime() { return entryTime; }
    public boolean isUsingCharging() { return usingCharging; }
}
