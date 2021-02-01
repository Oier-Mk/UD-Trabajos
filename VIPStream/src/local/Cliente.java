package local;

import java.io.*;
import java.net.Socket;
import java.net.SocketTimeoutException;

import server.Usuario;
import server.Video;


/**
 * @author Mentxaka
 *
 */
public class Cliente {

	public static final int PORT = 4000;
	public static ObjectOutputStream flujoOut;
	public static boolean finComunicacion = false;
	public static String miHost = "localhost";

	/**
	 * INICIA TODOS LOS VALORES NECESARIOS PARA INICIAR EL CLIENTE
	 * ¡¡¡PUERTO 4000 PUESTO POR DEFECTO!!!
	 */
	public static void iniciaCliente() {
		miHost = "localhost";
		Cliente c = new Cliente();
		(new Thread() {
			@Override
			public void run() {
				c.lanzaCliente();
			}
		}).start();		
	}
	boolean b = true;

	/**
	 * LANZA EL CLIENTE Y CON ELLO UN BUCLE INFINITO EN EL CUAL SE ESPERA A 
	 * LA RESPUESTA DE LAS PETICIONES HECHAS AL SERVIDOR
	 */
	public void lanzaCliente() { 
		try (Socket socket = new Socket( miHost, PORT )) {
			socket.setSoTimeout( 1000 );
			flujoOut = new ObjectOutputStream(socket.getOutputStream());
			ObjectInputStream echoes = new ObjectInputStream(socket.getInputStream());
			do { 
				try {
					Object feedback = echoes.readObject();  
					if (feedback==null) { //TODO GESTIONAR NULO
					}
					//EN CASO DE SER UN OBJETO DE TIPO STRING LO QUE SE DEVUELVA POR PARTE DEL SERVIDOR
					else if (feedback instanceof String) { 
						if(((String) feedback).contentEquals("PonerATrue")) Window.recibido = true;
						else{
							//SE ABRE EL REPRODUCTOR CON EL ENLACE RECIBIDO
							Window.getWindow().vReproductor((String) feedback);
							Window.recibido = true;
						}
					}
					//EN CASO DE SER UN OBJETO DE TIPO BOOLEAN LO QUE SE DEVUELVA POR PARTE DEL SERVIDOR
					else if (feedback instanceof Boolean) { 
						//SE ASIGNA LA VARIABLE EXISTE DE LA VENTANA
						Window.existe = (boolean) feedback;
						Window.recibido = true;
					}
					//EN CASO DE SER UN OBJETO DE TIPO USUARIO LO QUE SE DEVUELVA POR PARTE DEL SERVIDOR
					else if (feedback instanceof Usuario) { 
						//SE ASIGNA LA VARIABLE USER DE LA VENTANA
						Window.user = (Usuario) feedback;
						Window.recibido = true;
					}
					//EN CASO DE SER UN OBJETO DE TIPO STRING ARRAY LO QUE SE DEVUELVA POR PARTE DEL SERVIDOR
					else if (feedback instanceof String[]) { 
						if (b) { // SOLO HAY DOS ARRAYS, CON EL BOOLEANO SE CONTROLA CUAL SE RELLENA PRIMERO
							b = false;
							Window.SlRecomendados = (String[]) feedback;
							Window.recibido = true;
						}else {
							Window.SlNovedades = (String[]) feedback;
							Window.recibido = true;
						}
					}
					//EN CASO DE SER UN OBJETO DE TIPO VIDEO LO QUE SE DEVUELVA POR PARTE DEL SERVIDOR
					else if (feedback instanceof Video) { 
						//SE ASIGNA LA VARIABLE VIDEO DE LA VENTANA
						Window.video = (Video) feedback;
						Window.recibido = true;
					}
				} catch (SocketTimeoutException e) {} 
			} while(!finComunicacion);

		} catch (Exception e) {
			//EN CASO DE ERROR SE TERMINA LA COMUNICACIÓN
			e.printStackTrace();
			finComunicacion = true;
		}
	}
}

