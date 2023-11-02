package bookingsystem;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.Scanner;


public class Booking {
	  private Date startTime;
	  private Date endTime;
	  private boolean cancelled;
	  private double cost;
	  private double deposit;
	  private Parkingspace parkingSpace;
	
	  public String path = "../parkingManagement/CSVs/booking.csv";
	  private static Scanner x;
	

	  public void book() {
	  }

	  public void cancelBooking() {
	  
	  }
	  
	  public boolean isOccupied(String name) {
			
		  String line = "";
		    String[] val = null;
		    boolean isExists = false;

		    try {
		        BufferedReader bfr = new BufferedReader(new FileReader(path));
		        while ((line = bfr.readLine()) != null) {
		            val = line.split(",");
		            List<String> lst = Arrays.asList(val);
		            if (lst.get(5).contains(name)) {
		                isExists = true;
		                break;
		            }
		        }
		        bfr.close();
		    } catch (FileNotFoundException e) {
		        e.printStackTrace();
		    } catch (IOException e) {
		        e.printStackTrace();
		    }

		    if (isExists) {
		        System.out.println("Spot is occupied");
		    } else {
		        System.out.println("Spot is free");
		    }

		    return isExists;
		}

	  public void extendBooking(Date newEndTime) {
	      if (newEndTime.after(endTime)) {
	          endTime = newEndTime;
	          System.out.println("Booking extended until " + endTime);
	      } else {
	          System.out.println("Invalid booking extension.");
	      }
	  }
	  
	  
	  public void bookparkingSpace(String userName,String date, String time, 
				String duration,String spotName, String price, String paymentStat, String licenceNum) throws IOException {
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
			
			pw.printf("%s,%s,%s,%s,%s,%s,%s,%s\n", userName,date,time,duration,spotName,price,paymentStat,licenceNum);		// writes to the file
			pw.flush();							// flushes the data into the csv
			pw.close();							//close file
			
		}
	  
	  
		public void cancelBooking(String userName, String date, String time, 
				String duration,String spotName, String price, String paymentStat, String licenceNum) {
			
			String tmpFile = "tmp.csv";
			String target1 = ""; String target2 = ""; String target3 = ""; 
			String target4 = "";String target5 = "";String target6 = "";String target7 = "";String target8 = "";String target9 = "";
			File oldFile = new File(path);
			File newFile = new File(tmpFile);
			
			System.out.println(spotName);
			try {
				FileWriter fw = new FileWriter(tmpFile, true);
				BufferedWriter bfw = new BufferedWriter(fw);
				PrintWriter pw = new PrintWriter(bfw);
				x = new Scanner(new File(path));			// reads/scans the file 
				x.useDelimiter("[,\n]");						// write each field separated by newline or comma 
				
				// scan the file till the end
				while (x.hasNext()) {
					target1 = x.next();		// username
					target2 = x.next();		// date
					target3 = x.next();		// time
					target4 = x.next();		// duration
					target5 = x.next();		// spotName
					target6 = x.next();		// price
					target7 = x.next();		// paymentStat
					target8 = x.next();		// licenceNum
					
					// if the target is not equals the input credentials then write all the other lines accept for the line that mathches the inputs  
					if (!target2.equals(date) && !target3.equals(time) && !target4.equals(duration) && !target5.equals(spotName)&& !target6.equals(price) && !target7.equals(paymentStat) && !target8.equals(licenceNum)){
						pw.println(target1 + ","+ target2 + ","+ target3 + "," + target4 + ","+ target5 + "," + target6 + ","+ target7 + "," + target8);
					}		
				}
				x.close();
				pw.flush();
				pw.close();
				oldFile.delete();				// delete the old file
				File dmp = new File(path);		// recreate a file in the path
				newFile.renameTo(dmp);			// rename the new file to old file's name					
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
		
		
		public String bookingID() {
			int len = 4;
			String chr = "abcdefghijk0123456789";
			Random rn = new Random();
			StringBuilder str = new StringBuilder(len);
			for (int i = 0; i < len; i++) {
				str.append(chr.charAt(rn.nextInt(chr.length())));
			}
			return str.toString();
		}
		
		
 

	  // Getters and setters
	  public Date getStartTime() {
	      return startTime;
	  }

	  public void setStartTime(Date startTime) {
	      this.startTime = startTime;
	  }

	  public Date getEndTime() {
	      return endTime;
	  }

	  public void setEndTime(Date endTime) {
	      this.endTime = endTime;
	  }

	 

	  public boolean isCancelled() {
	      return cancelled;
	  }

	  public void setCancelled(boolean cancelled) {
	      this.cancelled = cancelled;
	  }

	  public double getCost() {
	      return cost;
	  }

	  public void setCost(double cost) {
	      this.cost = cost;
	  }

	  public double getDeposit() {
	      return deposit;
	  }

	  public void setDeposit(double deposit) {
	      this.deposit = deposit;
	  }

	  public Parkingspace getParkingSpace() {
	      return parkingSpace;
	  }

	  public void setParkingSpace(Parkingspace parkingSpace) {
	      this.parkingSpace = parkingSpace;
	  }
	  
	  
	  public static void main(String[] args) throws FileNotFoundException {
			// TODO Auto-generated method stub
			System.out.println("Booking Backend");
			
			Booking bk = new Booking();
			bk.deposit =50.00;
			String spotName= "york12";
			bk.isOccupied(spotName);
			
			
			System.out.println(bk.bookingID());
			
			String user = "farhan95";

		}
	  
}
