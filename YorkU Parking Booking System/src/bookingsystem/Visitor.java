package bookingsystem;

public class Visitor extends Client {
    private boolean validated;

    public Visitor(String email, String password) {
        super(email, password);
    }

    @Override
    public void register() {
        // Register non-faculty staff
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
		return "Visitor";
	}
	
	@Override
    public ParkingRate getParkingRate() {
        return new VisitorParkingRate();
    }

}
