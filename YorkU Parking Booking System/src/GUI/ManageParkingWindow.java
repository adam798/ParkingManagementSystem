package GUI;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import bookingsystem.ManageParking;



public class ManageParkingWindow extends JFrame {
	private JPanel contentPane;
	private JLabel thisLabel;
	private JButton addButton;
	private JButton removeButton;
	private JLabel addLabel;
	private JLabel removeLabel;
	private JLabel addRemoveMessage;
	private JTextField addInput;
	private JTextField removeInput;
	private JComboBox lst;
	private JList list;
	private String selLoc;
	private JLabel locationCode;
	private JScrollPane scrollPane;
	String userName;
	ManageParking team = new ManageParking();
	
	public static void main(String[] args) {
		System.out.println("This is the Management pane");
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ManageParkingWindow peo = new ManageParkingWindow();
					peo.setVisible(true);
				} catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
				}
			}
		});
	}
	
	public ManageParkingWindow() {
		setTitle("Management page");
		// create the panel
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 550, 400); // x y len wid
		contentPane = new JPanel();
		contentPane.setForeground(new Color(0, 0, 0));
		contentPane.setBackground(new Color(192, 192, 192)); // background color of the app -> Silver
		contentPane.setBorder(new EmptyBorder(6, 6, 6, 6));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		// title
		thisLabel = new JLabel("Management"); // Logo
		thisLabel.setHorizontalAlignment(SwingConstants.CENTER);
		thisLabel.setFont(new Font("Sans-serif", Font.PLAIN, 16));
		thisLabel.setBackground(Color.WHITE);
		thisLabel.setBounds(140, 15, 170, 25); // set the position of the component
		contentPane.add(thisLabel);
		
		
		JLabel tag1 = new JLabel("Select Location: ");
		tag1.setBounds(10, 60, 500, 25);
		contentPane.add(tag1);
		
		// location options
		String[] locOptions = {"Lot1", "Lot2", "Lot3", "Lot4"};
		lst = new JComboBox(locOptions);
		lst.setSelectedIndex(3);
//		lst.addActionListener(this);
		lst.setBounds(120, 60, 170, 25);
		contentPane.add(lst);
		
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
				
				} else {
				}
				addInput.setText(locationCode.getText());
				removeInput.setText(locationCode.getText());
			}
		});

		locationCode = new JLabel();
		locationCode.setBounds(89, 105, 73, 25);
		int count = 1500;
		ManageParking p = new ManageParking();
		int availableCount = 0;
		try {
			availableCount = p.countParkingSpace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		// buttons
		// add parking space
		addLabel = new JLabel();
		addLabel.setText("Add Parking Space: ");
		addLabel.setBounds(10, 244, 500, 25);
		contentPane.add(addLabel);
		
		// add button
		addButton = new JButton("+ Add");
		addButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String spotName = addInput.getText();
				if (spotName == null) {
					addRemoveMessage.setText("Please leave no field empty");
					throw new RuntimeException();
					}
				try {
					ManageParking team = new ManageParking();
					team.addSpaces(spotName);
					list = new JList(team.viewBooking(userName).toArray());
					scrollPane.setViewportView(list);
					addRemoveMessage.setText("Successfully Added Parking Space");		
				} catch (Exception e2) {
					// TODO: handle exception
					e2.printStackTrace();
				}
			}
		});
		addButton.setBounds(348, 245, 90, 25);
		contentPane.add(addButton);

		// Remove parking space
		removeLabel = new JLabel();
		removeLabel.setText("Remove Parking space: ");
		removeLabel.setBounds(10, 281, 500, 25);
		contentPane.add(removeLabel);

		// remove button
		removeButton = new JButton("- Remove");
		removeButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String spotName = removeInput.getText();
				if (spotName == null) {
						addRemoveMessage.setText("Please leave no field empty");
						throw new RuntimeException();
					}
				try {
					ManageParking team = new ManageParking();
					team.removeSpaces(spotName);
					list = new JList(team.viewBooking(userName).toArray());
					scrollPane.setViewportView(list);
					addRemoveMessage.setText("Successfully Removed Parking Space");		
				} catch (Exception e2) {
					// TODO: handle exception
					e2.printStackTrace();
				}
			}
		});
		removeButton.setBounds(348, 282, 90, 25);
		contentPane.add(removeButton);
		
		//addRemoveMessage
	    addRemoveMessage = new JLabel("");  					//Successfully Added/Removed  Parking Space
	    addRemoveMessage.setBounds(178,310,303,25);
	    contentPane.add(addRemoveMessage);
			    
	    JButton logout = new JButton("Logout");
		logout.setBounds(444, 15, 100, 25);
		logout.addActionListener(new ActionListener() {
			// takes to loginView
			public void actionPerformed(ActionEvent e) {
				LoginWindow lv = new LoginWindow();
				lv.setVisible(true);
			}
		});
		contentPane.add(logout);
		
		// parking spot number 
		addInput = new JTextField();
		addInput.setBounds(178, 243, 170, 26);
		contentPane.add(addInput);
		addInput.setColumns(10);
		
		removeInput = new JTextField();
		removeInput.setColumns(10);
		removeInput.setBounds(178, 280, 170, 26);
		contentPane.add(removeInput);
		
		userName = LoginWindow.email;
	    ArrayList<String> mx = new ArrayList<String>();
	    
		scrollPane = new JScrollPane();
		scrollPane.setBounds(20, 97, 418, 131);
		contentPane.add(scrollPane);
		list = new JList(team.viewBooking(userName).toArray());
		scrollPane.setViewportView(list);
	}
}
