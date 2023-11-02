package bookingsystem;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;
import javax.swing.JTextField;

class GenerateAccountListener implements ActionListener {
    private String name;
    private String password;
    private JTextField emailField;

    public GenerateAccountListener(String name, String password) {
        this.name = name;
        this.password = password;
   
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // display generated account to user
        String message = "Account generated:\nName: " + name + "\nPassword: " + password;
        JOptionPane.showMessageDialog(null, message, "Account Generated", JOptionPane.INFORMATION_MESSAGE);
    }
}