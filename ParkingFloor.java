import java.util.List;

public class ParkingFloor {
    private final int floorNo;
    private final List<ParkingSpot> spots;

    public ParkingFloor(int floorNo, List<ParkingSpot> spots) {
        this.floorNo = floorNo;
        this.spots = spots;
    }

    public int getFloorNo() { return floorNo; }
    public List<ParkingSpot> getSpots() { return spots; }
}
