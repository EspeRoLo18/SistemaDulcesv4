package mx.edu.uacm.controlador;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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
import org.springframework.web.servlet.view.RedirectView;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import mx.edu.uacm.constantes.Error;
import mx.edu.uacm.domain.Catalogo;
import mx.edu.uacm.domain.Cliente;
import mx.edu.uacm.domain.Dulce;
import mx.edu.uacm.domain.Producto;
import mx.edu.uacm.domain.TipoDulce;
import mx.edu.uacm.excepciones.AppExcepcion;
import mx.edu.uacm.excepciones.ExcepcionCatalogo;
import mx.edu.uacm.excepciones.ExcepcionProducto;
import mx.edu.uacm.service.CatalogoService;
import mx.edu.uacm.service.ClienteService;


@Controller
public class CatalogoController {
	
	private static final Logger log = LogManager.getLogger(CatalogoController.class);
	
//	@Value("${uploadDir}")
	private String uploadFolder = "/resources";
	
	@Autowired
	private CatalogoService catalogoService;
	
	@Autowired
	private ClienteService clienteService; 
	
	@RequestMapping(path = "/", method = RequestMethod.GET)
	public String obtenerCatalogo(Model model) throws AppExcepcion {
		log.debug("CatalogoController.obtenerCatalogo()");
		List<Catalogo> catalogo = new ArrayList<Catalogo>();
		try {
			log.debug("buscando catalogos");
			catalogo = catalogoService.consultarCatalogo();
			log.debug("Regresando el resultado");
			log.debug(catalogo.toString());
			
			model.addAttribute("catalogos", catalogo);
		} catch (AppExcepcion e) {
			log.debug("CatalogoController.obtenerCatalogo() error: ");
			log.debug(e.getMessage());
		}
		return "index";
	}
	
	@RequestMapping(path = "/obtenerCatalogo/{clienteId}", method = RequestMethod.GET)
	public String obtenerCatalogoCliente(@PathVariable("clienteId") Long clienteId, Model model) throws AppExcepcion {
		log.debug("CatalogoController.obtenerCatalogoCliente()");
		List<Catalogo> catalogo = new ArrayList<Catalogo>();
		try {
			log.debug("clienteId: " + clienteId);
			catalogo = catalogoService.consultarCatalogo();
			Cliente cliente = clienteService.buscarPorId(clienteId);
			log.debug("cliente: " + cliente.getNick_name());
			model.addAttribute("catalogos", catalogo);
			model.addAttribute("cliente", cliente);
		} catch (AppExcepcion e) {
			log.debug("CatalogoController.obtenerCatalogo() error: ");
			log.debug(e.getMessage());
		}
		return "tienda";
	}
	
