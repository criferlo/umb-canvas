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
		super(context, Constantes.getNombreBD(), factory, Constantes.getVersionBd());
	}

	@Override
	public long crear(Object obj) throws Exception{
		//probado
		EntidadPlan plan = new EntidadPlan();
		plan =(EntidadPlan)obj;
		SQLiteDatabase data = this.getWritableDatabase();
		ContentValues valores = new ContentValues();
		valores.put("nombre", plan.getNombre());
		valores.put("descripcion", plan.getDescripcion());
		valores.put("creador",plan.getAutor());
		long id = data.insert("plan", null, valores);
		data.close();
		return id;
	}

	@Override
	public boolean modificar(Object obj) throws Exception{
		//probado
		EntidadPlan plan = new EntidadPlan();
		plan =(EntidadPlan)obj;
		SQLiteDatabase data = this.getWritableDatabase();
		ContentValues valores = new ContentValues();
		valores.put("nombre", plan.getNombre());
		valores.put("descripcion", plan.getDescripcion());
		valores.put("creador",plan.getAutor());		
		String[] args = {plan.getId()+""};
		//retorna el numero de registros  afectados
		long id = data.update("plan", valores, "id=?",args);		
		data.close();
		if(id>0){
			return true;
		}else{
			return false;
		}					
	}

	@Override
	public boolean eliminar(Object obj) throws Exception{
		//probado
		EntidadPlan plan = new EntidadPlan();
		plan =(EntidadPlan)obj;
		SQLiteDatabase data = this.getWritableDatabase();
		String[] args = {plan.getId()+""};
		long id =data.delete("plan", "id=?", args);
		data.close();
		if(id>0){
			return true;
		}
		else{
			return false;
		}
	}

	@Override
	public Object consultarUno(long id) throws Exception{
		//probado
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
				entidad.setAutor(cursor.getString(3));
			}while(cursor.moveToNext());
		}
		else{
			return null;
		}
		return entidad;
	}

	@Override
	public ArrayList<Object> consultarTodos() throws Exception{
		//probado
		SQLiteDatabase data = this.getReadableDatabase();
		String[] columns = {"id","nombre","descripcion","creador"};
		Cursor cursor = data.query("plan", columns, null, null, null, null, null);
		
		EntidadPlan entidad = null;
		ArrayList<Object> lista=null;; 
		
		if(cursor.moveToFirst()){
			
			lista = new ArrayList<Object>();
			do{
				entidad = new EntidadPlan();
				entidad.setId(cursor.getLong(0));
				entidad.setNombre(cursor.getString(1));
				entidad.setDescripcion(cursor.getString(2));
				entidad.setAutor(cursor.getString(3));
				lista.add(entidad);
			}while(cursor.moveToNext());
		}
		else{
			return null;
		}
		return lista;
	}

	@Override
	public ArrayList<Object> consultarLike(String parte) throws Exception{
		SQLiteDatabase data = this.getReadableDatabase();
		String[] columns = {"id","nombre","descripcion","creador"};
		String[] args = {parte};
		Cursor cursor = data.query("plan", columns, "descripcion like '%?%'", args , null, null, null);
		
		EntidadPlan entidad = null;
		ArrayList<Object> lista=null;; 
		
		if(cursor.moveToFirst()){
			
			lista = new ArrayList<Object>();
			do{
				entidad = new EntidadPlan();
				entidad.setId(cursor.getLong(0));
				entidad.setNombre(cursor.getString(1));
				entidad.setDescripcion(cursor.getString(2));
				entidad.setAutor(cursor.getString(3));
				lista.add(entidad);
			}while(cursor.moveToNext());
		}
		else{
			return null;
		}
		return lista;
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
