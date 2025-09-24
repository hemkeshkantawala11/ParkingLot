import java.util.*;

public class BSTSlotStrategy implements SlotAllocationStrategy {
    private final Map<SpotType, TreeSet<ParkingSpot>> spotTrees = new HashMap<>();

    public BSTSlotStrategy(SpotDistanceComparator comparator) {
        for (SpotType type : SpotType.values()) {
            spotTrees.put(type, new TreeSet<>(comparator));
        }
    }

    public void addSpot(ParkingSpot spot) {
        if (spot.isAvailable()) {
            spotTrees.get(spot.getType()).add(spot);
        }
    }

    public void removeSpot(ParkingSpot spot) {
        spotTrees.get(spot.getType()).remove(spot);
    }

    @Override
    public ParkingSpot findSpot(List<ParkingSpot> unused, Vehicle vehicle) {
        List<SpotType> allowed = SpotCompatibility.getAllowedSpots(vehicle.getType());

        for (SpotType type : allowed) {
            TreeSet<ParkingSpot> tree = spotTrees.get(type);
            if (tree != null && !tree.isEmpty()) {
                return tree.first();
            }
        }
        return null;
    }
}