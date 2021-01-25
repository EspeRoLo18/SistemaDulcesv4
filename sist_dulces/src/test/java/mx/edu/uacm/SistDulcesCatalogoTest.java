package mx.edu.uacm;

import static org.junit.jupiter.api.Assertions.*;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;
import org.junit.jupiter.api.Order;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;




@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes=SistDulcesApplication.class)
public class SistDulcesCatalogoTest {
	private static final Logger log = LogManager.getLogger(SistDulcesCatalogoTest.class);
	
	
	
	@Test
	@Order(1)
	public void test_agregar_nulo() {
		try {
//			Producto p = null;
//			p = new Dulce("Picagomas", "Vero", 40, 150, TipoDulce.GOMITA);
//			((Dulce)p).setImagen("picagomas.jpeg");
//			((Dulce)p).setDescripcion("Es una gomita picosa");
//			catalogo.agregar(p);
		} catch (Exception e) {
			log.debug(e.getMessage());
			assertEquals("El producto es nulo :(", e.getMessage());
		}
	}
	
	@Test
	@Order(2)
	public void test_agregar_producto() {
		try {
//			Producto p;
//			p = new Dulce("Picagomas", "Vero", 40, 150, TipoDulce.GOMITA);
//			((Dulce)p).setImagen("picagomas.jpeg");
//			((Dulce)p).setDescripcion("Es una gomita picosa");
//			catalogo.agregar(p);
			
//			Producto regresa = catalogo.obtenerProducto(0);
//			assertNotNull(regresa);
		} catch (Exception e) {
			log.debug(e.getMessage());
		}
	}
	
	@Test
	@Order(3)
	public void test_eliminar_nulo() {
		try {
//			Producto p;
//			p = new Dulce("Picagomas", "Vero", 40, 150, TipoDulce.GOMITA);
//			((Dulce)p).setImagen("picagomas.jpeg");
//			((Dulce)p).setDescripcion("Es una gomita picosa");
//			catalogo.agregar(p);
//			catalogo.eliminar(null);
			
//			Producto regresa = catalogo.obtenerProducto(0);
//			assertNotNull(regresa);
		} catch (Exception e) {
			log.debug(e.getMessage());
			assertEquals("El producto es nulo :(", e.getMessage());
		}
	}
	
	@Test
	@Order(4)
	public void test_eliminar_producto() {
		try {
//			Producto p;
//			p = new Dulce("Picagomas", "Vero", 40, 150, TipoDulce.GOMITA);
//			((Dulce)p).setImagen("picagomas.jpeg");
//			((Dulce)p).setDescripcion("Es una gomita picosa");
//			catalogo.agregar(p);
//			catalogo.eliminar(p);
			
//			Producto regresa = catalogo.obtenerProducto(0);
//			assertNotNull(regresa);
		} catch (Exception e) {
			log.debug(e.getMessage());
			assertEquals("La posicion es > tama\\u00f1o del catalogo :(", e.getMessage());
		}
	}
	
	@Test
	@Order(5)
	public void test_obtener_producto_posicion_menor_a_cero() {
		try {
//			Producto p;
//			p = new Dulce("Picagomas", "Vero", 40, 150, TipoDulce.GOMITA);
//			((Dulce)p).setImagen("picagomas.jpeg");
//			((Dulce)p).setDescripcion("Es una gomita picosa");
//			catalogo.agregar(p);
//			catalogo.eliminar(p);
			
//			Producto regresa = catalogo.obtenerProducto(-1);
//			assertNotNull(regresa);
		} catch (Exception e) {
			log.debug(e.getMessage());
			assertEquals("La posicion es < 0 :(", e.getMessage());
		}
	}
	
	@Test
	@Order(6)
	public void test_obtener_producto_posicion_mayor() {
		try {
//			Producto p;
//			p = new Dulce("Picagomas", "Vero", 40, 150, TipoDulce.GOMITA);
//			((Dulce)p).setImagen("picagomas.jpeg");
//			((Dulce)p).setDescripcion("Es una gomita picosa");
//			catalogo.agregar(p);
//			Producto regresa = catalogo.obtenerProducto(11);
		} catch (Exception e) {
			log.debug(e.getMessage());
			assertEquals("La posicion es > tamaño del catalogo :(", e.getMessage());
		}
	}
	
	@Test
	@Order(7)
	public void test_obtener_producto_correcto() {
		try {
//			Producto p;
//			p = new Dulce("Picagomas", "Vero", 40, 150, TipoDulce.GOMITA);
//			((Dulce)p).setImagen("picagomas.jpeg");
//			((Dulce)p).setDescripcion("Es una gomita picosa");
//			catalogo.agregar(p);
//			Producto regresa = catalogo.obtenerProducto(0);
//			assertEquals(p, regresa);
		} catch (Exception e) {
			log.debug(e.getMessage());
		}
	}
	
}
