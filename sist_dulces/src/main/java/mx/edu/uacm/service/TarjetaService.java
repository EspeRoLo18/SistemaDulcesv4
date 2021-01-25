package mx.edu.uacm.service;

import mx.edu.uacm.domain.Tarjeta;
import mx.edu.uacm.excepciones.AppExcepcion;

public interface TarjetaService {

	public Tarjeta registrarTarjeta(Tarjeta nueva_tarjeta) throws AppExcepcion;
	
	public Tarjeta consultarTarjetaPorId(Long id) throws AppExcepcion; 
}
