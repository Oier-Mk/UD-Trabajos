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
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import MkNotepad.JPanelConFondo;
import people.Client;
import people.Patient;
import program.ContainingClass;
import program.MkPsycho;
import usefullClasses.JLabelGrafico;

public class AddByWorker extends JFrame{

	private static final long serialVersionUID = 1L;

	//	public static void main(String[] args) {
	//		ContainingClass = new ContainingClass();
	//		ContainingClass.getWorkers().put("worker", new Worker("adfad", "adfad", 1, "adfad", "adfad", "adfad", "adfad"));
	//		ContainingClass.getWorkers().put("wrker", new Worker("adfad", "adfad", 1, "adfad", "adfad", "adfad", "adfad"));
	//		AddByWorker w = new AddByWorker(ContainingClass);
	//		w.setVisible(true);
	//	}

	private JCheckBox client;
	private JCheckBox patient;
	private ButtonGroup radioB;
	private ButtonGroup cbox;
	private JLabelGrafico icon;
	private final int width = 500;
	private final int height = 130;
	private String background = "/media/BackgroundMini.jpg";
	private String backgroundMini = "/media/BackgroundLittle.png";
	private static String micon1 = "/miniIcons/icon1.png";
	private static String micon2 = "/miniIcons/icon2.png";
	private static String micon3 = "/miniIcons/icon3.png";
	private static String micon4 = "/miniIcons/icon4.png";
	private static String micon5 = "/miniIcons/icon5.png";
	private static String micon6 = "/miniIcons/icon6.png";
	private static String micon7 = "/miniIcons/icon7.png";
	private static String micon8 = "/miniIcons/icon8.png";
	private static String micon9 = "/miniIcons/icon9.png";
	private static JPanel boxP;
	private static Dimension screenSize;
	private static JPanel panel;
	private static JButton accept;
	private static JButton save;
	private static JButton exit;
	private static String iconString;
	private static Image img;
	private static String picture;
	private static JTextField name;
	private static JTextField surname;
	private static JTextField patientid;
	private static JTextField email;
	private static JTextField enterprise;
	private static JLabel error;
	private JCheckBox interview;
	private JCheckBox conference;
	@SuppressWarnings("unused")
	private static ContainingClass ContainingClass;
	JButton person;


