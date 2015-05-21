package com.umb.datos.entidades;

public class EntidadComponente {

	private long id;
	private String nombre;
	private String descripcion;
	private long canvas_id;
	private String tipocomponente;
	private String color;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	public String getTipocomponente() {
		return tipocomponente;
	}
	public void setTipocomponente(String tipocomponente) {
		this.tipocomponente = tipocomponente;
	}
	public long getCanvas_id() {
		return canvas_id;
	}
	public void setCanvas_id(long canvas_id) {
		this.canvas_id = canvas_id;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	
	
	
}
