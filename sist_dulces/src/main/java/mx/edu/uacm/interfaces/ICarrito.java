package mx.edu.uacm.interfaces;

import mx.edu.uacm.excepciones.ExcepcionCarrito;

public interface ICarrito {
	public void agregar(Vendible v) throws ExcepcionCarrito;
	public void eliminar(int posicion) throws ExcepcionCarrito;
	public double dameTotal();
}
