package GUI;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;



public class RegistrationWindow extends JFrame {

	private static JLabel userlabel;
	private static JLabel usertype;
	private static JLabel passwordLabel;
	private static JLabel success;
	private static JLabel thisLabel;
	private static JLabel name;
	private static JLabel email;
	private static JTextField nameInput;
	private static JTextField emailInput;
	private static JPanel contentPane;
	private static JTextField userText;
	private static JPasswordField passwordText;
	private static JPasswordField usertypeText;
	private static JButton loginButton;
	private static JButton registerButton;

	public static void main(String[] args) {
		System.out.println("This is the registration pane");
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RegistrationWindow frm = new RegistrationWindow();
					 frm.setVisible(true);
				} catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
				}
			}
		});
	}

	/*
	 * create the frame
	 */
	public RegistrationWindow() {
		setTitle("Register");
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
		thisLabel = new JLabel("Yorku Parking"); 			// Logo
		thisLabel.setHorizontalAlignment(SwingConstants.CENTER);
		thisLabel.setFont(new Font("Sans-serif", Font.PLAIN, 16));
		thisLabel.setBackground(Color.WHITE);
		thisLabel.setBounds(100, 15, 200, 25); // set the position of the component
		contentPane.add(thisLabel); // add to the content
		
	
		
		// email
		email = new JLabel("Email: ");
		email.setBounds(10, 75, 80, 25);
		contentPane.add(email);
		
		// email input
		emailInput = new JTextField(20); // max length of input char = 20
		emailInput.setBounds(120, 75, 165, 25);
		contentPane.add(emailInput);
		
	
		// password part
		passwordLabel = new JLabel("Password");
		passwordLabel.setBounds(10, 200, 80, 25);
		contentPane.add(passwordLabel);

		// password input
		passwordText = new JPasswordField(20);			 // max length of input char = 20
		passwordText.setBounds(120, 200, 165, 25);
		contentPane.add(passwordText);

		
		// usertype part
		usertype = new JLabel("User Type: ");
		passwordLabel.setBounds(10, 200, 80, 25);
		contentPane.add(usertype);
		
		String[] userOptions = { "Student", "Faculty", "NonFaculty", "Visitor"};
		final JComboBox lst = new JComboBox(userOptions);
		lst.setSelectedIndex(0);
//		lst.addActionListener(this);
		lst.setBounds(120, 175, 170, 25);
		contentPane.add(lst);

	
		// register button, takes to the registration page, 
		// later, enter user information and once that is done put the info in the csv file of user table and 
		registerButton = new JButton("Register");
//		registerButton.setBackground(Color.BLUE);
		
		// write the user input to the database.csv
		registerButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// get the data [later make sure inputs are regular expressions no numbers or random input]
				String email = emailInput.getText();
				
				String password = passwordText.getText();
				String userType = (String) lst.getSelectedItem();
				
				
				// write into database.csv by calling writeUser method
				if (email == null || password.isEmpty()|| userType == null ) {
					success.setText("Please leave no field empty");
					throw new RuntimeException();
				} else {
					Login rg = new Login();
					try {
						rg.register(userType, email, password);
						success.setText("You are Successfully registered!!");
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				
				System.out.println(email);
				System.out.println(password);
				System.out.println(userType);
			}
		});
		
		registerButton.setBounds(110, 235, 190, 25);
		contentPane.add(registerButton);
		
		
		// Creating login button 
		loginButton = new JButton("Login Page");
		loginButton.setBounds(110, 260, 190, 25);
		loginButton.addActionListener(new ActionListener() {
			// goes to the login page
			public void actionPerformed(ActionEvent e) {
				// go to the loginView
				LoginWindow login = new LoginWindow();
				login.setVisible(true);
			}
		});
		contentPane.add(loginButton);

		success = new JLabel("");
		success.setBounds(109, 297, 224, 25);
		contentPane.add(success);
	}
}
