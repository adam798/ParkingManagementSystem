package GUI;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import bookingsystem.Booking;
import bookingsystem.Payment;


public class BookingWindow extends JFrame{
	private JPanel contentPane;
	private JLabel thisLabel;
	private JLabel selectParkingSpot;
	private JTextField timeSlotinput;
	private JLabel slabel;
	private JButton bookButton;
	private JButton payButton;
	private JButton viewBookingButton;
	private JTextField parkingspotinput;
	private JLabel lblLicensePlate;
	private JTextField licenceinput;
	private JComboBox lst;
	private JLabel locationCode;
	private JTextField durationInput;
	private String selLoc;
	private JLabel lblDate;
	private JTextField dateInput;
	private JButton dateButton;
	Booking booking = new Booking();
	String spotName; String bookingID; String currentTime; String currentDate;
	String startTime;String dateinput; String duration; String licenseplate; String expiryTime;
	String prc; double price; String paymentStat;
	private JTextField creditcardinput;
	private JLabel priceTag;
	private JLabel lblThisWillDisplay;
	Payment payment = new Payment();
					
	/*
	 *REQ-4.4 and 4.5 and 4.6 Booking Space/ Main Dashboard 
	 */
	public static void main(String[] args) {
		System.out.println("This is the HomePage pane");
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BookingWindow hp = new BookingWindow();
					hp.setVisible(true);
				} catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
				}
			}
		});
	}
	
	public BookingWindow() {
		setTitle("Client HomePage");
		// create the panel
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 650, 400); // x y len wid
		contentPane = new JPanel();
		contentPane.setForeground(new Color(0, 0, 0));
		contentPane.setBackground(new Color(192, 192, 192)); // background color of the app -> Silver
		contentPane.setBorder(new EmptyBorder(6, 6, 6, 6));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		// title
		thisLabel = new JLabel("Yorku Parking"); // Logo
		thisLabel.setHorizontalAlignment(SwingConstants.CENTER);
		thisLabel.setFont(new Font("Sans-serif", Font.PLAIN, 16));
		thisLabel.setBackground(Color.WHITE);
		thisLabel.setBounds(243, 6, 170, 25); 	// set the position of the component
		contentPane.add(thisLabel);
		
		// choose location
		JLabel location = new JLabel();
		location.setText("Choose ParkingLot: ");
		location.setBounds(6, 52, 134, 25);
		contentPane.add(location);
		
		// location options
		String[] locOptions = {"Lot1", "Lot2", "Lot3", "Lot4"};
		lst = new JComboBox(locOptions);
//		lst.setSelectedIndex(4);
//		lst.addActionListener(this);
		lst.setBounds(140, 53, 105, 25);
		contentPane.add(lst);
		
		/* view of all the available spot of the choosen location */
		//available slot
		JLabel tag1 = new JLabel();
		int count = 0;
		tag1.setText("Available Spots: 0");
		tag1.setBounds(257, 52, 170, 25);
		contentPane.add(tag1);
		
		/* get the code of the location and check if the parking slots is empty */
		selectParkingSpot = new JLabel();
		selectParkingSpot.setText("Choose ParkingSpot: ");
		selectParkingSpot.setBounds(6, 89, 140, 25);
		contentPane.add(selectParkingSpot);
		
		// get the selected locations code
		lst.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				selLoc = lst.getSelectedItem().toString();
				System.out.println(selLoc);
				if (selLoc.equals("Lot1")) {
					locationCode.setText("L1");
				} else if (selLoc.equals("Lot2")) {
					locationCode.setText("L2");
				} else if (selLoc.equals("Lot3")) {
					locationCode.setText("L3");
				} else if (selLoc.equals("Lot4")) {
					locationCode.setText("L4");
				} 
			
				 else {}
				parkingspotinput.setText(locationCode.getText());
			}
		});
		
		locationCode = new JLabel();
		locationCode.setBounds(89, 105, 73, 25);
//		contentPane.add(locationCode);
		
		// parking spot input
		parkingspotinput = new JTextField(20);
		parkingspotinput.setBounds(146, 88, 100, 25);
		contentPane.add(parkingspotinput);
		
		// after clicking this button the system will check if parkingspot is taken or not, by checking booking.csv index4
		JButton selectparkingspotbtn = new JButton("Check ParkingSpot");
		selectparkingspotbtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String pks = parkingspotinput.getText();
				
				System.out.println(pks);
				if (!booking.isOccupied(pks)) {
					slabel.setText("This spot is free");
				} else {
					slabel.setText("This spot is occupied Please try another spot");
				}
			}
		});
		selectparkingspotbtn.setBounds(243, 89, 184, 25);
		contentPane.add(selectparkingspotbtn);
		
		//date
		lblDate = new JLabel();
		lblDate.setText("Date:");
		lblDate.setBounds(10, 153, 170, 25);
		contentPane.add(lblDate);
		
		dateInput = new JTextField(20);
		dateInput.setBounds(120, 153, 100, 25);
		contentPane.add(dateInput);
		
		// gets the current date
		DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		Date dt = new Date();
		currentDate =  df.format(dt);
		System.out.println(currentDate);
		
		dateButton = new JButton("D");
		dateButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dateInput.setText(currentDate);
			}
		});
		dateButton.setBounds(220, 152, 58, 25);
		contentPane.add(dateButton);
		
		// available time slot
		JLabel timeSlot = new JLabel();
		timeSlot.setText("Start Time:");
		timeSlot.setBounds(10, 190, 170, 25);
		contentPane.add(timeSlot);
		
		// timeSlot input
		timeSlotinput = new JTextField(20);					// max length of input char = 20
		timeSlotinput.setBounds(120,189,100,25);				
		contentPane.add(timeSlotinput);
		
		// duration label 
		JLabel durationLabel = new JLabel();
		durationLabel.setText("Duration (Hrs):");
		durationLabel.setBounds(285, 190, 116, 25);
		contentPane.add(durationLabel);
		
		durationInput = new JTextField(20);
		durationInput.setBounds(390, 189, 109, 25);
		contentPane.add(durationInput);
		
		// get the current date
		DateFormat dt2 = new SimpleDateFormat("HH:mm:ss");
		Date date = new Date();
		currentTime = dt2.format(date);
		System.out.println(currentTime);
		
		JButton startTimeButton = new JButton("T");
		startTimeButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				timeSlotinput.setText(currentTime);
			}
		});
		startTimeButton.setBounds(220, 189, 58, 25);
		contentPane.add(startTimeButton);
		
		// this button will dump spot number time slot and licsence plate# to booking.csv
		bookButton = new JButton("Book Spot ");
		bookButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String userName = LoginWindow.email;
				spotName = parkingspotinput.getText();
				startTime = timeSlotinput.getText();
				dateinput = dateInput.getText();
				duration = durationInput.getText();
				licenseplate = licenceinput.getText();
				price = payment.checkOut(Double.parseDouble(duration),userName);
				prc = Double.toString(price);
				paymentStat = "Paid at:" +payment.timeStamp(); 
