package com.umb;

import com.umb.datos.CanvasHelper;
import com.umb.datos.ComponenteHelper;
import com.umb.datos.entidades.EntidadCanvas;
import com.umb.util.Constantes;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

public class EditarCanvasActivity extends Activity {

	private long idCanvas;
	private String nombreCanvas;
	private String descripcionCanvas;
	private String autorCanvas;
	EntidadCanvas x1 = new EntidadCanvas();
	
	CanvasHelper helper;
	ComponenteHelper helpercom;
	
	EditText txtNombreCanvas;
	EditText txtDescripcionCanvas;
	EditText txtAutorCanvas;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_editar_canvas);
			
		setTitle("Editar canvas");
		
		helper = new CanvasHelper(getApplicationContext(), null, null, Constantes.getVersionBd());
		helpercom = new ComponenteHelper(getApplicationContext(), null, null, Constantes.getVersionBd());
		
		//bundle
		Bundle bundle = getIntent().getExtras();
		
		idCanvas = bundle.getLong("idcanvas");
		nombreCanvas = bundle.getString("nombrecanvas");
		descripcionCanvas = bundle.getString("descripcioncanvas");
		autorCanvas = bundle.getString("autorcanvas");
		
		//aterrizar
		txtNombreCanvas = (EditText)findViewById(R.id.txtEditarNombre);
		txtDescripcionCanvas = (EditText)findViewById(R.id.txtEditarDescripcion);
		txtAutorCanvas = (EditText)findViewById(R.id.txtEditarAutor);
		
		txtNombreCanvas.setText(nombreCanvas);
		txtDescripcionCanvas.setText(descripcionCanvas);
		txtAutorCanvas.setText(autorCanvas);
		
		x1.setId(idCanvas);
		x1.setAutor(autorCanvas);
		x1.setDescripcion(descripcionCanvas);
		x1.setNombre(nombreCanvas);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.editar_canvas, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		int id = item.getItemId();
		if (id == R.id.modificarCanvas) {
			grabarCanvas();
		}
		if (id == R.id.eliminarCanvas) {
			eliminarCanvas();
		}
		return super.onOptionsItemSelected(item);
	}

	private void eliminarCanvas() {
		try{
			//eliminar detalle
			helpercom.eliminarPorIdCanvas(idCanvas);
			//eliminar canvas
			helper.eliminar(x1);
			Toast.makeText(getApplicationContext(), R.string.eliminado_exito,
					Toast.LENGTH_LONG).show();
			
			Intent intent = new Intent(this,MainActivity.class);
			startActivity(intent);
		}
		catch(Exception ex){
			Toast.makeText(getApplicationContext(),ex.getMessage(),
					Toast.LENGTH_LONG).show();
		}
	}

	private void grabarCanvas() {
		try{
			
			x1.setAutor(txtAutorCanvas.getText().toString());
			x1.setDescripcion(txtDescripcionCanvas.getText().toString());
			x1.setNombre(txtNombreCanvas.getText().toString());
			
			if(helper.modificar(x1)){
				Toast.makeText(getApplicationContext(), R.string.grabado_exito,
						Toast.LENGTH_LONG).show();
				
				Intent intent = new Intent(this,CanvasActivity.class);
				intent.putExtra("idcanvas",x1.getId());
				intent.putExtra("nombrecanvas", x1.getNombre());
				intent.putExtra("autorcanvas", x1.getAutor());
				startActivity(intent);
			}
			else{
				Toast.makeText(getApplicationContext(), R.string.grabado_noexito,
						Toast.LENGTH_LONG).show();
			}
		}	
		catch(Exception ex){
			Toast.makeText(getApplicationContext(), ex.getMessage(),
					Toast.LENGTH_LONG).show();
		}
	}
	
	
}
