package mx.edu.uacm.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.edu.uacm.constantes.Error;
import mx.edu.uacm.constantes.Regla;
import mx.edu.uacm.domain.Cliente;
import mx.edu.uacm.excepciones.AppExcepcion;
import mx.edu.uacm.persistence.ClienteRepository;
import mx.edu.uacm.service.ClienteService;



@Service
public class ClienteServiceImpl implements ClienteService {
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	public Cliente alta(Cliente cliente) throws AppExcepcion {
		
		//validacion de reglas
		if (!cliente.getCorreo().matches(Regla.REGEX_CORREO)) {
			throw new AppExcepcion(Error.MAL_CORREO, Error.NO_CORREO);
		}
		
		return clienteRepository.save(cliente);
	}
	
	public Cliente findByEmailAndPassword(String correo, String password) throws AppExcepcion {
		Cliente cliente = clienteRepository.findByEmailAndPassword(correo, password);
		return cliente;
	}
	
	public Cliente buscarPorId(Long id) throws AppExcepcion {
		Optional<Cliente> cliente = clienteRepository.findById(id);
		return cliente.get();
	}
	
	public Cliente actualizaCliente(Cliente cliente) throws AppExcepcion {
		Cliente actualizado;
		actualizado = clienteRepository.save(cliente);
		return actualizado;
	}
}
