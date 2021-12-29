package es.deusto.ingenieria.sd.strava.client.gui.GraphicalWindows;

import javax.swing.*;

import es.deusto.ingenieria.sd.strava.client.gui.HomeWindow;
import es.deusto.ingenieria.sd.strava.client.gui.LoginDialog;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class WelcomeW extends JFrame implements ActionListener {

	Container container = getContentPane();
	JLabel welcomeLabel = new JLabel("Welcome to Strava");
	JButton mailButton = new JButton("MAIL LOGIN");
	JButton googleButton = new JButton("GOOGLE LOGIN");
	JButton metaButton = new JButton("META LOGIN");
	JButton registerMailButton = new JButton("MAIL REGISTER");
	JButton registerMetaButton = new JButton("META REGISTER");
	JButton registerGoogleButton = new JButton("GOOGLE REGISTER");

	int w=370;
	int he=600;
	
	private LoginDialog l;
	private HomeWindow h;
	private String mail;
	private String password;
	
    public WelcomeW(LoginDialog l,HomeWindow h,String mail, String password) {
    	this.l = l;
    	this.h = h;
    	this.mail = mail;
    	this.password = password;
    	setSize(w, he);
		setLocationRelativeTo(null);
		setResizable(false);
		setLayout(null);
		setTitle("Login Form");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationAndSize();
		addComponentsToContainer();
		addActionEvent();
		setVisible(true);
	}

	public void setLocationAndSize() {
		welcomeLabel.setBounds((w-130)/2, 25, 130, 30);
		mailButton.setBounds((w-130)/2,50,130,30);
		googleButton.setBounds((w-130)/2,75,130,30);
		metaButton.setBounds((w-130)/2,100,130,30);
		registerMailButton.setBounds((w-130)/2,125,130,30);
		registerGoogleButton.setBounds((w-130)/2,150,130,30);
		registerMetaButton.setBounds((w-130)/2,175,130,30);
	}

	public void addComponentsToContainer() {
		container.add(welcomeLabel);
		container.add(mailButton);
		container.add(googleButton);
		container.add(metaButton);
		container.add(registerMailButton);
		container.add(registerMetaButton);
		container.add(registerGoogleButton);
	}

	public void addActionEvent() {
		mailButton.addActionListener(this);
		googleButton.addActionListener(this);
		metaButton.addActionListener(this);
		registerMailButton.addActionListener(this);
		registerMetaButton.addActionListener(this);
		registerGoogleButton.addActionListener(this);
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == mailButton) {
			this.dispose();
			(new LoginW(1,l,h,mail,password)).setVisible(true);
		}
		if (e.getSource() == googleButton) {
			this.dispose();
			(new LoginW(2,l,h,mail,password)).setVisible(true);
		}
		if (e.getSource() == metaButton) {
			this.dispose();
			(new LoginW(3,l,h,mail,password)).setVisible(true);
		}
		if (e.getSource() == registerMailButton) {
			this.dispose();
			new RegisterW(l,h);
		}
		if (e.getSource() == registerGoogleButton) {
			this.dispose();
			new CheckW(2,l,h,mail,password);
		}
		if (e.getSource() == registerMetaButton) {
			this.dispose();
			new CheckW(3,l,h,mail,password);
		}
	}
}