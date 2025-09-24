public class PerHourPricingStrategy implements PricingStrategy {
    private final double ratePerHour;

    public PerHourPricingStrategy(double ratePerHour) {
        this.ratePerHour = ratePerHour;
    }

    @Override
    public double calculatePrice(Ticket ticket) {
        long durationInMillis = System.currentTimeMillis() - ticket.getEntryTime();
        long hours = (durationInMillis / (1000 * 60 * 60)) + 1;
        return ratePerHour * hours;
    }
}