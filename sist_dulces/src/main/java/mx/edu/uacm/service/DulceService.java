package mx.edu.uacm.service;

import java.util.Optional;

import org.springframework.web.multipart.MultipartFile;

import mx.edu.uacm.domain.Catalogo;
import mx.edu.uacm.domain.Dulce;
import mx.edu.uacm.domain.TipoDulce;
import mx.edu.uacm.excepciones.AppExcepcion;

public interface DulceService {
	
	public Optional<Dulce> consultarDulcePorId(Long id) throws AppExcepcion;

	public Dulce registrarDulce(String nombre, String marca, int cantidad, String descripcion, double descuento,
			TipoDulce tipo, double precio, Optional<Catalogo> catalogo, MultipartFile imagen, String uploadDirectory,
			String filePath) throws AppExcepcion;
	
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
		) throws AppExcepcion;

	public void eliminarDulce(int idInt) throws AppExcepcion;
	
	public Dulce actualizarCantidad(Dulce dulce) throws AppExcepcion;
}
