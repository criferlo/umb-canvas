package com.umb.util;

public class Constantes {

	public static String SOCIO="SOCIO";
	public static String ACTIVIDAD="ACTIVIDAD";
	public static String PROPUESTA="PROPUESTA";
	public static String RELACION_CLIENTE="RELACION";
	public static String SEGMENTO_CLIENTE="SEGMENTO";
	public static String RECURSOS_CLAVE="RECURSO";
	public static String CANALES_DISTRIBUCION="CANAL";
	public static String ESTRUCTURA_COSTOS="ESTRUCTURA";
	public static String FUENTES_INGRESO="INGRESO";
	
	public static String AMARILLO="AMARILLO";
	public static String AZUL="AZUL";
	public static String VERDE="VERDE";
	public static String ROJO="ROJO";			
	
	public static String NOMBRE_PDF="canvas.pdf";
	public static String NOMBRe_DIR="canvasumb";
	
	private static String sentenciaCrearTablaCanvas="create table canvas ("
			+ "id integer primary key,"
			+ "nombre text,"
			+ "descripcion text,"
			+ "creador text"
			+ ");";
	
	private static String sentenciaCrearTablaComponente="create table componente ("
			+ "id integer primary key,"
			+ "nombre text,"
			+ "descripcion text,"
			+ "canvas_id integer,"
			+ "tipocomponente text,"
			+ "color text,"
			+ " foreign key(canvas_id) references canvas(id)"			
			+ ");";
	
	
	private static String sentenciaBorrarTablaCanvas="drop table if exists canvas;";
	
	private static String sentenciaBorrarTablaComponente="drop table if exists componente;";
	
	private static String nombreBD="canvasbd";
	
	private static int versionBd=14;
	
	public static String getSentenciaCrearTablaCanvas() {
		return sentenciaCrearTablaCanvas;
	}

	public static String getSentenciaBorrarTablaCanvas() {
		return sentenciaBorrarTablaCanvas;
	}

	public static String getNombreBD() {
		return nombreBD;
	}

	public static int getVersionBd() {
		return versionBd;
	}

	public static String getSentenciaCrearTablaComponente() {
		return sentenciaCrearTablaComponente;
	}

	public static String getSentenciaBorrarTablaComponente() {
		return sentenciaBorrarTablaComponente;
	}

	

}
