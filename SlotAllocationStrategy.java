import java.util.List;

public interface SlotAllocationStrategy {
    ParkingSpot findSpot(List<ParkingSpot> unused, Vehicle vehicle);
}