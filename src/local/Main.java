package local;

import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Main {
	public static Logger logger;
	public static void main(String[] args) {
		
		//SE CREA UN LOGGER
		logger = creacionDeLogger();

		//SE INICIA EL CLIENTES
		Cliente.iniciaCliente();

		//CREACION DE OBJETO VENTANA Y VISUALIZACION
		Window w = Window.getWindow();  
		w.setVisible(true); 
	}

	/**
	 * @return OBJETO DE TIPO LOGGER
	 */
	public static Logger creacionDeLogger() {
		try {
			Handler h = new FileHandler( "src/loggerOutput/loggerOutput.xml", false );
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
