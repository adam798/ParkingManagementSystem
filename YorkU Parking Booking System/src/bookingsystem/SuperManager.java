package bookingsystem;

import java.awt.GridLayout;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class SuperManager {
    private static SuperManager instance;
    private Set<String> usedNames;
    
    // private constructor to prevent outside instantiation
    private SuperManager() {
        usedNames = new HashSet<String>();
    }
    
    // public static method to get the singleton instance
    public static SuperManager getInstance() {
        if (instance == null) {
            instance = new SuperManager();
        }
        return instance;
    }
    
    // generate a unique name that is not already in use
    public String generateUniqueName() {
        Random random = new Random();
        String prefix = "user";
        String name = prefix + random.nextInt(1000);
        while (usedNames.contains(name)) {
            name = prefix + random.nextInt(1000);
        }
        usedNames.add(name);
        return name;
    }
    
    // generate a unique password
    public String generateUniquePassword() {
        String upperCase = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String lowerCase = "abcdefghijklmnopqrstuvwxyz";
        String numbers = "0123456789";

        String allCharacters = upperCase + lowerCase + numbers;
        
        Random random = new Random();
        StringBuilder password = new StringBuilder();
        
        for (int i = 0; i < 5; i++) {
            int index = random.nextInt(allCharacters.length());
            password.append(allCharacters.charAt(index));
        }
        
        return password.toString();
    }
    
    // other methods and properties of the SuperManager class here
public void launchAutoAccountGenerationForm() {
    // create and set up new JFrame for form
    JFrame frame = new JFrame("Auto Account Generation Form");
    frame.setLayout(new GridLayout(3, 2));
    frame.setSize(300, 150);
    frame.setLocationRelativeTo(null);
    
    // create form components
    SuperManager manager = SuperManager.getInstance();
    String name = manager.generateUniqueName();
    String password = manager.generateUniquePassword();
    JTextField nameField = new JTextField(name);
    nameField.setEditable(false);
   
    JButton generateButton = new JButton("Generate Account");
    generateButton.addActionListener(new GenerateAccountListener(name, password));
  
    
    // add components to form JFrame
    frame.add(new JLabel("Name:"));
    frame.add(nameField);
    frame.add(new JLabel(""));
    frame.add(generateButton);
    
    // show form JFrame
    frame.setVisible(true);
}
}