package com.umb.util;

import com.umb.datos.entidades.EntidadCanvas;
import com.umb.datos.entidades.EntidadComponente;

public class Constantes {

	public static String SOCIO = "SOCIO";
	public static String ACTIVIDAD = "ACTIVIDAD";
	public static String PROPUESTA = "PROPUESTA";
	public static String RELACION_CLIENTE = "RELACION";
	public static String SEGMENTO_CLIENTE = "SEGMENTO";
	public static String RECURSOS_CLAVE = "RECURSO";
	public static String CANALES_DISTRIBUCION = "CANAL";
	public static String ESTRUCTURA_COSTOS = "ESTRUCTURA";
	public static String FUENTES_INGRESO = "INGRESO";

	public static String AMARILLO = "AMARILLO";
	public static String AZUL = "AZUL";
	public static String VERDE = "VERDE";
	public static String ROJO = "ROJO";

	public static String NOMBRE_PDF = "canvas.pdf";
	public static String NOMBRe_DIR = "canvasumb";

	public static final int ABRIRFICHERO_RESULT_CODE = 1;

	private static String sentenciaCrearTablaCanvas = "create table canvas ("
			+ "id integer primary key," + "nombre text," + "descripcion text,"
			+ "creador text" + ");";

	private static String sentenciaCrearTablaComponente = "create table componente ("
			+ "id integer primary key,"
			+ "nombre text,"
			+ "descripcion text,"
			+ "canvas_id integer,"
			+ "tipocomponente text,"
			+ "color text,"
			+ " foreign key(canvas_id) references canvas(id)" + ");";

	private static String sentenciaBorrarTablaCanvas = "drop table if exists canvas;";

	private static String sentenciaBorrarTablaComponente = "drop table if exists componente;";

	private static String nombreBD = "canvasbd";

	private static int versionBd = 15;

	public static String AUTOR_EJEMPLO = "TeleHuerta";

	public static String DESCRIPCION_EJEMPLO = "Es una empresa de servicios que tiene como objetivo diseñar y planificar huertos llave en mano telegestionados,  que se comercializan como una actividad de ocio para familias y empresas en la zona de Bogotá";

	public static String NOMBRE_EJEMPLO="Modelo canvas TeleHuerta";

	private EntidadCanvas canvas  = new EntidadCanvas();
	private EntidadComponente cliente1 = new EntidadComponente();
	private EntidadComponente cliente2 = new EntidadComponente();
	private EntidadComponente prop1 = new EntidadComponente();
	private EntidadComponente prop2 = new EntidadComponente();
	private EntidadComponente canal1 = new EntidadComponente();
	private EntidadComponente canal2 = new EntidadComponente();	
	private EntidadComponente canal3 = new EntidadComponente();
	private EntidadComponente relcli1 = new EntidadComponente();
	private EntidadComponente relcli2 = new EntidadComponente();
	private EntidadComponente fu1 = new EntidadComponente();
	private EntidadComponente fu2 = new EntidadComponente();
	private EntidadComponente fu3 = new EntidadComponente();
	private EntidadComponente fu4 = new EntidadComponente();
	private EntidadComponente ac1 = new EntidadComponente();
	private EntidadComponente ac2 = new EntidadComponente();
	private EntidadComponente ac3 = new EntidadComponente();
	private EntidadComponente rc1 = new EntidadComponente();
	private EntidadComponente rc2 = new EntidadComponente();
	private EntidadComponente rc3 = new EntidadComponente();
	private EntidadComponente rc4 = new EntidadComponente();
	private EntidadComponente so1 = new EntidadComponente();
	private EntidadComponente so2 = new EntidadComponente();
	private EntidadComponente ecos1 = new EntidadComponente();
	private EntidadComponente ecos2 = new EntidadComponente();
	private EntidadComponente ecos3 = new EntidadComponente();
	
