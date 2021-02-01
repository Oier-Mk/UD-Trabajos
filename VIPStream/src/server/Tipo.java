package server;

import java.io.Serializable;

public class Tipo implements Serializable{
	private int id;
	private String nombre;
	
	public Tipo(String id,String nombre) {
		setId(id);
		setNombre(nombre);
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
	@Override
	public String toString() {
		return "Tipo [id=" + id + ", nombre=" + nombre + "]";
	}
	
}
