package com.umb.datos;

import java.util.ArrayList;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

import com.umb.datos.entidades.EntidadCanvas;
import com.umb.datos.interfaces.ICrud;
import com.umb.util.Constantes;

public class CanvasHelper extends SQLiteOpenHelper implements ICrud {

	public CanvasHelper(Context context, String name, CursorFactory factory,
			int version) {
		super(context, Constantes.getNombreBD(), factory, Constantes.getVersionBd());
	}

	@Override
	public long crear(Object obj) throws Exception{
		//probado
		EntidadCanvas plan = new EntidadCanvas();
		plan =(EntidadCanvas)obj;
		SQLiteDatabase data = this.getWritableDatabase();
		ContentValues valores = new ContentValues();
		valores.put("nombre", plan.getNombre());
		valores.put("descripcion", plan.getDescripcion());
		valores.put("creador",plan.getAutor());
		long id = data.insert("canvas", null, valores);
		data.close();
		return id;
	}

	@Override
	public boolean modificar(Object obj) throws Exception{
		//probado
		EntidadCanvas plan = new EntidadCanvas();
		plan =(EntidadCanvas)obj;
		SQLiteDatabase data = this.getWritableDatabase();
		ContentValues valores = new ContentValues();
		valores.put("nombre", plan.getNombre());
		valores.put("descripcion", plan.getDescripcion());
		valores.put("creador",plan.getAutor());		
		String[] args = {plan.getId()+""};
		//retorna el numero de registros  afectados
		long id = data.update("canvas", valores, "id=?",args);		
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
		EntidadCanvas plan = new EntidadCanvas();
		plan =(EntidadCanvas)obj;
		SQLiteDatabase data = this.getWritableDatabase();
		String[] args = {plan.getId()+""};
		long id =data.delete("canvas", "id=?", args);
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
		Cursor cursor = data.query("canvas", columns, "id=?", whereArgs, null, null, null);
		
		EntidadCanvas entidad = null;
		
		if(cursor.moveToFirst()){
			entidad = new EntidadCanvas();
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
		Cursor cursor = data.query("canvas", columns, null, null, null, null, null);
		
		EntidadCanvas entidad = null;
		ArrayList<Object> lista=null;; 
		
		if(cursor.moveToFirst()){
			
			lista = new ArrayList<Object>();
			do{
				entidad = new EntidadCanvas();
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
		Cursor cursor = data.query("canvas", columns, "descripcion like '%?%'", args , null, null, null);
		
		EntidadCanvas entidad = null;
		ArrayList<Object> lista=null;; 
		
		if(cursor.moveToFirst()){
			
			lista = new ArrayList<Object>();
			do{
				entidad = new EntidadCanvas();
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
