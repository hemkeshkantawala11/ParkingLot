public abstract class Vehicle {
    private final String vehicleNo;

    protected Vehicle(String vehicleNo) {
        this.vehicleNo = vehicleNo;
    }

    public String getVehicleNo() {
        return vehicleNo;
    }

    public abstract VehicleType getType();
}