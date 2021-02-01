package local;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JSpinner.DateEditor;
import javax.swing.JTextField;
import javax.swing.SpinnerDateModel;
import javax.swing.SwingConstants;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.filechooser.FileNameExtensionFilter;
import utils.JPasswordFieldRedondo;
import utils.JTextFieldRedondo;
import server.Contenido;
import server.Temporada;
import server.Usuario;
import server.Video;
import uk.co.caprica.vlcj.player.component.EmbeddedMediaPlayerComponent;


/**
 * @author Mentxaka
 *
 */
@SuppressWarnings("deprecation")
public class Window extends JFrame{

	private static final long serialVersionUID = 1L;

	public static final Font fBoton  = new Font ("Arial Rounded MT Bold", Font.BOLD, 17);
	public static final Font fTextField = new Font ("Arial Rounded MT Bold", Font.PLAIN, 20);
	public static final Font fLabel = new Font ("Arial Rounded MT Bold", Font.PLAIN, 20);
	public static final Font fVIPStream = new Font ("Arial Rounded MT Bold", Font.BOLD, 58);
	public static final Font fUyP = new Font ("Arial Rounded MT Bold", Font.BOLD, 17); //User y password

	private static JPanel pPrincipal, pSuperior, pCentral, pCentralSuperior, pCentralInferior, pBotones;
	private static JButton bUneteAhora, bIniarSesion, bContinuar, bFotoPerfil, bFotoPerfilAjustes, b3, b4, b5, bAjustes, bStop, b10p, b10m, bAtrasReproductor, fScreen;
	private static JSpinner sFechaNaci;
	public static DateEditor deFechaNaci;
	public static ImageIcon iiFotoPerfil;
	public static Image iFotoPerfil;

	public static Boolean indicador = false;


	@SuppressWarnings("unused")
	private static Thread t;

	public static JTextField tfFechaNaci, tfUsuario, tfNombre,tfApellidos,tfNumCuenta,tfNumMovil,tfEmail;
	public static JTextFieldRedondo tfrUsuario;
	public static JPasswordField pfContrasenya, pfContrasenya2;
	public static JPasswordFieldRedondo pfPassword; 
	public static JScrollPane fRecomendados, fNovedades, fSmiLista;

	private static JLabel lMiLista, lRecomendados, lNovedades, lLogo, lUsuario, lPassword;

	private static EmbeddedMediaPlayerComponent vPlayer;

	public static Usuario user;
	static String[] SlRecomendados = null; 
	static String[] SlNovedades = null;
	static ArrayList<Video> videosUsuario ;

	private static Window miWindow = new Window();

	private Image Image;

	public static Video video;

	public static boolean existe;

	/** Devuelve la ventana en la que nos encontramos  
	 * @return Ventana en la que nos encontramos
	 */
	public static Window getWindow(){
		return miWindow;
	}

	/**
	 * PONE LOS AJUSTES PRINCIPALES DE LA VENTANA jFAME
	 */
	public Window() {
		pPrincipal = new JPanel(new BorderLayout());
		pSuperior = new JPanel(new FlowLayout(FlowLayout.CENTER, 100, 0));
		pCentral = new JPanel();
		pCentral.setLayout(new BoxLayout(pCentral, BoxLayout.Y_AXIS));
		add(pPrincipal);

		setLocationRelativeTo(null);
		setMinimumSize(new Dimension(1200,800));
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setExtendedState(MAXIMIZED_BOTH);

		addWindowListener(new WindowAdapter() {
			public void windowClosed(WindowEvent e) {
				vPlayer.mediaPlayer().controls().stop();
				peticion("2222");			
			}
		});

		creaVSesion();
		vSesion();

		creaVReproductor();
	}

