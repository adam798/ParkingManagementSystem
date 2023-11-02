package bookingsystem;

public class FacultyParkingRate implements ParkingRate {
    private static final double RATE_PER_HOUR = 8.0;

    @Override
    public double calculateParkingRate(double hours) {
        return RATE_PER_HOUR * hours;
    }
}
