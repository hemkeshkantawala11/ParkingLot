public class ElectricBike extends Vehicle implements ElectricVehicle {
    private boolean wantsCharging;

    public ElectricBike(String vehicleNo, boolean wantsCharging) {
        super(vehicleNo);
        this.wantsCharging = wantsCharging;
    }

    @Override
    public VehicleType getType() {
        return VehicleType.ELECTRIC_BIKE;
    }

    @Override
    public boolean wantsCharging() {
        return wantsCharging;
    }

    @Override
    public void setWantsCharging(boolean wantsCharging) {
        this.wantsCharging = wantsCharging;
    }
}