package windows;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import MkNotepad.JPanelConFondo;
import people.Worker;
import program.ContainingClass;
import program.MkPsycho;
import usefullClasses.JLabelGrafico;

public class AddWorker extends JFrame{

	public static final long serialVersionUID = 1L;

	public JCheckBox client;
	public JCheckBox patient;
	public ButtonGroup radioB;
	public ButtonGroup cbox;
	public JLabelGrafico icon;
	public final int width = 500;
	public final int height = 130;
	public String background = "/media/BackgroundMini.jpg";
	public String backgroundMini = "/media/BackgroundLittle.png";
	public static String micon1 = "/miniIcons/icon1.png";
	public static String micon2 = "/miniIcons/icon2.png";
	public static String micon3 = "/miniIcons/icon3.png";
	public static String micon4 = "/miniIcons/icon4.png";
	public static String micon5 = "/miniIcons/icon5.png";
	public static String micon6 = "/miniIcons/icon6.png";
	public static String micon7 = "/miniIcons/icon7.png";
	public static String micon8 = "/miniIcons/icon8.png";
	public static String micon9 = "/miniIcons/icon9.png";
	public static JPanel boxP;
	public static Dimension screenSize;
	public static JPanel panel;
	public static JButton accept;
	public static JButton save;
	public static JButton exit;
	public static String iconString;
	public static Image img;
	public static String picture;
	public static JTextField name;
	public static JTextField surname;
	public static JTextField officenumber;
	public static JTextField passwordl;
	public static JTextField email;
	public static JLabel error;
	JButton person;

	public static void main(String[] args) {
		AddWorker aw = new AddWorker(MkPsycho.ContainingClass);
		aw.setVisible(true);
	}

	public AddWorker(ContainingClass ContainingClass) {
		setDefaultCloseOperation( JFrame.DISPOSE_ON_CLOSE );
		setSize( width, height );
		setUndecorated(true);
		screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		setSize(600,350);
		setLocation( screenSize.width/2 - 600/2, screenSize.height/2 - 500/2);  
		panel = new JPanelConFondo(background);
		add(panel);
		panel.setLayout(null);

//		String name, String surname, int officeNumber, String email,String password, String image, String button
		
		picture = "/icons/generic.png";
		icon = new JLabelGrafico(picture,112,128);
		icon.setBounds(60, 30, 112, 128);
		panel.add(icon);

		error = new JLabel("");
		error.setOpaque(false);
		error.setForeground(Color.WHITE);
		error.setBounds(325, 1, 290, 20);
		panel.add(error);

		JLabel na = new JLabel("Name:");
		na.setBounds(230, 50, 50, 20);
		na.setOpaque(false);
		panel.add(na);
		name = new JTextField();
		name.setBounds(300, 50, 100, 20);
		panel.add(name);

		JLabel su = new JLabel("Surname:");
		su.setBounds(230, 150 - 50, 80, 20);
		su.setOpaque(false);
		panel.add(su);
		surname = new JTextField();
		surname.setBounds(300, 150 - 50, 100, 20);
		panel.add(surname);

		JLabel id = new JLabel("Office number:");
		id.setBounds(410, 100 - 50, 100, 20);
		id.setOpaque(false);
		panel.add(id);
		officenumber = new JTextField();
		officenumber.setBounds(530, 100 - 50, 30, 20);
		officenumber.setHorizontalAlignment(JTextField.CENTER);
		
		panel.add(officenumber);
		
		JLabel password = new JLabel("Password:");
		password.setBounds(410, 100 , 80, 20);
		password.setOpaque(false);
		panel.add(password);
		passwordl = new JTextField();
		passwordl.setBounds(480, 100 , 80, 20);

		panel.add(passwordl);
		

		JLabel em = new JLabel("Email:");
		em.setBounds(230, 200 - 50, 100, 20);
		em.setOpaque(false);
		panel.add(em);
		email = new JTextField();
		email.setBounds(300, 200 - 50, 260, 20);
		panel.add(email);

		boxP = new JPanel();
		BoxLayout boxLayout = new BoxLayout( boxP, BoxLayout.Y_AXIS );
		boxP.setLayout(boxLayout);
		boxP.setOpaque(false);

		String[] icons = {micon1,micon2,micon3,micon4,micon5,micon6,micon7,micon8,micon9};

		for (String i : icons) {
			JButton icon = new JButton();
			try {
				img = ImageIO.read(getClass().getResource(i));
				icon.setIcon(new ImageIcon(img));
			} catch (IOException e) {{}}
			icon.setContentAreaFilled(false);
			icon.setBorderPainted(false);
			icon.setSize(88, 100);
			icon.setText(i);
			Font f = new Font("Arial",Font.PLAIN,0);
			icon.setHorizontalTextPosition(JButton.CENTER);
			icon.setVerticalTextPosition(JButton.BOTTOM);
			icon.setFont(f);
			boxP.setBorder(null);
			boxP.add(icon);

		}
		boxP.setOpaque(false);
		boxP.setLayout(new FlowLayout());
		panel.add(boxP);

		JScrollPane buttonScroll = new JScrollPane(boxP);
		buttonScroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
		buttonScroll.setOpaque(false);
		buttonScroll.setBounds(20, 235 - 40 - 5, 560, 135);
		panel.add( buttonScroll );

		save = new JButton("Save");
		save.setBounds(300-3-75, 350-2-20, 75, 20);
		panel.add(save);

		exit = new JButton("Exit");
		exit.setBounds(300+3, 350-2-20, 75, 20);
		panel.add(exit);

		//ACTION LISTENERS

		for(Component boton : boxP.getComponents()) {
			JButton b = (JButton) boton;

			b.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					icon.setImagen(b.getText());
					iconString = ""+b.getText().charAt(15);
				}
			});
		}
		save.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					String finalName = name.getText(); 
					String finalSurname = surname.getText();
					int finalOfficenumber = Integer.parseInt(officenumber.getText()); 
					String finalEmail = email.getText();
					String finalPassword = passwordl.getText();
					String finalIcon = "/icons/icon" + iconString + ".png";
					String finalButton = "/buttons/Button" + iconString + ".png";
					Worker newWorker = new Worker(finalName,finalSurname,finalOfficenumber,finalEmail,finalPassword,finalIcon,finalButton);
					MkPsycho.ContainingClass.getWorkers().put(finalSurname, newWorker);
					dispose();
					System.out.println(newWorker);
					JOptionPane.showMessageDialog(null, "Done!");
					ContainingClass.writeData(MkPsycho.savedData);
				}catch(Exception exc){
					error.setText("The office number must be an integer number!");
					System.out.println("error");
					exc.printStackTrace();

				}
			}


		});

		exit.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();

			}
		});

	}	
}

