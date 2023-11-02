package GUI;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import bookingsystem.Client;
import bookingsystem.ClientFactory;

public class Login {

    private String userPath = "../parkingManagement/CSVs/client.csv";
    private List<Client> clients = new ArrayList<>();

    public void register(String type, String email, String password) {
        Client client = ClientFactory.createClient(type, email, password);
        clients.add(client);
        writeClientToFile(client);
    }

    public boolean validate(String email, String password) {
        System.out.println("Validating login for email: " + email + " password: " + password);
        System.out.println("Number of clients in list: " + clients.size());
        for (Client client : clients) {
            System.out.println("Checking client email: " + client.getEmail() + " password: " + client.getPassword());
            if (client.getEmail().equalsIgnoreCase(email) && client.getPassword().equals(password)) {
                System.out.println("Login successful for email: " + email);
                return true;
            }
        }
        System.out.println("Login failed for email: " + email);
        return false;
    }

    public String checkClientType(String email) {
    	if (email==null) {
    		return null;
    	}
        for (Client client : clients) {
            if (client.getEmail().equalsIgnoreCase(email)) {
                return client.getClientType();
            }
        }
        return null;
    }

    private void writeClientToFile(Client client) {
        try {
            FileWriter writer = new FileWriter(userPath, true);
            writer.write(client.getEmail() + "," + client.getPassword() + "," + client.getClientType() + "\n");
            writer.close();
        } catch (IOException e) {
            System.out.println("An error occurred while writing to the file: " + e.getMessage());
        }
    }

    public void loadClientsFromFile() {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(userPath));
            String line = reader.readLine();
            while (line != null) {
                String[] parts = line.split(",");
                String email = parts[0];
                String password = parts[1];
                String type = parts[2];
                Client client = ClientFactory.createClient(type, email, password);
                clients.add(client);
                line = reader.readLine();
            }
            reader.close();
        } catch (IOException e) {
            System.out.println("An error occurred while reading from the file: " + e.getMessage());
        }
    }

	
	public static void main(String[] args) throws IOException {
		
		String userPath = "/Users/abdul/eclipse-workspace/parkingManagement/CSVs/client.csv";
		System.out.println("Login");
		Login login = new Login();

		
		String email = "tom@yorku.ca";
		String password = "pass123";
		String userType = "Student";
		login.validate(email, password);
		System.out.println("====================================================");
		
	
		//System.out.println(login.checkUserType(user, password));
	}
}