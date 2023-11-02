package bookingsystem;


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Timestamp;
public class Payment {
	 private Parkingspace parkingSpace;
	

	    public Payment(Parkingspace parkingSpace) {
	        this.parkingSpace = parkingSpace;
	   
	    }
	    
	    public Payment() {
	    	super();
	    }

	    
	    public Booking getBooking() {
	        return parkingSpace.getBooking();
	    }

	   
	 // to be done in the client side
		public String selectPaymentMethod() {
			return null;
		}
		
		

		public	double checkOut(double hours, String email){
			    double parkingRate = 0.0; // Parking rate for the specified client type
			    double parkingFee = 0.0;
			    String clientType = getClientType(email);
			    
			    // Determine the parking rate based on the client type
			    if(clientType != null && clientType.equalsIgnoreCase("Student")) {
			        parkingRate = 5.0;
			    } else if(clientType != null && clientType.equalsIgnoreCase("faculty")) {
			        parkingRate = 8.0;
			    } else if(clientType != null && clientType.equalsIgnoreCase("nonfaculty")) {
			        parkingRate = 10.0;
			    } else if(clientType != null && clientType.equalsIgnoreCase("visitor")) {
			        parkingRate = 15.0;
			    } else {
			        return -1.0; // Invalid client type
			    }
			    
			    if (hours > 0) {
			        parkingFee = parkingRate * hours;
			        
			        // Deduct the deposit amount if the client shows up within the first hour
			        if (hours <= 1) {
			            parkingFee -= parkingRate;
			        }
			    }
			    
			    return parkingFee;
			}
		public String getClientType(String email) {
			String path = "../parkingManagement/CSVs/client.csv";
		    String line = "";
		    String clientType = "";

		    try (BufferedReader br = new BufferedReader(new FileReader(path))) {
		        while ((line = br.readLine()) != null) {
		            String[] client = line.split(",");
		            if (client[0].equals(email)) {
		                clientType = client[2];
		                break;
		            }
		        }
		    } catch (IOException e) {
		        e.printStackTrace();
		    }

		    return clientType;
		}
		
		public String timeStamp() {
			Timestamp ts = new Timestamp(System.currentTimeMillis());	// gets the current time
			System.out.println(ts);
			return ts.toString();
		}
		   
	
	    
	 
	    
	    
	public static void main(String[] args) {
		  // Create a new Booking
	 
		
		
	}
}