	@GetMapping("/image/display/{id}")
	@ResponseBody
	public void showImageCatalogo(@PathVariable("id") Long id, HttpServletResponse response, Optional<Catalogo> catalogo)
			throws AppExcepcion {
		log.info("CatalogoController.showImageCatalogo()");
		log.info("Id :: " + id);
		catalogo = catalogoService.consultarCatalogoPorId(id);
		response.setContentType("image/jpeg, image/jpg, image/png, image/gif");
		try {
			response.getOutputStream().write(catalogo.get().getCatlogImg());
			response.getOutputStream().close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	@RequestMapping(value="/registrarCatalogo", method = RequestMethod.POST)
//	@ResponseBody
//	public ResponseEntity<?> registrarCatalogo(
	public String registrarCatalogo(
		@RequestParam(value = "nombre") String nombre,
		final @RequestParam(value = "imagen") MultipartFile imagen,
		Model model,
		HttpServletRequest request
	) throws AppExcepcion {
		log.debug("CatalogoController.registrarCatalogo()");
		Catalogo regresa = new Catalogo();
		try {
			
			String uploadDirectory = request.getServletContext().getRealPath(uploadFolder);
			log.info("uploadDirectory: " + uploadDirectory);
			String fileName = imagen.getOriginalFilename();
			String filePath = Paths.get(uploadDirectory, fileName).toString();
			log.info("FileName: " + imagen.getOriginalFilename());
			
			log.debug("registrando catalogo");
			regresa = catalogoService.registrarCatalogo(nombre, imagen, uploadDirectory, filePath);
			log.debug("Regresando el resultado");
			log.debug(regresa.toString());
			
			log.info("HttpStatus===" + new ResponseEntity<>(HttpStatus.OK));

			return "redirect:/";
		} catch (AppExcepcion e) {
			String mensaje;
			switch (e.getMessage()) {
			case Error.TAM_NOMBRE_CATALOGO:
				mensaje = Error.TAM_NOMBRE_CATALOGO;
				break;
			default:
				mensaje = "Error desconocido";
				break;
			}
			return "redirect:/error";
		}
	}
	
	
	@RequestMapping(value="/editarCatalogo", method = RequestMethod.POST)
	public String editarCatalogo(
		@RequestParam(value = "id") String id,
		@RequestParam(value = "nombre") String nombre,
		final @RequestParam(value = "imagen") MultipartFile imagen,
		Model model,
		HttpServletRequest request
	) throws AppExcepcion {
		log.debug("CatalogoController.editarCatalogo()");
		int regresa;
		try {
			Optional<Catalogo> catalogoObj = catalogoService.consultarCatalogoPorId(Long.valueOf(id));
			String uploadDirectory = request.getServletContext().getRealPath(uploadFolder);
			log.info("uploadDirectory: " + uploadDirectory);
			String fileName = imagen.getOriginalFilename();
			String filePath = Paths.get(uploadDirectory, fileName).toString();
			log.info("FileName: " + imagen.getOriginalFilename());
			
			int idInt = Integer.valueOf(id);
			
			log.debug("registrando catalogo");
			regresa = catalogoService.editarCatalogo(idInt, nombre, imagen, uploadDirectory, filePath);
			log.debug("Regresando el resultado");
			log.debug(regresa);
			
			log.info("HttpStatus===" + new ResponseEntity<>(HttpStatus.OK));
			return "redirect:/";
		} catch (AppExcepcion e) {
			String mensaje;
			switch (e.getMessage()) {
			case Error.TAM_NOMBRE_CATALOGO:
				mensaje = Error.TAM_NOMBRE_CATALOGO;
				break;
			default:
				mensaje = "Error desconocido";
				break;
			}
			return "redirect:/error";
		}
	}
	
	
	
	@RequestMapping(path = "/catalogo/productos/{id}", method = RequestMethod.GET)
	public String obtenerDulcesDeCatalogo(@PathVariable("id") Long id, Model model) throws AppExcepcion {
		log.debug("CatalogoController.obtenerDulcesDeCatalogo()");
		Optional<Catalogo> catalogo;
		try {
			log.debug("buscando Dulces");
			catalogo = catalogoService.consultarCatalogoPorId(id);
			log.debug("Regresando el resultado");
			log.debug(catalogo.get().getDulces().toString());
			model.addAttribute("dulces", catalogo.get().getDulces());
			model.addAttribute("catalogo", catalogo.get());
		} catch (AppExcepcion e) {
			log.debug("CatalogoController.obtenerCatalogo() error: ");
			log.debug(e.getMessage());
		}
		return "catalogo";
	}
	
	
	@RequestMapping(path = "/catalogo/productos/tienda", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public String obtenerDulcesDeCatalogoTienda(
		@RequestParam(value = "id") String id,
//		@PathVariable("id") Long id,
		Model model
	) throws AppExcepcion {
		log.debug("CatalogoController.obtenerDulcesDeCatalogoTienda()");
		Optional<Catalogo> catalogo;
		try {
			log.debug("buscando Dulces");
			catalogo = catalogoService.consultarCatalogoPorId(Long.valueOf(id));
			log.debug("Regresando el resultado");
			log.debug(catalogo.get().getDulces().toString());
			model.addAttribute("dulces", catalogo.get().getDulces());
			ObjectMapper mapper = new ObjectMapper();
			String json = mapper.writeValueAsString(catalogo.get().getDulces());
			return json;
//			return "tienda";
		} catch (AppExcepcion e) {
			log.debug("CatalogoController.obtenerCatalogo() error: ");
			log.debug(e.getMessage());
			return "error";
//			return "error";
		} catch (JsonGenerationException e) {
			log.debug("CatalogoController.obtenerCatalogo() error: ");
			log.debug(e.getMessage());
			return "error";
		} catch (JsonMappingException e) {
			log.debug("CatalogoController.obtenerCatalogo() error: ");
			log.debug(e.getMessage());
			return "error";
		} catch (IOException e) {
			log.debug("CatalogoController.obtenerCatalogo() error: ");
			log.debug(e.getMessage());
			return "error";
		}
	}
	
	
	
	
}
