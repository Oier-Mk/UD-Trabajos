package server;

import java.io.*;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.util.Vector;
import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JOptionPane;

import local.Window;

import java.net.ServerSocket;

/**
 * @author Mentxaka
 *
 */
public class Servidor {
	public static void main(String[] args) {
		inicioServidor();
	}

	// Atributos de comunicación
	private static Vector<HiloComunicacion> listaHilos; // Lista de hilos de comunicación  
	private static Vector<Socket> listaSockets;  
	private static boolean finComunicacion = false;
	public static final int PORT = 4000;
	public static Logger logger;

	/**
	 * INICIA LOS PARÁMETROS NECESARIOS PARA ENCENDER EL SERVIDOR´
	 * INICIA UN NUEVO LOGGER
	 * INICIA LA BASE DE DATOS
	 */
	public static void inicioServidor() {
		JOptionPane.showMessageDialog(null, "Inicio de servidor");
		logger = creacionDeLogger();
		Api.abrirConexion("src/dataBase/VIPStream.db");
		listaHilos = new Vector<>();
		Servidor s = new Servidor();
		(new Thread() {
			@Override
			public void run() {
				s.lanzaServidor();
			}
		}).start();
	}

	/**
	 * CREA UN SERVIDOR 
	 */
	public void lanzaServidor() {
		try(ServerSocket serverSocket = new ServerSocket( PORT )) {
			serverSocket.setSoTimeout( 5000 );  // Pone un timeout de 5 milisegundos
			while (!finComunicacion) {
				// Escucha una conexión por parte de cliente y se queda bloqueado hasta que no se recibe
				try {
					Socket socket = serverSocket.accept();   //ACEPTO NUEVOS CLIENTES Y ME QUEDO ESPERANDO HASTA QUE SE ACEPTA Y CUANDO SE ACEPTA ME QUEDO CON SU SOCKET
					// Ya hay un cliente conectado. Lanza un hilo que gestiona a partir de ahora la conexión con ese cliente
					// Nombra a cada cliente con el número secuencial que le corresponde
					HiloComunicacion hilo = new HiloComunicacion( socket );
					hilo.start();
				} catch (SocketTimeoutException e) {}  // Si es un timeout no es un error, simplemente se vuelve a intentar salvo que la ventana se cierre (finComunicacion)
			}
			serverSocket.close();  // Cierra el socket de servidor
		} catch(IOException e) {
			e.printStackTrace();
		}
	}


	/**
	 * @author Mentxaka
	 * INICIA UN BUCLE INFINITO A LA ESPERA DE PETICIONES POR PARTE DEL CIENTE
	 */
	private static class HiloComunicacion extends Thread {
		private Socket socket;  // socket de comunicación con cada cliente
		public HiloComunicacion(Socket socket) {
			this.socket = socket;
		}
		String EXISTE = "0000";
		String GETUSER = "1111";
		String FIN = "2222";
		String RECOMENDADOS = "3333";
		String NOVEDADES = "4444";
		String VIDEO = "5555";
		String PATH = "6666";
		String nick;
		String codigo;

