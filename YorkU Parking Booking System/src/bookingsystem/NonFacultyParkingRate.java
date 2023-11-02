package bookingsystem;

public class NonFacultyParkingRate implements ParkingRate {
	 private static final double RATE_PER_HOUR = 10.0;

    @Override
    public double calculateParkingRate(double hours) {
        return RATE_PER_HOUR * hours;
    }
}
