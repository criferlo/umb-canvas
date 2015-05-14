package com.umb.datos;

import java.util.ArrayList;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

import com.umb.datos.entidades.EntidadComponente;
import com.umb.datos.entidades.EntidadPlan;
import com.umb.datos.interfaces.ICrud;

public class ComponenteHelper extends SQLiteOpenHelper implements ICrud {

	public ComponenteHelper(Context context, String name,
			CursorFactory factory, int version) {
		super(context, name, factory, version);
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
		valores.put("plan_id", plan.getPlan_id());
		valores.put("tipocomponente_id", plan.getTipocomponente_id());
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
		valores.put("plan_id", plan.getPlan_id());
		valores.put("tipocomponente_id", plan.getTipocomponente_id());
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
		long id = data.delete("plan", "id=?", args);
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
		String[] columns = { "id", "nombre", "descripcion", "plan_id",
				"tipocomponente_id" };
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
				entidad.setPlan_id(cursor.getLong(3));
				entidad.setTipocomponente_id(cursor.getLong(4));
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
		String[] columns = { "id", "nombre", "descripcion", "plan_id","tipocomponente_id" };
		Cursor cursor = data.query("componente", columns, null, null, null, null,
				null);

		EntidadPlan entidad = null;
		ArrayList<Object> lista = null;		

		if (cursor.moveToFirst()) {

			lista = new ArrayList<Object>();
			do {
				entidad = new EntidadPlan();
				entidad.setId(cursor.getLong(0));
				entidad.setNombre(cursor.getString(1));
				entidad.setDescripcion(cursor.getString(2));
				entidad.setCreador(cursor.getString(3));
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
