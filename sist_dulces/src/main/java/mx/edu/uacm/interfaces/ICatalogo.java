package mx.edu.uacm.interfaces;


import mx.edu.uacm.domain.Producto;
import mx.edu.uacm.excepciones.ExcepcionCatalogo;

public interface ICatalogo {
	public void agregar(Producto p) throws ExcepcionCatalogo;
	public void eliminar(Producto p) throws ExcepcionCatalogo;
	public Producto obtenerProducto(int posicion) throws ExcepcionCatalogo;
}
