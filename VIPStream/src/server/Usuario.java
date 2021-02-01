package server;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

import org.junit.runners.Parameterized.Parameter;

/**
 * Esta clase representa un usuario con sus distintos
 * datos característicos.
 */

public class Usuario implements Serializable{
	private static final long serialVersionUID = 1L;
	private String nombre;
	private String apellido;
	private String pathFoto;
	private int numCuenta;
	private int numMovil;
	private String email;
	private Date fechaNacimiento;
	private ArrayList<Video> reproduccionEnCurso;
	private String contrasenya;
	private String nick;

	/**
	 * Construye un usuario a partir de los atributos proporcionados. 
	 * @param nick nombre de identificación del usuario en cuestión.
	 * @param nombre nombre del usuario.
	 * @param apellido apellido del usuario.
	 * @param numCuenta cadena de diguitos que indican el numero de cuenta del usuario.
	 * @param pathFoto pathFoto Ruta del contenido.
	 * @param numMovil cadena de diguitos que indican el numero de telefono del usuario.
	 * @param email cadena de caracteres que corresponde al email del usuario. 
	 * @param contrasenya contraseña que utiliza el usuario para proteger su cuenta.
	 * @param fechaNacimineto fecha de naciemiento del usuario.
	 * @param video arrayList con todos los videos que el usuario ha visualizado o están en reproducción.
	 */
	
	public Usuario(String nick, String nombre, String apellido, String numCuenta, String pathFoto, 
			String numMovil, String email, String contrasenya, String fechaNacimiento,	ArrayList<Video> arrayList) {
		setNick(nick);
		setNombre(nombre);
		setApellido(apellido);
		setNumCuenta(numCuenta);
		setPathFoto(pathFoto);
		setNumMovil(numMovil);
		setEmail(email);
		setContrasenya(contrasenya);
		setFechaNacimiento(fechaNacimiento);
		setReproduccionEnCurso(arrayList);
	}


	/**
	 * Obtiene el nombre del usuario.
	 * @return nombre que tiene el usuario que se registra en la plataforma.
	 */
	
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	/**
	 * Obtiene el apellido del usuario.
	 * @return apellido que tiene el usuario que se registra en la plataforma.
	 */
	
	public String getApellido() {
		return apellido;
	}
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}
	
	/**
	 * Obtiene la ruta dónde se encuentra la foto del usuario.
	 * @return pathFoto del usuario.
	 */
	
	public String getPathFoto() {
		return pathFoto;
	}
	
	/**
	 * Modifica la ruta dónde se encuentra la fot del usuario.
	 * @param pathFoto del usuario.
	 */

	public void setPathFoto(String pathFoto) {
		this.pathFoto = pathFoto;
	}
	
	/**
	 * Obtiene el numero que indican el numero de cuenta del usuario..
	 * @return numero de cuenta del usuario.
	 */
	
	public int getNumCuenta() {
		return numCuenta;
	}
	
	/**
	 * Modifica el numero de cuenta del usuario partiendo de un string y lo modifica a un int.
	 * @param numCuenta numero de cuenta del usuario.
	 */
	
	public void setNumCuenta(String numCuenta) {
		try {
			this.numCuenta = Integer.parseInt(numCuenta);
		} catch (Exception e) {
		}
	}
	
	/**
	 * Obtiene el numero de movil del usuario.
	 * @return numero de telefono del usuario.
	 */
	
	public int getNumMovil() {
		return numMovil;
	}

	/**
	 * Modifica el numero de telefono del usuario partiendo de un string y lo modifica a un int.
	 * @param numMovil numero de telefono del usuario.
	 */
	
	public void setNumMovil(String numMovil) {
		try {
			this.numMovil = Integer.parseInt(numMovil);
		} catch (Exception e) {
		}
	}
	
	/**
	 * Obtiene el email del usuario.
	 * @return email del usuario.
	 */
	
	public String getEmail() {
		return email;
	}
	
	/**
	 * Modifica el email del usuario de forma que est contenga obligatoriamente un @.
	 * @param email del usuario.
	 */
	
	public void setEmail(String email) {
		try {
			int arroba = email.indexOf('@');
			int dot = email.indexOf('.');
			if (arroba != -1 && dot != -1) {
				this.email = email;
			}else{
				Integer.parseInt("s");
			}
		} catch (Exception e) {
		}
	}
	
	/**
	 * Obtiene fecha de naciemiento del usuario.
	 * @return fechaNacimiento del usuario.
	 */
	
	public Date getFechaNacimiento() {
		return fechaNacimiento;
	}
	
	/**
	 * Convierte de String a date
	 * @param fecha cadena de texto de la fecha de naciemiento del usuario.
	 */
	
	@SuppressWarnings("deprecation")
	public void setFechaNacimiento(String fechaNacimiento) {
		Date d = new Date();
		int dia = fechaNacimiento.charAt(0)+fechaNacimiento.charAt(1);
		d.setDate(dia);
		int mes = fechaNacimiento.charAt(3)+fechaNacimiento.charAt(4);
		d.setMonth(mes);
		int anyo = fechaNacimiento.charAt(6)+fechaNacimiento.charAt(7)+fechaNacimiento.charAt(8)+fechaNacimiento.charAt(9);
		d.setYear(anyo);		
		this.fechaNacimiento = d;
	}
	
	/**
	 * Obtiene un arrayList que incluye todos los videos que ha puede visualizar el usuario.
	 * @return videos que el usuario puede ver.
	 */
	
	public ArrayList<Video> getReproduccionEnCurso() {
		return reproduccionEnCurso;
	}
	
	/**
	 * Modifica el arrayList de videos que puede ver el usuario.
	 * @param videos que el usuario puede ver.
	 */
	
	public void setReproduccionEnCurso(ArrayList<Video> reproduccionEnCurso) {
		this.reproduccionEnCurso = reproduccionEnCurso;
	}
	
	/**
	 * Obtiene la contraseña del usuario.
	 * @return cotrasenya del usuario.
	 */
	
	public String getContrasenya() {
		return contrasenya;
	}
	
	/**
	 * Modifica la contraseña del usuario.
	 * @param contrasenya del usuario.
	 */
	
	public void setContrasenya(String contrasenya) {
		this.contrasenya = contrasenya;
	}
	
	/**
	 * Obtiene el nick del usuario.
	 * @return nick del usuario.
	 */
	
	public String getNick() {
		return this.nick;
	}
	
	/**
	 * Modifica el nick del usuario.
	 * @param nick del usuario.
	 */
	
	public void setNick(String nick) {
		this.nick = nick;
	}
	
	/**
	 * Método toString que devueleve un solo string con todos los atributos del usuario incluidos en el
	 * constructor de esta clase.
	 */
	
	@Override
	public String toString() {
		return "Usuario [nombre=" + nombre + ", apellido=" + apellido + ", pathFoto=" + pathFoto + ", numCuenta="
				+ numCuenta + ", numMovil=" + numMovil + ", email=" + email + ", fechaNacimiento=" + fechaNacimiento
				+ ", contrasenya=" + contrasenya + ", nick=" + nick + "]";
	}

}
