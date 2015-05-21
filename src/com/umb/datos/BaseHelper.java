package com.umb.datos;

import com.umb.util.Constantes;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class BaseHelper extends SQLiteOpenHelper {

	public BaseHelper(Context context, String name, CursorFactory factory,
			int version) {
		super(context, name, factory, version);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		try {
			// crear
			db.execSQL(Constantes.getSentenciaCrearTablaCanvas());
			Log.e("basehelper", "se crea tabla canvas - oncreate ");
			db.execSQL(Constantes.getSentenciaCrearTablaComponente());
			Log.e("basehelper", "se crea tabla componente - oncreate ");
		} catch (Exception ex) {
			Log.e("basehelper-oncreate-ex", ex.getMessage());
		}
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

		try {
			// borrar
			Log.e("basehelper", "se borra tabla canvas - onupgrade");
			db.execSQL(Constantes.getSentenciaBorrarTablaCanvas());
			Log.e("basehelper", "se borra tabla componente - onupgrade");
			db.execSQL(Constantes.getSentenciaBorrarTablaComponente());
			// crear
			db.execSQL(Constantes.getSentenciaCrearTablaCanvas());
			Log.e("basehelper", "se crea tabla canvas - onupgrade ");
			db.execSQL(Constantes.getSentenciaCrearTablaComponente());
			Log.e("basehelper", "se crea tabla componente - onupgrade ");
		} catch (Exception ex) {
			Log.e("basehelper-onupgrade-ex", ex.getMessage());	
		}
	}
}
