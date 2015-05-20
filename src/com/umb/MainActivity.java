package com.umb;

import java.util.ArrayList;

import com.umb.datos.BaseHelper;
import com.umb.datos.PlanHelper;
import com.umb.datos.entidades.EntidadPlan;
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
		if (base != null) {
			base.close();
		} else {
			Log.e("estado", "ups! error base nula");
		}
	}

	public void clic(View view) {
		Intent intent = new Intent(this, InicialCanvasActivity.class);
		startActivity(intent);
		overridePendingTransition(R.anim.zoom_entra, R.anim.zoom_sale);
		/*
		PlanHelper plan = new PlanHelper(this, null, null,
				Constantes.getVersionBd());
		EntidadPlan ep = new EntidadPlan();
		ep.setCreador("CristhianLombana");
		ep.setDescripcion("DescripcionCanvas");
		ep.setNombre("CanvasFutbol");
		long id = -1;
		try {
			id = plan.crear(ep);
			Log.e("id", id + "");
		} catch (Exception e) {
			e.printStackTrace();
		}

		ep = new EntidadPlan();
		try {
			ep = (EntidadPlan) plan.consultarUno(id);
			Log.e("dato", ep.getDescripcion() + ep.getCreador());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// modificar
		ep.setCreador("Otro creador");
		try {
			if (plan.modificar(ep)) {
				Log.e("modificado", "si");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// consultartodos
		try {
			ArrayList<Object> lista = plan.consultarTodos();
			if (lista != null) {
				Log.e("consultados", lista.size() + "");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// eliminar
		try {
			if (plan.eliminar(ep)) {
				Log.e("eliminado", "si");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/

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
