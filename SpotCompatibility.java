import java.util.*;

public class SpotCompatibility {
    private static final Map<VehicleType, List<SpotType>> compatibilityMap = new HashMap<>();

    static {
        compatibilityMap.put(VehicleType.BIKE,
                Arrays.asList(SpotType.SMALL, SpotType.MEDIUM, SpotType.LARGE, SpotType.ELECTRIC));
        compatibilityMap.put(VehicleType.CAR,
                Arrays.asList(SpotType.MEDIUM, SpotType.LARGE, SpotType.ELECTRIC));
        compatibilityMap.put(VehicleType.BUS,
                Arrays.asList(SpotType.LARGE));
        compatibilityMap.put(VehicleType.ELECTRIC_BIKE,
                Arrays.asList(SpotType.ELECTRIC, SpotType.SMALL, SpotType.MEDIUM, SpotType.LARGE));
        compatibilityMap.put(VehicleType.ELECTRIC_CAR,
                Arrays.asList(SpotType.ELECTRIC, SpotType.MEDIUM, SpotType.LARGE));
    }

    public static List<SpotType> getAllowedSpots(VehicleType type) {
        return compatibilityMap.getOrDefault(type, Collections.emptyList());
    }
}