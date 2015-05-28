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

			// consultar canvas

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
			// Creamos el flujo de datos de salida para el fichero donde
			// guardaremos el pdf.
			FileOutputStream ficheroPdf = new FileOutputStream(
					f.getAbsolutePath());
			// Asociamos el flujo que acabamos de crear al documento.
			PdfWriter writer = PdfWriter.getInstance(documento, ficheroPdf);
			// Abrimos el documento.
			documento.open();

			PdfContentByte canvas = writer.getDirectContent();
			PdfImportedPage page = writer.getImportedPage(reader, 1);
			// page.setHorizontalScaling(20);

			// documento.newPage();
			// para el canvas de la pagina
			// canvas.addTemplate(page, 0.2f, 0, 0, 0.2f, 80, 20);
			canvas.addTemplate(page, 1.2f, 0, 0, 1.2f, -10, 20);
			Font fontAmarillo = FontFactory.getFont(FontFactory.HELVETICA, 8,
					Font.NORMAL, Color.YELLOW);

			BaseFont fuente = BaseFont.createFont();
			// BaseFont fuente = fontAmarillo.getBaseFont();

			canvas.setFontAndSize(fuente, 12);

			// canvas.showText("ssss");
			canvas.beginText();
			// canvas.setRGBColorFill(255,217, 0);//amarillo
			canvas.setRGBColorFill(0, 20, 137);// azul
			// canvas.setRGBColorFill(0,171, 132);//verde
			// canvas.setRGBColorFill(249,56, 34);//rojo
			int posy = 440;
			int cantidad = 4;
			String va = "ESTA ES UNA PRUEBA MAS LARGA PERO SERA QUE ESTA VAINA SI FUNCIONA";

			String[] cad = va.split(" ");
			StringBuilder tex = new StringBuilder();
			int rep = Math.round(cad.length / cantidad);

			if (cad.length <= cantidad) {
				for (int j = 0; j < cad.length; j++) {
					tex.append(cad[j]);
					// escriba
					canvas.showTextAligned(PdfContentByte.ALIGN_LEFT,
							tex.toString(), 15, posy, 0);
					Log.e("let", tex.toString());
				}
			} else {
				int k=0;
				int lon=cad.length-k;
				for (int i = 0; i < rep+1; i++) {
					
					if(lon<=cantidad){
						for(int j=0;j<lon;j++){
							tex.append(cad[k]);
							k++;
						}
						canvas.showTextAligned(PdfContentByte.ALIGN_LEFT,
								tex.toString(), 15, posy, 0);
						Log.e("let", tex.toString());
						break;
					}
					
					for (int j = 0; j < cantidad; j++) {						
						tex.append(cad[k]);
						k++;
					}
					
					lon = cad.length-k;//lo que falta
					
					canvas.showTextAligned(PdfContentByte.ALIGN_LEFT,
							tex.toString(), 15, posy, 0);
					Log.e("let", tex.toString());
					posy-=15;	
					tex = new StringBuilder();

				}
			}

			canvas.endText();

			// http://itextpdf.com/examples/iia.php?id=113

			// Añadimos un título con una fuente personalizada.

			// documento.add(new Paragraph("Título personalizado", font));

			// PdfPCell cell = new PdfPCell();

			// Insertamos una imagen que se encuentra en los recursos de la
			// aplicación.
			/*
			 * Bitmap bitmap = BitmapFactory.decodeResource(this.getResources(),
			 * R.drawable.canvasvacio2);
			 * 
			 * ByteArrayOutputStream stream = new ByteArrayOutputStream();
			 * 
			 * bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream); Image
			 * imagen = Image.getInstance(stream.toByteArray());
			 * 
			 * imagen.setAbsolutePosition(0,-3);
			 * 
			 * documento.add(imagen);
			 * 
			 * // Añadimos un título con la fuente por defecto.
			 * documento.add(new Paragraph("Título 1"));
			 */

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
