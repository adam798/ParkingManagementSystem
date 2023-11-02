package bookingsystem;

public abstract class Client {
	 private String email;
	    private String password;

	    public Client(String email, String password) {
	        this.email = email;
	        this.password = password;
	    }
	    
	    
	    public abstract String getClientType () ;
	    public abstract ParkingRate getParkingRate();
	    public abstract void register() ;
	    
	    	
	   

	    // Getters and setters
	    public String getEmail() {
	        return email;
	    }

	    public void setEmail(String email) {
	        this.email = email;
	    }

	    public String getPassword() {
	        return password;
	    }

	    public void setPassword(String password) {
	        this.password = password;
	    }
}
