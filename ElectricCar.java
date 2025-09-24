public class ElectricCar extends Vehicle implements ElectricVehicle {
    private boolean wantsCharging;

    public ElectricCar(String vehicleNo, boolean wantsCharging) {
        super(vehicleNo);
        this.wantsCharging = wantsCharging;
    }

    @Override
    public VehicleType getType() {
        return VehicleType.ELECTRIC_CAR;
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
