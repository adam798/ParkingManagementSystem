package bookingsystem;

public class ClientFactory {
    public static Client createClient(String type, String email, String password) {
        if (type.equalsIgnoreCase("student")) {
            return new Student(email, password);
        } else if (type.equalsIgnoreCase("faculty")) {
            return new Faculty(email, password);
        } else if (type.equalsIgnoreCase("nonfaculty")) {
            return new NonFaculty(email, password);
        } else if (type.equalsIgnoreCase("visitor")) {
            return new Visitor(email, password);
        } else {
            throw new IllegalArgumentException("Invalid client type: " + type);
        }
    }
}