	public AddByWorker(ContainingClass ContainingClass) {
		setDefaultCloseOperation( JFrame.DISPOSE_ON_CLOSE );
		setSize( width, height );
		setUndecorated(true);
		JPanel bg = new JPanelConFondo(backgroundMini);
		screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		setLocation( screenSize.width/2 - width/2, screenSize.height/2 - height/2);  
		add(bg);


		panel = new JPanelConFondo(background);
		panel.setOpaque(false);
		panel.setLayout( null );



		client = new JCheckBox( "Client" );
		client.setBounds(250-85, 50, 75, 20);
		client.setOpaque(false);
		client.setBorderPainted(false);
		client.setFont(new Font("Arial",Font.PLAIN,20));
		panel.add(client);

		patient = new JCheckBox( "Patient" );
		patient.setBounds(250+100-105, 50, 88, 20);
		patient.setOpaque(false);
		patient.setBorderPainted(false);
		patient.setFont(new Font("Arial",Font.PLAIN,20));
		panel.add(patient);

		radioB = new ButtonGroup();
		radioB.add(patient); radioB.add(client);	

		JLabel title = new JLabel( "What kind of person do you want to select?" );
		title.setFont(new Font("Arial",Font.PLAIN,20));
		title.setBounds(60, 10, 395, 20);
		panel.add(title);

		add( panel );


		accept = new JButton("Accept");
		accept.setBounds(200, 90, 100, 30);
		panel.add(accept);

		accept.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if(client.isSelected()) {
					System.out.println("client is selected");
					clientWindow();
				}

				if(patient.isSelected()) {
					System.out.println("patient is selected");
					paritentWindow();
				}
			}


		});	


	}


	public void paritentWindow() {

		setSize(600,350);
		setLocation( screenSize.width/2 - 600/2, screenSize.height/2 - 500/2);  
		remove(panel);
		remove(accept);
		panel = new JPanelConFondo(background);
		add(panel);
		panel.setLayout(null);

		//				int id, String name, String surname, String email, String image, String button
		picture = "/icons/generic.png";
		icon = new JLabelGrafico(picture,112,128);
		icon.setBounds(60, 30, 112, 128);
		panel.add(icon);

		error = new JLabel("");
		error.setOpaque(false);
		error.setForeground(Color.WHITE);
		error.setBounds(395, 1, 200, 20);
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

		JLabel id = new JLabel("Id:");
		id.setBounds(490, 100 - 50, 100, 20);
		id.setOpaque(false);
		panel.add(id);
		patientid = new JTextField();
		patientid.setText((MkPsycho.ContainingClass.getPatients().size()+1)+"");
		patientid.setHorizontalAlignment(patientid.getX()/2);
		patientid.setBounds(530, 100 - 50, 30, 20);
		patientid.setFocusable(false);

		panel.add(patientid);

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
					System.out.println(b.getText());
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
					int finalId = Integer.parseInt(patientid.getText()); 
					String finalEmail = email.getText();
					String finalIcon = "/icons/icon" + iconString + ".png";
					String finalButton = "/buttons/Button" + iconString + ".png";
					System.out.println(finalName + finalSurname + finalId + finalEmail + finalIcon + finalButton);
					Patient newPatient = new Patient(finalId,finalName,finalSurname,finalEmail,finalIcon,finalButton);
					MkPsycho.ContainingClass.getPatients().put(finalId, newPatient);
					MkPsycho.windowWorker.getWorkersArray().add(newPatient);
					dispose();
					MkPsycho.boxP.removeAll();
					for(int i=0;i<MkPsycho.windowWorker.getWorkersArray().size();i++) {
						Image img;
						person = new JButton(MkPsycho.windowWorker.getWorkersArray().get(i).getName());
						person.setHorizontalTextPosition(JButton.CENTER);
						person.setVerticalTextPosition(JButton.CENTER);
						person.setFont(new Font("Arial", Font.PLAIN, 25));
						person.setName(i+"");
						try {
							img = ImageIO.read(getClass().getResource(MkPsycho.windowWorker.getWorkersArray().get(i).getButton()));
							person.setIcon(new ImageIcon(img));
						} catch (IOException exc) {{}}
						person.setContentAreaFilled(false);
						person.setBorderPainted(false);
						MkPsycho.boxP.setBorder(null);
						MkPsycho.boxP.setUI(null);
						MkPsycho.boxP.add(person);
					}	
					MkPsycho.refreshActionListenersWestPanel();
					MkPsycho.boxP.repaint();
					repaint();
					revalidate();
				}catch(Exception exc){
					error.setText("The ID must be an integer number!");
					System.out.println("error");
					exc.printStackTrace();

				}
			}


		});
		
		exit.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("dispose");
				dispose();

			}
		});
		patientid.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent arg0) {
				patientid.setFocusable(true);
				patientid.setEditable(true);								
			}
		});

	}	


	public void clientWindow() {

		setSize(600,350);
		setLocation( screenSize.width/2 - 600/2, screenSize.height/2 - 500/2);  
		remove(panel);
		remove(accept);
		panel = new JPanelConFondo(background);
		add(panel);
		panel.setLayout(null);

		//				String name, String surname,String email, String enterprise,String activity, String image, String button
		picture = "/icons/generic.png";
		icon = new JLabelGrafico(picture,112,128);
		icon.setBounds(60, 30, 112, 128);
		panel.add(icon);



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

		JLabel em = new JLabel("Email:");
		em.setBounds(230, 200 - 50, 100, 20);
		em.setOpaque(false);
		panel.add(em);
		email = new JTextField();
		email.setBounds(300, 200 - 50, 260, 20);
		panel.add(email);

		JLabel en = new JLabel("Enterprise:");
		en.setBounds(430, 50, 100, 20);
		en.setOpaque(false);
		panel.add(en);
		enterprise = new JTextField();
		enterprise.setBounds(430, 75, 150, 20);
		panel.add(enterprise);

		boxP = new JPanel();
		BoxLayout boxLayout = new BoxLayout( boxP, BoxLayout.Y_AXIS );
		boxP.setLayout(boxLayout);
		boxP.setOpaque(false);

		interview = new JCheckBox("Interview");
		interview.setBounds(420, 115, 80, 20);
		interview.setOpaque(false);
		panel.add(interview);

		conference = new JCheckBox("Conference");
		conference.setBounds(500, 115, 95, 20);	
		conference.setOpaque(false);
		panel.add(conference);

		cbox = new ButtonGroup();
		cbox.add(conference); cbox.add(interview);	

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
					System.out.println(b.getText());
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
					String finalEmail = email.getText();
					String finalEnterprise = enterprise.getText();
					String finalActivity = "";
					if (conference.isSelected()) finalActivity = "Conference";
					if (interview.isSelected()) finalActivity = "Interview";
					String finalIcon = "/icons/icon" + iconString + ".png";
					String finalButton = "/buttons/Button" + iconString + ".png";
					Client newClient = new Client(finalName, finalSurname, finalEmail, finalEnterprise, finalActivity, finalIcon, finalButton);
					MkPsycho.ContainingClass.getClients().put(finalSurname, newClient);
					System.out.println(finalName + finalSurname + finalEmail + finalEnterprise + finalActivity + finalIcon + finalButton);
					MkPsycho.windowWorker.getWorkersArray().add(newClient);	
					MkPsycho.refreshActionListenersWestPanel();
					MkPsycho.boxP.removeAll();
					for(int i=0;i<MkPsycho.windowWorker.getWorkersArray().size();i++) {
						Image img;
						person = new JButton(MkPsycho.windowWorker.getWorkersArray().get(i).getName());
						person.setHorizontalTextPosition(JButton.CENTER);
						person.setVerticalTextPosition(JButton.CENTER);
						person.setFont(new Font("Arial", Font.PLAIN, 25));
						person.setName(i+"");
						try {
							img = ImageIO.read(getClass().getResource(MkPsycho.windowWorker.getWorkersArray().get(i).getButton()));
							person.setIcon(new ImageIcon(img));
						} catch (IOException exc) {{}}
						person.setContentAreaFilled(false);
						person.setBorderPainted(false);
						MkPsycho.boxP.setBorder(null);
						MkPsycho.boxP.setUI(null);
						MkPsycho.boxP.add(person);
					}	
					MkPsycho.refreshActionListenersWestPanel();
					MkPsycho.boxP.repaint();
					repaint();
					revalidate();
					dispose();
					repaint();
					revalidate();
				}catch(Exception exc){
					System.out.println("error");
				}
			}


		});
		exit.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("dispose");
				dispose();
			}
		});


	}
}