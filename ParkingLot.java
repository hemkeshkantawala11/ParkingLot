import java.util.*;

public class ParkingLot {
    private final List<ParkingFloor> floors;
    private final BSTSlotStrategy allocationStrategy;
    private final PricingStrategy pricingStrategy;

    private ParkingLot(List<ParkingFloor> floors,
                      BSTSlotStrategy allocationStrategy,
                      PricingStrategy pricingStrategy) {
        this.floors = floors;
        this.allocationStrategy = allocationStrategy;
        this.pricingStrategy = pricingStrategy;

        for (ParkingFloor floor : floors) {
            for (ParkingSpot spot : floor.getSpots()) {
                allocationStrategy.addSpot(spot);
            }
        }
    }

    public Ticket parkVehicle(Vehicle vehicle) {
        ParkingSpot spot = allocationStrategy.findSpot(null, vehicle);
        if (spot == null) throw new IllegalStateException("No spot available!");
        spot.park(vehicle);
        allocationStrategy.removeSpot(spot);

        boolean usingCharging = (vehicle instanceof ElectricVehicle) &&
                                ((ElectricVehicle) vehicle).wantsCharging() &&
                                spot.hasChargingPoint();

        return new Ticket(vehicle, spot, usingCharging);
    }

    public double unparkVehicle(Ticket ticket) {
        ParkingSpot spot = ticket.getSpot();
        spot.vacate();
        allocationStrategy.addSpot(spot);
        return pricingStrategy.calculatePrice(ticket);
    }

    public static class Builder {
        private List<ParkingFloor> floors;
        private BSTSlotStrategy allocationStrategy;
        private PricingStrategy pricingStrategy;

        public Builder floors(List<ParkingFloor> floors) {
            this.floors = floors;
            return this;
        }

        public Builder allocationStrategy(BSTSlotStrategy strategy) {
            this.allocationStrategy = strategy;
            return this;
        }

        public Builder pricingStrategy(PricingStrategy strategy) {
            this.pricingStrategy = strategy;
            return this;
        }

        public ParkingLot build() {
            return new ParkingLot(floors, allocationStrategy, pricingStrategy);
        }
    }
}
