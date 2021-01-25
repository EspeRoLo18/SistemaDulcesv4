package mx.edu.uacm.service;

import mx.edu.uacm.domain.Articulo;
import mx.edu.uacm.excepciones.AppExcepcion;

public interface ArticuloService {

	Articulo registrarArticulo(Articulo articulo) throws AppExcepcion;
	
}
