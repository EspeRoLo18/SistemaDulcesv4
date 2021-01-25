package mx.edu.uacm.service;

import java.util.List;
import java.util.Optional;

import org.springframework.web.multipart.MultipartFile;

import mx.edu.uacm.domain.Catalogo;
import mx.edu.uacm.excepciones.AppExcepcion;

public interface CatalogoService {
	
	public List<Catalogo> consultarCatalogo() throws AppExcepcion;
	
	public Optional<Catalogo> consultarCatalogoPorId(Long id) throws AppExcepcion;

	public Catalogo registrarCatalogo(String nombre, MultipartFile imagen, String uploadDirectory, String filePath) throws AppExcepcion;
	
	public int editarCatalogo(int id, String nombre, MultipartFile imagen, String uploadDirectory, String filePath) throws AppExcepcion;
}
