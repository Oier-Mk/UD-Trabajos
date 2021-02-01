package program;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.beans.PropertyChangeListener;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Array;
import java.sql.Date;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.TemporalAccessor;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;
import java.util.TreeSet;

import javax.imageio.ImageIO;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.RepaintManager;
import javax.swing.plaf.basic.BasicScrollBarUI;

import MkNotepad.JPanelConFondo;
import MkNotepad.MkNotepad;
import appointments.Appointment;
import appointments.Day;
import people.Client;
import people.Patient;
import people.Person;
import people.Worker;
import usefullClasses.BJPanel;
import usefullClasses.Boton;
import usefullClasses.JLabelGrafico;
import usefullClasses.VentanaGrafica;
import windows.AddAppointment;
import windows.AddByWorker;
import windows.AddWorker;

@SuppressWarnings("unused")
public class MkPsycho extends JFrame{

	private static final long serialVersionUID = 1L;

	//	MEDIA GENERAL 

	public static String background = "/media/Background.jpg"; //1136 × 936
	public static String mkpe = "/media/MK.P.E.png"; 
	public static String mk = "/media/mk.png";
	public static int mkX = 467;
	public static int mkY = 274;
	public static double mkZoom = 0.25;
	public static String mkPe = "/media/P edition.png";
	public static int mkPeX = 951;
	public static int mkPeY = 189;
	public static double mkPeZoom = 0.25;
	public static String postit = "/media/PostIt.png";
	public static String box = "/media/Box.png";
	public static String addUser = "/media/AddUser.png";
	public static String deleteUser = "/media/DeleteUser.png";
	public final static String rightButton = "/media/rightArrow.png";
	public final static String leftButton = "/media/leftArrow.png";


	//  ICONOS
	public final static double iZoom = 0.5;
	public static int iconX = (int)(112*iZoom);
	public static int iconY = (int)(128*iZoom);
	public static String icon1 = "/icons/icon1.png";
	public static String icon2 = "/icons/icon2.png";
	public static String icon3 = "/icons/icon3.png";
	public static String icon4 = "/icons/icon4.png";
	public static String icon5 = "/icons/icon5.png";
	public static String icon6 = "/icons/icon6.png";
	public static String icon7 = "/icons/icon7.png";
	public static String icon8 = "/icons/icon8.png";
	public static String icon9 = "/icons/icon9.png";
	public static String User = "/icons/generic.png";
	public static String adminIcon = "/icons/adminIcon.png";
	public static String moreOIcon = "/icons/moreOptions.png";
	public static String addIcon = "/icons/add.png";
	public static String removeIcon = "/icons/remove.png";
	public static String notepad = "/media/notepad.png";




	//	BOTONES

	public static int buttonX = (int)(271);
	public static int buttonY = (int)(67);
	public static String button1 = "/buttons/button1.png";
	public static String button2 = "/buttons/button2.png";
	public static String button3 = "/buttons/button3.png";
	public static String button4 = "/buttons/button4.png";
	public static String button5 = "/buttons/button5.png";
	public static String button6 = "/buttons/button6.png";
	public static String button7 = "/buttons/button7.png";
	public static String button8 = "/buttons/button8.png";
	public static String button9 = "/buttons/button9.png";

	//	FUENTE A USAR EN EL PROGRAMA

	public static Font font = new Font("Arial", Font.PLAIN, 20);

	//	VARIABLES USADAS MÁS ADELANTE

	public static boolean accepted = false;

	public static int quantityOfPasswords = 3;



	//	MEDIDAS DE LAS "CAJAS" TRANSPARENTES QUE TOMAN LA POSICIÓN DE LAS IMÁGENES	

	/**
	 *  GRACIAS A SWING PODRE PRESCINDIR DE ESTE LIO DE NUMEROS
	 */

	public final static int widthP = 450;
	public final static int heightP = 300;

	public final static int width = 1130;
	public final static int height = 625;

	public final static int Margen_supPostit = 80;
	public final static int Margen_izqPostit = 250;

	public final static int Margen_sup = 100;
	public final static int Margen_izq = 30;
	public final static int Margen_inf = 30;
	public final static int Margen_dch = 30;

	public final static int Margen_dchMK = 30;
	public final static int Margen_supMK = 45;
	public final static int Margen_izqMK = (int)(1130 - 30 - 467*0.70);
	public final static int Margen_infMK = (int)(625 - 30 - 274*0.70);

	public final static int Margen_izqPE = 30;
	public final static int Margen_supPE = 30;
	public final static int Margen_dchPE = (int)(1130 - 30 - 467*0.70 - 100 - 951*0.5);
	public final static int Margen_infPE = (int)(625 - 30 - 189*0.5);

	public final static int Margen_izqMO = 30;
	public final static int Margen_supMO = 30;
	public final static int Margen_dchMO = (int)(1130 - 30 - 467*0.70 - 100 - 951*0.5);
	public final static int Margen_infMO = (int)(625 - 30 - 189*0.5);

	//	BOTONES

	public final static double remove_addZoom = 0.5;
	public final static int remove_addX = (int)(496*remove_addZoom);
	public final static int remove_addY = (int)(111*remove_addZoom);

	public final static double moreZoom = 0.05;
	public final static int moreX = (int)(375*moreZoom);
	public final static int moreY = (int)(376*moreZoom);

	public final static double addZoom = 0.05;
	public final static int addX = (int)(422*moreZoom);
	public final static int addY = (int)(421*moreZoom);

	public final static double removeZoom = 0.05;
	public final static int removeX = (int)(422*moreZoom);
	public final static int removeY = (int)(421*moreZoom);

	public final static boolean shouldFill = true;
	public final static boolean shouldWeightX = true;
	public final static boolean RIGHT_TO_LEFT = false;



	// NOMBRE DEL ARCHIVO

	public static String savedData = "src/SaveFiles/SaveFile.dat";

	public static ContainingClass ContainingClass ;
	public static AddAppointment addA;
	public static MkNotepad notePad ;
	public static MkPsycho MkPsycho ;

