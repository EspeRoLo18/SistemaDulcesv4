package mx.edu.uacm.domain;


import java.util.regex.Pattern;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Type;

import mx.edu.uacm.excepciones.ExcepcionProducto;

//public class Dulce extends Producto {
@Entity
@Table(name = "\"DULCE\"")
public class Dulce {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	private String nombre;
	private String marca;
	private Integer cantidad;
	private String descripcion;
	private double descuento;
	
	@Enumerated(EnumType.STRING)
	private TipoDulce tipo;
	
	private double precio;
	
	@Lob
	@Column(name = "imagen", length = Integer.MAX_VALUE, nullable = true)
	@Type(type="org.hibernate.type.BinaryType")
	private byte[] imagen;
	
	
	@ManyToOne
	@JoinColumn(name="dulces")
	private Catalogo catalogo;
	
	
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="articulo_id")
	private Articulo articulo;
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public Integer getCantidad() {
		return cantidad;
	}

	public void setCantidad(Integer cantidad) {
		this.cantidad = cantidad;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public byte[] getImagen() {
		return imagen;
	}

	public void setImagen(byte[] imagen) {
		this.imagen = imagen;
	}

	public TipoDulce getTipo() {
		return tipo;
	}

	public void setTipo(TipoDulce tipo) {
		this.tipo = tipo;
	}

	public void setCatalogo(Catalogo catalogo) {
		this.catalogo = catalogo;
	}
	
	public Catalogo getCatalogo() {
		return this.catalogo;
	}

	public double getDescuento() {
		return descuento;
	}

	public void setDescuento(double descuento) {
		this.descuento = descuento;
	}

	public double getPrecio() {
		return precio;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}

	public Articulo getArticulo() {
		return articulo;
	}

	public void setArticulo(Articulo articulo) {
		this.articulo = articulo;
	}
	
	
	
//	public Dulce(String nombre, String marca, double precio, 
//			int cantidad, TipoDulce tipo) 
//			throws ExcepcionProducto {
//		super(precio);
//		
//		if (nombre != null) {
//			if (verificarNombre(nombre)) {
//				this.nombre = nombre;
//			} else {
//				throw new ExcepcionProducto("El nombre no cumple con la estructura :(");
//			}
//		} else {
//			throw new ExcepcionProducto("El nombre es nulo :(");
//		}
//		
//		if (marca != null) {
//			if (verificarNombre(marca)) {
//				this.marca = marca;
//			} else {
//				throw new ExcepcionProducto("La marca no cumple con la estructura :(");
//			}
//		} else {
//			throw new ExcepcionProducto("La marca se paso como nula :(");
//		}
//		
//		if (cantidad > 0) {
//			this.cantidad = cantidad;
//		} else {
//			throw new ExcepcionProducto("La cantidad es <= 0 :(");
//		}
//		
//		if (tipo != null) {
//			this.tipo = tipo;
//		} else {
//			throw new ExcepcionProducto("El tipo se paso como nulo :(");
//		}
//		descuento = 0.0;
//	}
//	
//	
//	private boolean verificarNombre(String nombre) {
//		String expresion = "";
//		expresion = "^([A-Z\u00d1\u00c1\u00c9\u00cd\u00da][a-z\u00f1\u00e1\u00e9\u00ed\u00f3\u00fa]+"
//				+ "[ ]?)+$";
//		return Pattern.matches(expresion, nombre);
//	}
//	
//	public Integer getCantidad() {
//		return cantidad;
//	}
//
//	public void setCantidad(int cantidad) throws ExcepcionProducto {
//		if (cantidad > 0) {
//			this.cantidad = cantidad;
//		} else {
//			throw new ExcepcionProducto("La cantidad es <= 0 :(");
//		}
//	}
//
//	public String getDescripcion() {
//		return descripcion;
//	}
//
//	public void setDescripcion(String descripcion) throws ExcepcionProducto {
//		if (descripcion != null) {
//			this.descripcion = descripcion;
//		} else {
//			throw new ExcepcionProducto("La descripcion se paso como nula :(");
//		}
//	}
//
//	public String getImagen() {
//		return imagen;
//	}
//
//	public void setImagen(String imagen) {
//		this.imagen = imagen;
//	}
//
//	public String getMarca() {
//		return marca;
//	}
//	
//	public double getDescuento() {
//		return descuento;
//	}
//
//	public void setDescuento(double descuento) throws ExcepcionProducto {
//		if (descuento >= 0) {
//			if (descuento <=1) {
//				this.descuento = getPrecio() * descuento; 
//			} else {
//				throw new ExcepcionProducto("El descuento es mayor al 100% :(");
//			}
//		} else {
//			throw new ExcepcionProducto("El descuento es menor que el 0% :(");
//		}
//	}
//	
//	public int getId() {
//		return this.id;
//	}
//	
//	public TipoDulce getTipo() {
//		return tipo;
//	}
//
//	@Override
//	public double calcularPrecio() {
//		return getPrecio() - descuento;
//	}
//	
//	
//	@Override
//	public String getNombre() {
//		return this.nombre;
//	}

}
