package bookingsystem;

public class StudentParkingRate implements ParkingRate {
    private static final double RATE_PER_HOUR = 5.0;

    @Override
    public double calculateParkingRate(double hours) {
        return RATE_PER_HOUR * hours;
    }
}

