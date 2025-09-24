// Main.java
import java.util.*;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        
        List<ParkingSpot> floor1Spots = Arrays.asList(
                new ParkingSpot(1, SpotType.SMALL, false, 1, 2, 0),
                new ParkingSpot(2, SpotType.MEDIUM, false, 2, 3, 0),
                new ParkingSpot(3, SpotType.LARGE, false, 3, 1, 0),
                new ParkingSpot(4, SpotType.ELECTRIC, true, 1, 1, 0)
        );
        ParkingFloor floor1 = new ParkingFloor(0, floor1Spots);

        List<ParkingSpot> floor2Spots = Arrays.asList(
                new ParkingSpot(5, SpotType.SMALL, false, 2, 2, 1),
                new ParkingSpot(6, SpotType.LARGE, false, 4, 2, 1)
        );
        ParkingFloor floor2 = new ParkingFloor(1, floor2Spots);

        
        SpotDistanceComparator comparator = new SpotDistanceComparator(0,0,0);
        BSTSlotStrategy allocationStrategy = new BSTSlotStrategy(comparator);
        PricingStrategy pricingStrategy = new PerHourPricingStrategy(50);

        ParkingLot lot = new ParkingLot.Builder()
                .floors(Arrays.asList(floor1, floor2))
                .allocationStrategy(allocationStrategy)
                .pricingStrategy(pricingStrategy)
                .build();

        
        EntryGate entryGate = new EntryGate(lot, 0, 0, 0);
        ExitGate exitGate = new ExitGate(lot, 0, 0, 0);

        
        Vehicle bike = new Bike("BIKE-123");
        Vehicle car = new Car("CAR-456");
        Vehicle bus = new Bus("BUS-789");
        Vehicle ecar = new ElectricCar("ECAR-101", true);

        
        Ticket t1 = entryGate.generateTicket(bike);
        System.out.println("Bike parked at Spot " + t1.getSpot().getId());

        Ticket t2 = entryGate.generateTicket(car);
        System.out.println("Car parked at Spot " + t2.getSpot().getId());

        Ticket t3 = entryGate.generateTicket(ecar);
        System.out.println("Electric Car parked at Spot " + t3.getSpot().getId());

        Ticket t4 = entryGate.generateTicket(bus);
        System.out.println("Bus parked at Spot " + t4.getSpot().getId());

        
        Thread.sleep(2000);

        
        double price = exitGate.processExit(t1);
        System.out.println("Bike exited. Price = " + price);

        price = exitGate.processExit(t2);
        System.out.println("Car exited. Price = " + price);

        price = exitGate.processExit(t3);
        System.out.println("Electric Car exited. Price = " + price);

        price = exitGate.processExit(t4);
        System.out.println("Bus exited. Price = " + price);
    }
}
