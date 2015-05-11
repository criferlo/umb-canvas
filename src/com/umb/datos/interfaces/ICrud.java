package com.umb.datos.interfaces;

import java.util.ArrayList;

public interface ICrud {
	public long crear(Object obj);
	public boolean modificar(Object obj);
	public boolean eliminar(Object obj);
	public Object consultarUno(long id);	
	public ArrayList<Object> consultarTodos();
	public ArrayList<Object> consultarLike(String parte);
}
