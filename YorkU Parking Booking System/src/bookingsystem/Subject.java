package bookingsystem;

//Observer pattern - Subject interface
interface Subject {
void attach(Observer observer);
void detach(Observer observer);
void notifyObservers();
}