	public Constantes(){
		
		
		canvas.setAutor(Constantes.AUTOR_EJEMPLO);		
		canvas.setDescripcion(Constantes.DESCRIPCION_EJEMPLO);
		canvas.setNombre(Constantes.NOMBRE_EJEMPLO);
		
		//clientes
		cliente1.setColor(Constantes.AMARILLO);
		cliente1.setDescripcion("Familias con zonas cultivables");
		cliente1.setNombre("Familias con zonas cultivables");
		cliente1.setTipocomponente(Constantes.SEGMENTO_CLIENTE);
		cliente2.setColor(Constantes.ROJO);
		cliente2.setDescripcion("Empresas de horticultura");
		cliente2.setNombre("Empresas de horticultura");
		cliente2.setTipocomponente(Constantes.SEGMENTO_CLIENTE);
		
		//propuestas de valor
		prop1.setColor(Constantes.AZUL);
		prop1.setDescripcion("Ocio en familia al aire libre");
		prop1.setNombre("Ocio en familia al aire libre");
		prop1.setTipocomponente(Constantes.PROPUESTA);
		prop2.setColor(Constantes.VERDE);
		prop2.setDescripcion("Fácil gestión de huertos, con menos desplazamientos");
		prop2.setNombre("Fácil gestión de huertos, con menos desplazamientos");
		prop2.setTipocomponente(Constantes.PROPUESTA);
		
		//canales
		canal1.setColor(Constantes.AMARILLO);
		canal1.setDescripcion("Web");
		canal1.setNombre("Web");
		canal1.setTipocomponente(Constantes.CANALES_DISTRIBUCION);
		canal2.setColor(Constantes.AZUL);
		canal2.setDescripcion("Establecimiento centro comercial");
		canal2.setNombre("Establecimiento centro comercial");
		canal2.setTipocomponente(Constantes.CANALES_DISTRIBUCION);
		canal3.setColor(Constantes.ROJO);
		canal3.setDescripcion("Visitas comerciales");
		canal3.setNombre("Visitas comerciales");
		canal3.setTipocomponente(Constantes.CANALES_DISTRIBUCION);
		
		//relacion cliente
		relcli1.setColor(Constantes.ROJO);
		relcli1.setDescripcion("Relación personal - se mantiene en el tiempo");
		relcli1.setNombre("Relación personal - se mantiene en el tiempo");
		relcli1.setTipocomponente(Constantes.RELACION_CLIENTE);
		relcli2.setColor(Constantes.VERDE);
		relcli2.setDescripcion("Red social - web");
		relcli2.setNombre("Red social - web");
		relcli2.setTipocomponente(Constantes.RELACION_CLIENTE);
		
		//fuente de ingresos
		fu1.setColor(Constantes.AZUL);
		fu1.setDescripcion("Diseño huertos");
		fu1.setNombre("Diseño huertos");
		fu1.setTipocomponente(Constantes.FUENTES_INGRESO);
		fu2.setColor(Constantes.ROJO);
		fu2.setDescripcion("Servicio post-venta");
		fu2.setNombre("Servicio post-venta");
		fu2.setTipocomponente(Constantes.FUENTES_INGRESO);
		fu3.setColor(Constantes.AMARILLO);
		fu3.setDescripcion("Suministros");
		fu3.setNombre("Suministros");
		fu3.setTipocomponente(Constantes.FUENTES_INGRESO);
		fu4.setColor(Constantes.AZUL);
		fu4.setDescripcion("Anunciantes web");
		fu4.setNombre("Anunciantes web");
		fu4.setTipocomponente(Constantes.FUENTES_INGRESO);
		
		//actividades clave
		
		ac1.setColor(Constantes.VERDE);
		ac1.setDescripcion("Diseño y ejecución llave en mano huertos");
		ac1.setNombre("Diseño y ejecución llave en mano huertos");
		ac1.setTipocomponente(Constantes.ACTIVIDAD);
		ac2.setColor(Constantes.AZUL);
		ac2.setDescripcion("Gestión de web");
		ac2.setNombre("Gestión de web");
		ac2.setTipocomponente(Constantes.ACTIVIDAD);
		ac3.setColor(Constantes.AZUL);
		ac3.setDescripcion("Punto de atención al cliente");
		ac3.setNombre("Punto de atención al cliente");
		ac3.setTipocomponente(Constantes.ACTIVIDAD);
		
		//recursos clave
		rc1.setColor(Constantes.AMARILLO);
		rc1.setDescripcion("Perfiles profesionales");
		rc1.setNombre("Perfiles profesionales");
		rc1.setTipocomponente(Constantes.RECURSOS_CLAVE);
		rc2.setColor(Constantes.AZUL);
		rc2.setDescripcion("Web interactiva");
		rc2.setNombre("Web interactiva");
		rc2.setTipocomponente(Constantes.RECURSOS_CLAVE);
		rc3.setColor(Constantes.AZUL);
		rc3.setDescripcion("Local");
		rc3.setNombre("Local");
		rc3.setTipocomponente(Constantes.RECURSOS_CLAVE);
		rc4.setColor(Constantes.AZUL);
		rc4.setDescripcion("Know how");
		rc4.setNombre("Know how");
		rc4.setTipocomponente(Constantes.RECURSOS_CLAVE);
		
		//socios
		so1.setColor(Constantes.AZUL);
		so1.setDescripcion("Proveedor tecnológico: sensores dispositivos");
		so1.setNombre("Proveedor tecnológico: sensores dispositivos");
		so1.setTipocomponente(Constantes.SOCIO);
		so2.setColor(Constantes.AZUL);
		so2.setDescripcion("Punto de atención al cliente: helpdesk");
		so2.setNombre("Punto de atención al cliente: helpdesk");
		so2.setTipocomponente(Constantes.SOCIO);	
		
		
		//estructura de costos
		ecos1.setColor(Constantes.AZUL);
		ecos1.setDescripcion("Sueldos y comisiones");
		ecos1.setNombre("Sueldos y comisiones");
		ecos1.setTipocomponente(Constantes.ESTRUCTURA_COSTOS);
		ecos2.setColor(Constantes.AZUL);
		ecos2.setDescripcion("Alquiler local");
		ecos2.setNombre("Alquiler local");
		ecos2.setTipocomponente(Constantes.ESTRUCTURA_COSTOS);
		ecos3.setColor(Constantes.AZUL);
		ecos3.setDescripcion("Diseño y mantenimiento web");
		ecos3.setNombre("Diseño y mantenimiento web");
		ecos3.setTipocomponente(Constantes.ESTRUCTURA_COSTOS);
		
	}
	
	
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


