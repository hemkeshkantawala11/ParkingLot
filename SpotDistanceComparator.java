import java.util.Comparator;

public class SpotDistanceComparator implements Comparator<ParkingSpot> {
    private final int gx, gy, gz; // gate coordinates

    public SpotDistanceComparator(int gx, int gy, int gz) {
        this.gx = gx;
        this.gy = gy;
        this.gz = gz;
    }

    @Override
    public int compare(ParkingSpot s1, ParkingSpot s2) {
        double d1 = distance(s1);
        double d2 = distance(s2);

        if (d1 < d2) return -1;
        else if (d1 > d2) return 1;
        else {
            if (s1.getZ() != s2.getZ()) return Integer.compare(s1.getZ(), s2.getZ());
            return Integer.compare(s1.getId(), s2.getId());
        }
    }

    private double distance(ParkingSpot spot) {
        return Math.sqrt(
            Math.pow(spot.getX() - gx, 2) +
            Math.pow(spot.getY() - gy, 2) +
            Math.pow(spot.getZ() - gz, 2)
        );
    }
}