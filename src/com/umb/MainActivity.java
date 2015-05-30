package com.umb;

import java.util.ArrayList;

import com.umb.datos.BaseHelper;
import com.umb.datos.CanvasHelper;
import com.umb.datos.ComponenteHelper;
import com.umb.datos.entidades.EntidadCanvas;
import com.umb.datos.entidades.EntidadComponente;
import com.umb.util.Constantes;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;

/***
 * Activity principal
 * 
 * @author pc1
 *
 */
public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		setTitle("Canvas");
		crearBD();

	}

	private void crearBD() {
		BaseHelper baseHelper = new BaseHelper(this, Constantes.getNombreBD(),
				null, Constantes.getVersionBd());
		SQLiteDatabase base = baseHelper.getWritableDatabase();

		// crear canvas ejemplo
		CanvasHelper canvashelper = new CanvasHelper(getApplicationContext(),
				null, null, Constantes.getVersionBd());
		ComponenteHelper componentehelper = new ComponenteHelper(
				getApplicationContext(), null, null, Constantes.getVersionBd());

		Constantes con = new Constantes();

		try {
			if (!canvashelper.consultarSiExisteEjemplo(con.getCanvas()
					.getNombre())) {

				long idcanvas = canvashelper.crear(con.getCanvas());
				con.getCliente1().setCanvas_id(idcanvas);
				componentehelper.crear(con.getCliente1());
				con.getCliente2().setCanvas_id(idcanvas);
				componentehelper.crear(con.getCliente2());
				con.getProp1().setCanvas_id(idcanvas);
				componentehelper.crear(con.getProp1());
				con.getProp2().setCanvas_id(idcanvas);
				componentehelper.crear(con.getProp2());
				con.getCanal1().setCanvas_id(idcanvas);
				componentehelper.crear(con.getCanal1());
				con.getCanal2().setCanvas_id(idcanvas);
				componentehelper.crear(con.getCanal2());
				con.getCanal3().setCanvas_id(idcanvas);
				componentehelper.crear(con.getCanal3());
				con.getRelcli1().setCanvas_id(idcanvas);
				componentehelper.crear(con.getRelcli1());
				con.getRelcli2().setCanvas_id(idcanvas);
				componentehelper.crear(con.getRelcli2());
				con.getFu1().setCanvas_id(idcanvas);
				componentehelper.crear(con.getFu1());
				con.getFu2().setCanvas_id(idcanvas);
				componentehelper.crear(con.getFu2());
				con.getFu3().setCanvas_id(idcanvas);
				componentehelper.crear(con.getFu3());
				con.getFu4().setCanvas_id(idcanvas);
				componentehelper.crear(con.getFu4());
				con.getAc1().setCanvas_id(idcanvas);
				componentehelper.crear(con.getAc1());
				con.getAc2().setCanvas_id(idcanvas);
				componentehelper.crear(con.getAc2());
				con.getAc3().setCanvas_id(idcanvas);
				componentehelper.crear(con.getAc3());
				con.getRc1().setCanvas_id(idcanvas);
				componentehelper.crear(con.getRc1());
				con.getRc2().setCanvas_id(idcanvas);
				componentehelper.crear(con.getRc2());
				con.getRc3().setCanvas_id(idcanvas);
				componentehelper.crear(con.getRc3());
				con.getRc4().setCanvas_id(idcanvas);
				componentehelper.crear(con.getRc4());
				con.getSo1().setCanvas_id(idcanvas);
				componentehelper.crear(con.getSo1());
				con.getSo2().setCanvas_id(idcanvas);
				componentehelper.crear(con.getSo2());
				con.getEcos1().setCanvas_id(idcanvas);
				componentehelper.crear(con.getEcos1());
				con.getEcos2().setCanvas_id(idcanvas);
				componentehelper.crear(con.getEcos2());
				con.getEcos3().setCanvas_id(idcanvas);
				componentehelper.crear(con.getEcos3());
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		//

		if (base != null) {
			base.close();
		} else {
			Log.e("estado", "ups! error base nula");
		}
	}

	public void clicCrearCanvas(View view) {
		Intent intent = new Intent(this, InicialCanvasActivity.class);
		startActivity(intent);
		overridePendingTransition(R.anim.zoom_entra, R.anim.zoom_sale);
	}

	public void clicListaCanvas(View view) {
		Intent intent = new Intent(this, ListaCanvasActivity.class);
		startActivity(intent);
		overridePendingTransition(R.anim.zoom_entra, R.anim.zoom_sale);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
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
}
