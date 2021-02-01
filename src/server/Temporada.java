package server;

import java.io.Serializable;

/**
 * Esta clase representa una temporada con sus distintos
 * datos característicos.
 */


public class Temporada implements Serializable{
	private int id;
	private int numero;
	private Contenido contenido;
	
	/**
	 * Construye una temporada a partir de los atributos proporcionados. 
	 * @param id numero de identificación de la temporada en cuestión.
	 * @param numero nombre de la temporada.
	 * @param contenido de la temporada.
	 */
	
	public Temporada(String id,String numero,Contenido contenido) {
		setId(id);
		setNumero(numero);
		setContenido(contenido);
	}
	/**
	 * Obtiene el numero de identificaión de la temporada.
	 * @return numero que identifica de la temporada en cuestión.
	 */
	
	public int getId() {
		return id;
	}
	
	/**
	 * Modifica el id que es el número de identificación de la temporada.
	 * @param id número de identificación de la temporada.
	 */
	
	public void setId(String id) {
		this.id = Integer.parseInt(id);
	}
	
	/**
	 * Obtiene el numero de la temprada.
	 * @return numero de temporada del contenido visual.
	 */
	
	public int getNumero() {
		return numero;
	}
	
	/**
	 * Modifica el numero de la temporada.
	 * @param numero de temporada.
	 */
	
	public void setNumero(String numero) {
		this.numero = Integer.parseInt(numero);
	}
	
	/**
	 * Obtiene el contenido de la temporada.
	 * @return contenido de la temporada.
	 */
	
	public Contenido getContenido() {
		return contenido;
	}
	
	/**
	 * Modifica el contenido de la temporada en cuestión.
	 * @param contenido de la temporada.
	 */
	
	public void setContenido(Contenido contenido) {
		this.contenido = contenido;
	}
	
	/**
	 * Método toString que devueleve un solo string con todos los atributos de la temporada incluidos en el
	 * constructor de esta clase.
	 */
	
	@Override
	public String toString() {
		return "Temporada [id=" + id + ", numero=" + numero + ", contenido=" + contenido + "]";
	}
	
}
