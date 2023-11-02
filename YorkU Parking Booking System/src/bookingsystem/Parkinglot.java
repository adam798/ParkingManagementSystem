package bookingsystem;

import java.util.ArrayList;
import java.util.List;

public class Parkinglot {

	private int id;
	private boolean enabled;
	private List<Parkingspace> parkingSpaces;


	public Parkinglot(int id) {
	    this.id = id;
	    this.enabled = true;
	    this.parkingSpaces = new ArrayList<>();
	    for (int i = 0; i < 100; i++) {
	        parkingSpaces.add(new Parkingspace(i+1, this.id));
	    }
	}

	public int getId() {
	    return this.id;
	}

	public boolean isEnabled() {
	    return this.enabled;
	}

	public void enable() {
	    this.enabled = true;
	}

	public void disable() {
	    this.enabled = false;
	    for (Parkingspace parkingSpace : parkingSpaces) {
	        parkingSpace.disable();
	    }
	}

	public List<Parkingspace> getParkingSpaces() {
	    return this.parkingSpaces;
	}

	// Other methods

}
