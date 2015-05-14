package com.umb.datos.interfaces;

import java.util.ArrayList;

public interface ICrud {
	public long crear(Object obj) throws Exception;
	public boolean modificar(Object obj) throws Exception;
	public boolean eliminar(Object obj) throws Exception;
	public Object consultarUno(long id) throws Exception;	
	public ArrayList<Object> consultarTodos() throws Exception;
	public ArrayList<Object> consultarLike(String parte) throws Exception;
}
