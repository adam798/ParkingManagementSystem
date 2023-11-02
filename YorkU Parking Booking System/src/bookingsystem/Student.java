package bookingsystem;

public class Student extends Client {
    private boolean validated;

    public Student(String email, String password) {
        super(email, password);
    }

    @Override
    public void register() {
        // Register student
        validated = false;
    }

	@Override
	public String getClientType() {
		// TODO Auto-generated method stub
		return "Student";
	}
	
	  @Override
	    public ParkingRate getParkingRate() {
	        return new StudentParkingRate();
	    }
}