	/**
	 * Crea los objetos necesarios para visualizar la ventana de registro
	 * Se define el diseño de la ventana registro y sus eventos
	 */
	@SuppressWarnings("static-access")
	private void creaVRegistro() {
		iFotoPerfil = new ImageIcon("src/local/fotoPerfil.jpg").getImage();
		iiFotoPerfil = new ImageIcon(iFotoPerfil.getScaledInstance(65, 65, Image.SCALE_SMOOTH));

		bFotoPerfilAjustes = new JButton(iiFotoPerfil);

		bContinuar = new JButton("Continuar");
		tfNombre = new JTextField("Nombre...");
		tfApellidos = new JTextField("Apellidos...");
		sFechaNaci = new JSpinner(new SpinnerDateModel());
		tfNumMovil = new JTextField("Teléfono...");
		tfEmail = new JTextField("Correo...");
		tfNumCuenta = new JTextField("Número de cuenta...");
		tfUsuario = new JTextField("Usuario...");
		pfContrasenya = new JPasswordField("Contraseña...");
		pfContrasenya2 = new JPasswordField("Repita la contraseña...");

		//DECORACIONES
		//bFotoPerfilAjustes
		bFotoPerfilAjustes.setBorderPainted(false);

		//bContinue
		bContinuar.setSize(300, 100);
		bContinuar.setFont(fBoton);
		bContinuar.setPreferredSize(new Dimension(150,50));

		//tfNombre
		setDeco(tfNombre);

		//tfApellidos
		setDeco(tfApellidos);

		//sFechaNaci
		deFechaNaci = new JSpinner.DateEditor(sFechaNaci, "dd.MM.yyyy");
		sFechaNaci.setEditor(deFechaNaci);

		deFechaNaci.getTextField().setHorizontalAlignment( JFormattedTextField.CENTER );
		//		deFechaNaci.getTextField().setBackground(new Color(0, 0, 0, 0));
		deFechaNaci.getTextField().setBorder(BorderFactory.createLineBorder(Color.RED, 0));
		deFechaNaci.getTextField().setFont(fTextField);

		//tfNumMovil
		setDeco(tfNumMovil);

		//tfEmail
		setDeco(tfEmail);

		//tfNumCuenta
		setDeco(tfNumCuenta);

		//tfUsuario
		setDeco(tfUsuario);

		//pfContrasenya
		pfContrasenya.setEchoChar((char) 0);
		setDeco(pfContrasenya);

		//pfContrasenya2
		pfContrasenya2.setEchoChar((char) 0);
		setDeco(pfContrasenya2);


		//EVENTOS
		listenersRegistro();
	}

	/**
	 * Redistribuye la nueva ventana de registro quitando los componenetes de la ventnana anterior y 
	 * los asigna de la forma deseada
	 */
	public void vRegistro() {
		pPrincipal.removeAll();
		pSuperior.removeAll();
		pCentral.removeAll();
		pCentralSuperior.removeAll();
		pCentralInferior.removeAll();

		pPrincipal.setLayout(new BorderLayout());
		pSuperior.setLayout(new FlowLayout(FlowLayout.CENTER));
		pCentral.setLayout(new GridLayout(9, 1)); //Hacemos como si fuese el panel inferior, no centralInferior
		pCentralInferior.setLayout(new BorderLayout());

		pPrincipal.add(pSuperior, BorderLayout.NORTH);
		pPrincipal.add(pCentral, BorderLayout.CENTER);
		pPrincipal.add(pCentralInferior ,BorderLayout.SOUTH);

		pSuperior.add(bFotoPerfilAjustes);
		pCentralInferior.add(bContinuar);

		pCentral.add(tfNombre);
		pCentral.add(tfApellidos);
		pCentral.add(sFechaNaci);
		pCentral.add(tfNumMovil);
		pCentral.add(tfEmail);
		pCentral.add(tfNumCuenta);
		pCentral.add(tfUsuario);
		pCentral.add(pfContrasenya);
		pCentral.add(pfContrasenya2);

		revalidate();
	}

