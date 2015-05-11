package com.umb.util;

public class Constantes {

	private static String sentenciaCrearTablaPlan="create table plan ("
			+ "id integer primary key,"
			+ "nombre text,"
			+ "descripcion text,"
			+ "creador text"
			+ ");";
	
	private static String sentenciaCrearTablaTipoComponente="create table tipocomponente ("
			+ "id integer primary key,"
			+ "nombre text"			
			+ ");";
	
	private static String sentenciaCrearTablaComponente="create table componente ("
			+ "id integer primary key,"
			+ "nombre text,"
			+ "descripcion text,"
			+ "plan_id integer,"
			+ "tipocomponente_id integer,"
			+ " foreign key(plan_id) references plan(id),"
			+ " foreign key(tipocomponente_id) references tipocomponente(id)"
			+ ");";
	
	
	
	private static String sentenciaBorrarTablaPlan="drop table if exists plan;";
	
	private static String sentenciaBorrarTablaTipoComponente="drop table if exists tipocomponente;";
	
	private static String sentenciaBorrarTablaComponente="drop table if exists componente;";
	
	private static String nombreBD="canvasbd";
	
	private static int versionBd=9;
	

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

	public static String getSentenciaCrearTablaTipoComponente() {
		return sentenciaCrearTablaTipoComponente;
	}

	public static String getSentenciaCrearTablaComponente() {
		return sentenciaCrearTablaComponente;
	}

	public static String getSentenciaBorrarTablaTipoComponente() {
		return sentenciaBorrarTablaTipoComponente;
	}

	public static String getSentenciaBorrarTablaComponente() {
		return sentenciaBorrarTablaComponente;
	}

	

}
