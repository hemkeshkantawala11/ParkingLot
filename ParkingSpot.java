public class ParkingSpot {
    private final int id;
    private final SpotType type;
    private final boolean hasChargingPoint;
    private final int x, y, z;  // coordinates
    private Vehicle currentVehicle;

    public ParkingSpot(int id, SpotType type, boolean hasChargingPoint, int x, int y, int z) {
        this.id = id;
        this.type = type;
        this.hasChargingPoint = hasChargingPoint;
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public int getId() { return id; }
    public SpotType getType() { return type; }
    public boolean isAvailable() { return currentVehicle == null; }

    public void park(Vehicle vehicle) {
        if (!isAvailable()) throw new IllegalStateException("Spot already occupied!");
        this.currentVehicle = vehicle;
    }

    public void vacate() { this.currentVehicle = null; }
    public Vehicle getCurrentVehicle() { return currentVehicle; }
    public boolean hasChargingPoint() { return hasChargingPoint; }

    public int getX() { return x; }
    public int getY() { return y; }
    public int getZ() { return z; }
}
