package com.umb.datos;

import java.util.ArrayList;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

import com.umb.datos.entidades.EntidadTipoComponente;
import com.umb.datos.interfaces.ICrud;

public class TipoComponenteHelper extends SQLiteOpenHelper implements ICrud {

	public TipoComponenteHelper(Context context, String name,
			CursorFactory factory, int version) {
		super(context, name, factory, version);
		// TODO Auto-generated constructor stub
	}

	@Override
	public long crear(Object obj) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean modificar(Object obj) throws Exception {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean eliminar(Object obj) throws Exception {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Object consultarUno(long id) throws Exception {
		// probado
		SQLiteDatabase data = this.getReadableDatabase();
		String[] columns = { "id", "nombre" };
		String[] whereArgs = { String.valueOf(id) };
		Cursor cursor = data.query("tipocomponente", columns, "id=?",
				whereArgs, null, null, null);

		EntidadTipoComponente entidad = null;

		if (cursor.moveToFirst()) {
			entidad = new EntidadTipoComponente();
			do {
				entidad.setId(cursor.getLong(0));
				entidad.setNombre(cursor.getString(1));
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
		String[] columns = { "id", "nombre"};
		Cursor cursor = data.query("tipocomponente", columns, null, null, null, null,
				null);

		EntidadTipoComponente entidad = null;
		ArrayList<Object> lista = null;		

		if (cursor.moveToFirst()) {

			lista = new ArrayList<Object>();
			do {
				entidad = new EntidadTipoComponente();
				entidad.setId(cursor.getLong(0));
				entidad.setNombre(cursor.getString(1));
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
