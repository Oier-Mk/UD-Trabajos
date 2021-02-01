package server;

import java.io.Serializable;

public class Video implements Serializable{
	private int id;
	private String nombre;
	private String path;
	private int valoracion;
	private Contenido contenido = null;
	private Temporada temporada = null;

	public Video(String id, String nombre, String path, String valoracion, Contenido contenido) {
		setId(id);
		setNombre(nombre);
		setPath(path);
		setValoracion(valoracion);
		setContenido(contenido);
	}
	public Video(String id, String nombre, String path, String valoracion, Temporada temporada) {
		setId(id);
		setNombre(nombre);
		setPath(path);
		setValoracion(valoracion);
		setTemporada(temporada);
	}

	public int getId() {
		return id;
	}

	public void setId(String id) {
		this.id = Integer.parseInt(id);
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public int getValoracion() {
		return valoracion;
	}

	@SuppressWarnings("null")
	public void setValoracion(String valoracion) {
		int n = Integer.parseInt(valoracion);
		if(n <= 0 || n >= 5) {
			this.valoracion = n;
		}else {
			this.valoracion = (Integer) null;
		}
	}
	public Contenido getContenido() {
		return contenido;
	}
	public void setContenido(Contenido contenido) {
		this.contenido = contenido;
	}
	public Temporada getTemporada() {
		return temporada;
	}
	public void setTemporada(Temporada temporada) {
		this.temporada = temporada;
	}
	@Override
	public String toString() {
		return "Video [id=" + id + ", nombre=" + nombre + ", path=" + path + ", valoracion=" + valoracion
				+ ", contenido=" + contenido + ", temporada=" + temporada + "]";
	}




}
