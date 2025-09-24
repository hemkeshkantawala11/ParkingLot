public class EntryGate {
    private final ParkingLot lot;
    private final int x, y, z;

    public EntryGate(ParkingLot lot, int x, int y, int z) {
        this.lot = lot;
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public Ticket generateTicket(Vehicle vehicle) {
        return lot.parkVehicle(vehicle);
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getZ() {
        return z;
    }
}