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
	
	private static String sentenciaCrearTablaPlan="create table plan ("
			+ "id integer primary key,"
			+ "nombre text,"
			+ "descripcion text,"
			+ "creador text"
			+ ");";
	
	private static String sentenciaCrearTablaComponente="create table componente ("
			+ "id integer primary key,"
			+ "nombre text,"
			+ "descripcion text,"
			+ "plan_id integer,"
			+ "tipocomponente text,"
			+ " foreign key(plan_id) references plan(id)"			
			+ ");";
	
	
	private static String sentenciaBorrarTablaPlan="drop table if exists plan;";
	
	private static String sentenciaBorrarTablaComponente="drop table if exists componente;";
	
	private static String nombreBD="canvasbd";
	
	private static int versionBd=10;
	

	public static  String getSentenciaCrearTablaPlan() {
		return sentenciaCrearTablaPlan;
	}

	public static String getSentenciaBorrarTablaPlan() {
		return sentenciaBorrarTablaPlan;
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