	public static String fileName ="src/MkNotepad/notepadText.txt";
	public static JScrollPane textBoxS;
	public static JTextArea textBox;
	public static String date = "";
	public static String str = "";


	//		CREACION A LO BESTIA DE PERSONAS Y CITAS

	public static Worker Admin;
	public static Worker Worker1;
	public static Worker Worker2;
	public static Worker Worker3;
	public static Client Client1;
	public static Client Client2;
	public static Client Client3;
	public static Patient Patient1;
	public static Patient Patient2;
	public static Patient Patient3;
	public static Appointment Appointment1;
	public static Appointment Appointment2;
	public static Appointment Appointment3;
	public static Worker windowWorker;
	public static JPanel backgroundP;
	public static JPanel day1;
	public static JPanel day2;
	public static JPanel day3;
	public static JPanel day4;
	public static JPanel day5;
	public static JPanel westLayout;
	public static JPanel hours;
	public static JPanel postitP;
	public static JPanel postitCenter;
	public static JPanel northLayout;
	public static JPanel boxP;
	public static JButton rightB;
	public static JButton leftB;
	public static JLabel actualDayL;
	public static Date systemDate;
	public static Date actualDay;
	public static int dayWidth;
	public static int hourHeight;
	public static JLabel monday;
	public static JLabel tuesday;
	public static JLabel wednesday;
	public static JLabel thursday;
	public static JLabel friday;
	public static JLabel nothing;
	public static JPanel day1center;
	public static JPanel day2center;
	public static JPanel day3center;
	public static JPanel day4center;
	public static JPanel day5center;
	public static JPanel centralHours;
	public static JLabel hour0830;
	public static JLabel hour0930;
	public static JLabel hour1030;
	public static JLabel hour1130;
	public static JLabel hour1230;
	public static JLabel hour1330;
	public static JLabel hour1430;
	public static JLabel hour1530;
	public static JLabel hour1630;
	public static JLabel hour1730;
	public static JLabel hour1830;
	public static JLabel hour1930;
	public static JLabel hour2030;
	public static JLabel hour2130;
	public static Thread resize;
	public static Thread assignAppointments;
	public static int dayNum;


	//											MAIN,  COMIENZO DE PROGRAMA


	@SuppressWarnings("deprecation")
	public static void main(String[] args) throws InterruptedException {

		ContainingClass = new ContainingClass();

		File data = new File(savedData);

		if (data.exists()) {
			ContainingClass.readData(savedData);
			Long today = System.currentTimeMillis();
			int daysPerYear = 365;
			if (new Date(today).getYear()%4 == 0) daysPerYear = 366;
			Long day = (long) (24*3600*1000);	


		}
		else {

			//		CREACION A LO BESTIA DE PERSONAS Y CITAS
			Admin = new Worker("Admin", "", 00,"admin@mkpsycho.com", "admin",adminIcon, adminIcon);
			Worker1 = new Worker("Iker", "Yañez", 101, "ikeryañez@mkpsycho.com", "YañezI",icon5, button5);
			Worker2 = new Worker("Iñigo", "Aldayturriaga", 103, "ialdaytu@mkpsycho.com", "AldaI",icon6, button6);
			Worker3 = new Worker("Jon", "Zalba", 102, "zalba@mkpsycho.com", "JON",icon7, button7);
			Client1 = new Client( "Oier", "Mentxaka", "oiermentxaka@mk.com","mk","conference",icon7, button7);
			Client2 = new Client( "Jonathan", "Barroeta", "jonatanbarroeta@deia.eus","deia","interview",icon2, button2);
			Client3 = new Client("Danel","Arias","darias@opendeusto.es","Universidad de Deusto","Interview",icon4,button4);
			Patient1 = new Patient(1, "Ale", "Arche", "alearche@gmail.com",icon3, button3);
			Patient2 = new Patient(2, "Iñigo", "Dios", "iñigodedios@gmail.com",icon9, button9);
			Patient3 = new Patient(3, "Aida", "Gomezbueno", "aidagomezbueno@gmail.com",icon1, button1);
			Worker3.getWorkersArray().add(Client1);
			Worker3.getWorkersArray().add(Client2);
			Worker3.getWorkersArray().add(Client3);
			Worker3.getWorkersArray().add(Patient1);
			Worker3.getWorkersArray().add(Patient2);
			Worker3.getWorkersArray().add(Patient3);

			//		AÑADIR A CLASE CONTENEDORA TODOS LOS DATOS CREADOS A MANO

			ContainingClass.putWorker(Admin);
			ContainingClass.putWorker(Worker1);
			ContainingClass.putWorker(Worker2);
			ContainingClass.putWorker(Worker3);
			ContainingClass.putClient(Client1);
			ContainingClass.putClient(Client2);
			ContainingClass.putClient(Client3);
			ContainingClass.putPatient(Patient1);
			ContainingClass.putPatient(Patient2);
			ContainingClass.putPatient(Patient3);

		}
		//		CREACIÓN DE VENTANA INICIAL CON UNA VENTANA GRÁFICA

		logoWindow();

		//		CREACIÓN DE VENTANA DE IDENTIFICACION DE TRABAJADOR

		identificationWindow();

		//		VENTANA PRINCIPAL DE USUARIO

		//			userWindow();

		//		PRINT DE TODOS LOS TREEMAPS PARA COMPROBAR QUE TOD0 FUNCIONA CORRECTAMENTE. 

		treeMapCheck();

		//		GUARDADO DE TODOS LOS USUARIOS 

		try{MkPsycho.dispose();addA.dispose();}catch(Exception e) {};
		ContainingClass.writeData(savedData);
		System.out.println("Fin del main");

	}



	//										METODOS Y FUNCIONES


	/**
	 * @param PRINTS ALL  THE TREEMAPS OF THE PROGRAM
	 */
	private static void treeMapCheck() {

		System.out.println(ContainingClass.toStrPatients());

		System.out.println(ContainingClass.toStrWorkers());

		System.out.println(ContainingClass.toStrClients());

		for (Map.Entry<String,Worker> entry : ContainingClass.getWorkers().entrySet()) {
			Worker w = entry.getValue();
			System.out.println(w.getCalendar().toString());
		}



	}


