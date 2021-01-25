package mx.edu.uacm.domain;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Type;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.annotation.JsonBackReference;

import mx.edu.uacm.excepciones.ExcepcionCatalogo;
import mx.edu.uacm.interfaces.ICatalogo;


//public class Catalogo implements ICatalogo, Iterable<Producto> {
@Entity
@Table(name = "\"CATALOGO\"")
public class Catalogo {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	
	private String nombre;
	
	@Lob
	@Column(name = "imagen", length = Integer.MAX_VALUE, nullable = true)
	@Type(type="org.hibernate.type.BinaryType")
	private byte[] catlogImg;
	
	@OneToMany(mappedBy = "catalogo", cascade = CascadeType.ALL,
			orphanRemoval = true, fetch = FetchType.LAZY)
	@JsonBackReference
	private List<Dulce> dulces;
	
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
	
	public byte[] getCatlogImg() {
		return catlogImg;
	}
	public void setCatlogImg(byte[] catlogImg) {
		this.catlogImg = catlogImg;
	}
	public List<Dulce> getDulces() {
		return dulces;
	}
	public void setDulces(List<Dulce> dulces) {
		this.dulces = dulces;
		for (Dulce dulce: dulces) {
			dulce.setCatalogo(this);
		}
	}
	
	
	
	
//	private List<Producto> productos;
//	
//	
//	
//	public Catalogo() {
//		productos = new ArrayList<Producto>();
//	}
//	
//	@Override
//	public void agregar(Producto p) throws ExcepcionCatalogo {
//		if (p != null) {
//			productos.add(p);
//		} else {
//			throw new ExcepcionCatalogo("El producto es nulo :(");
//		}
//	}
//	@Override
//	public void eliminar(Producto p) throws ExcepcionCatalogo {
//		if (p != null) {
//			productos.add(p);
//		} else {
//			throw new ExcepcionCatalogo("El producto es nulo :(");
//		}
//	}
//	@Override
//	public Producto obtenerProducto(int posicion) throws ExcepcionCatalogo {
//		Producto p = null;
//		if (posicion >= 0) {
//			if (posicion < productos.size()) {
//				p = productos.get(posicion);
//			} else {
//				throw new ExcepcionCatalogo("La posicion es > tama\u00f1o del catalogo :(");
//			}
//		} else {
//			throw new ExcepcionCatalogo("La posicion es < 0 :(");
//		}
//		return p;
//	}
//
//	@Override
//	public Iterator<Producto> iterator() {
//		return productos.iterator();
//	}


}
