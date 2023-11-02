package bookingsystem;

public class Faculty extends Client {
    private boolean validated;

    public Faculty(String email, String password) {
        super(email, password);
    }

    @Override
    public void register() {
        // Register faculty
        validated = false;
    }

    public void setValidated(boolean validated) {
        this.validated = validated;
    }

    public boolean isValidated() {
        return validated;
    }

	@Override
	public String getClientType() {
		// TODO Auto-generated method stub
		return "Faculty";
	}
	
	@Override
    public ParkingRate getParkingRate() {
        return new FacultyParkingRate();
    }
}
