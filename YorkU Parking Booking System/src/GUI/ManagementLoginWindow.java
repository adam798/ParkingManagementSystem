package GUI;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class ManagementLoginWindow extends JFrame implements ActionListener {
   private JTextField usernameField;
   private JPasswordField passwordField;
   private JButton loginButton;
   private ArrayList<String[]> managementAccounts;
   String path = "../parkingManagement/CSVs/management.csv";
   public ManagementLoginWindow() {
      // load management accounts from management.csv
      loadManagementFile();
      
      // set up login GUI components
      usernameField = new JTextField(20);
      passwordField = new JPasswordField(20);
      loginButton = new JButton("Login");
      loginButton.addActionListener(this);
      
      // add login components to JFrame
      JPanel loginPanel = new JPanel();
      loginPanel.setLayout(new GridLayout(3, 2));
      loginPanel.add(new JLabel("Username:"));
      loginPanel.add(usernameField);
      loginPanel.add(new JLabel("Password:"));
      loginPanel.add(passwordField);
      loginPanel.add(new JLabel(""));
      loginPanel.add(loginButton);
      getContentPane().add(loginPanel);
      
      // set JFrame properties
      setTitle("Management Login");
      setSize(400, 161);
      setLocationRelativeTo(null);
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      setVisible(true);
   }
   
   public void actionPerformed(ActionEvent e) {
      if (e.getSource() == loginButton) {
    	  String username = usernameField.getText().trim();
    	  String password = new String(passwordField.getPassword()).trim();
         System.out.println("Entered username: " + username);
         System.out.println("Entered password: " + password);
     
         boolean validCredentials = authenticate(username, password);
         if (validCredentials) {
            // launch management GUI
            launchManagementGUI();
         } else {
            JOptionPane.showMessageDialog(null, "Invalid username or password.", "Error", JOptionPane.ERROR_MESSAGE);
         }
      }
   }
   private void loadManagementFile() {
	 
	   managementAccounts = new ArrayList<String[]>();
	   try {
	      BufferedReader csvReader = new BufferedReader(new FileReader(path));
	      String row;
	      while ((row = csvReader.readLine()) != null) {
	         String[] data = row.split(",");
	         data[0] = data[0].trim();
	         data[1] = data[1].trim();
	         managementAccounts.add(data);
	      }
	      csvReader.close();
	      
	      // Remove trailing empty line
	      if (managementAccounts.get(managementAccounts.size() - 1)[0].isEmpty()) {
	         managementAccounts.remove(managementAccounts.size() - 1);
	      }
	      
	      // print the contents of the managementAccounts ArrayList
	      for (String[] account : managementAccounts) {
	         System.out.println(account[0] + "," + account[1]);
	      }
	   } catch (IOException e) {
	      JOptionPane.showMessageDialog(null, "Error reading management credentials.", "Error", JOptionPane.ERROR_MESSAGE);
	   }
	}
   
   public boolean authenticate(String userName, String password) {
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
				System.out.println("userName:" + lst.get(0)  +  "password:" + lst.get(1));
				
				// check if the list index 4(username) and index 7(password) contains the input
				if (lst.get(0).contains(userName) && lst.get(1).contains(password)) {
					isExists = true;
					String msg = "User exists";
					System.out.println("is exists = " + isExists + " " + msg);
					break;
				} else {
					String msg = "User doesn't exist";
					System.out.println("is exists = " + isExists + " " + msg);
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

   
   private void launchManagementGUI() {
      // remove login components from JFrame
      getContentPane().removeAll();
   
      // launch the management window
      ManageParkingWindow managementWindow = new ManageParkingWindow();
      managementWindow.setVisible(true);

      // resize and refresh JFrame
      validate();
      repaint();
      setSize(500, 150);
   }
   
   public static void main(String[] args) {
      new ManagementLoginWindow();
   }
}