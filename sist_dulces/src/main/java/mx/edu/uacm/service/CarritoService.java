package mx.edu.uacm.service;

import mx.edu.uacm.domain.Carrito;
import mx.edu.uacm.excepciones.AppExcepcion;


public interface CarritoService {
	
	public Carrito consultarCarritoPorId(Long id) throws AppExcepcion;
	
	public Carrito registrarCarrito(Carrito nuevo_carrito) throws AppExcepcion;
	
	public Carrito actualizarCarrito(Carrito carrito) throws AppExcepcion;
}
