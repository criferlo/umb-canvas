package com.umb;

import harmony.java.awt.Color;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import com.lowagie.text.Document;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.Image;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Rectangle;
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.PdfContentByte;
import com.lowagie.text.pdf.PdfImportedPage;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfReader;
import com.lowagie.text.pdf.PdfWriter;
import com.umb.adapter.ItemComponenteAdapter;
import com.umb.datos.ComponenteHelper;
import com.umb.datos.entidades.EntidadCanvas;
import com.umb.datos.entidades.EntidadComponente;
import com.umb.util.Constantes;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

/***
 * activity que muestra todas las opciones del canvas
 * 
 * @author pc1
 *
 */
public class CanvasActivity extends Activity {

	private long idcanvas;
	private String nombreCanvas;
	private String autorCanvas;
	TextView titulo1boton;
	TextView titulo2boton;
	TextView titulo3boton;
	TextView titulo4boton;
	TextView titulo5boton;
	TextView titulo6boton;
	TextView titulo7boton;
	TextView titulo8boton;
	TextView titulo9boton;
	ComponenteHelper x3;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_canvas);

		// helper
		x3 = new ComponenteHelper(getApplicationContext(), null, null,
				Constantes.getVersionBd());

		// aterrizar
		titulo1boton = (TextView) findViewById(R.id.titulo1boton);
		titulo2boton = (TextView) findViewById(R.id.titulo2boton);
		titulo3boton = (TextView) findViewById(R.id.titulo3boton);
		titulo4boton = (TextView) findViewById(R.id.titulo4boton);
		titulo5boton = (TextView) findViewById(R.id.titulo5boton);
		titulo6boton = (TextView) findViewById(R.id.titulo6boton);
		titulo7boton = (TextView) findViewById(R.id.titulo7boton);
		titulo8boton = (TextView) findViewById(R.id.titulo8boton);
		titulo9boton = (TextView) findViewById(R.id.titulo9boton);
		// parametros
		Bundle x1 = getIntent().getExtras();
		setTitle(x1.getString("nombrecanvas"));
		idcanvas = x1.getLong("idcanvas");
		nombreCanvas = x1.getString("nombrecanvas");
		autorCanvas = x1.getString("autorcanvas");

		// boton atras
		ActionBar actionBar = getActionBar();
		actionBar.setDisplayHomeAsUpEnabled(true);

		contarComponentes();
	}

	private void contarComponentes() {

		List<Object> x4 = null;
		try {
			x4 = x3.consultarTodosPorPlanyTipo(idcanvas, Constantes.SOCIO);
			if (x4 != null) {
				titulo1boton.setText(getString(R.string.titulo1boton) + "("
						+ x4.size() + ")");
			}

			x4 = x3.consultarTodosPorPlanyTipo(idcanvas, Constantes.ACTIVIDAD);
			if (x4 != null) {
				titulo2boton.setText(getString(R.string.titulo2boton) + "("
						+ x4.size() + ")");
			}

			x4 = x3.consultarTodosPorPlanyTipo(idcanvas, Constantes.PROPUESTA);
			if (x4 != null) {
				titulo3boton.setText(getString(R.string.titulo3boton) + "("
						+ x4.size() + ")");
			}

			x4 = x3.consultarTodosPorPlanyTipo(idcanvas,
					Constantes.RELACION_CLIENTE);
			if (x4 != null) {
				titulo4boton.setText(getString(R.string.titulo4boton) + "("
						+ x4.size() + ")");
			}

			x4 = x3.consultarTodosPorPlanyTipo(idcanvas,
					Constantes.SEGMENTO_CLIENTE);
			if (x4 != null) {
				titulo5boton.setText(getString(R.string.titulo5boton) + "("
						+ x4.size() + ")");
			}

			x4 = x3.consultarTodosPorPlanyTipo(idcanvas,
					Constantes.RECURSOS_CLAVE);
			if (x4 != null) {
				titulo6boton.setText(getString(R.string.titulo6boton) + "("
						+ x4.size() + ")");
			}

			x4 = x3.consultarTodosPorPlanyTipo(idcanvas,
					Constantes.CANALES_DISTRIBUCION);
			if (x4 != null) {
				titulo7boton.setText(getString(R.string.titulo7boton) + "("
						+ x4.size() + ")");
			}

			x4 = x3.consultarTodosPorPlanyTipo(idcanvas,
					Constantes.ESTRUCTURA_COSTOS);
			if (x4 != null) {
				titulo8boton.setText(getString(R.string.titulo8boton) + "("
						+ x4.size() + ")");
			}

			x4 = x3.consultarTodosPorPlanyTipo(idcanvas,
					Constantes.FUENTES_INGRESO);
			if (x4 != null) {
				titulo9boton.setText(getString(R.string.titulo9boton) + "("
						+ x4.size() + ")");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.canvas, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();

		if (id == android.R.id.home) {
			this.finish();
			Intent x1 = new Intent(this, MainActivity.class);
			startActivity(x1);
			return false;
		}

		if (id == R.id.exportarCanvas) {

			exportarCanvas();
		}
		return super.onOptionsItemSelected(item);
	}

	// enviar canvas pdf
	private void exportarCanvas() {

		try {

			// consultar canvas

			List<Object> listaSocio = null;
			List<Object> listaActividades = null;
			List<Object> listaPropuesta = null;
			List<Object> listaRelacionCliente = null;
			List<Object> listaSegmentoCliente = null;
			List<Object> listaRecursoClave = null;
			List<Object> listaCanalDistribucion = null;
			List<Object> listaEstructura = null;
			List<Object> listaFuente = null;
			try {

				listaSocio = x3.consultarTodosPorPlanyTipo(idcanvas,
						Constantes.SOCIO);
				listaActividades = x3.consultarTodosPorPlanyTipo(idcanvas,
						Constantes.ACTIVIDAD);
				listaPropuesta = x3.consultarTodosPorPlanyTipo(idcanvas,
						Constantes.PROPUESTA);
				listaRelacionCliente = x3.consultarTodosPorPlanyTipo(idcanvas,
						Constantes.RELACION_CLIENTE);
				listaSegmentoCliente = x3.consultarTodosPorPlanyTipo(idcanvas,
						Constantes.SEGMENTO_CLIENTE);
				listaRecursoClave = x3.consultarTodosPorPlanyTipo(idcanvas,
						Constantes.RECURSOS_CLAVE);
				listaCanalDistribucion = x3.consultarTodosPorPlanyTipo(
						idcanvas, Constantes.CANALES_DISTRIBUCION);
				listaEstructura = x3.consultarTodosPorPlanyTipo(idcanvas,
						Constantes.ESTRUCTURA_COSTOS);
				listaFuente = x3.consultarTodosPorPlanyTipo(idcanvas,
						Constantes.FUENTES_INGRESO);

			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			// consultar canvas

			// expotar plantilla
			AssetManager mngr = getAssets();
			InputStream is = mngr.open("canvasfinal.pdf");
			OutputStream out = new FileOutputStream(
					crearFichero("template.pdf"));

			int read = 0;
			byte[] bytes = new byte[1024];

			while ((read = is.read(bytes)) != -1) {
				out.write(bytes, 0, read);
			}
			out.close();
			is.close();
			Log.e("mensaje", "template creado");
			// explotar plantilla

			PdfReader reader = new PdfReader(getRuta() + "/template.pdf");

			Document documento = new Document(PageSize.LETTER.rotate(), 0, 0,
					0, 0);
			File f = crearFichero(Constantes.NOMBRE_PDF);
			FileOutputStream ficheroPdf = new FileOutputStream(
					f.getAbsolutePath());
			PdfWriter writer = PdfWriter.getInstance(documento, ficheroPdf);
			documento.open();

			PdfContentByte canvas = writer.getDirectContent();
			PdfImportedPage page = writer.getImportedPage(reader, 1);

			canvas.addTemplate(page, 1.2f, 0, 0, 1.2f, -10, 20);
			/*
			 * Font fontAmarillo = FontFactory.getFont(FontFactory.HELVETICA, 8,
			 * Font.NORMAL, Color.YELLOW);
			 */

			// iteramos la lista para mostrar por categoria
			int posx = 20;
			int posy = 430;

			int cantidad = 4;

			if (listaSocio != null) {
				// listasocios
				for (Object b : listaSocio) {

					EntidadComponente compo = (EntidadComponente) b;
					BaseFont fuente = BaseFont.createFont();
					canvas.setFontAndSize(fuente, 10);
					canvas.beginText();

					if (compo.getColor().equals(Constantes.AMARILLO))
						canvas.setRGBColorFill(255, 217, 0);// amarillo
					if (compo.getColor().equals(Constantes.AZUL))
						canvas.setRGBColorFill(0, 20, 137);// azul
					if (compo.getColor().equals(Constantes.VERDE))
						canvas.setRGBColorFill(0, 171, 132);// verde
					if (compo.getColor().equals(Constantes.ROJO))
						canvas.setRGBColorFill(249, 56, 34);// rojo

					String va = compo.getDescripcion();

					String[] cad = va.split(" ");
					StringBuilder tex = new StringBuilder();
					int rep = Math.round(cad.length / cantidad);

					if (cad.length <= cantidad) {
						for (int j = 0; j < cad.length; j++) {
							tex.append(cad[j]);
							tex.append(" ");
						}
						canvas.showTextAligned(PdfContentByte.ALIGN_LEFT,
								tex.toString(), posx, posy, 0);
						Log.e("let", tex.toString());
					} else {
						int k = 0;
						int lon = cad.length - k;
						for (int i = 0; i < rep + 1; i++) {

							if (lon <= cantidad) {
								for (int j = 0; j < lon; j++) {
									tex.append(cad[k]);
									tex.append(" ");
									k++;
								}
								canvas.showTextAligned(
										PdfContentByte.ALIGN_LEFT,
										tex.toString(), posx, posy, 0);
								Log.e("let", tex.toString());
								break;
							}

							for (int j = 0; j < cantidad; j++) {
								tex.append(cad[k]);
								tex.append(" ");
								k++;
							}

							lon = cad.length - k;// lo que falta

							canvas.showTextAligned(PdfContentByte.ALIGN_LEFT,
									tex.toString(), posx, posy, 0);
							Log.e("let", tex.toString());
							posy -= 15;
							tex = new StringBuilder();

						}
					}
					canvas.endText();
					posy -= 20;
				}
			}
			// canvas.endText();
			// listasocios

			// lista actividades
			// canvas.beginText();
			posx = 170;
			posy = 430;

			if (listaActividades != null) {

				for (Object b : listaActividades) {

					EntidadComponente compo = (EntidadComponente) b;
					BaseFont fuente = BaseFont.createFont();
					canvas.setFontAndSize(fuente, 10);
					canvas.beginText();

					if (compo.getColor().equals(Constantes.AMARILLO))
						canvas.setRGBColorFill(255, 217, 0);// amarillo
					if (compo.getColor().equals(Constantes.AZUL))
						canvas.setRGBColorFill(0, 20, 137);// azul
					if (compo.getColor().equals(Constantes.VERDE))
						canvas.setRGBColorFill(0, 171, 132);// verde
					if (compo.getColor().equals(Constantes.ROJO))
						canvas.setRGBColorFill(249, 56, 34);// rojo

					String va = compo.getDescripcion();

					String[] cad = va.split(" ");
					StringBuilder tex = new StringBuilder();
					int rep = Math.round(cad.length / cantidad);

					if (cad.length <= cantidad) {
						for (int j = 0; j < cad.length; j++) {
							tex.append(cad[j]);
							tex.append(" ");
						}
						canvas.showTextAligned(PdfContentByte.ALIGN_LEFT,
								tex.toString(), posx, posy, 0);
						Log.e("let", tex.toString());
					} else {
						int k = 0;
						int lon = cad.length - k;
						for (int i = 0; i < rep + 1; i++) {

							if (lon <= cantidad) {
								for (int j = 0; j < lon; j++) {
									tex.append(cad[k]);
									tex.append(" ");
									k++;
								}
								canvas.showTextAligned(
										PdfContentByte.ALIGN_LEFT,
										tex.toString(), posx, posy, 0);
								Log.e("let", tex.toString());
								break;
							}

							for (int j = 0; j < cantidad; j++) {
								tex.append(cad[k]);
								tex.append(" ");
								k++;
							}

							lon = cad.length - k;// lo que falta

							canvas.showTextAligned(PdfContentByte.ALIGN_LEFT,
									tex.toString(), posx, posy, 0);
							Log.e("let", tex.toString());
							posy -= 15;
							tex = new StringBuilder();

						}
					}
					canvas.endText();
					posy -= 20;
				}
			}

			// lista actividades

			// lista propuesta de valor
			// canvas.beginText();
			posx = 320;
			posy = 430;

			if (listaPropuesta != null) {
				for (Object b : listaPropuesta) {

					EntidadComponente compo = (EntidadComponente) b;
					BaseFont fuente = BaseFont.createFont();
					canvas.setFontAndSize(fuente, 10);
					canvas.beginText();

					if (compo.getColor().equals(Constantes.AMARILLO))
						canvas.setRGBColorFill(255, 217, 0);// amarillo
					if (compo.getColor().equals(Constantes.AZUL))
						canvas.setRGBColorFill(0, 20, 137);// azul
					if (compo.getColor().equals(Constantes.VERDE))
						canvas.setRGBColorFill(0, 171, 132);// verde
					if (compo.getColor().equals(Constantes.ROJO))
						canvas.setRGBColorFill(249, 56, 34);// rojo

					String va = compo.getDescripcion();

					String[] cad = va.split(" ");
					StringBuilder tex = new StringBuilder();
					int rep = Math.round(cad.length / cantidad);

					if (cad.length <= cantidad) {
						for (int j = 0; j < cad.length; j++) {
							tex.append(cad[j]);
							tex.append(" ");
						}
						canvas.showTextAligned(PdfContentByte.ALIGN_LEFT,
								tex.toString(), posx, posy, 0);
						Log.e("let", tex.toString());
					} else {
						int k = 0;
						int lon = cad.length - k;
						for (int i = 0; i < rep + 1; i++) {

							if (lon <= cantidad) {
								for (int j = 0; j < lon; j++) {
									tex.append(cad[k]);
									tex.append(" ");
									k++;
								}
								canvas.showTextAligned(
										PdfContentByte.ALIGN_LEFT,
										tex.toString(), posx, posy, 0);
								Log.e("let", tex.toString());
								break;
							}

							for (int j = 0; j < cantidad; j++) {
								tex.append(cad[k]);
								tex.append(" ");
								k++;
							}

							lon = cad.length - k;// lo que falta

							canvas.showTextAligned(PdfContentByte.ALIGN_LEFT,
									tex.toString(), posx, posy, 0);
							Log.e("let", tex.toString());
							posy -= 15;
							tex = new StringBuilder();

						}
					}
					canvas.endText();
					posy -= 20;
				}
			}

			// lista propuesta de valor

			// lista relacion clientes
			// canvas.beginText();
			posx = 480;
			posy = 430;

			if (listaRelacionCliente != null) {
				for (Object b : listaRelacionCliente) {

					EntidadComponente compo = (EntidadComponente) b;
					BaseFont fuente = BaseFont.createFont();
					canvas.setFontAndSize(fuente, 10);
					canvas.beginText();

					if (compo.getColor().equals(Constantes.AMARILLO))
						canvas.setRGBColorFill(255, 217, 0);// amarillo
					if (compo.getColor().equals(Constantes.AZUL))
						canvas.setRGBColorFill(0, 20, 137);// azul
					if (compo.getColor().equals(Constantes.VERDE))
						canvas.setRGBColorFill(0, 171, 132);// verde
					if (compo.getColor().equals(Constantes.ROJO))
						canvas.setRGBColorFill(249, 56, 34);// rojo

					String va = compo.getDescripcion();

					String[] cad = va.split(" ");
					StringBuilder tex = new StringBuilder();
					int rep = Math.round(cad.length / cantidad);

					if (cad.length <= cantidad) {
						for (int j = 0; j < cad.length; j++) {
							tex.append(cad[j]);
							tex.append(" ");
						}
						canvas.showTextAligned(PdfContentByte.ALIGN_LEFT,
								tex.toString(), posx, posy, 0);
						Log.e("let", tex.toString());
					} else {
						int k = 0;
						int lon = cad.length - k;
						for (int i = 0; i < rep + 1; i++) {

							if (lon <= cantidad) {
								for (int j = 0; j < lon; j++) {
									tex.append(cad[k]);
									tex.append(" ");
									k++;
								}
								canvas.showTextAligned(
										PdfContentByte.ALIGN_LEFT,
										tex.toString(), posx, posy, 0);
								Log.e("let", tex.toString());
								break;
							}

							for (int j = 0; j < cantidad; j++) {
								tex.append(cad[k]);
								tex.append(" ");
								k++;
							}

							lon = cad.length - k;// lo que falta

							canvas.showTextAligned(PdfContentByte.ALIGN_LEFT,
									tex.toString(), posx, posy, 0);
							Log.e("let", tex.toString());
							posy -= 15;
							tex = new StringBuilder();

						}
					}
					canvas.endText();
					posy -= 20;
				}
			}

			// lista relacion clientes

			// lista segmentos clientes
			// canvas.beginText();
			posx = 630;
			posy = 430;

			if (listaSegmentoCliente != null) {
				for (Object b : listaSegmentoCliente) {

					EntidadComponente compo = (EntidadComponente) b;
					BaseFont fuente = BaseFont.createFont();
					canvas.setFontAndSize(fuente, 10);
					canvas.beginText();

					if (compo.getColor().equals(Constantes.AMARILLO))
						canvas.setRGBColorFill(255, 217, 0);// amarillo
					if (compo.getColor().equals(Constantes.AZUL))
						canvas.setRGBColorFill(0, 20, 137);// azul
					if (compo.getColor().equals(Constantes.VERDE))
						canvas.setRGBColorFill(0, 171, 132);// verde
					if (compo.getColor().equals(Constantes.ROJO))
						canvas.setRGBColorFill(249, 56, 34);// rojo

					String va = compo.getDescripcion();

					String[] cad = va.split(" ");
					StringBuilder tex = new StringBuilder();
					int rep = Math.round(cad.length / cantidad);

					if (cad.length <= cantidad) {
						for (int j = 0; j < cad.length; j++) {
							tex.append(cad[j]);
							tex.append(" ");
						}
						canvas.showTextAligned(PdfContentByte.ALIGN_LEFT,
								tex.toString(), posx, posy, 0);
						Log.e("let", tex.toString());
					} else {
						int k = 0;
						int lon = cad.length - k;
						for (int i = 0; i < rep + 1; i++) {

							if (lon <= cantidad) {
								for (int j = 0; j < lon; j++) {
									tex.append(cad[k]);
									tex.append(" ");
									k++;
								}
								canvas.showTextAligned(
										PdfContentByte.ALIGN_LEFT,
										tex.toString(), posx, posy, 0);
								Log.e("let", tex.toString());
								break;
							}

							for (int j = 0; j < cantidad; j++) {
								tex.append(cad[k]);
								tex.append(" ");
								k++;
							}

							lon = cad.length - k;// lo que falta

							canvas.showTextAligned(PdfContentByte.ALIGN_LEFT,
									tex.toString(), posx, posy, 0);
							Log.e("let", tex.toString());
							posy -= 15;
							tex = new StringBuilder();

						}
					}
					canvas.endText();
					posy -= 20;
				}
			}

			// lista segmentos cliente

			// lista recursos clave
			// canvas.beginText();
			posx = 170;
			posy = 270;
			if (listaRecursoClave != null) {
				for (Object b : listaRecursoClave) {

					EntidadComponente compo = (EntidadComponente) b;
					BaseFont fuente = BaseFont.createFont();
					canvas.setFontAndSize(fuente, 10);
					canvas.beginText();

					if (compo.getColor().equals(Constantes.AMARILLO))
						canvas.setRGBColorFill(255, 217, 0);// amarillo
					if (compo.getColor().equals(Constantes.AZUL))
						canvas.setRGBColorFill(0, 20, 137);// azul
					if (compo.getColor().equals(Constantes.VERDE))
						canvas.setRGBColorFill(0, 171, 132);// verde
					if (compo.getColor().equals(Constantes.ROJO))
						canvas.setRGBColorFill(249, 56, 34);// rojo

					String va = compo.getDescripcion();

					String[] cad = va.split(" ");
					StringBuilder tex = new StringBuilder();
					int rep = Math.round(cad.length / cantidad);

					if (cad.length <= cantidad) {
						for (int j = 0; j < cad.length; j++) {
							tex.append(cad[j]);
							tex.append(" ");
						}
						canvas.showTextAligned(PdfContentByte.ALIGN_LEFT,
								tex.toString(), posx, posy, 0);
						Log.e("let", tex.toString());
					} else {
						int k = 0;
						int lon = cad.length - k;
						for (int i = 0; i < rep + 1; i++) {

							if (lon <= cantidad) {
								for (int j = 0; j < lon; j++) {
									tex.append(cad[k]);
									tex.append(" ");
									k++;
								}
								canvas.showTextAligned(
										PdfContentByte.ALIGN_LEFT,
										tex.toString(), posx, posy, 0);
								Log.e("let", tex.toString());
								break;
							}

							for (int j = 0; j < cantidad; j++) {
								tex.append(cad[k]);
								tex.append(" ");
								k++;
							}

							lon = cad.length - k;// lo que falta

							canvas.showTextAligned(PdfContentByte.ALIGN_LEFT,
									tex.toString(), posx, posy, 0);
							Log.e("let", tex.toString());
							posy -= 15;
							tex = new StringBuilder();

						}
					}
					canvas.endText();
					posy -= 20;
				}
			}

			// lista recursos clave

			// lista distribucion
			// canvas.beginText();
			posx = 480;
			posy = 270;
			if (listaCanalDistribucion != null) {
				for (Object b : listaCanalDistribucion) {

					EntidadComponente compo = (EntidadComponente) b;
					BaseFont fuente = BaseFont.createFont();
					canvas.setFontAndSize(fuente, 10);
					canvas.beginText();

					if (compo.getColor().equals(Constantes.AMARILLO))
						canvas.setRGBColorFill(255, 217, 0);// amarillo
					if (compo.getColor().equals(Constantes.AZUL))
						canvas.setRGBColorFill(0, 20, 137);// azul
					if (compo.getColor().equals(Constantes.VERDE))
						canvas.setRGBColorFill(0, 171, 132);// verde
					if (compo.getColor().equals(Constantes.ROJO))
						canvas.setRGBColorFill(249, 56, 34);// rojo

					String va = compo.getDescripcion();

					String[] cad = va.split(" ");
					StringBuilder tex = new StringBuilder();
					int rep = Math.round(cad.length / cantidad);

					if (cad.length <= cantidad) {
						for (int j = 0; j < cad.length; j++) {
							tex.append(cad[j]);
							tex.append(" ");
						}
						canvas.showTextAligned(PdfContentByte.ALIGN_LEFT,
								tex.toString(), posx, posy, 0);
						Log.e("let", tex.toString());
					} else {
						int k = 0;
						int lon = cad.length - k;
						for (int i = 0; i < rep + 1; i++) {

							if (lon <= cantidad) {
								for (int j = 0; j < lon; j++) {
									tex.append(cad[k]);
									tex.append(" ");
									k++;
								}
								canvas.showTextAligned(
										PdfContentByte.ALIGN_LEFT,
										tex.toString(), posx, posy, 0);
								Log.e("let", tex.toString());
								break;
							}

							for (int j = 0; j < cantidad; j++) {
								tex.append(cad[k]);
								tex.append(" ");
								k++;
							}

							lon = cad.length - k;// lo que falta

							canvas.showTextAligned(PdfContentByte.ALIGN_LEFT,
									tex.toString(), posx, posy, 0);
							Log.e("let", tex.toString());
							posy -= 15;
							tex = new StringBuilder();

						}
					}
					canvas.endText();
					posy -= 20;
				}
			}

			// lista distribucion

			// lista estructura costos
			// canvas.beginText();
			posx = 20;
			posy = 120;
			if (listaEstructura != null) {
				for (Object b : listaEstructura) {

					EntidadComponente compo = (EntidadComponente) b;
					BaseFont fuente = BaseFont.createFont();
					canvas.setFontAndSize(fuente, 10);
					canvas.beginText();

					if (compo.getColor().equals(Constantes.AMARILLO))
						canvas.setRGBColorFill(255, 217, 0);// amarillo
					if (compo.getColor().equals(Constantes.AZUL))
						canvas.setRGBColorFill(0, 20, 137);// azul
					if (compo.getColor().equals(Constantes.VERDE))
						canvas.setRGBColorFill(0, 171, 132);// verde
					if (compo.getColor().equals(Constantes.ROJO))
						canvas.setRGBColorFill(249, 56, 34);// rojo

					String va = compo.getDescripcion();

					String[] cad = va.split(" ");
					StringBuilder tex = new StringBuilder();
					int rep = Math.round(cad.length / cantidad);

					if (cad.length <= cantidad) {
						for (int j = 0; j < cad.length; j++) {
							tex.append(cad[j]);
							tex.append(" ");
						}
						canvas.showTextAligned(PdfContentByte.ALIGN_LEFT,
								tex.toString(), posx, posy, 0);
						Log.e("let", tex.toString());
					} else {
						int k = 0;
						int lon = cad.length - k;
						for (int i = 0; i < rep + 1; i++) {

							if (lon <= cantidad) {
								for (int j = 0; j < lon; j++) {
									tex.append(cad[k]);
									tex.append(" ");
									k++;
								}
								canvas.showTextAligned(
										PdfContentByte.ALIGN_LEFT,
										tex.toString(), posx, posy, 0);
								Log.e("let", tex.toString());
								break;
							}

							for (int j = 0; j < cantidad; j++) {
								tex.append(cad[k]);
								tex.append(" ");
								k++;
							}

							lon = cad.length - k;// lo que falta

							canvas.showTextAligned(PdfContentByte.ALIGN_LEFT,
									tex.toString(), posx, posy, 0);
							Log.e("let", tex.toString());
							posy -= 15;
							tex = new StringBuilder();

						}
					}
					canvas.endText();
					posy -= 20;
				}
			}

			// lista distribucion

			// lista fuente de ingresos
			// canvas.beginText();
			posx = 400;
			posy = 120;
			if (listaFuente != null) {
				for (Object b : listaFuente) {

					EntidadComponente compo = (EntidadComponente) b;
					BaseFont fuente = BaseFont.createFont();
					canvas.setFontAndSize(fuente, 10);
					canvas.beginText();

					if (compo.getColor().equals(Constantes.AMARILLO))
						canvas.setRGBColorFill(255, 217, 0);// amarillo
					if (compo.getColor().equals(Constantes.AZUL))
						canvas.setRGBColorFill(0, 20, 137);// azul
					if (compo.getColor().equals(Constantes.VERDE))
						canvas.setRGBColorFill(0, 171, 132);// verde
					if (compo.getColor().equals(Constantes.ROJO))
						canvas.setRGBColorFill(249, 56, 34);// rojo

					String va = compo.getDescripcion();

					String[] cad = va.split(" ");
					StringBuilder tex = new StringBuilder();
					int rep = Math.round(cad.length / cantidad);

					if (cad.length <= cantidad) {
						for (int j = 0; j < cad.length; j++) {
							tex.append(cad[j]);
							tex.append(" ");
						}
						canvas.showTextAligned(PdfContentByte.ALIGN_LEFT,
								tex.toString(), posx, posy, 0);
						Log.e("let", tex.toString());
					} else {
						int k = 0;
						int lon = cad.length - k;
						for (int i = 0; i < rep + 1; i++) {

							if (lon <= cantidad) {
								for (int j = 0; j < lon; j++) {
									tex.append(cad[k]);
									tex.append(" ");
									k++;
								}
								canvas.showTextAligned(
										PdfContentByte.ALIGN_LEFT,
										tex.toString(), posx, posy, 0);
								Log.e("let", tex.toString());
								break;
							}

							for (int j = 0; j < cantidad; j++) {
								tex.append(cad[k]);
								tex.append(" ");
								k++;
							}

							lon = cad.length - k;// lo que falta

							canvas.showTextAligned(PdfContentByte.ALIGN_LEFT,
									tex.toString(), posx, posy, 0);
							Log.e("let", tex.toString());
							posy -= 15;
							tex = new StringBuilder();

						}
					}
					canvas.endText();
					posy -= 20;
				}
			}

			// lista fuente de ingresos
			
			//titulo			
			posx = 340;
			posy = 500;
			canvas.setRGBColorFill(0, 0, 0);// amarillo
			canvas.showTextAligned(PdfContentByte.ALIGN_LEFT,autorCanvas, posx, posy, 0);
			
			posx = 520;
			canvas.showTextAligned(PdfContentByte.ALIGN_LEFT,nombreCanvas, posx, posy, 0);
						
			//

			documento.close();
			reader.close();

			Uri path = Uri.fromFile(f);
			Intent pdfIntent = new Intent(Intent.ACTION_VIEW);
			pdfIntent.setDataAndType(path, "application/pdf");
			pdfIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
			startActivity(pdfIntent);

		} catch (Exception ex) {
			Log.e("error", ex.getMessage());
		}
	}

	public static File crearFichero(String nombreFichero) throws IOException {
		File ruta = getRuta();
		File fichero = null;
		if (ruta != null)
			fichero = new File(ruta, nombreFichero);
		return fichero;
	}

	public static File getRuta() {

		// El fichero será almacenado en un directorio dentro del directorio
		// Descargas
		File ruta = null;
		if (Environment.MEDIA_MOUNTED.equals(Environment
				.getExternalStorageState())) {
			ruta = new File(
					Environment
							.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS),
					Constantes.NOMBRe_DIR);

			if (ruta != null) {
				if (!ruta.mkdirs()) {
					if (!ruta.exists()) {
						return null;
					}
				}
			}
		} else {
		}

		return ruta;
	}

	// Socios
	public void cliccanvas_1(View v) {
		// consultar detalle del id del canvas
		ComponenteHelper x3 = new ComponenteHelper(getApplicationContext(),
				null, null, Constantes.getVersionBd());
		ArrayList<Object> x4 = null;
		try {
			x4 = x3.consultarTodosPorPlanyTipo(idcanvas, Constantes.SOCIO);

		} catch (Exception e) {
			e.printStackTrace();
		}
		// no encuentra detalles
		if (x4 == null) {
			Intent x1 = new Intent(this, SinComponenteActivity.class);
			String x2 = getString(R.string.titulo1boton);
			x1.putExtra("nombrecanvas", nombreCanvas);
			x1.putExtra("idcanvas", idcanvas);
			x1.putExtra("categoria", x2);
			x1.putExtra("tipocomponente", Constantes.SOCIO);
			startActivity(x1);
		} else {
			if (x4.size() > 0) {
				Intent x5 = new Intent(this, ListaComponentesActivity.class);
				String x2 = getString(R.string.titulo1boton);
				x5.putExtra("nombrecanvas", nombreCanvas);
				x5.putExtra("idcanvas", idcanvas);
				x5.putExtra("categoria", x2);
				x5.putExtra("tipocomponente", Constantes.SOCIO);
				startActivity(x5);
			}
		}

		// fin consultar

	}

	// actividades clave
	public void cliccanvas_2(View v) {
		// consultar detalle del id del canvas
		ComponenteHelper x3 = new ComponenteHelper(getApplicationContext(),
				null, null, Constantes.getVersionBd());
		ArrayList<Object> x4 = null;
		try {
			x4 = x3.consultarTodosPorPlanyTipo(idcanvas, Constantes.ACTIVIDAD);

		} catch (Exception e) {
			e.printStackTrace();
		}
		// no encuentra detalles
		if (x4 == null) {
			Intent x1 = new Intent(this, SinComponenteActivity.class);
			String x2 = getString(R.string.titulo2boton);
			x1.putExtra("nombrecanvas", nombreCanvas);
			x1.putExtra("idcanvas", idcanvas);
			x1.putExtra("categoria", x2);
			x1.putExtra("tipocomponente", Constantes.ACTIVIDAD);
			startActivity(x1);
		} else {
			if (x4.size() > 0) {
				Intent x5 = new Intent(this, ListaComponentesActivity.class);
				String x2 = getString(R.string.titulo2boton);
				x5.putExtra("nombrecanvas", nombreCanvas);
				x5.putExtra("idcanvas", idcanvas);
				x5.putExtra("categoria", x2);
				x5.putExtra("tipocomponente", Constantes.ACTIVIDAD);
				startActivity(x5);
			}
		}

		// fin consultar
	}

	// propuesta de valor
	public void cliccanvas_3(View v) {
		// consultar detalle del id del canvas
		ComponenteHelper x3 = new ComponenteHelper(getApplicationContext(),
				null, null, Constantes.getVersionBd());
		ArrayList<Object> x4 = null;
		try {
			x4 = x3.consultarTodosPorPlanyTipo(idcanvas, Constantes.PROPUESTA);

		} catch (Exception e) {
			e.printStackTrace();
		}
		// no encuentra detalles
		if (x4 == null) {
			Intent x1 = new Intent(this, SinComponenteActivity.class);
			String x2 = getString(R.string.titulo3boton);
			x1.putExtra("nombrecanvas", nombreCanvas);
			x1.putExtra("idcanvas", idcanvas);
			x1.putExtra("categoria", x2);
			x1.putExtra("tipocomponente", Constantes.PROPUESTA);
			startActivity(x1);
		} else {
			if (x4.size() > 0) {
				Intent x5 = new Intent(this, ListaComponentesActivity.class);
				String x2 = getString(R.string.titulo3boton);
				x5.putExtra("nombrecanvas", nombreCanvas);
				x5.putExtra("idcanvas", idcanvas);
				x5.putExtra("categoria", x2);
				x5.putExtra("tipocomponente", Constantes.PROPUESTA);
				startActivity(x5);
			}
		}

		// fin consultar
	}

	// relacion con cliente
	public void cliccanvas_4(View v) {
		// consultar detalle del id del canvas
		ComponenteHelper x3 = new ComponenteHelper(getApplicationContext(),
				null, null, Constantes.getVersionBd());
		ArrayList<Object> x4 = null;
		try {
			x4 = x3.consultarTodosPorPlanyTipo(idcanvas,
					Constantes.RELACION_CLIENTE);

		} catch (Exception e) {
			e.printStackTrace();
		}
		// no encuentra detalles
		if (x4 == null) {
			Intent x1 = new Intent(this, SinComponenteActivity.class);
			String x2 = getString(R.string.titulo4boton);
			x1.putExtra("nombrecanvas", nombreCanvas);
			x1.putExtra("idcanvas", idcanvas);
			x1.putExtra("categoria", x2);
			x1.putExtra("tipocomponente", Constantes.RELACION_CLIENTE);
			startActivity(x1);
		} else {
			if (x4.size() > 0) {
				Intent x5 = new Intent(this, ListaComponentesActivity.class);
				String x2 = getString(R.string.titulo4boton);
				x5.putExtra("nombrecanvas", nombreCanvas);
				x5.putExtra("idcanvas", idcanvas);
				x5.putExtra("categoria", x2);
				x5.putExtra("tipocomponente", Constantes.RELACION_CLIENTE);
				startActivity(x5);
			}
		}

		// fin consultar
	}

	// segmentos de clientes
	public void cliccanvas_5(View v) {
		// consultar detalle del id del canvas
		ComponenteHelper x3 = new ComponenteHelper(getApplicationContext(),
				null, null, Constantes.getVersionBd());
		ArrayList<Object> x4 = null;
		try {
			x4 = x3.consultarTodosPorPlanyTipo(idcanvas,
					Constantes.SEGMENTO_CLIENTE);

		} catch (Exception e) {
			e.printStackTrace();
		}
		// no encuentra detalles
		if (x4 == null) {
			Intent x1 = new Intent(this, SinComponenteActivity.class);
			String x2 = getString(R.string.titulo5boton);
			x1.putExtra("nombrecanvas", nombreCanvas);
			x1.putExtra("idcanvas", idcanvas);
			x1.putExtra("categoria", x2);
			x1.putExtra("tipocomponente", Constantes.SEGMENTO_CLIENTE);
			startActivity(x1);
		} else {
			if (x4.size() > 0) {
				Intent x5 = new Intent(this, ListaComponentesActivity.class);
				String x2 = getString(R.string.titulo5boton);
				x5.putExtra("nombrecanvas", nombreCanvas);
				x5.putExtra("idcanvas", idcanvas);
				x5.putExtra("categoria", x2);
				x5.putExtra("tipocomponente", Constantes.SEGMENTO_CLIENTE);
				startActivity(x5);
			}
		}

		// fin consultar
	}

	// recursos clave
	public void cliccanvas_6(View v) {
		// consultar detalle del id del canvas
		ComponenteHelper x3 = new ComponenteHelper(getApplicationContext(),
				null, null, Constantes.getVersionBd());
		ArrayList<Object> x4 = null;
		try {
			x4 = x3.consultarTodosPorPlanyTipo(idcanvas,
					Constantes.RECURSOS_CLAVE);

		} catch (Exception e) {
			e.printStackTrace();
		}
		// no encuentra detalles
		if (x4 == null) {
			Intent x1 = new Intent(this, SinComponenteActivity.class);
			String x2 = getString(R.string.titulo6boton);
			x1.putExtra("nombrecanvas", nombreCanvas);
			x1.putExtra("idcanvas", idcanvas);
			x1.putExtra("categoria", x2);
			x1.putExtra("tipocomponente", Constantes.RECURSOS_CLAVE);
			startActivity(x1);
		} else {
			if (x4.size() > 0) {
				Intent x5 = new Intent(this, ListaComponentesActivity.class);
				String x2 = getString(R.string.titulo6boton);
				x5.putExtra("nombrecanvas", nombreCanvas);
				x5.putExtra("idcanvas", idcanvas);
				x5.putExtra("categoria", x2);
				x5.putExtra("tipocomponente", Constantes.RECURSOS_CLAVE);
				startActivity(x5);
			}
		}

		// fin consultar
	}

	// canales de distribucion
	public void cliccanvas_7(View v) {
		// consultar detalle del id del canvas
		ComponenteHelper x3 = new ComponenteHelper(getApplicationContext(),
				null, null, Constantes.getVersionBd());
		ArrayList<Object> x4 = null;
		try {
			x4 = x3.consultarTodosPorPlanyTipo(idcanvas,
					Constantes.CANALES_DISTRIBUCION);

		} catch (Exception e) {
			e.printStackTrace();
		}
		// no encuentra detalles
		if (x4 == null) {
			Intent x1 = new Intent(this, SinComponenteActivity.class);
			String x2 = getString(R.string.titulo7boton);
			x1.putExtra("nombrecanvas", nombreCanvas);
			x1.putExtra("idcanvas", idcanvas);
			x1.putExtra("categoria", x2);
			x1.putExtra("tipocomponente", Constantes.CANALES_DISTRIBUCION);
			startActivity(x1);
		} else {
			if (x4.size() > 0) {
				Intent x5 = new Intent(this, ListaComponentesActivity.class);
				String x2 = getString(R.string.titulo7boton);
				x5.putExtra("nombrecanvas", nombreCanvas);
				x5.putExtra("idcanvas", idcanvas);
				x5.putExtra("categoria", x2);
				x5.putExtra("tipocomponente", Constantes.CANALES_DISTRIBUCION);
				startActivity(x5);
			}
		}

		// fin consultar
	}

	// estructura de costos
	public void cliccanvas_8(View v) {
		// consultar detalle del id del canvas
		ComponenteHelper x3 = new ComponenteHelper(getApplicationContext(),
				null, null, Constantes.getVersionBd());
		ArrayList<Object> x4 = null;
		try {
			x4 = x3.consultarTodosPorPlanyTipo(idcanvas,
					Constantes.ESTRUCTURA_COSTOS);

		} catch (Exception e) {
			e.printStackTrace();
		}
		// no encuentra detalles
		if (x4 == null) {
			Intent x1 = new Intent(this, SinComponenteActivity.class);
			String x2 = getString(R.string.titulo8boton);
			x1.putExtra("nombrecanvas", nombreCanvas);
			x1.putExtra("idcanvas", idcanvas);
			x1.putExtra("categoria", x2);
			x1.putExtra("tipocomponente", Constantes.ESTRUCTURA_COSTOS);
			startActivity(x1);
		} else {
			if (x4.size() > 0) {
				Intent x5 = new Intent(this, ListaComponentesActivity.class);
				String x2 = getString(R.string.titulo8boton);
				x5.putExtra("nombrecanvas", nombreCanvas);
				x5.putExtra("idcanvas", idcanvas);
				x5.putExtra("categoria", x2);
				x5.putExtra("tipocomponente", Constantes.ESTRUCTURA_COSTOS);
				startActivity(x5);
			}
		}

		// fin consultar
	}

	// fuentes de ingreso
	public void cliccanvas_9(View v) {
		// consultar detalle del id del canvas
		ComponenteHelper x3 = new ComponenteHelper(getApplicationContext(),
				null, null, Constantes.getVersionBd());
		ArrayList<Object> x4 = null;
		try {
			x4 = x3.consultarTodosPorPlanyTipo(idcanvas,
					Constantes.FUENTES_INGRESO);

		} catch (Exception e) {
			e.printStackTrace();
		}
		// no encuentra detalles
		if (x4 == null) {
			Intent x1 = new Intent(this, SinComponenteActivity.class);
			String x2 = getString(R.string.titulo9boton);
			x1.putExtra("nombrecanvas", nombreCanvas);
			x1.putExtra("idcanvas", idcanvas);
			x1.putExtra("categoria", x2);
			x1.putExtra("tipocomponente", Constantes.FUENTES_INGRESO);
			startActivity(x1);
		} else {
			if (x4.size() > 0) {
				Intent x5 = new Intent(this, ListaComponentesActivity.class);
				String x2 = getString(R.string.titulo9boton);
				x5.putExtra("nombrecanvas", nombreCanvas);
				x5.putExtra("idcanvas", idcanvas);
				x5.putExtra("categoria", x2);
				x5.putExtra("tipocomponente", Constantes.FUENTES_INGRESO);
				startActivity(x5);
			}
		}

		// fin consultar
	}
}
