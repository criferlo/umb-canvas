package com.umb;

import harmony.java.awt.Color;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.lowagie.text.Document;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.Image;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Rectangle;
import com.lowagie.text.pdf.PdfWriter;
import com.umb.adapter.ItemComponenteAdapter;
import com.umb.datos.ComponenteHelper;
import com.umb.util.Constantes;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
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
	TextView titulo1boton;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_canvas);
		// aterrizar
		titulo1boton = (TextView) findViewById(R.id.titulo1boton);
		// parametros
		Bundle x1 = getIntent().getExtras();
		setTitle(x1.getString("nombrecanvas"));
		idcanvas = x1.getLong("idcanvas");
		nombreCanvas = x1.getString("nombrecanvas");

		// boton atras
		ActionBar actionBar = getActionBar();
		actionBar.setDisplayHomeAsUpEnabled(true);

		contarComponentes();
	}

	private void contarComponentes() {
		ComponenteHelper x3 = new ComponenteHelper(getApplicationContext(),
				null, null, Constantes.getVersionBd());
		List<Object> x4 = null;
		try {
			x4 = x3.consultarTodosPorPlanyTipo(idcanvas, Constantes.SOCIO);
			if (x4 != null) {
				titulo1boton.setText(getString(R.string.titulo1boton) + "("
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
			Document documento = new Document(PageSize.LETTER.rotate());
			File f = crearFichero(Constantes.NOMBRE_PDF);
			// Creamos el flujo de datos de salida para el fichero donde
			// guardaremos el pdf.
			FileOutputStream ficheroPdf = new FileOutputStream(
					f.getAbsolutePath());
			// Asociamos el flujo que acabamos de crear al documento.
			PdfWriter.getInstance(documento, ficheroPdf);
			// Abrimos el documento.
			documento.open();		
			

			// A�adimos un t�tulo con la fuente por defecto.
			//documento.add(new Paragraph("T�tulo 1"));

			// A�adimos un t�tulo con una fuente personalizada.
			/*Font font = FontFactory.getFont(FontFactory.HELVETICA, 28,
					Font.BOLD, Color.RED);
			documento.add(new Paragraph("T�tulo personalizado", font));*/

			// Insertamos una imagen que se encuentra en los recursos de la aplicaci�n.
			Bitmap bitmap = BitmapFactory.decodeResource(this.getResources(), R.drawable.canvasvacio);
			ByteArrayOutputStream stream = new ByteArrayOutputStream();
			bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream);
			Image imagen = Image.getInstance(stream.toByteArray());
			
			documento.add(imagen);
			
			documento.close();
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

		// El fichero ser� almacenado en un directorio dentro del directorio
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

	public void cliccanvas_2(View v) {

	}

	public void cliccanvas_3(View v) {

	}

	public void cliccanvas_4(View v) {

	}

	public void cliccanvas_5(View v) {

	}

	public void cliccanvas_6(View v) {

	}

	public void cliccanvas_7(View v) {

	}

	public void cliccanvas_8(View v) {

	}

	public void cliccanvas_9(View v) {

	}
}
