package server;

import java.io.Serializable;
import java.util.Date;

/**
 * Esta clase representa el contenido de los videos 
 * con sus distintos datos característicos para diferenciarlos unos de otros 
 */

public class Contenido implements Serializable{
	private int id; //Numero de identificación del contenido en cuestión.
	private String titulo; //Título del contenido.
	private String director; //Nombre del contenido.
	private int valoracion; //numero de valoración de cada contenido.
	private String sinopsis; //Sinopsis del video.
	private Date fecha; //Fecha de estreno del contenido.
	private String pathFoto; //Ruta del contenido.
	private Tipo tipo = null; //Tipo de contenido.
	private Genero genero = null; //Genero del contenido.

	/**
	 * Construye un contenido de video a partir de los atributos proporcionados. 
	 * @param id numero de identificación del contenido en cuestión.
	 * @param titulo titulo del contenido.
	 * @param director nombre del director.
	 * @param valoración valoración del contenido.
	 * @param sinopsis una breve descripción del contenido.
	 * @param fecha Fecha de estreno del contenido.
	 * @param pathFoto Ruta del contenido.
	 * @param tipo Tipo de contenido.
	 * @param genero Genero que define el contenido.
	 */
	
	public Contenido(String id, String titulo, String director, String valoracion, String sinopsis, String fecha, String pathFoto, Tipo tipo, Genero genero) {
		setId(id);
		setTitulo(titulo);
		setDirector(director);
		setValoracion(valoracion);
		setSinopsis(sinopsis);
		setFecha(fecha);
		setPathFoto(pathFoto);
		setTipo(tipo);
		setGenero(genero);

	}
	
	/**
	 * Obtiene el numero de identificaión del contenido de video.
	 * @return numero que identifica del contenido en cuestión.
	 */
	
	public int getId() {
		return id;
	}
	
	/**
	 * Modifica el id que es el número de identificación del contenido.
	 * @param id número de identificación del contenido
	 */

	public void setId(String id) {
		this.id = Integer.parseInt(id);
	}
	
	/**
	 * Obtiene el título del contenido de video.
	 * @return titulo del contenido en cuestión.
	 */
	
	public String getTitulo() {
		return titulo;
	}
	
	/**
	 * Modifica el título que es el nombre del contenido.
	 * @param título del contenido.
	 */

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	
	/**
	 * Obtiene el nombre del director del contenido.
	 * @return director del contenido.
	 */
	
	public String getDirector() {
		return director;
	}
	
	/**
	 * Modifica el nombre del director del contenido.
	 * @param director del contenido.
	 */

	public void setDirector(String director) {
		this.director = director;
	}
	
	/**
	 * Obtiene la valoración del contenido.
	 * @return valoración del contenido en cuestión.
	 */
	
	public int getValoracion() {
		return valoracion;
	}
	
	/**
	 * Modifica la valoración del contenido. La valoración solamente puede albergar
	 * valores numéricos entre el cero (incluido) y el cinco (incluido).
	 * @param valoración del contenido.
	 */

	public void setValoracion(String valoracion) {
		int v = Integer.parseInt(valoracion);
		if (v <= 5 && v >= 0 ) this.valoracion = v ;
	}
	
	/**
	 * Obtiene una breve sinopsis sobre contenido de video.
	 * @return sinopsis del contenido en cuestión.
	 */
	
	public String getSinopsis() {
		return sinopsis;
	}
	
	/**
	 * Modifica la sinopsis del contenido.
	 * @param sinopsis del contenido de video.
	 */

	public void setSinopsis(String sinopsis) {
		this.sinopsis = sinopsis;
	}
	
	/**
	 * Obtiene la fecha de estreno del contenido de video.
	 * @return fecha del contenido en cuestión.
	 */
	
	public Date getFecha() {
		return fecha;
	}
	
	/**
	 * Convierte de String a date
	 * @param fecha cadena de texto de la fecha de estreno del contenido
	 */
	
	@SuppressWarnings("deprecation")
	public void setFecha(String fecha) {
		Date d = new Date();
		int dia = fecha.charAt(0)+fecha.charAt(1);
		d.setDate(dia);
		int mes = fecha.charAt(3)+fecha.charAt(4);
		d.setMonth(mes);
		int anyo = fecha.charAt(6)+fecha.charAt(7)+fecha.charAt(8)+fecha.charAt(9);
		d.setYear(anyo);		
		this.fecha = d;//TODO FILTRAR ESTO 	
	}
	
	/**
	 * Obtiene la ruta dónde se encuentra el contenido del vídeo.
	 * @return pathFoto del contenido.
	 */
	
	public String getPathFoto() {
		return pathFoto;
	}
	
	/**
	 * Modifica la ruta dónde se encuentra el contenido de vídeo.
	 * @param pathFoto del contenido.
	 */

	public void setPathFoto(String pathFoto) {
		this.pathFoto = pathFoto;
	}
	
	/**
	 *Obtiene el tipo de contenido del vídeo.
	 * @return tipo de contenido.
	 */
	
	public Tipo getTipo() {
		return tipo;
	}
	
	/**
	 * Modifica tipo de contenido.
	 * @param tipo de contenido.
	 */

	public void setTipo(Tipo tipo) {
		this.tipo = tipo;
	}
	
	/**
	 * Obtiene el gérnero del contenido de video.
	 * @return genero del contenido en cuestión.
	 */
	
	public Genero getGenero() {
		return genero;
	}
	
	/**
	 * Modifica el género que clasifica el contenido.
	 * @param genero del contenido.
	 */

	public void setGenero(Genero genero) {
		this.genero = genero;
	}
	
	/**
	 * Método toString que devueleve un solo string con todos los atributos del contenido incluidos en el
	 * constructor de esta clase.
	 */
	
	@Override
	public String toString() {
		return "Contenido [id=" + id + ", titulo=" + titulo + ", director=" + director + ", valoracion=" + valoracion
				+ ", sinopsis=" + sinopsis + ", fecha=" + fecha + ", pathFoto=" + pathFoto + ", tipo=" + tipo
				+ ", genero=" + genero + "]";
	}

}