		@Override
		public void run() {
			try {
				socket.setSoTimeout( 1000 ); // Pone el timeout para que no se quede eternamente en la espera (1)
				ObjectInputStream input = new ObjectInputStream(socket.getInputStream());  // Canal de entrada de socket (leer del cliente)
				ObjectOutputStream output = new ObjectOutputStream(socket.getOutputStream());  // Canal de salida de socket (escribir al cliente)
				listaHilos.add( this );
				while(!finComunicacion) {  // Bucle de comunicación de tiempo real con el cliente
					try {
						Object objRecibido = input.readObject();  // Espera a recibir petición de cliente (1) - se acaba con timeout
						if (objRecibido==null) {  // Si se recibe un objeto nulo es un error
							// TODO GESTIONAR SI ES NULO
						} 
						//EN CASO DE SER UN OBJETO DE TIPO STRING LO QUE SE PIDA EL CLIENTE
						else if(objRecibido instanceof String ) { 
							//LOS PRIMEROS 4 DIGITOS FORMAN UN CÓDIGO DE OPERACIÓN
							//EL RESTO DE LETRAS COMPONEN UN NICK O ID DEPENDIENDO DE SU CÓDIGO DE OPERACIÓN
							try {
								nick = ((String) objRecibido).substring(4);
								codigo = (String) ((String) objRecibido).subSequence(0,4);
							} catch (Exception e) {}
							//SE MANDA UN BOOLEANO EN CASO DE QUE EL USUARIO EXISTA
							if (codigo.contentEquals(EXISTE)) {
								output.writeObject( Api.checkObjeto(nick, "USUARIO") ); 
							}
							//SE MANDA UN OBJETO USUARIO
							//APARTE DEL CÓDIGO DE GETUSER SE SACA EL NICK DEL USUARIO PARA COGERLO DE LA BASE DE DATOS
							else if (codigo.contentEquals(GETUSER)){
								output.writeObject( Api.getObjectFromDB(nick, "USUARIO") ); 
							}
							//SE CIERRA LA CONEXIÓN
							else if(codigo.contentEquals(FIN)) {
								Api.cerrarConexion();
							}
							//SE MANDA EL ARRAY DE PELÍCULAS RECOMENDADAS
							else if (codigo.contentEquals(RECOMENDADOS)){
								output.writeObject( Api.getRecommendedArray(Window.user) ); 
							}
							//SE MANDA EL ARRAY DE PELÍCULAS NUEVAS
							else if (codigo.contentEquals(NOVEDADES)){
								output.writeObject( Api.getNewsArray() ); 
							}
							//SE MANDA EL VIDEO SOLICITADO
							//SE ESPECIFICA EL VÍDEO CON EL ID PASADO DESPUES DEL CÓDIGO 
							else if(codigo.contentEquals(VIDEO)){
								Video v = (Video) Api.getObjectFromDB((String) nick, "VIDEO");
								output.writeObject( v ); 
							}
							//SE MANDA EL PATH DEL VIDEO SOLICITADO
							//SE ESPECIFICA EL VÍDEO CON EL ID PASADO DESPUES DEL CÓDIGO 
							else if(codigo.contentEquals(PATH)) {
								output.writeObject(((Video) Api.getObjectFromDB(nick, "VIDEO")).getPath());								
							}
						}
						//EN CASO DE SER UN OBJETO DE TIPO USUARIO LO QUE SE PIDA EL CLIENTE
						else if(objRecibido instanceof Usuario ) {
							String nick = ((Usuario) objRecibido).getNick();
							//EN CASO DE QUE EL USUARIO EXISTA
							if (Api.checkObjeto(nick, "USUARIO")) {
								//MODIFICA EL USUARIO CON LOS ATRIBUTOS DE ESTOS USUARIOS
								Api.modifyUser((Usuario) objRecibido);
							}
							//EN CASO DE QUE EL USUARIO NO EXISTA
							else{
								//INTRODUCE EN LA BASE DE DATOS EL NUEVO USUARIO
								Api.addObjetoToDb(objRecibido);
							}
							output.writeObject( "PonerATrue" ); 

						}
					} catch (SocketTimeoutException e) {} // Excepción de timeout - no es un problema
				}
				socket.close();
				// Quita el cliente de las listas para que no se use en lo sucesivo
				int clienteACerrar = listaSockets.indexOf( socket );
				listaHilos.removeElementAt( clienteACerrar );
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * @return CREA UN NUEVO OBJETO DE TIPO LOGGER
	 */
	public static Logger creacionDeLogger() {
		try {
			Handler h = new FileHandler( "src/loggerOutput/logger2Output.xml", false );
			logger = Logger.getLogger( "loggerOutput" );
			logger.addHandler( h );
			logger.setLevel( Level.INFO );  
			h.setLevel( Level.INFO);  
		} catch (Exception e) {
			e.printStackTrace();
		}
		return logger;		
	}	 

}