	public static String getSOCIO() {
		return SOCIO;
	}


	public static void setSOCIO(String sOCIO) {
		SOCIO = sOCIO;
	}


	public static String getACTIVIDAD() {
		return ACTIVIDAD;
	}


	public static void setACTIVIDAD(String aCTIVIDAD) {
		ACTIVIDAD = aCTIVIDAD;
	}


	public static String getPROPUESTA() {
		return PROPUESTA;
	}


	public static void setPROPUESTA(String pROPUESTA) {
		PROPUESTA = pROPUESTA;
	}


	public static String getRELACION_CLIENTE() {
		return RELACION_CLIENTE;
	}


	public static void setRELACION_CLIENTE(String rELACION_CLIENTE) {
		RELACION_CLIENTE = rELACION_CLIENTE;
	}


	public static String getSEGMENTO_CLIENTE() {
		return SEGMENTO_CLIENTE;
	}


	public static void setSEGMENTO_CLIENTE(String sEGMENTO_CLIENTE) {
		SEGMENTO_CLIENTE = sEGMENTO_CLIENTE;
	}


	public static String getRECURSOS_CLAVE() {
		return RECURSOS_CLAVE;
	}


	public static void setRECURSOS_CLAVE(String rECURSOS_CLAVE) {
		RECURSOS_CLAVE = rECURSOS_CLAVE;
	}


	public static String getCANALES_DISTRIBUCION() {
		return CANALES_DISTRIBUCION;
	}


