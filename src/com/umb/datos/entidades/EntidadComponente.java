package com.umb.datos.entidades;

public class EntidadComponente {

	private long id;
	private String nombre;
	private String descripcion;
	private long plan_id;
	private long tipocomponente_id;
	
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
	public long getPlan_id() {
		return plan_id;
	}
	public void setPlan_id(long plan_id) {
		this.plan_id = plan_id;
	}
	public long getTipocomponente_id() {
		return tipocomponente_id;
	}
	public void setTipocomponente_id(long tipocomponente_id) {
		this.tipocomponente_id = tipocomponente_id;
	}
	
	
}