	/**
	 * MANAGES THE IDENTIFICATION WINDOW
	 */
	private static void identificationWindow() {

		VentanaGrafica vIdentificacion = new VentanaGrafica(widthP, heightP, "");
		vIdentificacion.ventana.setResizable(false);
		vIdentificacion.dibujaImagen(background, widthP/2,heightP/2 ,1, 0, 1.0f);
		vIdentificacion.dibujaTexto((double)(widthP * 1/4 - 20 ), (double)(heightP/2 + 30 + 128/2),  "User" , font,  java.awt.Color.black);
		vIdentificacion.dibujaTexto((double)(widthP * 3/4 - 30 ), (double)(heightP/2 + 30 + 128/2), ContainingClass.getWorkers().get("").getName() , font,  java.awt.Color.black);



		//		SELECCIÓN DE USUARIO

		Boton user = new Boton(widthP * 1/4 ,heightP/2,112,128,vIdentificacion,User);
		user.dibujar(vIdentificacion);

		Boton admin = new Boton(widthP * 3/4 ,heightP/2,112,128,vIdentificacion,adminIcon);
		admin.dibujar(vIdentificacion);

		boolean closed = false;

		while (!closed) {

			adminSelected(admin,vIdentificacion);
			userSelected(user,vIdentificacion);

			if (vIdentificacion.estaCerrada()) {
				closed = true; 
				vIdentificacion.acaba();
			}
		}	

		vIdentificacion.acaba();
	}


	private static void adminSelected(Boton admin, VentanaGrafica vIdentificacion) {

		if (admin.estaPulsado(vIdentificacion)) {
			vIdentificacion.espera(100);
			try {
				int tries = 1;

				Worker Admin = ContainingClass.getWorkers().get("");
				String password = getPasswordAdmin();
				String AdminPassword = Admin.getPassword();

				while (tries <= quantityOfPasswords ) {

					if (AdminPassword.contentEquals(password)){
						administrate(); //FUNCION
						break;
					}
					else {
						tries ++;
						if(tries<=quantityOfPasswords) {
							password =  getPasswordAdmin();
						}
						else {
							String warning = "ACCESS DENIED\nYou failed to enter your password " + (tries-1) + " times";
							JOptionPane.showMessageDialog(null, warning);
							System.exit(0);
						}
					}

				}
			}catch(NullPointerException e) {
				{}
			}
		}

	}
	@SuppressWarnings("deprecation")
	public static String getPasswordAdmin() {
	    JPasswordField jpf = new JPasswordField(24);
	    JLabel jl = new JLabel("Introduce the administrators password ");
	    Box box = Box.createVerticalBox();
	    box.add(jl);
	    box.add(jpf);

	    int x = JOptionPane.showConfirmDialog(null, box, "", JOptionPane.OK_CANCEL_OPTION);
	    box.addComponentListener(new ComponentAdapter() {
			
			@Override
			public void componentShown(ComponentEvent e) {
			    jpf.requestFocus();
			}

		});
	    if (x == JOptionPane.OK_OPTION) {
	      return jpf.getText();
	      
	    }
	    return null;
	  }

	
	
	/** 	SELECTS THE USER OF THE WINDOW
	 * @param user
	 * @param vIdentificacion
	 */
	private static void userSelected(Boton user, VentanaGrafica vIdentificacion) {
		if (user.estaPulsado(vIdentificacion)) {
			vIdentificacion.espera(100);
			try {
				windowWorker = userLogin( vIdentificacion ); //FUNCION DE IDENTIFICACION
				MkPsycho = new MkPsycho();
				MkPsycho.setVisible(true);
			}
			catch(NullPointerException e) {
				{e.printStackTrace();}	
			}		
		}
	}