	public static void setCANALES_DISTRIBUCION(String cANALES_DISTRIBUCION) {
		CANALES_DISTRIBUCION = cANALES_DISTRIBUCION;
	}


	public static String getESTRUCTURA_COSTOS() {
		return ESTRUCTURA_COSTOS;
	}


	public static void setESTRUCTURA_COSTOS(String eSTRUCTURA_COSTOS) {
		ESTRUCTURA_COSTOS = eSTRUCTURA_COSTOS;
	}


	public static String getFUENTES_INGRESO() {
		return FUENTES_INGRESO;
	}


	public static void setFUENTES_INGRESO(String fUENTES_INGRESO) {
		FUENTES_INGRESO = fUENTES_INGRESO;
	}


	public static String getAMARILLO() {
		return AMARILLO;
	}


	public static void setAMARILLO(String aMARILLO) {
		AMARILLO = aMARILLO;
	}


	public static String getAZUL() {
		return AZUL;
	}


	public static void setAZUL(String aZUL) {
		AZUL = aZUL;
	}


	public static String getVERDE() {
		return VERDE;
	}


	public static void setVERDE(String vERDE) {
		VERDE = vERDE;
	}


	public static String getROJO() {
		return ROJO;
	}


	public static void setROJO(String rOJO) {
		ROJO = rOJO;
	}


	public static String getNOMBRE_PDF() {
		return NOMBRE_PDF;
	}


	public static void setNOMBRE_PDF(String nOMBRE_PDF) {
		NOMBRE_PDF = nOMBRE_PDF;
	}


	public static String getNOMBRe_DIR() {
		return NOMBRe_DIR;
	}


	public static void setNOMBRe_DIR(String nOMBRe_DIR) {
		NOMBRe_DIR = nOMBRe_DIR;
	}


	public static String getAUTOR_EJEMPLO() {
		return AUTOR_EJEMPLO;
	}


	public static void setAUTOR_EJEMPLO(String aUTOR_EJEMPLO) {
		AUTOR_EJEMPLO = aUTOR_EJEMPLO;
	}


	public static String getDESCRIPCION_EJEMPLO() {
		return DESCRIPCION_EJEMPLO;
	}


	public static void setDESCRIPCION_EJEMPLO(String dESCRIPCION_EJEMPLO) {
		DESCRIPCION_EJEMPLO = dESCRIPCION_EJEMPLO;
	}


	public static String getNOMBRE_EJEMPLO() {
		return NOMBRE_EJEMPLO;
	}


	public static void setNOMBRE_EJEMPLO(String nOMBRE_EJEMPLO) {
		NOMBRE_EJEMPLO = nOMBRE_EJEMPLO;
	}


	public EntidadCanvas getCanvas() {
		return canvas;
	}


	public void setCanvas(EntidadCanvas canvas) {
		this.canvas = canvas;
	}


	public EntidadComponente getCliente1() {
		return cliente1;
	}


	public void setCliente1(EntidadComponente cliente1) {
		this.cliente1 = cliente1;
	}


	public EntidadComponente getCliente2() {
		return cliente2;
	}


	public void setCliente2(EntidadComponente cliente2) {
		this.cliente2 = cliente2;
	}


	public EntidadComponente getProp1() {
		return prop1;
	}


	public void setProp1(EntidadComponente prop1) {
		this.prop1 = prop1;
	}


	public EntidadComponente getProp2() {
		return prop2;
	}


	public void setProp2(EntidadComponente prop2) {
		this.prop2 = prop2;
	}


	public EntidadComponente getCanal1() {
		return canal1;
	}


	public void setCanal1(EntidadComponente canal1) {
		this.canal1 = canal1;
	}


	public EntidadComponente getCanal2() {
		return canal2;
	}


	public void setCanal2(EntidadComponente canal2) {
		this.canal2 = canal2;
	}


	public EntidadComponente getCanal3() {
		return canal3;
	}


	public void setCanal3(EntidadComponente canal3) {
		this.canal3 = canal3;
	}


	public EntidadComponente getRelcli1() {
		return relcli1;
	}


