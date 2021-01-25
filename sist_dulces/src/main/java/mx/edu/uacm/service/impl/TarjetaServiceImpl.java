package mx.edu.uacm.service.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.edu.uacm.domain.Tarjeta;
import mx.edu.uacm.excepciones.AppExcepcion;
import mx.edu.uacm.service.TarjetaService;
import mx.edu.uacm.persistence.TarjetaRepository;

@Service
public class TarjetaServiceImpl implements TarjetaService{
	
	private static final Logger log = LogManager.getLogger(TarjetaServiceImpl.class);
	
	@Autowired
	private TarjetaRepository tarjetaRepository;
	
	public Tarjeta consultarTarjetaPorId(Long id) throws AppExcepcion {
		return tarjetaRepository.buscarPorId(id);
	}
	
	public Tarjeta registrarTarjeta(Tarjeta nueva_tarjeta) throws AppExcepcion {
		Tarjeta tarjeta_registrada;
		
		//validaciones
		
		tarjeta_registrada = tarjetaRepository.save(nueva_tarjeta);
		return tarjeta_registrada;
	}
}
