package mx.edu.uacm.service.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.edu.uacm.domain.Articulo;
import mx.edu.uacm.excepciones.AppExcepcion;
import mx.edu.uacm.persistence.ArticuloRepository;
import mx.edu.uacm.service.ArticuloService;

@Service
public class ArticuloServiceImpl implements ArticuloService {
	
	private static final Logger log = LogManager.getLogger(ArticuloServiceImpl.class);
	
	@Autowired
	private ArticuloRepository articuloRepository;
	
	public Articulo registrarArticulo(Articulo articulo) throws AppExcepcion {
		Articulo registrado;
		
		//excepciones
		
		registrado = articuloRepository.save(articulo);
		return registrado;
	}
}
