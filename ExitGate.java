public class ExitGate {
    private final ParkingLot lot;
    private final int x, y, z;

    public ExitGate(ParkingLot lot, int x, int y, int z) {
        this.lot = lot;
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public double processExit(Ticket ticket) {
        return lot.unparkVehicle(ticket);
    }
}