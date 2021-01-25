package mx.edu.uacm.domain;


import mx.edu.uacm.excepciones.ExcepcionProducto;
import mx.edu.uacm.interfaces.Vendible;

public abstract class Producto implements Vendible {
	private double precio;
	
	public Producto(double precio) throws ExcepcionProducto {
		if (precio > 0) {
			this.precio = precio;
		} else {
			throw new ExcepcionProducto("El precio es <= 0 ... :(");
		}
	}

	public double getPrecio() {
		return precio;
	}

	public void setPrecio(double precio) throws ExcepcionProducto {
		double IVA = 0.0;
		if (precio > 0) {
			this.precio = precio;
			IVA = this.precio * 0.16;
			this.precio += IVA;
		} else {
			throw new ExcepcionProducto("El precio es <= 0 ... :(");
		}
	}
	
	public abstract String getNombre();
	
	
	
	
}
