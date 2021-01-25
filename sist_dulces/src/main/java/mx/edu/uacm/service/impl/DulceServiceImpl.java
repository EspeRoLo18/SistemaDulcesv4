package mx.edu.uacm.service.impl;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import mx.edu.uacm.excepciones.AppExcepcion;
import mx.edu.uacm.persistence.DulceRepository;
import mx.edu.uacm.service.DulceService;
import mx.edu.uacm.constantes.Error;
import mx.edu.uacm.constantes.Regla;
import mx.edu.uacm.domain.Catalogo;
import mx.edu.uacm.domain.Dulce;
import mx.edu.uacm.domain.TipoDulce;

@Service
public class DulceServiceImpl implements DulceService {
	
	private static final Logger log = LogManager.getLogger(DulceServiceImpl.class);
	
	@Autowired
	private DulceRepository dulceRepository;
	
	public Optional<Dulce> consultarDulcePorId(Long id) throws AppExcepcion {
		log.debug("DulceServiceImpl.consultarDulcePorId()");
		return dulceRepository.findById(id);
	}
	
	public Dulce registrarDulce(
		String nombre, 
		String marca, 
		int cantidad, 
		String descripcion, 
		double descuento,
		TipoDulce tipo, 
		double precio, 
		Optional<Catalogo> catalogo, 
		MultipartFile imagen, 
		String uploadDirectory,
		String filePath
	) throws AppExcepcion {
		log.debug("DulceServiceImpl.registrarDulce()");
		Dulce registrado;
		Dulce nuevo_dulce = new Dulce();
		
		//Generar excepciones en caso de errores logicos
		
		if (cantidad < Regla.CANTIDAD_NEGATIVA) {
			throw new AppExcepcion(Error.CANTIDAD_NEGATIVA, Error.CANTIDAD_NEGATIVA);
		}
		
		if (precio < Regla.CANTIDAD_NEGATIVA) {
			throw new AppExcepcion(Error.PRECIO_NEGATIVO, Error.PRECIO_NEGATIVO);
		}
		
		try {
			File dir = new File(uploadDirectory);
			if (!dir.exists()) {
				log.info("Folder Created");
				dir.mkdirs();
			}
			// Save the file locally
			BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(new File(filePath)));
			stream.write(imagen.getBytes());
			stream.close();
		} catch (Exception e) {
			log.info("in catch");
			e.printStackTrace();
		}
		
		try {
			byte[] imageData = imagen.getBytes();
			nuevo_dulce.setImagen(imageData);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		nuevo_dulce.setNombre(nombre);
		nuevo_dulce.setMarca(marca);
		nuevo_dulce.setCantidad(cantidad);
		nuevo_dulce.setDescripcion(descripcion);
		nuevo_dulce.setDescuento(descuento);
		nuevo_dulce.setTipo(tipo);
		nuevo_dulce.setPrecio(precio);
		nuevo_dulce.setCatalogo(catalogo.get());
		
		registrado = dulceRepository.save(nuevo_dulce);
		return registrado;
	}
	
	public void editarDulce(
		int id, 
		String nombre, 
		String marca, 
		int cantidad, 
		String descripcion, 
		double descuento,
		String tipo, 
		double precio,
		MultipartFile imagen, 
		String uploadDirectory, 
		String filePath
	) throws AppExcepcion {
		log.debug("DulceServiceImpl.editarDulce()");
		
		//Generar excepciones en caso de errores logicos
		if (!nombre.isEmpty()) {
			if (nombre.length() > Regla.LONG_MAX_NOMBRE_CATALOGO) {
				throw new AppExcepcion(Error.TAM_NOMBRE_CATALOGO, Error.TAM_NOMBRE_CATALOGO);
			}
			dulceRepository.edita_nombre(nombre, id);
		}
		if (!marca.isEmpty()) {
			if (marca.length() > Regla.LONG_MAX_NOMBRE_CATALOGO) {
				throw new AppExcepcion(Error.TAM_NOMBRE_CATALOGO, Error.TAM_NOMBRE_CATALOGO);
			}
			dulceRepository.edita_marca(marca, id);
		}
		if (cantidad < Regla.CANTIDAD_NEGATIVA) {
			throw new AppExcepcion(Error.CANTIDAD_NEGATIVA, Error.CANTIDAD_NEGATIVA);
		}
		dulceRepository.edita_cantidad(cantidad, id);
		
		if (!descripcion.isEmpty()) {	
			dulceRepository.edita_descripcion(descripcion, id);
		}
		
		dulceRepository.edita_descuento(descuento, id);
			
		dulceRepository.edita_tipo(tipo, id);
		
		if (precio < Regla.CANTIDAD_NEGATIVA) {
			throw new AppExcepcion(Error.PRECIO_NEGATIVO, Error.PRECIO_NEGATIVO);
		}
		dulceRepository.edita_precio(precio, id);
		
		if (!imagen.isEmpty()) {
			try {
				File dir = new File(uploadDirectory);
				if (!dir.exists()) {
					log.info("Folder Created");
					dir.mkdirs();
				}
				// Save the file locally
				BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(new File(filePath)));
				stream.write(imagen.getBytes());
				stream.close();
				byte[] imageData = imagen.getBytes();
				dulceRepository.edita_imagen(imageData, id);
			} catch (Exception e) {
				log.info("error al cargar imagen");
				e.printStackTrace();
			}
		}
	}
	
	public void eliminarDulce(int idInt) throws AppExcepcion {
		dulceRepository.deleteById((long) idInt);
		
	}
	
	public Dulce actualizarCantidad(Dulce dulce) throws AppExcepcion {
		Dulce dulceActualizado = dulceRepository.save(dulce);
		return dulceActualizado;
	}
}
