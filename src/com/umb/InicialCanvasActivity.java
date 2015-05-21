package com.umb;


import com.umb.datos.CanvasHelper;
import com.umb.datos.entidades.EntidadCanvas;
import com.umb.util.Constantes;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

/***
 * Activity para iniciar a crear un nuevo canvas
 * @author pc1
 *
 */
public class InicialCanvasActivity extends Activity {

	private EditText txtNombre;
	private EditText txtDescripcion;
	private EditText txtAutor;
	
	//avisos
	
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
		EntidadCanvas x1 = new EntidadCanvas();
		x1.setAutor(txtAutor.getText().toString());
		x1.setDescripcion(txtAutor.getText().toString());
		x1.setNombre(txtNombre.getText().toString());
		CanvasHelper x2 = new CanvasHelper(getApplicationContext(), null, null, Constantes.getVersionBd());
		try{
			long id = x2.crear(x1);
			if(id!=0){
				Toast.makeText(getApplicationContext(), R.string.grabado_exito, Toast.LENGTH_LONG).show();
				//iniciar a crear canvas
				Intent x3 = new Intent(this, CanvasActivity.class);
				x3.putExtra("nombrecanvas", txtNombre.getText().toString());
				x3.putExtra("idcanvas", id);
				startActivity(x3);
				finish();
			}
			else{
				Toast.makeText(getApplicationContext(), R.string.grabado_noexito, Toast.LENGTH_LONG).show();
			}
		}
		catch(Exception ex){
			Toast.makeText(getApplicationContext(), ex.getMessage(), Toast.LENGTH_LONG);
		}
	}
}
