package com.umb;

import com.umb.datos.ComponenteHelper;
import com.umb.datos.entidades.EntidadComponente;
import com.umb.util.Constantes;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class NuevoComponenteActivity extends Activity {

	private long idcanvas;
	private String tipoComponente;
	private String nombreCanvas;

	EditText txtNuevoComponente;
	EditText txtNombreComponente;
	
	private String colorSeleccionado=Constantes.AMARILLO;
	
	int sdk = android.os.Build.VERSION.SDK_INT;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_crear_componente);

		txtNuevoComponente = (EditText) findViewById(R.id.txtNuevoComponente);
		txtNombreComponente = (EditText) findViewById(R.id.txtNombreComponente);

		setTitle("Crear nuevo Post");
		Bundle x1 = getIntent().getExtras();
		idcanvas = x1.getLong("idcanvas");
		tipoComponente = x1.getString("tipocomponente");
		nombreCanvas = x1.getString("nombrecanvas");

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.crear_detalle, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		int id = item.getItemId();

		// grabar detalle
		if (id == R.id.action_settings) {
			grabarComponenteCanvas();
		}
		return super.onOptionsItemSelected(item);
	}

	private void grabarComponenteCanvas() {
		try {
			ComponenteHelper x1 = new ComponenteHelper(getApplicationContext(),
					null, null, Constantes.getVersionBd());
			EntidadComponente x2 = new EntidadComponente();
			x2.setDescripcion(txtNuevoComponente.getText().toString());
			x2.setCanvas_id(idcanvas);
			x2.setTipocomponente(tipoComponente);
			x2.setNombre(txtNombreComponente.getText().toString());
			x2.setColor(colorSeleccionado);

			long id = x1.crear(x2);

			if (id != 0) {
				Toast.makeText(getApplicationContext(), R.string.grabado_exito,
						Toast.LENGTH_LONG).show();
				// iniciar a crear canvas
				Intent x3 = new Intent(this, CanvasActivity.class);
				x3.putExtra("nombrecanvas", nombreCanvas);
				x3.putExtra("idcanvas", idcanvas);
				startActivity(x3);
				finish();
			} else {
				Toast.makeText(getApplicationContext(),
						R.string.grabado_noexito, Toast.LENGTH_LONG).show();
			}
		} catch (Exception ex) {
			Toast.makeText(getApplicationContext(),
					ex.getMessage(), Toast.LENGTH_LONG).show();
		}
	}

	public void clicAmarillo(View view) {
		if(sdk < android.os.Build.VERSION_CODES.JELLY_BEAN) {
			txtNuevoComponente.setBackgroundDrawable(getResources().getDrawable(
					R.drawable.canvas_amarillo));
		} else {
			txtNuevoComponente.setBackground(getResources().getDrawable(
					R.drawable.canvas_amarillo));
		}
		
		colorSeleccionado = Constantes.AMARILLO;
	}

	public void clicAzul(View view) {
		if(sdk < android.os.Build.VERSION_CODES.JELLY_BEAN) {
			txtNuevoComponente.setBackgroundDrawable(getResources().getDrawable(
					R.drawable.canvas_azul));
		} else {
			txtNuevoComponente.setBackground(getResources().getDrawable(
					R.drawable.canvas_azul));
		}
		colorSeleccionado = Constantes.AZUL;
	}

	public void clicVerde(View view) {
		if(sdk < android.os.Build.VERSION_CODES.JELLY_BEAN) {
			txtNuevoComponente.setBackgroundDrawable(getResources().getDrawable(
					R.drawable.canvas_verde));
		} else {
			txtNuevoComponente.setBackground(getResources().getDrawable(
					R.drawable.canvas_verde));
		}
		colorSeleccionado = Constantes.VERDE;
	}

	public void clicRojo(View view) {
		if(sdk < android.os.Build.VERSION_CODES.JELLY_BEAN) {
			txtNuevoComponente.setBackgroundDrawable(getResources().getDrawable(
					R.drawable.canvas_rojo));
		} else {
			txtNuevoComponente.setBackground(getResources().getDrawable(
					R.drawable.canvas_rojo));
		}
		colorSeleccionado = Constantes.ROJO;
	}
}