	/**
	 * LOGO WINDOW  
	 */
	private static void logoWindow() {
		JFrame vMk = new JFrame();
		vMk.setSize(widthP-50, heightP-50);
		vMk.setUndecorated(true);
		vMk.setOpacity(1);

		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		vMk.setLocation( screenSize.width/2 - (widthP)/2, screenSize.height/2 - (heightP)/2);  
		JLabelGrafico picture = new JLabelGrafico(mk, mkX, mkY);
		picture.setZoom(0.75);
		vMk.add(picture);
		vMk.setVisible(true);
		try {
			Thread.sleep(700);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		vMk.dispose();


	}



	/**
	 * 	CHECKS IF THE PASSWORD IS CORRECT
	 * 	AT THE 3RD ATTEMPT WRONG, THE APP ENDS
	 * @param worker
	 * @param vIdentificacion
	 * @return 
	 * 
	 */
	private static Worker userLogin( VentanaGrafica vIdentificacion) {

		int tries = 1;

		String workerSurname = JOptionPane.showInputDialog ("Introduce your surname to identify yourself ");

		Worker worker;

		while (!ContainingClass.getWorkers().containsKey(workerSurname)) {
			try {
				workerSurname = JOptionPane.showInputDialog ("Introduce your surname to identify yourself ");
			}catch(NullPointerException e) {break;}
		}

		if(workerSurname.contentEquals("")) {
			JOptionPane.showMessageDialog(null, "INVALID ACCESS");
			System.exit(0); 
		}

		worker = ContainingClass.getWorkers().get(workerSurname);

		while (tries <= quantityOfPasswords) {
			String password = getPasswordUser(worker);
			if (!worker.getPassword().contentEquals(password)){
				tries += 1;
			}
			else {
				break;
			}

		}
		if(tries-1 == quantityOfPasswords) {
			String warning = "ACCESS DENIED\nYou failed to enter your password 3 times";

			JOptionPane.showMessageDialog(null, warning);
			System.exit(0); 
		}
		return worker;
	}
	@SuppressWarnings("deprecation")
	public static String getPasswordUser(Worker worker) {
	    JPasswordField jpf = new JPasswordField(24);
	    JLabel jl = new JLabel("Introduce your password " + worker.getName());
	    Box box = Box.createVerticalBox();
	    box.add(jl);
	    box.add(jpf);
	    int x = JOptionPane.showConfirmDialog(null, box, "", JOptionPane.OK_CANCEL_OPTION);

	    if (x == JOptionPane.OK_OPTION) {
	      return jpf.getText();
	      
	    }
	    return null;
	  }




	/**
	 * MANAGES ALL REGARDING TO THE ADMINISTRATORS WINDOW
	 * SHOWING THE BUTTONS AND DIRECTING TO THE CORRESPONDING FUNCTIONS
	 */
	static void administrate() {
		VentanaGrafica vAdmin = new VentanaGrafica(widthP, heightP, "");
		vAdmin.ventana.setResizable(false);
		vAdmin.dibujaImagen(background, widthP/2,heightP/2 ,1, 0, 1.0f);

		Boton add = new Boton(widthP /2 ,heightP * 1/3,remove_addX,remove_addY,vAdmin,addUser);
		add.dibujar(vAdmin);

		Boton remove = new Boton(widthP /2 ,heightP * 2/3,remove_addX,remove_addY,vAdmin,deleteUser);
		remove.dibujar(vAdmin);

		boolean addSelected;
		boolean remSelected;
		while (!vAdmin.estaCerrada()) {
			addSelected=add.estaPulsado(vAdmin);
			remSelected=remove.estaPulsado(vAdmin);
			if (addSelected) {
				vAdmin.espera(100);
				AddWorker aw = new AddWorker(ContainingClass);
				aw.setVisible(true);
				addSelected=false;
			}
			if (remSelected) {
				vAdmin.espera(100);
				remUser(); 
				remSelected=false;
			}
		}

	}



	
	/**
	 * REMOVES A USER OF THE PROGRAMS TREEMAP
	 */
	static void remUser(){
		Worker worker;
		String workerSurname = JOptionPane.showInputDialog ("Introduce the surname of the worker you want to delete: ");

		while (!ContainingClass.getWorkers().containsKey(workerSurname)) {
			workerSurname = JOptionPane.showInputDialog ("Introduce the surname of the worker you want to delete: ");
		}
		worker = ContainingClass.getWorkers().get(workerSurname);
		ContainingClass.removeWorker(worker);
		JOptionPane.showMessageDialog(null, "Done!");
		ContainingClass.writeData(savedData);

	}

	public MkPsycho() {

		// 1.- Configuración de la ventana

		this.setDefaultCloseOperation( JFrame.DISPOSE_ON_CLOSE ); 
		this.getContentPane().revalidate();
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation( screenSize.width/2 - width/2, screenSize.height/2 - height/2);  
		this.setSize(width, height);

		// 2.0- Creación de contenedores (paneles) y componentes

		backgroundP = new BJPanel(background);
		backgroundP.setLayout(new BorderLayout(5,5));
		backgroundP.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
		this.add(backgroundP);

		JLabelGrafico MK = new JLabelGrafico(mk, mkZoom*mkX, mkZoom*mkY);
		JLabelGrafico MKPE = new JLabelGrafico(mkPe, mkPeZoom*mkPeX, mkPeZoom*mkPeY);

		// 2.1- Creación del panel norte

		FlowLayout left = new FlowLayout();
		left.setAlignment(FlowLayout.LEFT);
		FlowLayout right = new FlowLayout();
		left.setAlignment(FlowLayout.RIGHT);

		northLayout = new JPanel();
		northLayout.setOpaque(false);

		JPanel northWest = new JPanel();
		FlowLayout NWL = new FlowLayout();
		northWest.setLayout(NWL);
		NWL.setAlignment(FlowLayout.LEFT);
		northWest.setSize(width/2, mkY + 5);
		northWest.setOpaque(false);
		northWest.setLayout(left);
		northWest.add( MK );
		northWest.add( MKPE );		

		northLayout.add(northWest);

		JPanel northEast = new JPanel();
		FlowLayout NEL = new FlowLayout();
		northEast.setLayout(NEL);
		NEL.setAlignment(FlowLayout.RIGHT);
		northWest.setSize(width/2, mkY + 5);
		northEast.setOpaque(false);
		JButton user = new JButton(windowWorker.getName());
		user.setHorizontalTextPosition(JButton.CENTER);
		user.setVerticalTextPosition(JButton.CENTER);
		user.setFont(new Font("Arial", Font.PLAIN, 25));
		Image userI;
		try {
			userI = ImageIO.read(getClass().getResource(windowWorker.getButton()));
			user.setIcon(new ImageIcon(userI));
			user.setContentAreaFilled(false);
			user.setBorderPainted(false);
			user.setBorder(null);
		} catch (Exception e) {{e.printStackTrace();		System.out.println("There is not printable button");}}
		northEast.add(user);
		JButton notepadB = new JButton();
		Image notepadI;
		try {
			notepadI = ImageIO.read(getClass().getResource(notepad));
			notepadI = notepadI.getScaledInstance(50,50,java.awt.Image.SCALE_SMOOTH);
			notepadB.setIcon(new ImageIcon(notepadI));
			notepadB.setContentAreaFilled(false);
			notepadB.setBorderPainted(false);
			notepadB.setBorder(null);
		} catch (Exception e) {{e.printStackTrace();		System.out.println("There is not printable button");}}
		northEast.add(notepadB);
		northEast.setLayout(right);

		rightB = new JButton();
		Image rightBI;
		try {
			rightBI = ImageIO.read(getClass().getResource(rightButton));
			rightBI = rightBI.getScaledInstance(50, 50, Image.SCALE_DEFAULT);
			rightB.setIcon(new ImageIcon(rightBI));
			rightB.setContentAreaFilled(false);
			rightB.setBorderPainted(false);
			rightB.setBorder(null);
		} catch (Exception e) {{e.printStackTrace();		System.out.println("There is not printable button");}}
		northEast.add(rightB);

		systemDate = new Date(System.currentTimeMillis());
		actualDay = new Date(System.currentTimeMillis());


		@SuppressWarnings("deprecation")
		String number = String.valueOf(actualDay.getDate()+"/"+(actualDay.getMonth()+1));

		actualDayL = new JLabel();
		actualDayL.setText(number);
		actualDayL.setForeground(Color.RED);

		Font f = new Font("Arial", Font.BOLD, 70);
		actualDayL.setFont(f);
		northEast.add(actualDayL);

		leftB = new JButton();
		Image leftBI;
		try {
			leftBI= ImageIO.read(getClass().getResource(leftButton));
			leftBI = leftBI.getScaledInstance(50, 50, Image.SCALE_DEFAULT);
			leftB.setIcon(new ImageIcon(leftBI));
			leftB.setContentAreaFilled(false);
			leftB.setBorderPainted(false);
			leftB.setBorder(null);
		} catch (Exception e) {{e.printStackTrace();		System.out.println("There is not printable button");}}
		northEast.add(leftB);

		northLayout.add(northEast);

		backgroundP.add( northLayout, BorderLayout.NORTH );

		// 2.2- Creación del panel oeste

		westLayout = new JPanel();
		//		westLayout.getBounds().getWidth();
		westLayout.setOpaque(false);
		westLayout.setLayout(new BorderLayout());
		boxP = new JPanel();
		BoxLayout boxLayout = new BoxLayout( boxP, BoxLayout.Y_AXIS );
		boxP.setLayout(boxLayout);
		boxP.setOpaque(false);

		for(int i=0;i<windowWorker.getWorkersArray().size();i++) {
			Image img;
			JButton person = new JButton(windowWorker.getWorkersArray().get(i).getName());
			person.setHorizontalTextPosition(JButton.CENTER);
			person.setVerticalTextPosition(JButton.CENTER);
			person.setFont(new Font("Arial", Font.PLAIN, 25));
			person.setName(i+"");
			try {
				img = ImageIO.read(getClass().getResource(windowWorker.getWorkersArray().get(i).getButton()));
				person.setIcon(new ImageIcon(img));
			} catch (IOException e) {{}}
			person.setContentAreaFilled(false);
			person.setBorderPainted(false);
			boxP.setBorder(null);
			boxP.setUI(null);
			boxP.add(person);
		}		

		JScrollPane buttonScroll = new JScrollPane(boxP);
		buttonScroll.setOpaque(false);
		buttonScroll.setHorizontalScrollBar(null);
		westLayout.add( buttonScroll, BorderLayout.CENTER );

		JButton options = new JButton();
		options.setOpaque(false);
		options.setBorderPainted(false);
		options.setContentAreaFilled(false);
		options.setAlignmentX( CENTER_ALIGNMENT);

		Image optionsI;
		try {
			optionsI = ImageIO.read(getClass().getResource(moreOIcon));
			options.setIcon(new ImageIcon(optionsI));
		} catch (IOException e) {{}}
		westLayout.add(options,BorderLayout.SOUTH);

		backgroundP.add(westLayout, BorderLayout.WEST);

		// 2.3- Panel central con el postit 
		BJPanel postItP = new BJPanel(postit);
		backgroundP.add(postItP, BorderLayout.CENTER);


		// 4.0- Panel del calendario

		Color color1 = new Color(255,255,150);
		Color color2 = new Color(150,255,150);
		Color color3 = new Color(150,255,255);
		Color color4 = new Color(150,150,255);
		Color color5 = new Color(255,150,255);
		Color color6 = new Color(255,150,150);
		
		postitP = new JPanel();
		BorderLayout l = new BorderLayout();
		postitP.setLayout(l);
		postitCenter = new JPanel();
		postitP.setOpaque(false);
		postitCenter.setLayout(new BoxLayout(postitCenter,  BoxLayout.X_AXIS));
		backgroundP.add(postitP,BorderLayout.CENTER);

		day1 = new JPanel();
		day1.setLayout(new BorderLayout());
		day1.setBackground(color2);
		day2 = new JPanel();
		day2.setLayout(new BorderLayout());
		day2.setBackground(color3);
		day3 = new JPanel();
		day3.setLayout(new BorderLayout());
		day3.setBackground(color4);
		day4 = new JPanel();
		day4.setLayout(new BorderLayout());
		day4.setBackground(color5);
		day5 = new JPanel();
		day5.setLayout(new BorderLayout());
		day5.setBackground(color6);

		
		monday = new JLabel("Monday");
		monday.setHorizontalAlignment(JLabel.CENTER);
		tuesday =  new JLabel("Tuesday");
		tuesday.setHorizontalAlignment(JLabel.CENTER);
		wednesday =  new JLabel("Wednesday");
		wednesday.setHorizontalAlignment(JLabel.CENTER);
		thursday =  new JLabel("Thursday");
		thursday.setHorizontalAlignment(JLabel.CENTER);
		friday =  new JLabel("Friday");
		friday.setHorizontalAlignment(JLabel.CENTER);
		nothing = new JLabel("L");
		nothing.setForeground(color1);
		nothing.setHorizontalAlignment(JLabel.CENTER);

		Font g = new Font("Arial", Font.BOLD, 20);
		monday.setFont(g);
		tuesday.setFont(g);
		wednesday.setFont(g);
		thursday.setFont(g);
		friday.setFont(g);
		nothing.setFont(g);

		postitP.setLayout(new BorderLayout());
		postitP.add(postitCenter);
		postitCenter.setLayout(new GridLayout(1,5));
		postitCenter.add(day1);
		day1.add(monday,BorderLayout.NORTH);
		postitCenter.add(day2);
		day2.add(tuesday,BorderLayout.NORTH);
		postitCenter.add(day3);
		day3.add(wednesday,BorderLayout.NORTH);
		postitCenter.add(day4);
		day4.add(thursday,BorderLayout.NORTH);
		postitCenter.add(day5);
		day5.add(friday,BorderLayout.NORTH);

		//PANELES CON LO QUE HAY DENTRO DEL CALENDARIO 

		day1center = new JPanel();
		day1center.setName("day1center");
		day1center.setLayout(null);
		day1center.setOpaque(true);
		day1center.setBackground(color2);
		day1.add(day1center,BorderLayout.CENTER);
		day2center = new JPanel();
		day2center.setName("day2center");
		day2center.setLayout(null);
		day2center.setOpaque(true);
		day2center.setBackground(color3);
		day2.add(day2center, BorderLayout.CENTER);
		day3center = new JPanel();
		day3center.setLayout(null);
		day3center.setName("day3center");
		day3center.setOpaque(true);
		day3center.setBackground(color4);
		day3.add(day3center, BorderLayout.CENTER);
		day4center = new JPanel();
		day4center.setLayout(null);
		day4center.setName("day4center");
		day4center.setOpaque(true);
		day4center.setBackground(color5);
		day4.add(day4center, BorderLayout.CENTER);
		day5center = new JPanel();
		day5center.setLayout(null);
		day5center.setName("day5center");
		day5center.setOpaque(true);
		day5center.setBackground(color6);
		day5.add(day5center, BorderLayout.CENTER);

		hours = new JPanel(); 
		hours.setBackground(color1);
		hours.setPreferredSize(new Dimension(40, northLayout.getY()));
		hours.setLayout(new BorderLayout());
		Font nf = new Font("Arial",Font.BOLD,15);
		hour0830 = new JLabel(" 8:30");
		hour0830.setBounds(1, 5, 40, 30);
		hour0830.setFont(nf);
		hour0930 = new JLabel(" 9:30");
		hour0930.setBounds(1, 5+31*1, 40, 30);
		hour0930.setFont(nf);
		hour1030 = new JLabel("10:30");
		hour1030.setBounds(1, 5+31*2, 40, 30);
		hour1030.setFont(nf);
		hour1130 = new JLabel("11:30");
		hour1130.setBounds(1, 5+31*3, 40, 30);
		hour1130.setFont(nf);
		hour1230 = new JLabel("12:30");
		hour1230.setBounds(1, 5+31*4, 40, 30);
		hour1230.setFont(nf);
		hour1330 = new JLabel("13:30");
		hour1330.setBounds(1, 5+31*5, 40, 30);
		hour1330.setFont(nf);
		hour1430 = new JLabel("14:30");
		hour1430.setBounds(1, 5+31*6, 40, 30);
		hour1430.setFont(nf);
		hour1530 = new JLabel("15:30");
		hour1530.setBounds(1, 5+31*7, 40, 30);
		hour1530.setFont(nf);
		hour1630 = new JLabel("16:30");
		hour1630.setBounds(1, 5+31*8, 40, 30);
		hour1630.setFont(nf);
		hour1730 = new JLabel("17:30");
		hour1730.setBounds(1, 5+31*9, 40, 30);
		hour1730.setFont(nf);
		hour1830 = new JLabel("18:30");
		hour1830.setBounds(1, 5+31*10, 40, 30);
		hour1830.setFont(nf);
		hour1930 = new JLabel("19:30");
		hour1930.setBounds(1, 5+31*11, 40, 30);
		hour1930.setFont(nf);
		hour2030 = new JLabel("20:30");
		hour2030.setBounds(1, 5+31*12, 40, 30);
		hour2030.setFont(nf);
		hour2130 = new JLabel("21:30");
		hour2130.setBounds(1, 5+31*13, 40, 30);
		hour2130.setFont(nf);
		hours.add(nothing,BorderLayout.NORTH);

		centralHours = new JPanel();
		centralHours.setBackground(color1);
		centralHours.setLayout(null);
		centralHours.add(hour0830);
		centralHours.add(hour0930);
		centralHours.add(hour1030);
		centralHours.add(hour1130);
		centralHours.add(hour1230);
		centralHours.add(hour1330);
		centralHours.add(hour1430);
		centralHours.add(hour1530);
		centralHours.add(hour1630);
		centralHours.add(hour1730);
		centralHours.add(hour1830);
		centralHours.add(hour1930);
		centralHours.add(hour2030);
		centralHours.add(hour2130);
		hours.add(centralHours);
		postitP.add(hours,BorderLayout.WEST);

		// 5.0- ActionListeners
		addComponentListener(new ComponentAdapter() {
			@SuppressWarnings("deprecation")
			public void componentResized(ComponentEvent componentEvent) {
				hour0830.setLocation(1,(5*getHeight()/height));
				hour0930.setLocation(1,(5*getHeight()/height+31*getHeight()/height));
				hour1030.setLocation(1,(5*getHeight()/height+31*2*getHeight()/height));
				hour1130.setLocation(1,(5*getHeight()/height+31*3*getHeight()/height));
				hour1230.setLocation(1,(5*getHeight()/height+31*4*getHeight()/height));
				hour1330.setLocation(1,(5*getHeight()/height+31*5*getHeight()/height));
				hour1430.setLocation(1,(5*getHeight()/height+31*6*getHeight()/height));
				hour1530.setLocation(1,(5*getHeight()/height+31*7*getHeight()/height));
				hour1630.setLocation(1,(5*getHeight()/height+31*8*getHeight()/height));
				hour1730.setLocation(1,(5*getHeight()/height+31*9*getHeight()/height));
				hour1830.setLocation(1,(5*getHeight()/height+31*10*getHeight()/height));
				hour1930.setLocation(1,(5*getHeight()/height+31*11*getHeight()/height));
				hour2030.setLocation(1,(5*getHeight()/height+31*12*getHeight()/height));
				hour2130.setLocation(1,(5*getHeight()/height+31*13*getHeight()/height));

				Date dayOfWeek = actualDay;
				if(actualDay.getDay()==0){
					dayOfWeek = new Date(actualDay.getTime() - 6*24*3600000);
				}
				else {
					dayOfWeek = new Date(dayOfWeek.getTime() - (dayOfWeek.getDay()-1)*24*3600000);
				}

				for(Component boton : day1center.getComponents()) {	
					boton.setSize(146*getWidth()/width, boton.getHeight()*getHeight()/height);
					boton.setLocation(0,(int) (Math.round((Integer.parseInt(boton.getName())-510)*31/60))*getHeight()/height);
				}
				for(Component boton : day2center.getComponents()) {	
					boton.setSize(146*getWidth()/width, boton.getHeight()*getHeight()/height);
					boton.setLocation(0,(int) (Math.round((Integer.parseInt(boton.getName())-510)*31/60))*getHeight()/height);
				}
				for(Component boton : day3center.getComponents()) {								
					boton.setSize(146*getWidth()/width, boton.getHeight()*getHeight()/height);
					boton.setLocation(0,(int) (Math.round((Integer.parseInt(boton.getName())-510)*31/60))*getHeight()/height);
				}
				for(Component boton : day4center.getComponents()) {								
					boton.setSize(146*getWidth()/width, boton.getHeight()*getHeight()/height);
					boton.setLocation(0,(int) (Math.round((Integer.parseInt(boton.getName())-510)*31/60))*getHeight()/height);
				}
				for(Component boton : day5center.getComponents()) {								
					boton.setSize(146*getWidth()/width, boton.getHeight()*getHeight()/height);
					boton.setLocation(0,(int) (Math.round((Integer.parseInt(boton.getName())-510)*31/60))*getHeight()/height);
				}
				paintCalendar(actualDay);

			}
			
		});

					//ACTION LISTENERS DE LOS BOTONES DE LA BARRA LATERAL

					refreshActionListenersWestPanel();

					//ACTION LISTENER DEL BOTON DE NOTEPAD

					notepadB.addActionListener(new ActionListener() {

						@Override
						public void actionPerformed(ActionEvent e) {
							fileName = "src/SaveFiles/notePad-" + windowWorker.getName() + windowWorker.getSurname() + ".txt";
							notePad = new MkNotepad(fileName);
							date = MkNotepad.currentTime();
							File data = new File(fileName);
							if (data.exists()) {
								MkNotepad.textBox.setText(MkNotepad.ReadFromTXT(fileName) + "\n" + date + "\n\n");
								MkNotepad.textBox.setSelectionStart( MkNotepad.textBox.getText().length() );
							}
							notePad.setVisible(true);
						}

					});

					//ACTION LISTENER DEL BOTON DE AÑADIR PERSONAS

					options.addActionListener(new ActionListener() {

						@Override
						public void actionPerformed(ActionEvent e) {
							AddByWorker abw = new AddByWorker(ContainingClass);
							abw.setVisible(true);
							boxP.removeAll();
							for(int i=0;i<windowWorker.getWorkersArray().size();i++) {
								Image img;
								JButton person = new JButton(windowWorker.getWorkersArray().get(i).getName());
								person.setHorizontalTextPosition(JButton.CENTER);
								person.setVerticalTextPosition(JButton.CENTER);
								person.setFont(new Font("Arial", Font.PLAIN, 25));
								person.setName(i+"");
								try {
									img = ImageIO.read(getClass().getResource(windowWorker.getWorkersArray().get(i).getButton()));
									person.setIcon(new ImageIcon(img));
								} catch (IOException exc) {{}}
								person.setContentAreaFilled(false);
								person.setBorderPainted(false);
								boxP.setBorder(null);
								boxP.setUI(null);
								boxP.add(person);
							}	
							boxP.repaint();
							boxP.revalidate();
							repaint();
							revalidate();
						}
					});


					//ACTION LISTENER DEL BOTON DE LA DERECHA DEL CALENDARIO EN EL QUE SE ELIGE LA SEMANA EN LA QUE SE ESTÁ

					rightB.addActionListener(new ActionListener() {

						@SuppressWarnings("deprecation")
						@Override
						public void actionPerformed(ActionEvent e) {

							int inc;
							if(actualDay.getDay()==0){
								inc=1;
							}
							else {
								inc=actualDay.getDay()+6;
							}
							actualDay = new Date(actualDay.getTime()-24*60*60*1000*inc);
							actualDayL.setText((actualDay.getDate())+"/"+(actualDay.getMonth()+1));

							if(actualDay.getDate() != systemDate.getDate()) actualDayL.setForeground(Color.WHITE);

							if(systemDate.getTime() == actualDay.getTime()+24*3600000*(systemDate.getDay()-1)) {
								actualDayL.setForeground(Color.orange);
							}
							paintCalendar(actualDay);

						}
					});

					//ACTION LISTENER DEL BOTON DE LA IZQUIERDA DEL CALENDARIO EN EL QUE SE ELIGE LA SEMANA EN LA QUE SE ESTÁ

					leftB.addActionListener(new ActionListener() {

						@SuppressWarnings("deprecation")
						@Override
						public void actionPerformed(ActionEvent e) {


							int inc;
							if(actualDay.getDay()==0){
								inc=1;
							}
							else {
								inc=actualDay.getDay()+6;
							}
							actualDay = new Date(actualDay.getTime()+24*60*60*1000*inc);
							actualDayL.setText((actualDay.getDate())+"/"+(actualDay.getMonth()+1));

							if(actualDay.getDate() != systemDate.getDate()) actualDayL.setForeground(Color.WHITE);

							if(systemDate.getTime() == actualDay.getTime()+24*3600000*(systemDate.getDay()-1)) {
								actualDayL.setForeground(Color.orange);
							}
							paintCalendar(actualDay);

						}
					});


	}


	@SuppressWarnings("deprecation")
	public static void paintCalendar(Date actualDay) {
		day1center.removeAll();
		day2center.removeAll();
		day3center.removeAll();
		day4center.removeAll();
		day5center.removeAll();

		Date dayOfWeek = actualDay;


		if(actualDay.getDay()==0){
			dayOfWeek = new Date(actualDay.getTime() - 6*24*3600000);
		}
		else {
			dayOfWeek = new Date(dayOfWeek.getTime() - (dayOfWeek.getDay()-1)*24*3600000);
		}


		Integer dayOfWeekKey = Integer.parseInt(dayOfWeek.getYear()+""+(dayOfWeek.getMonth()+1)+""+dayOfWeek.getDate());
		Integer dayOfWeekKeySaturday = Integer.parseInt(new Date(dayOfWeek.getTime()+6*24*3600000).getYear()+""+(new Date(dayOfWeek.getTime()+6*24*3600000).getMonth()+1)+""+new Date(dayOfWeek.getTime()+6*24*3600000).getDate());

		TreeMap<Integer, Day> calendar = windowWorker.calendar.getCalendar();

		if (!calendar.isEmpty()) {

			for(Entry<Integer, Day> entry : calendar.entrySet()) {
				Day dia = entry.getValue();
				int dayKey = Integer.parseInt(dia.getDay().getYear()+""+(dia.getDay().getMonth()+1)+""+dia.getDay().getDate());
				if(dayKey>=dayOfWeekKey && dayKey<=dayOfWeekKeySaturday) {
					paintDay(dia);
				}

			}
		}

		day1center.revalidate();
		day1center.repaint();
		day2center.revalidate();
		day2center.repaint();
		day3center.revalidate();
		day3center.repaint();
		day4center.revalidate();
		day4center.repaint();
		day5center.revalidate();
		day5center.repaint();

	}

	@SuppressWarnings({ "deprecation", "static-access" })
	private static void paintDay(Day dia) {
		for (Appointment actualAppointment : dia.getAppointmentsPerDay()) {

			int hourAppointment = actualAppointment.getHour()*60+actualAppointment.getMinute();

			Person person = actualAppointment.getPerson();

			int buttonNumber = Character.getNumericValue(person.getButton().charAt(15));
			JButton valueButton = new JButton();

			valueButton.setName(hourAppointment+"");
			
			switch (buttonNumber) {
			case 1:
				valueButton.setBackground(new Color(188,242,76));
				break;
			case 2:
				valueButton.setBackground(new Color(207,83,186));
				break;
			case 3:
				valueButton.setBackground(new Color(237,63,124));
				break;
			case 4:
				valueButton.setBackground(new Color(51,153,234));
				break;
			case 5:
				valueButton.setBackground(new Color(46,91,155));
				break;
			case 6:
				valueButton.setBackground(new Color(240,155,87));
				break;
			case 7:
				valueButton.setBackground(new Color(196,199,43));
				break;
			case 8:
				valueButton.setBackground(new Color(100,47,145));
				break;
			case 9:
				valueButton.setBackground(new Color(255,156,21));
				break;
			}
			valueButton.setBorderPainted(false);
			valueButton.setHorizontalTextPosition(JButton.CENTER);
			valueButton.setVerticalTextPosition(JButton.CENTER);
			valueButton.setText(person.getName());
			valueButton.setFont(new Font("Arial",Font.PLAIN,20));

			valueButton.addActionListener(new ActionListener() {

				public void actionPerformed(ActionEvent e) {
					String minutes = actualAppointment.getMinute()+"";
					if((actualAppointment.getMinute()+"").length()==1) minutes = "0"+(actualAppointment.getMinute()+"");
					int input = JOptionPane.showConfirmDialog(null, "The time of this appointment is "+actualAppointment.getHour()+":"+minutes+"\n"+"Do you want to delete this appointment?", null, JOptionPane.YES_NO_OPTION, JOptionPane.ERROR_MESSAGE);

					if (input == 0) {
						JPanel parent = (JPanel) valueButton.getParent();
						ContainingClass.removeAppointment(valueButton,actualAppointment);
						parent.remove(valueButton);
						parent.repaint();
						parent.revalidate();
					}
					if (input == 1) {

					}
				}
			});

			valueButton.setBounds(0, (int) (Math.round((hourAppointment-510)*31/60))*MkPsycho.getHeight()/height, MkPsycho.day5.getWidth(), 31*MkPsycho.getHeight()/height);

			if(actualAppointment.getDuration()) {
				valueButton.setBounds(0, (int) (Math.round(((hourAppointment-510)*31/60)))*MkPsycho.getHeight()/height, MkPsycho.day5.getWidth(), 45*MkPsycho.getHeight()/height);
			}

			switch (dia.getDay().getDay()) {
			case 1:
				day1center.add(valueButton);
				day1center.revalidate();
				day1center.repaint();
				break;
			case 2:
				day2center.add(valueButton);
				day2center.revalidate();
				day2center.repaint();
				break;
			case 3:
				day3center.add(valueButton);
				day3center.revalidate();
				day3center.repaint();
				break;
			case 4:
				day4center.add(valueButton);
				day4center.revalidate();
				day4center.repaint();
				break;
			case 5:
				day5center.add(valueButton);
				day5center.revalidate();
				day5center.repaint();
				break;
			}
		}

	}

	static int integerDivision(int i, int j) {
		return (i-(i%j))/j;
	}

	public static void refreshActionListenersWestPanel() {

		for(Component boton : boxP.getComponents()) {
			int cont = 0;
			JButton b = (JButton) boton;		
			b.addMouseListener(new MouseAdapter() {

				@Override
				public void mouseReleased(MouseEvent e) {
					int x = e.getX();
					int y = e.getY();

					int margin = 15;
					int westSize = westLayout.getWidth();
					int dayWidth = day1.getWidth();
					dayNum = integerDivision(x-(349*MkPsycho.getWidth()/width),dayWidth) + 1;
					int postitHeight=postitP.getHeight();
					int daysHeight = monday.getHeight();
					int hourHeight = (postitHeight-daysHeight) / 5 ;
					int hourNum = integerDivision(y-(daysHeight+margin*2),hourHeight) + 1; 

					if (x>westSize) {
						addA = new AddAppointment(b, ContainingClass);
						addA.setVisible(true);
						paintCalendar(actualDay);
					}
				}

				@Override
				public void mouseClicked(MouseEvent arg0) {
					int input = JOptionPane.showConfirmDialog(null, "Do you want to delete this user?", null, JOptionPane.YES_NO_OPTION, JOptionPane.ERROR_MESSAGE);

					if (input == 0) {
						boxP.remove(Integer.parseInt(b.getName()));
						boxP.repaint();
						Person p = windowWorker.getWorkersArray().get(Integer.parseInt(b.getName()));
						try {
							if(p instanceof Client)ContainingClass.getClients().remove(p.getSurname());
							if(p instanceof Patient) ContainingClass.getPatients().remove((((Patient) p).getId()));
						}catch(Exception e) {}
						windowWorker.getWorkersArray().remove(Integer.parseInt(b.getName()));
					}
					if (input == 1) {
					}
				}
			});


		}
	}


}








