package GUI;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class Reservation {

	private static String path = "../parkingManagement/CSVs/booking.csv";	

	/*
	 * display parking space booking information, including expiry time
	 */
	public List<String> viewBooking(String userName) {
		String line = "";
		String[] val = null;
		List<String> lst = new ArrayList<String>();
		
 		try {
			BufferedReader bfr = new BufferedReader(new FileReader(path));
			while ((line = bfr.readLine()) != null) {
				val = line.split(",");
				
				if (val[0].contains(userName)) {
					String pp = val[0] + "  ,  " + val[2] + "  ,  " + val[3] + "hrs" + "  ,  " + val[4] + "  ,  " + "$"+ val[5];
					lst.add(pp);
					System.out.println(lst);
				}
				val = new String[20];
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return lst;
	}
	

	public void notifyCustomer() {
		
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("View Booking");
		Reservation vb = new Reservation();
		String username = "farhan95";
		vb.viewBooking(username);
	}

	
}
