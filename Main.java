import java.util.*;

public class Main {
    public static void main(String[] args) {
        
        List<ParkingSpot> floor1Spots = new ArrayList<>();
        floor1Spots.add(new ParkingSpot(1, SpotType.SMALL, 1, 0, 0, 0));
        floor1Spots.add(new ParkingSpot(2, SpotType.SMALL, 1, 1, 0, 0));
        floor1Spots.add(new ParkingSpot(3, SpotType.MEDIUM, 1, 2, 0, 0));
        floor1Spots.add(new ParkingSpot(4, SpotType.LARGE, 1, 3, 0, 0));

        ParkingFloor floor1 = new ParkingFloor(1, floor1Spots);
        List<ParkingFloor> floors = Arrays.asList(floor1);

        
        EntryGate entryGate = new EntryGate(null, 0, 0, 0);
        ExitGate exitGate = new ExitGate(null, 0, 0, 0);

        
        System.out.println("=== Using LinearScanSlotStrategy (O(N)) ===");
        ParkingLot linearLot = new ParkingLot.Builder()
                .floors(floors)
                .allocationStrategy(new LinearScanSlotStrategy())
                .pricingStrategy(new PerHourPricingStrategy(10.0))
                .build();

        
        setLotInGates(entryGate, exitGate, linearLot);

        runDemo(entryGate, exitGate);

        
        System.out.println("\n=== Using BSTSlotStrategy (O(log N)) ===");
        SpotDistanceComparator comparator = new SpotDistanceComparator(entryGate.getX(), entryGate.getY(), entryGate.getZ());
        BSTSlotStrategy bstStrategy = new BSTSlotStrategy(comparator);

        ParkingLot bstLot = new ParkingLot.Builder()
                .floors(floors)
                .allocationStrategy(bstStrategy)
                .pricingStrategy(new PerHourPricingStrategy(10.0))
                .build();

        setLotInGates(entryGate, exitGate, bstLot);

        runDemo(entryGate, exitGate);
    }

    private static void runDemo(EntryGate entryGate, ExitGate exitGate) {
        Vehicle bike1 = new Bike("BIKE-123");
        Vehicle bike2 = new Bike("BIKE-456");
        Vehicle car1 = new Car("CAR-789");

        
        Ticket t1 = entryGate.generateTicket(bike1);
        System.out.println("Bike1 parked at spot " + t1.getSpot().getId());

        
        Ticket t2 = entryGate.generateTicket(bike2);
        System.out.println("Bike2 parked at spot " + t2.getSpot().getId());

        
        Ticket t3 = entryGate.generateTicket(car1);
        System.out.println("Car parked at spot " + t3.getSpot().getId());

        
        Vehicle bike3 = new Bike("BIKE-999");
        Ticket t4 = entryGate.generateTicket(bike3);
        System.out.println("Bike3 parked at spot " + t4.getSpot().getId() + " (fallback to larger)");

        
        double price = exitGate.processExit(t1);
        System.out.println("Bike1 exited, price = " + price);
    }

    private static void setLotInGates(EntryGate entry, ExitGate exit, ParkingLot lot) {
        try {
            java.lang.reflect.Field lotField = EntryGate.class.getDeclaredField("lot");
            lotField.setAccessible(true);
            lotField.set(entry, lot);

            lotField = ExitGate.class.getDeclaredField("lot");
            lotField.setAccessible(true);
            lotField.set(exit, lot);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