//				expiryTime = payment.counter(Integer.parseInt(startTime));
				
				// dump the data to booking.csv later -> add exceptions for null input or in valid input
				if (userName.equals(null) || spotName.equals(null) || currentDate.equals(null) || currentTime.equals(null) ||
						duration.equals(null) || licenseplate.equals(null)) {
					slabel.setText("Leave no field empty");
				} 
				
				else {
					try {
						booking.bookparkingSpace(userName,dateinput, startTime, duration, spotName, prc, paymentStat, licenseplate);
						slabel.setText("Space Reserved, Payment successful");
						
					}
					 catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				System.out.println(userName);
				System.out.println(spotName);
//				System.out.println(startTime);
				System.out.println(duration);
				System.out.println(licenseplate);
			}	    	
	     });
		bookButton.setBounds(109,280,218,25);
	    contentPane.add(bookButton);
		
	    slabel = new JLabel();
	    slabel.setBounds(120,336,344,25);
	    contentPane.add(slabel);

	    // view booking button  [later add action listener]
		viewBookingButton = new JButton("View Bookings");
		viewBookingButton.addActionListener(new ActionListener() {
			// takes to reservationView
			public void actionPerformed(ActionEvent e) {
				ReservationWindow rv = new ReservationWindow();
				rv.setVisible(true);
			}
		});
		viewBookingButton.setBounds(109, 317, 218, 25);
		contentPane.add(viewBookingButton);
		
		JButton logout = new JButton("Logout");
		logout.setBounds(494, 307, 100, 25);
		logout.addActionListener(new ActionListener() {
			// takes to loginView
			public void actionPerformed(ActionEvent e) {
				LoginWindow lv = new LoginWindow();
				lv.setVisible(true);
			}
		});
		contentPane.add(logout);
		
		lblLicensePlate = new JLabel();
		lblLicensePlate.setText("License Plate#:");
		lblLicensePlate.setBounds(10, 220, 170, 25);
		contentPane.add(lblLicensePlate);
		
		// license plate input
		licenceinput = new JTextField(20);
		licenceinput.setBounds(120, 220, 140, 25);
		contentPane.add(licenceinput);
		
		JLabel lblchooseAnyNumber = new JLabel();
		lblchooseAnyNumber.setText("(Choose any spot between 1-100)");
		lblchooseAnyNumber.setBounds(156, 112, 256, 25);
		contentPane.add(lblchooseAnyNumber);
		
		JLabel lblCreditCard = new JLabel();
		lblCreditCard.setText("Card Number:");
		lblCreditCard.setBounds(283, 220, 170, 25);
		contentPane.add(lblCreditCard);
		
		creditcardinput = new JTextField(20);
		creditcardinput.setBounds(390, 219, 109, 25);
		contentPane.add(creditcardinput);
		
		String[] payOptions = {"Credit card","Debit card", "Mobilepayment" };	
		JComboBox lst_1 = new JComboBox(payOptions);
		lst_1.setBounds(509, 221, 116, 25);
		contentPane.add(lst_1);
		
		JButton durationbtn = new JButton("CheckOut");
		// gets the duration input for calculating the price 
		durationbtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String userName = LoginWindow.email;
				String v = durationInput.getText();
				int h = Integer.parseInt(v);
				if (h < 0 || h > 24) {
					lblThisWillDisplay.setText("Can't book more the 24 hrs or less then 0 hrs");
				} else if(durationInput.getText().equals(null)) {
					lblThisWillDisplay.setText("Leave no field blank");
				}
			
				double price2 = payment.checkOut(Double.parseDouble(durationInput.getText()),userName);
				String prc2 = Double.toString(price2);
				lblThisWillDisplay.setText(prc2);
			}
		});
		durationbtn.setBounds(509, 191, 116, 25);
		contentPane.add(durationbtn);
		
		priceTag = new JLabel();
		priceTag.setText("Price ($ CAD): ");
		priceTag.setBounds(10, 253, 170, 25);
		contentPane.add(priceTag);
		
		lblThisWillDisplay = new JLabel();
		lblThisWillDisplay.setBounds(120, 253, 405, 25);
		contentPane.add(lblThisWillDisplay);
	}

}
