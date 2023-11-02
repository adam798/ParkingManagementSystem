package bookingsystem;

public class VisitorParkingRate implements ParkingRate {
    private static final double RATE_PER_HOUR = 15.0;

    @Override
    public double calculateParkingRate(double hours) {
        return RATE_PER_HOUR * hours;
    }
}
