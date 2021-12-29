package es.deusto.ingenieria.sd.strava.client.gui.GraphicalWindows;
import javax.swing.*;

import es.deusto.ingenieria.sd.strava.client.controller.LoginController;
import es.deusto.ingenieria.sd.strava.client.gui.HomeWindow;
import es.deusto.ingenieria.sd.strava.client.gui.LoginDialog;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

@SuppressWarnings("serial")
public class LoginW extends JFrame implements ActionListener {

    Container container = getContentPane();
    JLabel userLabel = new JLabel("USERNAME");
    JLabel passwordLabel = new JLabel("PASSWORD");
    JTextField mailTextField = new JTextField();
    JPasswordField passwordField = new JPasswordField();
    JButton loginButton = new JButton("LOGIN");
    JButton resetButton = new JButton("RESET");
    JButton backButton = new JButton("GO BACK");
    JCheckBox showPassword = new JCheckBox("Show Password");

	int w=370;
	int he=600;

	private LoginDialog l;
	private HomeWindow h;
	
	private int i;
	private String mail;
	private String password;
	
    public LoginW(int i, LoginDialog l,HomeWindow h,String mail, String password) {
    	this.l = l;
    	this.h = h;
    	this.i = i;
    	this.mail = mail;
    	this.password = password;
        setSize(w, he);
        setLocationRelativeTo(null);
        setResizable(false);
    	setTitle("Login Form");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayoutManager();
        setLocationAndSize();
        addComponentsToContainer();
        addActionEvent();

    }

    public void setLayoutManager() {
        container.setLayout(null);
    }

    public void setLocationAndSize() {
        userLabel.setBounds(50, 150, 100, 30);
        passwordLabel.setBounds(50, 220, 100, 30);
        mailTextField.setBounds(150, 150, 150, 30);
        passwordField.setBounds(150, 220, 150, 30);
        showPassword.setBounds(150, 250, 150, 30);
        loginButton.setBounds(50, 300, 100, 30);
        resetButton.setBounds(200, 300, 100, 30);
        backButton.setBounds((w-100)/2, 350, 100, 30);
        

    }

    public void addComponentsToContainer() {
        container.add(userLabel);
        container.add(passwordLabel);
        container.add(mailTextField);
        container.add(passwordField);
        container.add(showPassword);
        container.add(loginButton);
        container.add(resetButton);
        container.add(backButton);
    }

    public void addActionEvent() {
        loginButton.addActionListener(this);
        resetButton.addActionListener(this);
        showPassword.addActionListener(this);
        backButton.addActionListener(this);
    }


    @SuppressWarnings("deprecation")
	@Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == loginButton) {
            mail = mailTextField.getText();
            password = passwordField.getText();
            System.out.println("  #  "+password);
            if (l.login(i,mail,password)) {
                JOptionPane.showMessageDialog(this, "Login Successful");
                this.dispose();
        		new HomeW(h.getUser(l.getToken()),l,h);
            } else {
                JOptionPane.showMessageDialog(this, "Invalid Username or Password");
            }
        }
        if (e.getSource() == resetButton) {
            mailTextField.setText("");
            passwordField.setText("");
        }
        if (e.getSource() == showPassword) {
            if (showPassword.isSelected()) {
                passwordField.setEchoChar((char) 0);
            } else {
                passwordField.setEchoChar('*');
            }
        }        
        if (e.getSource() == backButton) {
			this.dispose();
			(new WelcomeW(l,h,mail, password)).setVisible(true);
        }
    }
}