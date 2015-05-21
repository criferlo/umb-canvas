package com.umb;

import java.util.ArrayList;
import java.util.List;

import com.umb.adapter.ItemComponenteAdapter;
import com.umb.datos.ComponenteHelper;
import com.umb.util.Constantes;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

public class ListaComponentesActivity extends Activity {

	ListView lista;
	private long idcanvas;
	private String categoria;
	private String tipoComponente;
	private String nombreCanvas;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_lista_componentes);

		// aterrizar
		Bundle x1 = getIntent().getExtras();
		idcanvas = x1.getLong("idcanvas");
		tipoComponente = x1.getString("tipocomponente");
		nombreCanvas = x1.getString("nombrecanvas");
		categoria = x1.getString("categoria");
		
		setTitle(tipoComponente);

		// lista
		lista = (ListView) findViewById(R.id.lstComponentes);
		llenarLista();
	}

	private void llenarLista() {
		// consultar detalle del id del canvas
		ComponenteHelper x3 = new ComponenteHelper(getApplicationContext(),
				null, null, Constantes.getVersionBd());
		List<Object> x4 = null;
		try {
			x4 = x3.consultarTodosPorPlanyTipo(idcanvas, tipoComponente);
			lista.setAdapter(new ItemComponenteAdapter(getApplicationContext(), x4));

		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.detalle, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			crearNuevoComponente();
		}
		return super.onOptionsItemSelected(item);
	}

	private void crearNuevoComponente() {
		Intent x1 = new Intent(this,NuevoComponenteActivity.class);
		x1.putExtra("idcanvas", idcanvas);
		x1.putExtra("tipocomponente", tipoComponente);
		x1.putExtra("nombrecanvas", nombreCanvas);		
		startActivity(x1);
		
	}
}
