package com.umb;

import com.umb.datos.ComponenteHelper;
import com.umb.datos.entidades.EntidadComponente;
import com.umb.util.Constantes;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class EditarComponenteActivity extends Activity {

	private long idComponente;
	private String tipoComponente;
	private String nombreCanvas;
	
	private EntidadComponente entidad;
	private ComponenteHelper helper;
	private String colorSeleccionado=Constantes.AMARILLO;
	int sdk = android.os.Build.VERSION.SDK_INT;
	
	EditText txtEditarComponente;
	EditText txtEditarNombreComponente;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_editar_componente);
		
		//campos
		txtEditarComponente = (EditText) findViewById(R.id.txtEditarComponente);
		txtEditarNombreComponente = (EditText) findViewById(R.id.txtEditarNombreComponente);
		
		setTitle("Editar Post");
		
		//aterrizar
		Bundle x1 = getIntent().getExtras();
		idComponente = x1.getLong("idcomponente");
		tipoComponente = x1.getString("tipocomponente");
		nombreCanvas = x1.getString("nombrecanvas");
		
		helper = new ComponenteHelper(getApplicationContext(), null, null, Constantes.getVersionBd());		
		
		try {
			entidad = (EntidadComponente)helper.consultarUno(idComponente);
			cargarDatosEnControles();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void cargarDatosEnControles() {
		txtEditarComponente.setText(entidad.getDescripcion());
		txtEditarNombreComponente.setText(entidad.getNombre());
		
		if(entidad.getColor().equals(Constantes.AMARILLO)){			
			if(sdk < android.os.Build.VERSION_CODES.JELLY_BEAN) {
				txtEditarComponente.setBackgroundDrawable(getResources().getDrawable(
						R.drawable.canvas_amarillo));
			} else {
				txtEditarComponente.setBackground(getResources().getDrawable(
						R.drawable.canvas_amarillo));
			}
			colorSeleccionado = Constantes.AMARILLO;
		}
		if(entidad.getColor().equals(Constantes.AZUL)){			
			if(sdk < android.os.Build.VERSION_CODES.JELLY_BEAN) {
				txtEditarComponente.setBackgroundDrawable(getResources().getDrawable(
						R.drawable.canvas_azul));
			} else {
				txtEditarComponente.setBackground(getResources().getDrawable(
						R.drawable.canvas_azul));
			}
			colorSeleccionado = Constantes.AZUL;
		}
		if(entidad.getColor().equals(Constantes.VERDE)){			
			if(sdk < android.os.Build.VERSION_CODES.JELLY_BEAN) {
				txtEditarComponente.setBackgroundDrawable(getResources().getDrawable(
						R.drawable.canvas_verde));
			} else {
				txtEditarComponente.setBackground(getResources().getDrawable(
						R.drawable.canvas_verde));
			}
			colorSeleccionado = Constantes.VERDE;
		}
		if(entidad.getColor().equals(Constantes.ROJO)){			
			if(sdk < android.os.Build.VERSION_CODES.JELLY_BEAN) {
				txtEditarComponente.setBackgroundDrawable(getResources().getDrawable(
						R.drawable.canvas_rojo));
			} else {
				txtEditarComponente.setBackground(getResources().getDrawable(
						R.drawable.canvas_rojo));
			}
			colorSeleccionado = Constantes.ROJO;
		}
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.editar_componente, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			grabarComponenteCanvas();
		}
		if(id == R.id.eliminarComponente){
			eliminarComponenteCanvas();
		}
		return super.onOptionsItemSelected(item);
	}
	
	private void eliminarComponenteCanvas() {		
		try{
			helper.eliminar(entidad);
			Toast.makeText(getApplicationContext(), R.string.eliminado_exito,
					Toast.LENGTH_LONG).show();
			// iniciar a crear canvas
			Intent x3 = new Intent(this, CanvasActivity.class);
			x3.putExtra("nombrecanvas", nombreCanvas);
			x3.putExtra("idcanvas", entidad.getCanvas_id());
			startActivity(x3);
			finish();
			
		}
		catch(Exception ex){
			Toast.makeText(getApplicationContext(), ex.getMessage(),
					Toast.LENGTH_LONG).show();
		}
	}

	private void grabarComponenteCanvas() {
		try {					
			entidad.setDescripcion(txtEditarComponente.getText().toString());
			//entidad.setCanvas_id(idcanvas);
			//entidad.setTipocomponente(tipoComponente);
			entidad.setNombre(txtEditarNombreComponente.getText().toString());
			entidad.setColor(colorSeleccionado);

			boolean r = helper.modificar(entidad);

			if (r) {
				Toast.makeText(getApplicationContext(), R.string.grabado_exito,
						Toast.LENGTH_LONG).show();
				// iniciar a crear canvas
				Intent x3 = new Intent(this, CanvasActivity.class);
				x3.putExtra("nombrecanvas", nombreCanvas);
				x3.putExtra("idcanvas", entidad.getCanvas_id());
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
			txtEditarComponente.setBackgroundDrawable(getResources().getDrawable(
					R.drawable.canvas_amarillo));
		} else {
			txtEditarComponente.setBackground(getResources().getDrawable(
					R.drawable.canvas_amarillo));
		}
		
		colorSeleccionado = Constantes.AMARILLO;
	}

	public void clicAzul(View view) {
		if(sdk < android.os.Build.VERSION_CODES.JELLY_BEAN) {
			txtEditarComponente.setBackgroundDrawable(getResources().getDrawable(
					R.drawable.canvas_azul));
		} else {
			txtEditarComponente.setBackground(getResources().getDrawable(
					R.drawable.canvas_azul));
		}
		colorSeleccionado = Constantes.AZUL;
	}

	public void clicVerde(View view) {
		if(sdk < android.os.Build.VERSION_CODES.JELLY_BEAN) {
			txtEditarComponente.setBackgroundDrawable(getResources().getDrawable(
					R.drawable.canvas_verde));
		} else {
			txtEditarComponente.setBackground(getResources().getDrawable(
					R.drawable.canvas_verde));
		}
		colorSeleccionado = Constantes.VERDE;
	}

	public void clicRojo(View view) {
		if(sdk < android.os.Build.VERSION_CODES.JELLY_BEAN) {
			txtEditarComponente.setBackgroundDrawable(getResources().getDrawable(
					R.drawable.canvas_rojo));
		} else {
			txtEditarComponente.setBackground(getResources().getDrawable(
					R.drawable.canvas_rojo));
		}
		colorSeleccionado = Constantes.ROJO;
	}
}
