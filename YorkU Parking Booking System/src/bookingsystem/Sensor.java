package bookingsystem;

import java.util.ArrayList;
import java.util.List;

public class Sensor implements Subject {
	private List<Observer> observers = new ArrayList<>();
	private boolean carDetected;

	public boolean isCarDetected() {
	    return carDetected;
	}

	public void setCarDetected(boolean carDetected) {
	    this.carDetected = carDetected;
	    notifyObservers();
	}

	@Override
	public void attach(Observer observer) {
	    observers.add(observer);
	}

	@Override
	public void detach(Observer observer) {
	    observers.remove(observer);
	}

	@Override
	public void notifyObservers() {
	    for (Observer observer : observers) {
	        observer.update();
	    }
	}
}
