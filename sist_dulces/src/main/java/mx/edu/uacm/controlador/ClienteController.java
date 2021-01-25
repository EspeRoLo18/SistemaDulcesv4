package mx.edu.uacm.controlador;

import java.io.IOException;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import mx.edu.uacm.domain.Cliente;
import mx.edu.uacm.domain.Dulce;
import mx.edu.uacm.domain.Tarjeta;
import mx.edu.uacm.excepciones.AppExcepcion;
import mx.edu.uacm.service.ClienteService;
import mx.edu.uacm.service.TarjetaService;

@Controller
@RequestMapping("/clienteController")
public class ClienteController {
	private static final Logger log = LogManager.getLogger(ClienteController.class);
	
	@Autowired
	private ClienteService clienteService; 
	
	@Autowired
	private TarjetaService tarjetaService; 
	
	@RequestMapping(value="/registrarCliente", method = RequestMethod.POST)
	public String registrarCliente(
		Cliente cliente,
		Model model,
		HttpServletRequest request,
		RedirectAttributes redirectAttributes
	) throws AppExcepcion {
		log.debug("ClienteController.registrarCliente()");
		try {
			clienteService.alta(cliente);
			model.addAttribute("cliente", cliente);
			
			log.info("HttpStatus===" + new ResponseEntity<>(HttpStatus.OK));
			return "redirect:/iniciarSesion";
			
		} catch (Exception e) {
			log.info("Error al registrar cliente");
			log.info(e.getMessage());
			e.getStackTrace();
	        return "redirect:/error";
		}
	}
	
	@RequestMapping(value="/login", method = RequestMethod.POST)
	public RedirectView login(Model model, Cliente cliente) {
		log.debug("ClienteController.login()");
		try {
			Cliente clienteExistente;
			clienteExistente = clienteService.findByEmailAndPassword(cliente.getCorreo(), cliente.getPassword());
			if (clienteExistente != null) {
				model.addAttribute("cliente", clienteExistente);
			}
			RedirectView rv = new RedirectView();
	        rv.setContextRelative(true);
	        rv.addStaticAttribute("clienteId", clienteExistente.getId());
	        rv.setUrl("/obtenerCatalogo/{clienteId}");
	        return rv;
//			return "redirect:/obtenerCatalogo";
		} catch (Exception e) {
			log.debug("error en ClienteController.login()");
			RedirectView rv = new RedirectView();
	        rv.setContextRelative(true);
	        rv.setUrl("/error");
	        return rv;
//			return "redirect:/error";
		}
	}
	
	@RequestMapping(path = "/obtenerMetodosDePago", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public String obtenerMetodosDePago(
		@RequestParam(value = "idCliente") String id,
		Model model
	) throws AppExcepcion {
		log.debug("ClienteController.obtenerMetodosDePago()");
		Cliente cliente;
		try {
			log.debug("buscando cliente");
			cliente = clienteService.buscarPorId(Long.valueOf(id));
			log.debug("Regresando el resultado");
			log.debug(cliente.getTarjetas().toString());
			ObjectMapper mapper = new ObjectMapper();
			String json = mapper.writeValueAsString(cliente.getTarjetas());
			return json;
		} catch (AppExcepcion e) {
			log.debug("ClienteController.obtenerMetodosDePago()");
			log.debug(e.getMessage());
			return "error";
		} catch (JsonGenerationException e) {
			log.debug("ClienteController.obtenerMetodosDePago()");
			log.debug(e.getMessage());
			return "error";
		} catch (JsonMappingException e) {
			log.debug("ClienteController.obtenerMetodosDePago()");
			log.debug(e.getMessage());
			return "error";
		} catch (IOException e) {
			log.debug("ClienteController.obtenerMetodosDePago()");
			log.debug(e.getMessage());
			return "error";
		}
	}
	
	@RequestMapping(path = "/registrarTarjeta", method = RequestMethod.POST, produces = "application/json")
	public RedirectView registrarTarejeta(
		@RequestParam(value = "idCliente") int idCliente,
		@RequestParam(value = "titular") String titular,
		@RequestParam(value = "numero") String numero,
		@RequestParam(value = "cv") String cv,
		@RequestParam(value = "mesValidoDesde") String mesValidoDesde,
		@RequestParam(value = "mesValidoHasta") String mesValidoHasta,
		@RequestParam(value = "anioValidoDesde") String anioValidoDesde,
		@RequestParam(value = "anioValidoHasta") String anioValidoHasta,
		Model model
	) throws AppExcepcion {
		log.debug("ClienteController.registrarTarejeta()");
		Cliente cliente;
		Tarjeta nueva_tarjeta = new Tarjeta();
		try {
			log.debug("buscando cliente");
			cliente = clienteService.buscarPorId(Long.valueOf(idCliente));
			nueva_tarjeta.setTitular(titular);
			nueva_tarjeta.setNumero(numero);
			nueva_tarjeta.setCv(cv);
			nueva_tarjeta.setMes_valido_dese(mesValidoDesde);
			nueva_tarjeta.setMes_valido_hasta(mesValidoHasta);
			nueva_tarjeta.setAnio_valido_desde(anioValidoDesde);
			nueva_tarjeta.setAnio_valido_hasta(anioValidoHasta);
			nueva_tarjeta.setCliente(cliente);
			tarjetaService.registrarTarjeta(nueva_tarjeta);
			log.debug("Regresando el resultado");
			log.debug(cliente.getTarjetas().toString());
			ObjectMapper mapper = new ObjectMapper();
			String json = mapper.writeValueAsString(cliente.getTarjetas());
			
			if (cliente != null) {
				model.addAttribute("cliente", cliente);
			}
			RedirectView rv = new RedirectView();
	        rv.setContextRelative(true);
	        rv.addStaticAttribute("clienteId", cliente.getId());
	        rv.setUrl("/obtenerCatalogo/{clienteId}");
	        return rv;
			
//			return json;
		} catch (AppExcepcion e) {
			log.debug("ClienteController.registrarTarejeta()");
			log.debug(e.getMessage());
			RedirectView rv = new RedirectView();
	        rv.setContextRelative(true);
	        rv.setUrl("/error");
	        return rv;
//			return "error";
		} catch (JsonGenerationException e) {
			log.debug("ClienteController.registrarTarejeta()");
			log.debug(e.getMessage());
			RedirectView rv = new RedirectView();
	        rv.setContextRelative(true);
	        rv.setUrl("/error");
	        return rv;
//			return "error";
		} catch (JsonMappingException e) {
			log.debug("ClienteController.registrarTarejeta()");
			log.debug(e.getMessage());
			RedirectView rv = new RedirectView();
	        rv.setContextRelative(true);
	        rv.setUrl("/error");
	        return rv;
//			return "error";
		} catch (IOException e) {
			log.debug("ClienteController.registrarTarejeta()");
			log.debug(e.getMessage());
			RedirectView rv = new RedirectView();
	        rv.setContextRelative(true);
	        rv.setUrl("/error");
	        return rv;
//			return "error";
		}
	}
}
