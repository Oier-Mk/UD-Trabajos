package MkNotepad;

import java.awt.*;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;
import javax.swing.*;

import java.awt.event.*;

public class MkNotepad extends JFrame{

//	public static final String fileName = "src/MkNotepad/Mk_notepad.txt";
	public static JScrollPane textBoxS = null;;
	public static JTextArea textBox = null;
	public static String date = "";
	public static String str = "";
	private static final long serialVersionUID = 1L;
	
	
//	public static void main(String[] args) {
//		MkNotepad notepad = new MkNotepad("src/MkNotepad/Mk_notepad.txt");
//		date = currentTime();
//		File data = new File(fileName);
//		if (data.exists()) {
//			textBox.setText(ReadFromTXT(fileName) + "\n" + date + "\n\n");
//			textBox.setSelectionStart( textBox.getText().length() );
//		}
//		notepad.setVisible(true);
//		System.out.println("Main finished");
//	}
	

	public MkNotepad(String fileName) {

		// 0.- Variables
		int width = 600;
		int height = 400;
		double zoom = 0.25;
		int imageX = 312;
		int imageY = 183;
		int image2X = 315;
		int image2Y = 183;
		String background = "/MkNotepad/background.png";
		// 1.- Configuración de la ventana
		this.setDefaultCloseOperation( JFrame.DISPOSE_ON_CLOSE );   // hace un this.dispose() cuando el usuario la cierra
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		this.setSize( width, height); 
		this.setLocation( screenSize.width/2 - width/2, screenSize.height/2 - height/2);  

		// 2.- Creación de contenedores (paneles) y componentes
		// 2.1- Creación del area del texto
		textBox  = new JTextArea(this.getWidth(),this.getHeight());
		textBoxS = new JScrollPane(textBox);
		textBox.setLineWrap(true);
		textBox.setWrapStyleWord(true);
		this.add( textBoxS, BorderLayout.CENTER );
		// 2.2- Creación botones
		JPanel buttons = new JPanelConFondo(background);
		buttons.setLayout( new GridLayout( 1, 4) );
		JButton exit = new JButton( "Exit" );
		JButton delete = new JButton( "Delete" );
		JButton save = new JButton( "Save" );
		JButton accept = new JButton( "Accept" );
		buttons.add( exit );   
		buttons.add( delete );
		buttons.add( save );
		buttons.add( accept );
		accept.setBorderPainted(false);
		accept.setOpaque(false);
		accept.setContentAreaFilled(false);
		delete.setBorderPainted(false);
		delete.setOpaque(false);
		delete.setContentAreaFilled(false);
		save.setBorderPainted(false);
		save.setOpaque(false);
		save.setContentAreaFilled(false);
		exit.setBorderPainted(false);
		exit.setOpaque(false);
		exit.setContentAreaFilled(false);
		this.add(buttons, BorderLayout.SOUTH);
		// 2.3- Creación de imágenes
		JLabelGrafico image = new JLabelGrafico("/MkNotepad/mk.6.45.png", imageX*zoom, imageY*zoom);
		JLabelGrafico image2 = new JLabelGrafico("/MkNotepad/text.png", image2X*zoom, image2Y*zoom);
		JPanel panel = new JPanelConFondo(background);
		FlowLayout layout = new FlowLayout();
		layout.setAlignment(FlowLayout.LEFT);
		panel.setLayout(layout);
		panel.add( image );
		panel.add( image2 );
		this.add( panel, BorderLayout.NORTH );
		
		// 3.- Comportamiento de los botones
		delete.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("Delete pressed");
				textBox.setText("");
			}
		});

		exit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("Exit pressed");
				dispose();
				}
		});

		save.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("Save pressed");
				str = textBox.getText();
				WriteToTXT(str, fileName);

			}
		});

		accept.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("Accept pressed");
				str = textBox.getText();
				WriteToTXT(str, fileName);
				dispose();
}
		});
	}

	// Función de formato de fecha

	public static String currentTime() {
		Date date = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
		String currentTime = formatter.format(date);
		return currentTime;
	}

	// Funciones de guardado

	public static void WriteToTXT(String str, String fileName ) {
		try {
			PrintStream write = new PrintStream( fileName );
			write.println(str);
			write.close();
		} catch (IOException e) {
			System.out.println( "It hasn not been able to save it to a file" );
			e.printStackTrace();
		}
	}

	public static String ReadFromTXT( String fileName ) {
		String str = "";
		try {
			Scanner read = new Scanner( new FileInputStream( fileName ) );
			while (read.hasNext()) {
				str += read.nextLine()+"\n";
			}
			read.close();
			return str;
		} catch (IOException e) {
			return ( "It hasn not been able to read from the file" );
		}
	}


}
