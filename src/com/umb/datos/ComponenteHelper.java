package com.umb.datos;

import java.util.ArrayList;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

import com.umb.datos.entidades.EntidadComponente;
import com.umb.datos.entidades.EntidadCanvas;
import com.umb.datos.interfaces.ICrud;
import com.umb.util.Constantes;

public class ComponenteHelper extends SQLiteOpenHelper implements ICrud {

	public ComponenteHelper(Context context, String name,
			CursorFactory factory, int version) {
		super(context, Constantes.getNombreBD(), factory, Constantes.getVersionBd());
		// TODO Auto-generated constructor stub
	}

	@Override
	public long crear(Object obj) throws Exception {

		// probado
		EntidadComponente plan = new EntidadComponente();
		plan = (EntidadComponente) obj;
		SQLiteDatabase data = this.getWritableDatabase();
		ContentValues valores = new ContentValues();
		valores.put("nombre", plan.getNombre());
		valores.put("descripcion", plan.getDescripcion());
		valores.put("canvas_id", plan.getCanvas_id());
		valores.put("tipocomponente", plan.getTipocomponente());
		valores.put("color", plan.getColor());
		long id = data.insert("componente", null, valores);
		data.close();
		return id;

	}

	@Override
	public boolean modificar(Object obj) throws Exception {
		// probado
		EntidadComponente plan = new EntidadComponente();
		plan = (EntidadComponente) obj;
		SQLiteDatabase data = this.getWritableDatabase();
		ContentValues valores = new ContentValues();
		valores.put("nombre", plan.getNombre());
		valores.put("descripcion", plan.getDescripcion());
		valores.put("canvas_id", plan.getCanvas_id());
		valores.put("tipocomponente", plan.getTipocomponente());
		String[] args = { plan.getId() + "" };
		// retorna el numero de registros afectados
		long id = data.update("componente", valores, "id=?", args);
		data.close();
		if (id > 0) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public boolean eliminar(Object obj) throws Exception {
		// probado
		EntidadComponente plan = new EntidadComponente();
		plan = (EntidadComponente) obj;
		SQLiteDatabase data = this.getWritableDatabase();
		String[] args = { plan.getId() + "" };
		long id = data.delete("componente", "id=?", args);
		data.close();
		if (id > 0) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public Object consultarUno(long id) throws Exception {
		// probado
		SQLiteDatabase data = this.getReadableDatabase();
		String[] columns = { "id", "nombre", "descripcion", "canvas_id",
				"tipocomponente","color" };
		String[] whereArgs = { String.valueOf(id) };
		Cursor cursor = data.query("componente", columns, "id=?", whereArgs,
				null, null, null);

		EntidadComponente entidad = null;

		if (cursor.moveToFirst()) {
			entidad = new EntidadComponente();
			do {
				entidad.setId(cursor.getLong(0));
				entidad.setNombre(cursor.getString(1));
				entidad.setDescripcion(cursor.getString(2));
				entidad.setCanvas_id(cursor.getLong(3));
				entidad.setTipocomponente(cursor.getString(4));
				entidad.setColor(cursor.getString(5));
			} while (cursor.moveToNext());
		} else {
			return null;
		}
		return entidad;
	}

	@Override
	public ArrayList<Object> consultarTodos() throws Exception {
		// probado
		SQLiteDatabase data = this.getReadableDatabase();
		String[] columns = { "id", "nombre", "descripcion", "canvas_id","tipocomponente" };
		Cursor cursor = data.query("componente", columns, null, null, null, null,
				null);

		EntidadComponente entidad = null;
		ArrayList<Object> lista = null;		

		if (cursor.moveToFirst()) {

			lista = new ArrayList<Object>();
			do {
				entidad = new EntidadComponente();
				entidad.setId(cursor.getLong(0));
				entidad.setNombre(cursor.getString(1));
				entidad.setDescripcion(cursor.getString(2));
				entidad.setCanvas_id(cursor.getLong(3));
				entidad.setTipocomponente(cursor.getString(4));
				entidad.setColor(cursor.getString(5));
				lista.add(entidad);
			} while (cursor.moveToNext());
		} else {
			return null;
		}
		return lista;
	}

	
	public ArrayList<Object> consultarTodosPorPlanyTipo(long idplan,String tipo) throws Exception {
		// probado
		SQLiteDatabase data = this.getReadableDatabase();
		String[] columns = { "id", "nombre", "descripcion", "canvas_id","tipocomponente","color"};
		String[] whereArgs =  {idplan+"",tipo};
		Cursor cursor = data.query("componente", columns, "canvas_id=? and tipocomponente=?", whereArgs, null, null,
				null);
		
		EntidadComponente entidad = null;
		ArrayList<Object> lista = null;		

		if (cursor.moveToFirst()) {

			lista = new ArrayList<Object>();
			do {
				entidad = new EntidadComponente();
				entidad.setId(cursor.getLong(0));
				entidad.setNombre(cursor.getString(1));
				entidad.setDescripcion(cursor.getString(2));
				entidad.setCanvas_id(cursor.getLong(3));
				entidad.setTipocomponente(cursor.getString(4));
				entidad.setColor(cursor.getString(5));
				
				lista.add(entidad);
			} while (cursor.moveToNext());
		} else {
			return null;
		}
		return lista;
	}
	
	@Override
	public ArrayList<Object> consultarLike(String parte) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub

	}

}
