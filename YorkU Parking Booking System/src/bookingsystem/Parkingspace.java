package bookingsystem;

//Observer pattern - Concrete observer
public class Parkingspace implements Observer {
private int id;
private int parkinglotId;
private boolean isOccupied;
private Booking booking;
private boolean enabled;


public Parkingspace(int id, int parkinglotId) {
 this.id = id;
 this.parkinglotId = parkinglotId;
 isOccupied = true;
 this.enabled = true;
}
public int getId() {
	return id;
}
public int getParkinglotId() {
    return this.parkinglotId;
}


public void enable() {
    this.enabled = true;
}

public void disable() {
    this.enabled = false;
}




public void setAvailable(boolean available) {
	isOccupied = available;
}
@Override
public void update() {
	// TODO Auto-generated method stub
	
}
public Booking getBooking() {
	// TODO Auto-generated method stub
	return booking;
}

//@Override
//public void update() {
//    if (isAvailable && ((Sensor) sender).isCarDetected()) {
//        setAvailable(false);
//    } else if (!isAvailable && !((Sensor) sender).isCarDetected()) {
//        setAvailable(true);
//    }
//}
}
