package mx.edu.uacm.service;

import mx.edu.uacm.domain.Cliente;
import mx.edu.uacm.excepciones.AppExcepcion;

public interface ClienteService {
	
	public Cliente alta(Cliente cliente) throws AppExcepcion;

	public Cliente findByEmailAndPassword(String correo, String password) throws AppExcepcion;
	
	public Cliente buscarPorId(Long id) throws AppExcepcion;
	
	public Cliente actualizaCliente(Cliente cliente) throws AppExcepcion;
}
