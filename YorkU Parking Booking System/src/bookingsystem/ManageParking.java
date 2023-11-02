package bookingsystem;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.LineNumberReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;


public class ManageParking {
	
	
	
	
	
	
	private static String path = "../parkingManagement/CSVs/parking.csv";				
	private static List<String> parkingspot = Arrays.asList(new String[1500]);
	private final int spotPerLocation = 100;
	private static Scanner x;
	private List<Parkinglot> parkingLots;		
	public ManageParking() {
		this.parkingLots = new ArrayList<>();
	}
 
	public void addSpaces(String spotName) throws IOException {
		String Zone1 = ""; String Zone2 = ""; String Zone3 = ""; String Zone4 = ""; 
		File fw = new File(path);							// path to the file
		FileWriter fwt = new FileWriter(fw, true);
		BufferedWriter bfw = new BufferedWriter(fwt);
		PrintWriter pw = new PrintWriter(bfw);				// prints to the file
		
		if (!fw.exists()) {
			fw.createNewFile();
			System.out.println("File exists");
		} else {
			System.out.println("File doesn't exist");
		}
		pw.printf("%s\n", spotName);		// writes to the file
		pw.flush();							// flushes the data into the csv
		pw.close();							//close file
	}

	
	/* 
	 * Remove parking spot from parking.csv
	 */
	public void removeSpaces(String spotName) {
		String tmpFile = "tmp.csv";
		String target = "";
		File oldFile = new File(path);
		File newFile = new File(tmpFile);
		
		System.out.println(spotName);
		try {
			FileWriter fw = new FileWriter(tmpFile, true);
			BufferedWriter bfw = new BufferedWriter(fw);
			PrintWriter pw = new PrintWriter(bfw);
			x = new Scanner(new File(path));
			x.useDelimiter("[,\n]");
			
			while (x.hasNext()) {
				target = x.next();
				if (!target.equals(spotName)) {
					pw.println(target);
				}
			}
			x.close();
			pw.flush();
			pw.close();
			oldFile.delete();
			File dmp = new File(path);
			newFile.renameTo(dmp);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
	public boolean checkParkingSpace(String spotName) {
		String line = "";
		String[] val = null;
		boolean isExists = false;
		
 		try {
			BufferedReader bfr = new BufferedReader(new FileReader(path));
			while ((line = bfr.readLine())!= null) {
				val = line.split(",");
				
				// convert the array into list and put the val into the arraylists
				List<String> lst = Arrays.asList(val);
				System.out.println(lst);
				System.out.println("spotName:" + lst.get(0));
				
				if (lst.get(0).contains(spotName)) {
					isExists = true;
					String msg = "Spot is occupied";
					System.out.println("is exists = " + isExists + "; " + msg);
					break;
				} else {
					String msg = "Spot is free";
					System.out.println("is exists = " + isExists + "; " + msg);
					isExists = false;
				}
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return isExists;
	}
	
	public int countParkingSpace() throws IOException {
		File file = new File(path);
		
		int cnt = 0;
		if (file.exists()) {
			FileReader fr = new FileReader(file);
			LineNumberReader ln = new LineNumberReader(fr);
			
			while (ln.readLine() != null) {
				cnt++;
			}
			System.out.println("Line number = " + cnt);
			ln.close();
		}
		return spotPerLocation - cnt;
	}
	
	
	// addParkingLot method to add a new ParkingLot to the list of parkingLots
		public void addParkingLot(Parkinglot parkingLot) {
		    this.parkingLots.add(parkingLot);
		    System.out.println("Parking lot " + parkingLot.getId() + " added.");
		}

		// enableParkingLot method to enable a ParkingLot
		public void enableParkingLot(Parkinglot parkingLot) {
		    parkingLot.enable();
		    System.out.println("Parking lot " + parkingLot.getId() + " enabled.");
		}

		// disableParkingLot method to disable a ParkingLot
		public void disableParkingLot(Parkinglot parkingLot) {
		    parkingLot.disable();
		    System.out.println("Parking lot " + parkingLot.getId() + " disabled.");
		}

		// enableParkingSpace method to enable a ParkingSpace
		public void enableParkingSpace(Parkinglot parkingSpace) {
		    parkingSpace.enable();
		    System.out.println("Parking space " + parkingSpace.getId() + " enabled.");
		}

		// disableParkingSpace method to disable a ParkingSpace
		public void disableParkingSpace(Parkingspace parkingSpace) {
		    parkingSpace.disable();
		    System.out.println("Parking space " + parkingSpace.getId() + " disabled for maintenance.");
		}
	
	
	/*
	 * views the booking by the Management Team
	 */
	public List<String> viewBooking(String userName) {
		String line = "";
		String[] val = null;
		List<String> lst = new ArrayList<String>();
		
		try {
			BufferedReader bfr = new BufferedReader(new FileReader(path));
			while ((line = bfr.readLine()) != null) {
				val = line.split(",");
				String pp = val[0];
				lst.add(pp);
				System.out.println(lst);
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
	
	
	
	public static void main(String[] args) throws IOException {
		System.out.println("Management");
//		addSpaces("york1");
//		removeSpaces("L1");
		ManageParking peo = new ManageParking();
		
		System.out.println(peo.viewBooking("sami"));
	}
}