	/**
	 * Crea los objetos necesarios para visualizar la ventana de sesión
	 * Se define el diseño de la ventana sesión y sus eventos
	 */
	private void creaVSesion() {
		pCentralSuperior = new JPanel(new BorderLayout());
		pCentralInferior = new JPanel(new GridLayout(4, 1));

		lLogo = new JLabel("VipStream");
		lUsuario = new JLabel("Usuario");
		lPassword = new JLabel("Contraseña");
		tfrUsuario = new JTextFieldRedondo("");
		pfPassword = new JPasswordFieldRedondo("");
		bIniarSesion = new JButton("Iniciar sesión");
		bUneteAhora = new JButton("Registrate!");

		apareceTexto();

		//DECORACIONES
		//lLogo
		lLogo.setHorizontalAlignment(SwingConstants.CENTER);
		lLogo.setFont(fVIPStream);

		//lUsuario
		lUsuario.setFont(fLabel);
		lUsuario.setHorizontalAlignment(SwingConstants.CENTER);

		//lPassword
		lPassword.setFont(fLabel);
		lPassword.setHorizontalAlignment(SwingConstants.CENTER);

		//tfrUsuario
		tfrUsuario.setFont(fTextField);
		tfrUsuario.setHorizontalAlignment(SwingConstants.CENTER);

		//pfPassword
		pfPassword.setFont(fTextField);
		pfPassword.setHorizontalAlignment(SwingConstants.CENTER);

		//bIniarSesion
		bIniarSesion.setFont(fBoton);
		bIniarSesion.setPreferredSize(new Dimension(00,50));

		//bUneteAhora
		bUneteAhora.setFont(fBoton);
		bUneteAhora.setPreferredSize(new Dimension(150,50));


		bIniarSesion.addActionListener(
				e -> {
					String nick = tfrUsuario.getText();
					char[] contA = pfPassword.getPassword();
					String cont = new String(contA);

					peticion("0000"+nick); 

					if(existe) {
						existe = false;
						peticion("1111"+nick);
						if(user.getContrasenya().contentEquals(cont)){
							indicador = true;
							creaVInicio();
							vInicio();
							revalidate();
						} else {
							ventanaEmergente v = new ventanaEmergente("Contraseña incorrecta!");
							v.setVisible(true); 
						}
					}else {
						ventanaEmergente v = new ventanaEmergente("Usuario incorrecto!");
						v.setVisible(true); 
					}
				});

		bUneteAhora.addActionListener(
				e -> {creaVRegistro(); vRegistro();
				});
	}

