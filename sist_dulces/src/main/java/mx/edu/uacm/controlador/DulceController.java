package mx.edu.uacm.controlador;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import mx.edu.uacm.domain.Catalogo;
import mx.edu.uacm.domain.Dulce;
import mx.edu.uacm.domain.TipoDulce;
import mx.edu.uacm.excepciones.AppExcepcion;
import mx.edu.uacm.service.CatalogoService;
import mx.edu.uacm.service.DulceService;

@Controller
public class DulceController {
	private static final Logger log = LogManager.getLogger(DulceController.class);
	
	private String uploadFolder = "/resources";
	
	@Autowired
	private DulceService dulceService;
	
	@Autowired
	private CatalogoService catalogoService;
	
	@GetMapping("/image/display/dulce/{id}")
	@ResponseBody
	public void showImageDulce(@PathVariable("id") Long id, HttpServletResponse response, Optional<Dulce> dulce)
			throws AppExcepcion {
		log.info("DulceController.showImageDulce()");
		log.info("Id :: " + id);
		dulce = dulceService.consultarDulcePorId(id);
		response.setContentType("image/jpeg, image/jpg, image/png, image/gif");
		try {
			response.getOutputStream().write(dulce.get().getImagen());
			response.getOutputStream().close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	@RequestMapping(value="/registrarDulce", method = RequestMethod.POST)
	public RedirectView registrarDulce(
		@RequestParam(value = "catalogo") String catalogo,
		@RequestParam(value = "nombre") String nombre,
		@RequestParam(value = "marca") String marca,
		@RequestParam(value = "cantidad") String cantidad,
		@RequestParam(value = "descripcion") String descripcion,
		@RequestParam(value = "descuento") String descuento,
		@RequestParam(value = "tipo") TipoDulce tipo,
		@RequestParam(value = "precio") String precio,
		final @RequestParam(value = "imagen") MultipartFile imagen,
		Model model,
		HttpServletRequest request,
		RedirectAttributes redirectAttributes
	) throws AppExcepcion {
		log.debug("DULCEController.registrarDulce()");
		Dulce regresa = new Dulce();
		try {
			
			Optional<Catalogo> catalogoObj = catalogoService.consultarCatalogoPorId(Long.valueOf(catalogo));
			String uploadDirectory = request.getServletContext().getRealPath(uploadFolder);
			log.info("uploadDirectory: " + uploadDirectory);
			String fileName = imagen.getOriginalFilename();
			String filePath = Paths.get(uploadDirectory, fileName).toString();
			log.info("FileName: " + imagen.getOriginalFilename());
			
			int cantidadInt = Integer.valueOf(cantidad); 
			double descuentoDoub = Double.valueOf(descuento);
			double precioDoub = Double.valueOf(precio);
			log.debug("registrando dulce");
			regresa = dulceService.registrarDulce(nombre, marca, cantidadInt, descripcion, descuentoDoub, tipo, precioDoub, catalogoObj, imagen, uploadDirectory, filePath);
			log.debug("Regresando el resultado");
			log.debug(regresa.toString());
			
			log.info("HttpStatus===" + new ResponseEntity<>(HttpStatus.OK));
			RedirectView rv = new RedirectView();
	        rv.setContextRelative(true);
	        rv.addStaticAttribute("id", catalogoObj.get().getId());
	        rv.setUrl("/catalogo/productos/{id}");
	        return rv;
			
		} catch (AppExcepcion e) {
			log.debug("DulceController.obtenerCatalogo() error: ");
			log.debug(e.getMessage());
			RedirectView rv = new RedirectView();
	        rv.setContextRelative(true);
	        rv.setUrl("/catalogo/productos/{id}");
	        return rv;
		}
	}
	
	
	@RequestMapping(value="/editarDulce", method = RequestMethod.POST, params="action=editar")
	public RedirectView editarDulce(
		@RequestParam(value = "catalogo") String catalogo,
		@RequestParam(value = "id") String id,
		@RequestParam(value = "nombre") String nombre,
		@RequestParam(value = "marca") String marca,
		@RequestParam(value = "cantidad") String cantidad,
		@RequestParam(value = "descripcion") String descripcion,
		@RequestParam(value = "descuento") String descuento,
		@RequestParam(value = "tipo") TipoDulce tipo,
		@RequestParam(value = "precio") String precio,
		final @RequestParam(value = "imagen") MultipartFile imagen,
		Model model,
		HttpServletRequest request,
		RedirectAttributes redirectAttributes
	) throws AppExcepcion {
		log.debug("DULCEController.editarDulce()");
		try {
			Optional<Catalogo> catalogoObj = catalogoService.consultarCatalogoPorId(Long.valueOf(catalogo));
			String uploadDirectory = request.getServletContext().getRealPath(uploadFolder);
			log.info("uploadDirectory: " + uploadDirectory);
			String fileName = imagen.getOriginalFilename();
			String filePath = Paths.get(uploadDirectory, fileName).toString();
			log.info("FileName: " + imagen.getOriginalFilename());
			
			int idInt = Integer.valueOf(id); 
			int cantidadInt = Integer.valueOf(cantidad);
			double descuentoDoub = Double.valueOf(descuento);
			double precioDoub = Double.valueOf(precio);
			log.debug("tipo: " + tipo.toString());
			log.debug("editando dulce");
			dulceService.editarDulce(idInt, nombre, marca, cantidadInt, descripcion, descuentoDoub, tipo.toString(), precioDoub, imagen, uploadDirectory, filePath);
			
			log.info("HttpStatus===" + new ResponseEntity<>(HttpStatus.OK));
			RedirectView rv = new RedirectView();
	        rv.setContextRelative(true);
	        rv.addStaticAttribute("id", catalogoObj.get().getId());
	        rv.setUrl("/catalogo/productos/{id}");
	        return rv;
			
		} catch (AppExcepcion e) {
			log.debug("DulceController.obtenerCatalogo() error: ");
			log.debug(e.getMessage());
			Optional<Catalogo> catalogoObj = catalogoService.consultarCatalogoPorId(Long.valueOf(catalogo));
			RedirectView rv = new RedirectView();
	        rv.setContextRelative(true);
	        rv.addStaticAttribute("id", catalogoObj.get().getId());
	        rv.setUrl("/catalogo/productos/{id}");
	        return rv;
		}
	}
	
	@RequestMapping(value="/editarDulce", method = RequestMethod.POST, params="action=eliminar")
	public RedirectView eliminarDulce(
		@RequestParam(value = "catalogo") String catalogo,
		@RequestParam(value = "id") String id,
		Model model,
		HttpServletRequest request,
		RedirectAttributes redirectAttributes
	) throws AppExcepcion {
		log.debug("DULCEController.eliminarDulce()");
		try {
			Optional<Catalogo> catalogoObj = catalogoService.consultarCatalogoPorId(Long.valueOf(catalogo));
			
			int idInt = Integer.valueOf(id); 
			
			log.debug("eliminar dulce");
			dulceService.eliminarDulce(idInt);
			
			log.info("HttpStatus===" + new ResponseEntity<>(HttpStatus.OK));
			RedirectView rv = new RedirectView();
	        rv.setContextRelative(true);
	        rv.addStaticAttribute("id", catalogoObj.get().getId());
	        rv.setUrl("/catalogo/productos/{id}");
	        return rv;
			
		} catch (AppExcepcion e) {
			log.debug("DulceController.obtenerCatalogo() error: ");
			log.debug(e.getMessage());
			Optional<Catalogo> catalogoObj = catalogoService.consultarCatalogoPorId(Long.valueOf(catalogo));
			RedirectView rv = new RedirectView();
	        rv.setContextRelative(true);
	        rv.addStaticAttribute("id", catalogoObj.get().getId());
	        rv.setUrl("/catalogo/productos/{id}");
	        return rv;
		}
	}
	
	@RequestMapping(path = "/dulce/obtenerDulce", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public String obtenerDulce(
		@RequestParam(value = "id") String id,
		Model model
	) throws AppExcepcion {
		log.debug("DulceController.obtenerDulce()");
		Optional<Dulce> dulce;
		try {
			log.debug("buscando Dulce");
			dulce = dulceService.consultarDulcePorId(Long.valueOf(id));
			log.debug("Regresando el resultado");
			log.debug(dulce.get().getNombre());
			ObjectMapper mapper = new ObjectMapper();
			String json = mapper.writeValueAsString(dulce.get());
			return json;
		} catch (AppExcepcion e) {
			log.debug("DulceController.obtenerDulce() error: ");
			log.debug(e.getMessage());
			return "error";
		} catch (JsonGenerationException e) {
			log.debug("DulceController.obtenerDulce() error: ");
			log.debug(e.getMessage());
			return "error";
		} catch (JsonMappingException e) {
			log.debug("DulceController.obtenerDulce() error: ");
			log.debug(e.getMessage());
			return "error";
		} catch (IOException e) {
			log.debug("DulceController.obtenerDulce() error: ");
			log.debug(e.getMessage());
			return "error";
		}
	}
	
}
