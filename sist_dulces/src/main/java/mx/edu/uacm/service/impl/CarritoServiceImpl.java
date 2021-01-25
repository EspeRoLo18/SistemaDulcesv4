package mx.edu.uacm.service.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.edu.uacm.domain.Carrito;
import mx.edu.uacm.excepciones.AppExcepcion;
import mx.edu.uacm.persistence.CarritoRepository;
import mx.edu.uacm.service.CarritoService;

@Service
public class CarritoServiceImpl implements CarritoService {
	
	private static final Logger log = LogManager.getLogger(CarritoServiceImpl.class);
	
	@Autowired
	private CarritoRepository carritoRepository;
	
	public Carrito consultarCarritoPorId(Long id) throws AppExcepcion {
		return carritoRepository.buscarPorId(id);
	}
	
	public Carrito registrarCarrito(Carrito nuevo_carrito) throws AppExcepcion {
		Carrito carrito_registrado;
		
		//Validaciones reglas y excepciones
		
		carrito_registrado = carritoRepository.save(nuevo_carrito);
		return carrito_registrado;
	}
	
	public Carrito actualizarCarrito(Carrito carrito) throws AppExcepcion {
		return carritoRepository.save(carrito);
	}
	
}
