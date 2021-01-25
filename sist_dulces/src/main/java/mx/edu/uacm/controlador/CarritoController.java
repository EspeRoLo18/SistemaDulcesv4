package mx.edu.uacm.controlador;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import mx.edu.uacm.domain.Articulo;
import mx.edu.uacm.domain.Carrito;
import mx.edu.uacm.domain.Cliente;
import mx.edu.uacm.domain.Dulce;
import mx.edu.uacm.domain.Tarjeta;
import mx.edu.uacm.excepciones.AppExcepcion;
import mx.edu.uacm.service.ArticuloService;
import mx.edu.uacm.service.CarritoService;
import mx.edu.uacm.service.ClienteService;
import mx.edu.uacm.service.DulceService;
import mx.edu.uacm.service.TarjetaService;

@Controller
@RequestMapping("/carritoController")
public class CarritoController {
	private static final Logger log = LogManager.getLogger(CarritoController.class);
	
	@Autowired
	private CarritoService carritoService; 
	
	@Autowired
	private ClienteService clienteService;
	
	@Autowired
	private DulceService dulceService;
	
	@Autowired
	private TarjetaService tarjetaService;
	
	@Autowired
	private ArticuloService articuloService;
	
	@RequestMapping(value="/pagarCarrito", method = RequestMethod.POST)
	@ResponseBody
	public String pagarCarrito(
		@RequestParam(value = "idCliente") String idCliente,
		@RequestParam(value = "articulos") String articulos,
		@RequestParam(value = "metodoDePagoId") String metodoDePagoId,
		Model model
	) {
		log.debug("CarritoController.registrarCarrito()");
		try {
			log.debug("articulos: " + articulos);
			Carrito nuevo_carrito = new Carrito();
			Cliente cliente = clienteService.buscarPorId(Long.valueOf(idCliente));
			log.debug("cliente: " + cliente.getNick_name());
			nuevo_carrito.setCliente(cliente);
			nuevo_carrito.setPagado(true);
			log.debug("metodoDePagoId: " + metodoDePagoId);
			Tarjeta metodoDePago = tarjetaService.consultarTarjetaPorId(Long.valueOf(metodoDePagoId));
			log.debug("metodoDePago: " + metodoDePago.getNumero());
			nuevo_carrito.setMetodoDePago(metodoDePago);
			nuevo_carrito = carritoService.registrarCarrito(nuevo_carrito);
			log.debug("nuevo_carrito: " + nuevo_carrito.getId());
			ObjectMapper mapper = new ObjectMapper();
			JsonNode actualObj = mapper.readTree(articulos);
			List<Articulo> listaDeArticulos = new ArrayList<Articulo>();
			double total = 0;
			for (JsonNode jsonNode : actualObj) {
				Long idProducto = jsonNode.get("idProducto").asLong();
				int cantidad = jsonNode.get("cantidad").asInt();
				Dulce dulce = dulceService.consultarDulcePorId(idProducto).get();
				Articulo articulo = new Articulo();
				articulo.setCarrito(nuevo_carrito);
				articulo.setDulce(dulce);
				articulo.setCantidad(cantidad);
				articulo = articuloService.registrarArticulo(articulo);
				log.debug("articuloRegistrado: " + articulo.getId());
				listaDeArticulos.add(articulo);
				log.debug("cantidad antes de actualizar: " + dulce.getCantidad());
				total += cantidad * dulce.getPrecio();
				dulce.setCantidad(dulce.getCantidad() - cantidad);
				dulce = dulceService.actualizarCantidad(dulce);
				log.debug("cantidad despues de actualizar: " + dulce.getCantidad());
			}
			log.debug("Lista de articulos: " + listaDeArticulos.toString());
			nuevo_carrito.setArticulos(listaDeArticulos);
			nuevo_carrito.setTotal(total);
			carritoService.actualizarCarrito(nuevo_carrito);
			List<Carrito> carritosLista = new ArrayList<Carrito>();
			carritosLista.add(nuevo_carrito);
			cliente.setCarritos(carritosLista);
			clienteService.actualizaCliente(cliente);
			return "pagado";
		} catch (AppExcepcion e) {
			log.debug(e.getMessage());
			return "error";
		} catch (JsonMappingException e) {
			e.printStackTrace();
			log.debug(e.getMessage());
			return "error";
		} catch (JsonProcessingException e) {
			log.debug(e.getMessage());
			return "error";
		}
		
	}
	
//	public String pagarCarrito() {
//		
//	}
	
}
