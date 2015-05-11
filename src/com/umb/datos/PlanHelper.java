package com.umb.datos;

import java.util.ArrayList;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

import com.umb.datos.entidades.EntidadPlan;
import com.umb.datos.interfaces.ICrud;
import com.umb.util.Constantes;

public class PlanHelper extends SQLiteOpenHelper implements ICrud {

	public PlanHelper(Context context, String name, CursorFactory factory,
			int version) {
		super(context, Constantes.getNombreBD(), factory, version);
	}

	@Override
	public long crear(Object obj) {
		
		EntidadPlan plan = new EntidadPlan();
		plan =(EntidadPlan)obj;
		SQLiteDatabase data = this.getWritableDatabase();
		ContentValues valores = new ContentValues();
		valores.put("nombre", plan.getNombre());
		valores.put("descripcion", plan.getDescripcion());
		valores.put("creador",plan.getCreador());
		long id = data.insert("plan", null, valores);
		data.close();
		return id;
	}

	@Override
	public boolean modificar(Object obj) {
		return true;		
	}

	@Override
	public boolean eliminar(Object obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Object consultarUno(long id) {
		
		SQLiteDatabase data = this.getReadableDatabase();
		String[] columns = {"id","nombre","descripcion","creador"};
		String[] whereArgs = {String.valueOf(id)};
		Cursor cursor = data.query("plan", columns, "id=?", whereArgs, null, null, null);
		
		EntidadPlan entidad = null;
		
		if(cursor.moveToFirst()){
			entidad = new EntidadPlan();
			do{
				entidad.setId(cursor.getLong(0));
				entidad.setNombre(cursor.getString(1));
				entidad.setDescripcion(cursor.getString(2));
				entidad.setCreador(cursor.getString(3));
			}while(cursor.moveToNext());
		}
		else{
			return null;
		}
		return entidad;
	}

	@Override
	public ArrayList<Object> consultarTodos() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Object> consultarLike(String parte) {
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
