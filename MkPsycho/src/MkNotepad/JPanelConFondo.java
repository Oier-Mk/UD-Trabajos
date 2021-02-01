package MkNotepad;

import java.awt.*;
import java.awt.image.*;
import javax.swing.*;
import java.io.IOException;
import java.net.URL;
import javax.imageio.ImageIO;


public class JPanelConFondo extends JPanel {
	private static final long serialVersionUID = 1L;
	private BufferedImage imagenOriginal;
	public JPanelConFondo( String nombreImagenFondo ) {
		URL imgURL = getClass().getResource(nombreImagenFondo);
		try {
			imagenOriginal = ImageIO.read( imgURL );
		} catch (IOException e) {
		}
	}
	
	protected void paintComponent(Graphics g) {
			Graphics2D g2 = (Graphics2D) g;  // El Graphics realmente es Graphics2D
				g2.drawImage(imagenOriginal, 0, 0, getWidth(), getHeight(), this );
			}

	
	
	
}