	public void setRelcli1(EntidadComponente relcli1) {
		this.relcli1 = relcli1;
	}


	public EntidadComponente getRelcli2() {
		return relcli2;
	}


	public void setRelcli2(EntidadComponente relcli2) {
		this.relcli2 = relcli2;
	}


	public EntidadComponente getFu1() {
		return fu1;
	}


	public void setFu1(EntidadComponente fu1) {
		this.fu1 = fu1;
	}


	public EntidadComponente getFu2() {
		return fu2;
	}


	public void setFu2(EntidadComponente fu2) {
		this.fu2 = fu2;
	}


	public EntidadComponente getFu3() {
		return fu3;
	}


	public void setFu3(EntidadComponente fu3) {
		this.fu3 = fu3;
	}


	public EntidadComponente getFu4() {
		return fu4;
	}


	public void setFu4(EntidadComponente fu4) {
		this.fu4 = fu4;
	}


	public EntidadComponente getAc1() {
		return ac1;
	}


	public void setAc1(EntidadComponente ac1) {
		this.ac1 = ac1;
	}


	public EntidadComponente getAc2() {
		return ac2;
	}


	public void setAc2(EntidadComponente ac2) {
		this.ac2 = ac2;
	}


	public EntidadComponente getAc3() {
		return ac3;
	}


	public void setAc3(EntidadComponente ac3) {
		this.ac3 = ac3;
	}


	public EntidadComponente getRc1() {
		return rc1;
	}


	public void setRc1(EntidadComponente rc1) {
		this.rc1 = rc1;
	}


	public EntidadComponente getRc2() {
		return rc2;
	}


	public void setRc2(EntidadComponente rc2) {
		this.rc2 = rc2;
	}


	public EntidadComponente getRc3() {
		return rc3;
	}


	public void setRc3(EntidadComponente rc3) {
		this.rc3 = rc3;
	}


	public EntidadComponente getRc4() {
		return rc4;
	}


	public void setRc4(EntidadComponente rc4) {
		this.rc4 = rc4;
	}


	public EntidadComponente getSo1() {
		return so1;
	}


	public void setSo1(EntidadComponente so1) {
		this.so1 = so1;
	}


	public EntidadComponente getSo2() {
		return so2;
	}


	public void setSo2(EntidadComponente so2) {
		this.so2 = so2;
	}


	public EntidadComponente getEcos1() {
		return ecos1;
	}


	public void setEcos1(EntidadComponente ecos1) {
		this.ecos1 = ecos1;
	}


	public EntidadComponente getEcos2() {
		return ecos2;
	}


	public void setEcos2(EntidadComponente ecos2) {
		this.ecos2 = ecos2;
	}


	public EntidadComponente getEcos3() {
		return ecos3;
	}


	public void setEcos3(EntidadComponente ecos3) {
		this.ecos3 = ecos3;
	}


	public static int getAbrirficheroResultCode() {
		return ABRIRFICHERO_RESULT_CODE;
	}


	public static void setSentenciaCrearTablaCanvas(String sentenciaCrearTablaCanvas) {
		Constantes.sentenciaCrearTablaCanvas = sentenciaCrearTablaCanvas;
	}


	public static void setSentenciaCrearTablaComponente(
			String sentenciaCrearTablaComponente) {
		Constantes.sentenciaCrearTablaComponente = sentenciaCrearTablaComponente;
	}


	public static void setSentenciaBorrarTablaCanvas(
			String sentenciaBorrarTablaCanvas) {
		Constantes.sentenciaBorrarTablaCanvas = sentenciaBorrarTablaCanvas;
	}


	public static void setSentenciaBorrarTablaComponente(
			String sentenciaBorrarTablaComponente) {
		Constantes.sentenciaBorrarTablaComponente = sentenciaBorrarTablaComponente;
	}


	public static void setNombreBD(String nombreBD) {
		Constantes.nombreBD = nombreBD;
	}


	public static void setVersionBd(int versionBd) {
		Constantes.versionBd = versionBd;
	}

	
	
}