	/**
	 * HILO DONDE SE MODIFICA EL COLOR DEL TEXTO PRINCIPAL
	 */
	private static void apareceTexto() {
		(new Thread() { 
			public void run() {
				while(true) {
					for(int i=255; i>=0; i--) {
						lLogo.setForeground(new Color(i, i, i));
						try {
							Thread.sleep(2);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
					for(int i=0;i<256;i++) {
						lLogo.setForeground(new Color(60, 255, 63));
						try {
							Thread.sleep(5);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
				}
			}
		}).start();
	}



	/**
	 * Redistribuye la nueva ventana de sesión quitando los componenetes de la ventnana anterior y 
	 * los asigna de la forma deseada
	 */
	public void vSesion() {
		pPrincipal.removeAll();
		pSuperior.removeAll();
		pCentral.removeAll();
		pCentralInferior.removeAll();
		pCentralSuperior.removeAll();

		pPrincipal.setLayout(new BorderLayout());
		pSuperior.setLayout(new FlowLayout(FlowLayout.RIGHT));
		pCentral.setLayout(new GridLayout(2, 1));

		pSuperior.setLayout(new FlowLayout(FlowLayout.RIGHT));
		pCentral.setLayout(new GridLayout(2,1));
		pCentralSuperior.setLayout(new BorderLayout()); 
		pCentralInferior.setLayout(new GridLayout(4,1));

		pPrincipal.add(pSuperior, BorderLayout.NORTH);
		pPrincipal.add(bIniarSesion, BorderLayout.SOUTH);
		pPrincipal.add(pCentral, BorderLayout.CENTER);

		pSuperior.add(bUneteAhora);

		pCentral.add(pCentralSuperior);
		pCentral.add(pCentralInferior);

		pCentralSuperior.add(lLogo, BorderLayout.CENTER);
		pCentralInferior.add(lUsuario);
		pCentralInferior.add(tfrUsuario);
		pCentralInferior.add(lPassword);
		pCentralInferior.add(pfPassword);

		pPrincipal.revalidate();
	}

	/**
	 * Crea los objetos necesarios para visualizar la ventana de inicio
	 * Se define el diseño de la ventana inicio y sus eventos
	 */
	@SuppressWarnings("static-access")
	public void creaVInicio() {

		iFotoPerfil = new ImageIcon("src/local/fotoPerfil.jpg").getImage();
		iiFotoPerfil = new ImageIcon(iFotoPerfil.getScaledInstance(65, 65, Image.SCALE_SMOOTH));

		bFotoPerfil = new JButton(iiFotoPerfil);

		b3 = new JButton("Serie");
		b4 = new JButton("Documentales"); 
		b5 = new JButton("Peliculas"); 
		bAjustes = new JButton("Ajustes");

		lMiLista = new JLabel("		Mi lista");
		lNovedades = new JLabel("		Novedades");
		lRecomendados = new JLabel("		Recomendados");		

		peticion("3333");
		peticion("4444");

		fRecomendados = filaBotones(SlRecomendados); 
		fNovedades = filaBotones(SlNovedades);

		videosUsuario = user.getReproduccionEnCurso();

		ArrayList<String> SmiLista = new ArrayList<>();
		for (int i = 0; i<videosUsuario.size();i++) {
			SmiLista.add(i, videosUsuario.get(i).getPath());
		}
		if(!SmiLista.isEmpty())	{
			fSmiLista = filaBotones((String[]) SmiLista.toArray());
		}else {
			fSmiLista = new JScrollPane();
		}

		//DECORACIONES
		//bFotoPerfil
		bFotoPerfil.setRolloverEnabled(false);
		bFotoPerfil.setFocusable(false);
		bFotoPerfil.setBorderPainted(false);

		//b3
		b3.setFont(fBoton);
		b3.setPreferredSize(new Dimension(100,35));

		//b4
		b4.setFont(fBoton);
		b4.setPreferredSize(new Dimension(150,35)); //Documentales no cabe en 100px

		//b5
		b5.setFont(fBoton);
		b5.setPreferredSize(new Dimension(100,35));

		//bAjustes
		bAjustes.setFont(fBoton);
		bAjustes.setPreferredSize(new Dimension(100,35));

		//EVENTOS
		listenersSesion();
	}

	/**
	 * Redistribuye la nueva ventana de inicio quitando los componenetes de la ventnana anterior y
	 * los asigna de la forma deseada
	 */
	public void vInicio() {

		pPrincipal.removeAll();
		pSuperior.removeAll();
		pCentral.removeAll();
		pCentralSuperior.removeAll();
		pCentralInferior.removeAll();

		pSuperior.setLayout(new FlowLayout(FlowLayout.CENTER, 100, 0));
		pCentral.setLayout(new BoxLayout(pCentral, BoxLayout.Y_AXIS));

		pPrincipal.setLayout(new BorderLayout()); 
		pPrincipal.add(pSuperior, BorderLayout.NORTH);
		pPrincipal.add(pCentral, BorderLayout.CENTER);

		pSuperior.add(bFotoPerfil);
		pSuperior.add(b3);
		pSuperior.add(b4);
		pSuperior.add(b5);
		pSuperior.add(bAjustes);

		pCentral.add(lMiLista);
		pCentral.add(fSmiLista);
		pCentral.add(lNovedades);
		pCentral.add(fNovedades);
		pCentral.add(lRecomendados);
		pCentral.add(fRecomendados);
		pPrincipal.revalidate();

		revalidate();
		pPrincipal.repaint();
	}

	/**
	 * Crea los objetos necesarios para visualizar la ventana del reproductor
	 * Se define el diseño de la ventana del reproductor y sus eventos
	 */
	private void creaVReproductor() {
		vPlayer = new EmbeddedMediaPlayerComponent();
		pBotones = new JPanel();
		bStop = new JButton("▶/❚❚");	
		b10p = new JButton(">>");	
		b10m = new JButton("<<");	
		bAtrasReproductor = new JButton("Back");	
		fScreen = new JButton("Full Screen");

		bStop.addActionListener(
				e -> vPlayer.mediaPlayer().controls().pause());
		b10m.addActionListener(
				e -> vPlayer.mediaPlayer().controls().skipTime(-10000));
		b10p.addActionListener(
				e -> vPlayer.mediaPlayer().controls().skipTime(10000));
		fScreen.addActionListener(
				e ->{vPlayer.mediaPlayer().fullScreen();
				revalidate();});
		bAtrasReproductor.addActionListener(
				e ->{vPlayer.mediaPlayer().controls().stop();
				vInicio();});
	}


	/**
	 * Redistribuye la nueva ventana de reproducción quitando los componenetes de la ventnana anterior y
	 * los asigna de la forma deseada
	 * @param path String de la ruta correspondiente a la ubicación del video 
	 */
	public void vReproductor(String path) {
		pPrincipal.removeAll();
		pPrincipal.setLayout(new BorderLayout());
		pPrincipal.add(vPlayer, BorderLayout.CENTER);
		pBotones.setBackground(Color.black);
		pPrincipal.add(pBotones, BorderLayout.SOUTH);

		pBotones.add(bAtrasReproductor);
		pBotones.add(b10m);
		pBotones.add(bStop);
		pBotones.add(b10p);
		pBotones.add(fScreen);

		vPlayer.mediaPlayer().audio().setVolume(100);
		vPlayer.mediaPlayer().media().play(path);

		revalidate();
		repaint();
	}


	/**
	 * @param array PASA POR PARAMETRO UN ARRAY CON LOS PATHS DE LAS IMÁGENES QUE SE LE QUIEREN ASIGNAR
	 * A CADA BOTON. 
	 * @return DEVUELVE UN JSCROLLPANE FORMADO POR BOTONES EN HORIZONTAL DONDE CADA BOTON TIENE UNA IMAGEN DIFERENTE
	 */
	private JScrollPane filaBotones(String [] array) {
		JComponent boxP = new JPanel();	
		BoxLayout boxLayout = new BoxLayout( boxP, BoxLayout.X_AXIS );
		boxP.setLayout(boxLayout);
		boxP.setOpaque(false);

		for (String i : array) {
			JButton icon = new JButton();
			peticion("5555"+i);
			//			video = (Video) Api.getObjectFromDB(i, "VIDEO");
			try {
				Contenido c = video.getContenido();
				String pathFoto = c.getPathFoto();
				if (pathFoto.charAt(0)=='/')pathFoto = pathFoto.substring(1);
				Image img = ImageIO.read(new File( pathFoto )); // descomentar ese trozo con los path originales
				@SuppressWarnings("static-access")
				Image resizedImg = img.getScaledInstance(125, 200, Image.SCALE_DEFAULT);
				ImageIcon imgIcon = new ImageIcon(resizedImg);
				icon.setIcon(imgIcon);
			} catch (Exception e) {
				Image img = null;
				try {
					Temporada t = video.getTemporada();
					Contenido c = t.getContenido();
					String pathFoto = c.getPathFoto();
					if (pathFoto.charAt(0)=='/')pathFoto = pathFoto.substring(1);
					img = ImageIO.read(new File( c.getPathFoto() ));
				} catch (Exception e1) {e.printStackTrace();} // descomentar ese trozo con los path originales
				@SuppressWarnings("static-access")
				Image resizedImg = img.getScaledInstance(125, 200, Image.SCALE_DEFAULT);
				ImageIcon imgIcon = new ImageIcon(resizedImg);
				icon.setIcon(imgIcon);
			}

			icon.setContentAreaFilled(false);
			icon.setBorderPainted(false);
			icon.setText(i);
			Font f = new Font("Arial",Font.PLAIN,0);
			icon.setHorizontalTextPosition(JButton.CENTER);
			icon.setVerticalTextPosition(JButton.BOTTOM);
			icon.setFont(f);
			boxP.add(icon);
			actionListenerCaratula(icon);
		}

		boxP.setBorder(null);
		boxP.setOpaque(false);

		JScrollPane buttonScroll = new JScrollPane(boxP);

		return buttonScroll;		
	}

	public static Boolean recibido = false; 
	int cont = 0;


	/**
	 * @param Pide un objeto al servidor
	 * Hay unos códigos que hacen que devuelva varios tipos de objetos
	 * 
	 * Si los primeros 4 dígitos del objeto de tipo String son devolverá el objeto solicitado
	 * 
	 * 	 EXISTE = "0000";
	 *   GETUSER = "1111";
	 *	 FIN = "2222";
	 *	 RECOMENDADOS = "3333";
	 *	 NOVEDADES = "4444";
	 *	 VIDEO = "5555";
	 *	 PATH = "6666";
	 * 
	 * Si el objeto pasado es de tipo Usuario mete el usuario a la BD o modifica sus parámetros.
	 * 
	 * El while mantiene el hilo principal del sistema ocupado hasta que se recibe el objeto solicitado al servidor
	 */
	private void peticion(Object obj) {
		try {Cliente.flujoOut.writeObject( obj );} catch (IOException e1) {}
		while(!recibido) {
			System.out.print("");
		}
		cont++;
		recibido = false;
	}


	/** 
	 * @param icon CON ESTE BOTON SE SELECCIONA EL PATH DEL VIDEO 
	 * ESTE PATH SE LE PIDE AL SERVIDOR, UN VIDEO.
	 * EL CLIENTE AL RECIBIRLO ABRE UNA VENTANA DE REPRODUCCI'ON CON EL VIDEO EN CUESTION
	 */
	private void actionListenerCaratula(JButton icon) {
		icon.addActionListener(
				e -> {peticion("6666"+icon.getText());});
	}


	/** No sirve para JButton, JLabel ni JSpinner ni derivados! Estos dos componentes hay que hacer por separado manualmente
	 * @param o
	 */ 
	public void setDeco (Object o) {
		//		((JComponent)o).setBackground(new Color(0,0,0,0));
		((JComponent) o).setBorder(null);
		((JTextField) o).setHorizontalAlignment(SwingConstants.CENTER);
		((JTextField) o).setFont(fTextField);
	}


	/**
	 * Gestiona la seleccion de la foto de perfil y la reescala
	 * @param boton Botón en el cual se va a establecer la foto de perfil @bFotoPerfil
	 */
	@SuppressWarnings("static-access")
	private void selFotoPerfil (JButton boton) {
		JFileChooser fc = new JFileChooser();
		File directorio = new File("Imagenes");
		fc.setCurrentDirectory(directorio);
		fc.setFileFilter(new FileNameExtensionFilter("TIPO JPG", "jpg"));
		fc.setFileFilter(new FileNameExtensionFilter("TIPO PNG", "png"));
		int sel = fc.showOpenDialog(null);
		if (sel == JFileChooser.APPROVE_OPTION) {
			File ficheroSeleccionado = fc.getSelectedFile();
			try {iFotoPerfil = new ImageIcon(ficheroSeleccionado.getCanonicalPath()).getImage();} catch (IOException e) {}
			iiFotoPerfil = new ImageIcon(iFotoPerfil.getScaledInstance(65, 65, Image.SCALE_SMOOTH));
			bFotoPerfilAjustes.setIcon(iiFotoPerfil);
			bFotoPerfilAjustes.repaint();
			try {user.setPathFoto(ficheroSeleccionado.getCanonicalPath());} catch (IOException e) {	}
		}
	}

	/**
	 * Método que recoge todos los eventos de la ventana sesión
	 */
	public void listenersSesion() {
		bAjustes.addActionListener(
				e-> {creaVRegistro(); vRegistro();
				tfNombre.setText(user.getNombre());
				tfApellidos.setText(user.getApellido());
				sFechaNaci.setValue(user.getFechaNacimiento());
				tfNumMovil.setText(user.getNumMovil() + "");
				tfEmail.setText(user.getEmail());
				tfNumCuenta.setText(user.getNumCuenta()+ "");
				tfUsuario.setText(user.getNick());
				update(getGraphics());
				});
	}

	/**
	 * Método que recoge todos los eventos de la ventnana registro
	 * IMPORTANTE: Las restricciones estan comentadas para facilitar la creación de usuarios de prueba
	 */
	public void listenersRegistro () {

		bFotoPerfilAjustes.addActionListener(
				e-> selFotoPerfil(bFotoPerfilAjustes));

		//tfNombre
		tfNombre.addFocusListener(new FocusListener() {

			@Override
			public void focusGained(FocusEvent e) {
				if (tfNombre.getText().equals("Nombre...")) {
					tfNombre.setText("");
				}
			}

			@Override
			public void focusLost(FocusEvent e) {
				if (tfNombre.getText().equals("")) {
					tfNombre.setText("Nombre...");
				}
			}
		});

		//tfApellidos
		tfApellidos.addFocusListener(new FocusListener() {

			@Override
			public void focusGained(FocusEvent e) {
				if (tfApellidos.getText().contentEquals("Apellidos...")) {
					tfApellidos.setText("");
				}
			}

			@Override
			public void focusLost(FocusEvent e) {
				if (tfApellidos.getText().equals("")) {
					tfApellidos.setText("Apellidos...");
				}

			}
		});

		//tfNumMovil
		tfNumMovil.addFocusListener(new FocusListener() {

			@Override
			public void focusGained(FocusEvent e) {
				if (tfNumMovil.getText().contentEquals("Teléfono...")) {
					tfNumMovil.setText("");
				}
			}

			@Override
			public void focusLost(FocusEvent e) {
				if (tfNumMovil.getText().equals("")) {
					tfNumMovil.setText("Teléfono...");
				}
				//String min = "[0-9]{9}"; 	
				//if (!tfNumMovil.getText().matches(min)) {
				//ventanaEmergente v = new ventanaEmergente ("No es un numero de teléfono correcto");
				//v.s\etVisible(true);
			}
		});

		//tfEmail
		tfEmail.addFocusListener(new FocusListener() {

			@Override
			public void focusGained(FocusEvent e) {
				if (tfEmail.getText().contentEquals("Correo...")) {
					tfEmail.setText("");
				}
			}

			@Override
			public void focusLost(FocusEvent e) {
				if (tfEmail.getText().equals("")) {
					tfEmail.setText("Correo...");
				}

				//No debe tener '@' ni 'ñ' ni antes ni despues del '@'. El dominio debe acabar un con un punto que debe contener entre 2 y 4 caracteres.
				//No importa si es mayusculas o minusculas
				//				String email = "[^@ñ]+@[^@ñ]+\\.[a-zA-Z]{2,4}"; 
				//				if (!tfEmail.getText().matches(email)){
				//					ventanaEmergente v = new ventanaEmergente("El correo debe tener un formato valido");
				//					v.setVisible(true);
				//				}
			}
		});

		//tfNumCuenta
		tfNumCuenta.addFocusListener(new FocusListener() {

			@Override
			public void focusLost(FocusEvent e) {
				if (tfNumCuenta.getText().equals("")) {
					tfNumCuenta.setText("Número de cuenta...");
				}

				//				String min = "[0 - 9]{9}"; 			
				//				if (!tfNumCuenta.getText().matches(min)) {
				//					ventanaEmergente p = new ventanaEmergente("El número de cuenta debe contener 9 dígitos");
				//					p.setVisible(true); 
				//					tfNumCuenta.setText("");
				//				}
			}

			@Override
			public void focusGained(FocusEvent e) {
				if (tfNumCuenta.getText().contentEquals("Número de cuenta...")) {
					tfNumCuenta.setText("");

				}
			}
		});

		//tfUsuario
		tfUsuario.addFocusListener(new FocusListener() {

			@Override
			public void focusGained(FocusEvent e) {
				if (tfUsuario.getText().contentEquals("Usuario...")) {
					tfUsuario.setText("");
				}

				if (tfUsuario.getText().contains("ñ") || tfUsuario.getText().contains(" ")) {
					ventanaEmergente v = new ventanaEmergente("La contraseña no puede contener caracteres especiales");
					v.setVisible(true);
				}
			}

			@Override
			public void focusLost(FocusEvent e) {
				if (tfUsuario.getText().equals("")) {
					tfUsuario.setText("Usuario...");
				}

			}
		});

		//pfContrasenya
		pfContrasenya.addFocusListener(new FocusListener() {

			@Override
			public void focusGained(FocusEvent e) {

				pfContrasenya.setText("");
				if (!pfContrasenya.getText().contentEquals("Contraseña...")) {
					pfContrasenya.setEchoChar('•');
				}
			}

			@Override
			public void focusLost(FocusEvent e) {
				if (pfContrasenya.getText().equals("")) {
					pfContrasenya.setText("Contraseña...");
					pfContrasenya.setEchoChar((char) 0);
				}

				//				Minimo un digito, una minuscula, una mayuscula; No espacios en blanco; Entre 8 y 12 caracteres
				//				String min = "(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=\\S+$).{8,12}";			
				//				if (!pfContrasenya.getText().matches(min)) {
				//					ventanaEmergente v = new ventanaEmergente("La contraseña debe cumplir los estándares de una contraseña");
				//					v.setVisible(true);
				//				}
			}
		});

		//		pfContrasenya.getDocument().addDocumentListener(new DocumentListener() {
		//
		//			@Override
		//			public void insertUpdate(DocumentEvent e) {
		//				String min = "(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=\\S+$).{8,}";
		//
		//				if(!pfContrasenya.getText().matches(min)) {
		//					pfContrasenya.setBackground(new Color(203, 50, 52, 200));
		//				}else {
		//					pfContrasenya.setBackground(new Color(0, 0, 0, 0));;
		//				}
		//
		//			}
		//
		//			@Override
		//			public void removeUpdate(DocumentEvent e) {
		//
		//				String min = "(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=\\S+$).{8,}";
		//
		//				if(!pfContrasenya.getText().matches(min)) {
		//					pfContrasenya.setBackground(new Color(203, 50, 52, 200));
		//				}else {
		//					pfContrasenya.setBackground(new Color(0, 0, 0, 0));;
		//				}
		//			}
		//
		//			@Override
		//			public void changedUpdate(DocumentEvent e) {
		//
		//				String min = "(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=\\S+$).{8,}";
		//
		//				if(!pfContrasenya.getText().matches(min)) {
		//					pfContrasenya.setBackground(new Color(203, 50, 52, 200));
		//				}else {
		//					pfContrasenya.setBackground(new Color(0, 0, 0, 0));;
		//				}
		//			}
		//
		//
		//		});

		//pfContrasenya2
		pfContrasenya2.addFocusListener(new FocusListener() {

			@Override
			public void focusGained(FocusEvent e) {
				pfContrasenya2.setText("");
				if (!pfContrasenya2.getText().contentEquals("Repita la contraseña...")) {
					pfContrasenya2.setEchoChar('•');
				}
			}

			@Override
			public void focusLost(FocusEvent e) {
				if (pfContrasenya2.getText().equals("")) {
					pfContrasenya2.setText("Contraseña...");
					pfContrasenya2.setEchoChar((char) 0);
				}

				if (!pfContrasenya.equals(pfContrasenya2)) {
					ventanaEmergente v = new ventanaEmergente("Las contraseñas no coinciden!");
					v.setVisible(true);
				}
			}
		});

//		pfContrasenya2.getDocument().addDocumentListener(new DocumentListener() {
//
//			@Override
//			public void removeUpdate(DocumentEvent e) {
//				if(!pfContrasenya.equals(pfContrasenya2)) {
//					pfContrasenya2.setBackground(new Color(203, 50, 52, 200));
//				}else {
//					pfContrasenya2.setBackground(new Color(0, 0, 0, 0));;
//				}
//
//			}
//
//			@Override
//			public void insertUpdate(DocumentEvent e) {
//				if(!pfContrasenya.equals(pfContrasenya2)) {
//					pfContrasenya2.setBackground(new Color(203, 50, 52, 200));
//				}else {
//					pfContrasenya2.setBackground(new Color(0, 0, 0, 0));;
//				}
//
//			}
//
//			@Override
//			public void changedUpdate(DocumentEvent e) {
//				if(!pfContrasenya.equals(pfContrasenya2)) {
//					pfContrasenya2.setBackground(new Color(203, 50, 52, 200));
//				}else {
//					pfContrasenya2.setBackground(new Color(0, 0, 0, 0));;
//				}
//
//			}
//		});

		bContinuar.addActionListener( 
				ee -> {
					if(indicador) { //Si indicador = true, se ha iniciado sesion, por lo tnaot, queremos editar el usuario
						user.setNombre(tfNombre.getText());
						user.setApellido(tfApellidos.getText());
						user.setFechaNacimiento(sFechaNaci.getValue()+"");
						user.setNumMovil(tfNumMovil.getText());
						user.setEmail(tfEmail.getText());
						tfUsuario.disable();
						user.setNumCuenta(tfNumCuenta.getText());
						if (pfContrasenya.equals(pfContrasenya2)){
							user.setContrasenya(pfContrasenya.getText());
						}
						Usuario userProv = new Usuario(user.getNick(), user.getNombre(), user.getApellido(), (user.getNumCuenta()+""), user.getPathFoto(), (user.getNumMovil()+""), user.getEmail(), user.getContrasenya(), (user.getFechaNacimiento()+""), user.getReproduccionEnCurso());
						peticion(userProv);
					}else { //Indicador false, no se ha iniciado sesion y queremos crear usuario
						user = new Usuario(
								tfUsuario.getText(), 
								tfNombre.getText(),
								tfApellidos.getText(),
								tfNumCuenta.getText(),
								"",//	tfPathFoto.getText(),
								tfNumMovil.getText(),
								tfEmail.getText(),
								pfContrasenya.getText(),
								sFechaNaci.getValue()+"",
								new ArrayList<>());
						peticion(user);
					}
					vSesion();
				});
	}
}