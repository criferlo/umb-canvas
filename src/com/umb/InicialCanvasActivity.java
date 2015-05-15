package com.umb;


import com.umb.datos.entidades.EntidadPlan;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

public class InicialCanvasActivity extends Activity {

	private EditText txtNombre;
	private EditText txtDescripcion;
	private EditText txtAutor;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_inicial_canvas);
		setTitle("Crear nuevo canvas");
		//setear los campos
		txtNombre = (EditText) findViewById(R.id.txtNombre);
		txtDescripcion = (EditText)findViewById(R.id.txtDescripcion);
		txtAutor = (EditText)findViewById(R.id.txtAutor);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.inicial_canvas, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		int id = item.getItemId();
		if (id == R.id.action_grabar) {
			grabarInfoCanvas();
		}
		return super.onOptionsItemSelected(item);
	}

	private void grabarInfoCanvas() {
		EntidadPlan x1 = new EntidadPlan();
		x1.setAutor(txtAutor.getText().toString());
		x1.setDescripcion(txtAutor.getText().toString());
		x1.setNombre(txtNombre.getText().toString());
		
	}
}
