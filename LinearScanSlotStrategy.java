import java.util.*;

public class LinearScanSlotStrategy implements SlotAllocationStrategy {

    @Override
    public ParkingSpot findSpot(List<ParkingSpot> spots, Vehicle vehicle) {
        List<SpotType> allowed = SpotCompatibility.getAllowedSpots(vehicle.getType());

        for (SpotType type : allowed) {
            for (ParkingSpot spot : spots) {
                if (spot.isAvailable() && spot.getType() == type) {
                    return spot;
                }
            }
        }
        return null; 
    }
}
