//package mx.edu.uacm;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//import java.util.Iterator;
//
//import org.apache.logging.log4j.LogManager;
//import org.apache.logging.log4j.Logger;
//import org.junit.Test;
//import org.junit.jupiter.api.Order;
//import org.junit.runner.RunWith;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
//
//import mx.edu.uacm.domain.Carrito;
//import mx.edu.uacm.domain.Dulce;
//import mx.edu.uacm.domain.TipoDulce;
//import mx.edu.uacm.interfaces.Vendible;
//
//
//@SpringBootTest
//public class SistDulcesCarritoTest {
//	private static final Logger log = LogManager.getLogger(SistDulcesApplicationTests.class);
//	
//	private Carrito carrito;
//	
//	
//	@Test
//	@Order(1)
//	public void test_agregar_nulo() {	
//		log.debug("SistDulcesCarrito.test_agregar_nulo()");
//		try {
//			carrito = new Carrito();
//			Dulce vendible = null;
////			carrito.agregar(vendible);
//			
//		} catch (Exception e) {
//			log.debug(e);
//			assertEquals(e.getMessage(), "El vendible es nulo :(");
//		}
//	}
//	
//	@Test
//	@Order(2)
//	public void test_agregar_vendible() {	
//		log.debug("SistDulcesCarrito.test_agregar_vendible()");
//		try {
//			carrito = new Carrito();
////			Dulce vendible = new Dulce("Picagomas", "Vero", 40, 150, TipoDulce.GOMITA);
////			carrito.agregar(vendible);
//			double total = carrito.dameTotal();
//			log.debug("total: " + total);
//			assertEquals(40, total);
//		} catch (Exception e) {
////			log.debug(e);
//		}
//	}
//	
//	@Test
//	@Order(3)
//	public void test_eliminar_posicio_menor_a_cero() {	
//		log.debug("SistDulcesCarrito.test_eliminar_posicio_menor_a_cero()");
//		try {
//			carrito = new Carrito();
//			carrito.eliminar(-1);
//		} catch (Exception e) {
//			log.debug(e);
//			assertEquals(e.getMessage(), "La posicion es < 0 :(");
//		}
//	}
//	
//	@Test
//	@Order(4)
//	public void test_eliminar_posicio_sobrepasada() {	
//		log.debug("SistDulcesCarrito.test_eliminar_posicio_sobrepasada()");
//		try {
////			carrito = new Carrito();
////			Dulce vendible = new Dulce("Picagomas", "Vero", 40, 150, TipoDulce.GOMITA);
////			carrito.agregar(vendible);
//			carrito.eliminar(20);
//		} catch (Exception e) {
//			log.debug(e);
//			assertEquals(e.getMessage(), "La posicion sobrepasa el limite :(");
//		}
//	}
//	
//	@Test
//	@Order(5)
//	public void test_eliminar_posicio_correcta() {	
//		log.debug("SistDulcesCarrito.test_eliminar_posicio_correcta()");
//		try {
//			carrito = new Carrito();
////			Dulce vendible = new Dulce("Picagomas", "Vero", 40, 150, TipoDulce.GOMITA);
////			carrito.agregar(vendible);
////			carrito.agregar(vendible);
//			carrito.eliminar(1);
//			double total = carrito.dameTotal();
//			log.debug("total: " + total);
//			assertEquals(40, total);
//		} catch (Exception e) {
//			log.debug(e);
////			assertEquals(e.getMessage(), "La posicion sobrepasa el limite :(");
//		}
//	}
//	
//	@Test
//	@Order(6)
//	public void test_dame_total() {	
//		log.debug("SistDulcesCarrito.test_dame_total()");
//		try {
//			carrito = new Carrito();
////			Dulce vendible = new Dulce("Picagomas", "Vero", 40, 150, TipoDulce.GOMITA);
////			carrito.agregar(vendible);
////			carrito.agregar(vendible);
//			double total = carrito.dameTotal();
//			log.debug("total: " + total);
//			assertEquals(80, total);
//		} catch (Exception e) {
//			log.debug(e);
//		}
//	}
//	
//	@Test
//	@Order(7)
//	public void test_iterator() {	
//		log.debug("SistDulcesCarrito.test_iterator()");
//		try {
//			carrito = new Carrito();
////			Dulce vendible = new Dulce("Picagomas", "Vero", 40, 150, TipoDulce.GOMITA);
////			carrito.agregar(vendible);
////			carrito.agregar(vendible);
//			Iterator<Vendible> iterator = carrito.iterator();
//			log.debug("total: " + iterator.next().calcularPrecio());
//			assertEquals(40, iterator.next().calcularPrecio());
//		} catch (Exception e) {
//			log.debug(e);
//		}
//	}
//	
//}
