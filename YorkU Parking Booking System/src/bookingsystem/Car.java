package bookingsystem;

public class Car {
	 private String licensePlate;
	    private String make;

	    public Car(String licensePlate, String make) {
	        this.licensePlate = licensePlate;
	        this.make = make;
	    }
	 // Getters and setters
		public String getLicensePlate() {
			return licensePlate;
		}

		public void setLicensePlate(String licensePlate) {
			this.licensePlate = licensePlate;
		}

		public String getMake() {
			return make;
		}

		public void setMake(String make) {
			this.make = make;
		}

	    
}
