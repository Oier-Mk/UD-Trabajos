package usefullClasses;

import java.awt.Point;

/**
 * @author mentxaka
 *
 */
public class Boton {


//	COORDENADA DEL BOTON EN EL EJE X
	int x;
//	COORDENADA DEL BOTON EN EL EJE Y
	int y;	
//	TAMAÑO DEL BOTON EN PIXELES
	int tamanyoX;
	int tamanyoY;
//	NOMBRE DEL BOTON
	String imagen;  


	
	/**
	 *	POSICION EN X, POSICION EN Y, TAMAÑO EN X, TAMAÑO EN Y, VENTANA EN LA QUE SE VERA, PATH DE LA IMAGEN DEL BOTON
	 */
	public Boton(int x, int y,int tamanyoX, int tamanyoY, VentanaGrafica ventana, String imagen) {
		setX(x);
		setY(y);
		this.tamanyoX = tamanyoX;
		this.tamanyoY = tamanyoY;
		this.imagen = imagen;
	}
	/**
	 * @return	DEVUELVE X
	 */
	public int getX() {
		return x;
	}

	/**
	 * @param x ASIGNA X
	 */
	public void setX(int x) {
		this.x = x;
	}

	/**
	 * @return DEVUELVE Y
	 */
	public int getY() {
		return y;
	}

	/**
	 * @param y	ASIGNA Y
	 */
	public void setY(int y) {
		this.y = y;
	}

	/**
	 * @return	DEVUELVE TAMAÑO EN X
	 */
	public int getTamanyoX() {
		return tamanyoX;
	}

	/**
	 * @param tamanyoX	ASIGNA TAMAÑO EN X
	 */
	public void setTamanyoX(int tamanyoX) {
		this.tamanyoX = tamanyoX;
	}

	/**
	 * @return	DEVUELVE TAMAÑO EN Y
	 */
	public int getTamanyoY() {
		return tamanyoY;
	}

	/**
	 * @param tamanyoY	ASIGNA TAMAÑO EN Y
	 */
	public void setTamanyoY(int tamanyoY) {
		this.tamanyoY = tamanyoY;
	}


	/**
	 * @return	DEVUELVE IMAGEN DEL BOTON
	 */
	public String getImagen() {
		return imagen;
	}
	
	/**
	 * @param imagen	ASIGNA IMAGEN AL BOTON
	 */
	public void setImagen(String imagen) {
		this.imagen = imagen;
	}

	/**
	 * @param ventana	NECESITA LA VENTANA EN LA QUE SE ENCUENTRA EL BOTON
	 * @return	DEVUELVE UN BOOLEANO, SI EL BOTÓN SE HA PULSADO O NO
	 */
	public boolean estaPulsado(VentanaGrafica ventana) {

		boolean estaPulsado = false;

		Point puls = ventana.getRatonPulsado();

		if (puls != null) {
			if (puls.getX() > x-tamanyoX/2 && puls.getX() < x+tamanyoX/2 && puls.getY() > y-tamanyoY/2 && puls.getY() < y+tamanyoY/2) {
				estaPulsado = true;
			}

		}

		return estaPulsado;

	}




	/**
	 * DIBUJA EN PANTALLA EL BOTON
	 * @param ventana	NECESITA ESPECIFICAR LA VENTANA
	 */
	public void dibujar(VentanaGrafica ventana) {
		ventana.dibujaImagen( imagen, x, y, tamanyoX, tamanyoY, 1, 0, 1.0f );
	}


	



}