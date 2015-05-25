package com.umb;

import java.util.List;

import com.umb.adapter.ItemCanvasAdapter;
import com.umb.adapter.ItemComponenteAdapter;
import com.umb.datos.CanvasHelper;
import com.umb.datos.ComponenteHelper;
import com.umb.datos.entidades.EntidadCanvas;
import com.umb.datos.entidades.EntidadComponente;
import com.umb.util.Constantes;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

public class ListaCanvasActivity extends Activity {

	ListView lista;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_lista_canvas);

		// boton atras
		ActionBar actionBar = getActionBar();
		actionBar.setDisplayHomeAsUpEnabled(true);

		// lista
		lista = (ListView) findViewById(R.id.lstCanvas);
		llenarLista();

		setTitle("Lista de canvas");

		// evento
		lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				EntidadCanvas item = (EntidadCanvas) lista.getAdapter()
						.getItem(position);
				// enviar componente a editar
				Intent x1 = new Intent(getApplicationContext(),
						EditarCanvasActivity.class);
				x1.putExtra("idcanvas", item.getId());
				x1.putExtra("nombrecanvas", item.getNombre());
				x1.putExtra("descripcioncanvas", item.getDescripcion());
				x1.putExtra("autorcanvas", item.getAutor());
				startActivity(x1);
			}

		});
	}

	private void llenarLista() {
		// consultar detalle del id del canvas
		CanvasHelper x3 = new CanvasHelper(getApplicationContext(), null, null,
				Constantes.getVersionBd());
		List<Object> x4 = null;
		try {
			x4 = x3.consultarTodos();
			lista.setAdapter(new ItemCanvasAdapter(getApplicationContext(), x4));

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		// getMenuInflater().inflate(R.menu.lista_canvas, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		int id = item.getItemId();

		if (id == android.R.id.home) {
			this.finish();
			Intent x1 = new Intent(this, MainActivity.class);
			startActivity(x1);
			return false;
		}

		/*
		 * if (id == R.id.action_settings) { return true; }
		 */
		return super.onOptionsItemSelected(item);
	}
}
