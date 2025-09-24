import java.util.*;

public class ParkingLot {
    private final List<ParkingFloor> floors;
    private final List<ParkingSpot> allSpots = new ArrayList<>();
    private final SlotAllocationStrategy allocationStrategy;
    private final PricingStrategy pricingStrategy;

    private ParkingLot(List<ParkingFloor> floors,
                      SlotAllocationStrategy allocationStrategy,
                      PricingStrategy pricingStrategy) {
        this.floors = floors;
        this.allocationStrategy = allocationStrategy;
        this.pricingStrategy = pricingStrategy;

        for (ParkingFloor floor : floors) {
            allSpots.addAll(floor.getSpots());
            if (allocationStrategy instanceof BSTSlotStrategy) {
                BSTSlotStrategy bstStrategy = (BSTSlotStrategy) allocationStrategy;
                for (ParkingSpot spot : floor.getSpots()) {
                    bstStrategy.addSpot(spot);
                }
            }
        }
    }

    public Ticket parkVehicle(Vehicle vehicle) {
        ParkingSpot spot = allocationStrategy.findSpot(allSpots, vehicle);
        if (spot == null) throw new IllegalStateException("No spot available!");
        spot.park(vehicle);

        if (allocationStrategy instanceof BSTSlotStrategy) {
            BSTSlotStrategy bstStrategy = (BSTSlotStrategy) allocationStrategy;
            bstStrategy.removeSpot(spot);
        }

        boolean usingCharging = (vehicle instanceof ElectricVehicle) &&
                                ((ElectricVehicle) vehicle).wantsCharging() &&
                                spot.hasChargingPoint();

        return new Ticket(vehicle, spot, usingCharging);
    }

    public double unparkVehicle(Ticket ticket) {
        ParkingSpot spot = ticket.getSpot();
        spot.vacate();

        if (allocationStrategy instanceof BSTSlotStrategy) {
            BSTSlotStrategy bstStrategy = (BSTSlotStrategy) allocationStrategy;
            bstStrategy.addSpot(spot);
        }

        return pricingStrategy.calculatePrice(ticket);
    }

    public static class Builder {
        private List<ParkingFloor> floors;
        private SlotAllocationStrategy allocationStrategy;
        private PricingStrategy pricingStrategy;

        public Builder floors(List<ParkingFloor> floors) {
            this.floors = floors;
            return this;
        }

        public Builder allocationStrategy(SlotAllocationStrategy strategy) {
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
