package server;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Esta clase representa un actor con sus distintos
 * datos característicos.
 */

public class Actor implements Serializable{
	private int id; //numero de identificación de actor en cuestión.
	private String nombre; //nombre del actor.
	private String apellido; // apellido del actor.
	private String descripcion; //descripción sobre el actor.
	private ArrayList<Video> videos; //arrayList con todos los videos que el actor ha protagonizado o ha formado parte.
	
	/**
	 * Construye un actor a partir de los atributos proporcionados. 
	 * @param id numero de identificación de actor en cuestión.
	 * @param nombre nombre del actor.
	 * @param apellido apellido del actor.
	 * @param descripción breve descripción sobre el actor.
	 * @param videos arrayList con todos los videos que el actor ha protagonizado o ha formado parte.
	 */
	
	public Actor(String id,String nombre, String apellido, String descripcion,ArrayList<Video> videos) {
		setId(id);
		setNombre(nombre);
		setApellido(apellido);
		setDescripcion(descripcion);
		setVideos(videos);
	}
	
	/**
	 * Obtiene el numero de identificaión del actor.
	 * @return numero que identifica al actor en cuestión.
	 */
	
	public int getId() {
		return id;
	}

	/**
	 * Modifica el id que es el número de identificación del actor.
	 * @param id número de identificación del actor.
	 */
	
	public void setId(String id) {
		this.id = Integer.parseInt(id);
	}
	
	/**
	 * Obtiene una descripción del actor.
	 * @return Descripción del actor en cuestión.
	 */
	
	public String getDescripcion() {
		return descripcion;
	}
	
	/**
	 * Modifica la descripción que es un texto que define al actor
	 * @param descripcion breve descripción sobre el actor.
	 */
	
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	/**
	 * Obtiene el nombre propio del actor.
	 * @return nombre propio del actor
	 */
	
	public String getNombre() {
		return nombre;
	}
	
	/**
	 * Modifica el nombre del actor.
	 * @param nombre nombre del actor.
	 */
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	/**
	 * Obtiene un arrayList que incluye todos los videos del actor.
	 * @return videos en los que aparce el actor en cuestión.
	 */
	
	public ArrayList<Video> getVideos() {
		return videos;
	}
	
	/**
	 * Modifica el arrayList de videos en el que aparece el actor.
	 * @param videos que aparece el actor.
	 */
	
	public void setVideos(ArrayList<Video> videos) {
		this.videos = videos;
	}
	
	/**
	 * Obtiene el apellido propio del actor.
	 * @return Apellidp propio del actor
	 */
	
	public String getApellido() {
		return apellido;
	}
	
	/**
	 * Modifica el apellido del actor.
	 * @param apellido apellido del actor.
	 */
	
	
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}
	
	/**
	 * Método toString que devueleve un solo string con todos los atributos del actor incluidos en el
	 * constructor de esta clase.
	 */
	
	@Override
	public String toString() {
		return "Actor [id=" + id + ", nombre=" + nombre + ", apellido=" + apellido + ", descripcion=" + descripcion
				+ ", videos=" + videos + "]";
	}
	
}