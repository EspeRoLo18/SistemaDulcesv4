package mx.edu.uacm.controlador;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import mx.edu.uacm.domain.Dulce;
import mx.edu.uacm.domain.Producto;
import mx.edu.uacm.domain.TipoDulce;
import mx.edu.uacm.excepciones.ExcepcionCatalogo;
import mx.edu.uacm.excepciones.ExcepcionProducto;
import mx.edu.uacm.interfaces.ICatalogo;

@Controller
public class ControladorVentas {
//	@Autowired
	private ICatalogo catalogo;
	
//	@GetMapping("/")
//	public String inicio(Model model) {
//		Producto p;
//		try {
//			p = new Dulce("Picagomas", "Vero", 40, 150, TipoDulce.GOMITA);
//			((Dulce)p).setImagen("picagomas.jpeg");
//			((Dulce)p).setDescripcion("Es una gomita picosa");
//			catalogo.agregar(p);
//			p = new Dulce("Pulparidno", "Rosa", 40, 150, TipoDulce.TAMARINDO);
//			((Dulce)p).setImagen("pulparindo.jpeg");
//			catalogo.agregar(p);
//			p = new Dulce("Tarugos", "Rosa", 40, 150, TipoDulce.TAMARINDO);
//			((Dulce)p).setImagen("tarugos.jpeg");
//			catalogo.agregar(p);
//			p = new Dulce("Ricaleta", "Rosa", 40, 150, TipoDulce.PALETA);
//			((Dulce)p).setImagen("ricaleta.jpeg");
//			catalogo.agregar(p);
//			p = new Dulce("Pelonetes", "Lucas", 40, 150, TipoDulce.PICANTE);
//			((Dulce)p).setImagen("pelon_pelonetes.jpeg");
//			catalogo.agregar(p);
//			p = new Dulce("Peonetes", "Lucas", 40, 150, TipoDulce.PICANTE);
//			((Dulce)p).setImagen("pelon_pelonetes.jpeg");
//			catalogo.agregar(p);
//			
//			model.addAttribute("catalogo", this.catalogo);
//		} catch (ExcepcionProducto e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (ExcepcionCatalogo e) {
//			e.printStackTrace();
//		}
//		return "index";
//	}
}
