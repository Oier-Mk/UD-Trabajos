package local;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;


/**
 * Clase que hereda de @JFrame 
 * Se encarga de crear una pequeña ventana emergente con un mensaje
 *
 */
public class ventanaEmergente extends JFrame{

	private static final long serialVersionUID = 1L;
	public static JPanel pPrincipal, pMensaje,pMensajeSur;
	public static JLabel lMensaje;
	public static JButton bAceptar;

	public static Font letraMensaje = new Font ("Arial Rounded MT Bold", Font.BOLD, 15);


	/**Constructor de la ventana emergente
	 * @param mensaje Mensaje que queremos que se muestre en la ventana
	 */
	public ventanaEmergente (String mensaje) {
		pPrincipal = new JPanel(new BorderLayout());
		lMensaje = new JLabel(mensaje);
		bAceptar = new JButton("Aceptar");
		add(pPrincipal);

		pPrincipal.add(lMensaje, BorderLayout.CENTER);
		pPrincipal.add(bAceptar, BorderLayout.SOUTH);
	
		lMensaje.setHorizontalAlignment(SwingConstants.CENTER);
		lMensaje.setFont(letraMensaje);
		
		pPrincipal.setBorder(BorderFactory.createMatteBorder(2,2,2,2, Color.RED));
		
		setSize(900, 125);
		setUndecorated(true); 
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);  
		
		bAceptar.addActionListener(
				e -> dispose());
	}
}
