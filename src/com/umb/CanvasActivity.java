package com.umb;

import java.util.ArrayList;

import com.umb.datos.ComponenteHelper;
import com.umb.util.Constantes;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

/***
 * activity que muestra todas las opciones del canvas
 * 
 * @author pc1
 *
 */
public class CanvasActivity extends Activity {

	private long idcanvas;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_canvas);
		//parametros
		Bundle x1 = getIntent().getExtras();
		setTitle(x1.getString("nombrecanvas"));
		idcanvas = x1.getLong("idcanvas");
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
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	//Socios
	public void cliccanvas_1(View v) {			
		//consultar detalle del id del canvas
		ComponenteHelper x3 = new ComponenteHelper(getApplicationContext(), null, null, Constantes.getVersionBd());
		ArrayList<Object> x4=null;
		try {
			 x4 = x3.consultarTodosPorPlanyTipo(idcanvas, Constantes.SOCIO);		 
			 
		} catch (Exception e) {
			e.printStackTrace();
		}
		//no encuentra detalles
		if(x4==null){
			Intent x1 = new Intent(this,SinDetalleActivity.class);
			String x2 = getString(R.string.titulo1boton);
			x1.putExtra("idcanvas", idcanvas);	
			x1.putExtra("categoria", x2);
			x1.putExtra("tipocomponente", Constantes.SOCIO);
			startActivity(x1);
		}
		else{
			if(x4.size()>0){
				Intent x5 = new Intent(this,DetalleActivity.class);
				String x2 = getString(R.string.titulo1boton);
				x5.putExtra("idcanvas", idcanvas);
				x5.putExtra("categoria", x2);
				x5.putExtra("tipocomponente", Constantes.SOCIO);
				startActivity(x5);
			}
		}
		
		//fin consultar
		
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
