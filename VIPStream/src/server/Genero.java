package server;

import java.io.Serializable;

/**
 * Esta clase representa un genero de video con sus distintos
 * datos característicos.
 */

public class Genero implements Serializable{
	private int id;
	private String nombre;
	
	/**
	 * Construye un genero a partir de los atributos proporcionados. 
	 * @param id numero de identificación de del genero en cuestión.
	 * @param nombre nombre del genero.
	 */
	
	public Genero(String id,String nombre) {
		setId(id);
		setNombre(nombre);
	}
	
	/**
	 * Obtiene el numero de identificaión del genero.
	 * @return numero que identifica al genero.
	 */
	
	public int getId() {
		return id;
	}
	
	/**
	 * Modifica el id que es el número de identificación del genero.
	 * @param id número de identificación del genero.
	 */
	
	public void setId(String id) {
		this.id = Integer.parseInt(id);
	}
	
	/**
	 * Obtiene el nombre del genero.
	 * @return nombre del genero.
	 */
	
	public String getNombre() {
		return nombre;
	}
	
	/**
	 * Modifica el nombre del genero.
	 * @param nombre del genero.
	 */
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	/**
	 * Método toString que devueleve un solo string con todos los atributos de la temporada incluidos en el
	 * constructor de esta clase.
	 */
	
	@Override
	public String toString() {
		return "Genero [id=" + id + ", nombre=" + nombre + "]";
	}
}
