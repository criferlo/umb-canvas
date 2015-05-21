package com.umb;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

/***
 * Cuando no tiene detalle entonces muestra esta activity
 * invitando a crear nuevo post
 * @author pc1
 *
 */
public class SinComponenteActivity extends Activity {

	private long idcanvas;
	private String tipoComponente;
	private String nombreCanvas;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_sin_componente);
		Bundle x1 = getIntent().getExtras();
		setTitle(x1.getString("categoria"));
		idcanvas = x1.getLong("idcanvas");
		tipoComponente = x1.getString("tipocomponente");
		nombreCanvas = x1.getString("nombrecanvas");
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	
	public void clic(View v){
		//llevar a crear un nuevo post
		Intent x1 = new Intent(this,NuevoComponenteActivity.class);
		x1.putExtra("idcanvas", idcanvas);
		x1.putExtra("tipocomponente", tipoComponente);
		x1.putExtra("nombrecanvas", nombreCanvas);
		
		startActivity(x1);
	}
}
