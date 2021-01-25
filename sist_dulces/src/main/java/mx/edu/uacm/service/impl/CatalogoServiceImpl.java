package mx.edu.uacm.service.impl;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import mx.edu.uacm.constantes.Regla;
import mx.edu.uacm.constantes.Error;
import mx.edu.uacm.domain.Catalogo;
import mx.edu.uacm.domain.Dulce;
import mx.edu.uacm.excepciones.AppExcepcion;
import mx.edu.uacm.persistence.CatalogoRepository;
import mx.edu.uacm.service.CatalogoService;

@Service
public class CatalogoServiceImpl implements CatalogoService {
	private static final Logger log = LogManager.getLogger(CatalogoServiceImpl.class);
	
	@Autowired
	private CatalogoRepository catalogoRepository;
	
	public List<Catalogo> consultarCatalogo() throws AppExcepcion {
		log.debug("CatalogoServiceImpl.consultarCatalogo()");
		List<Catalogo> catalogos = new ArrayList<Catalogo>();
		catalogos = catalogoRepository.findAllEvent();
		Catalogo catalogo = catalogoRepository.regresa_solo_un_catalogo();
		log.debug("Regresando del servicio el resultado");
		return catalogos;
	}
	
	public Optional<Catalogo> consultarCatalogoPorId(Long id) throws AppExcepcion {
		return catalogoRepository.buscarPorId(id);
	}
	
	public Catalogo registrarCatalogo(String nombre, MultipartFile imagen, String uploadDirectory, String filePath) throws AppExcepcion {
		log.debug("CatalogoServiceImpl.registrarCatalogo()");
		Catalogo registrado;
		Catalogo nuevo_catalogo = new Catalogo();
		
		//Generar excepciones en caso de errores logicos
		if (nombre.length() > Regla.LONG_MAX_NOMBRE_CATALOGO) {
			throw new AppExcepcion(Error.TAM_NOMBRE_CATALOGO, Error.TAM_NOMBRE_CATALOGO);
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
			nuevo_catalogo.setCatlogImg(imageData);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		nuevo_catalogo.setNombre(nombre);
		
		registrado = catalogoRepository.save(nuevo_catalogo);
		
		return registrado;
	}
	
	
	public int editarCatalogo(int id, String nombre, MultipartFile imagen, String uploadDirectory, String filePath) throws AppExcepcion {
		log.debug("CatalogoServiceImpl.editarCatalogo()");
		int registrado;
		
		//Generar excepciones en caso de errores logicos
		if (nombre.length() > Regla.LONG_MAX_NOMBRE_CATALOGO) {
			throw new AppExcepcion(Error.TAM_NOMBRE_CATALOGO, Error.TAM_NOMBRE_CATALOGO);
		}
		
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
				catalogoRepository.edita_catalogo_imagen(imageData, id);
			} catch (Exception e) {
				log.info("error al cargar imagen");
				e.printStackTrace();
			}
		}
		
		
		registrado = catalogoRepository.edita_catalogo_nombre(nombre, id);
		
		return registrado;
	}
	
}
