package mx.edu.uacm.domain;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;



@Entity
@Table(name = "\"ARTICULO\"")
public class Articulo {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	
	@ManyToOne
	@JoinColumn(name="fk_carrito")
	private Carrito carrito;
	
	@OneToOne(mappedBy = "articulo", cascade = CascadeType.ALL, 
			  fetch = FetchType.LAZY, optional = false)
	private Dulce dulce;
	
	private int cantidad;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Carrito getCarrito() {
		return carrito;
	}

	public void setCarrito(Carrito carrito) {
		this.carrito = carrito;
	}

	public Dulce getDulce() {
		return dulce;
	}

	public void setDulce(Dulce dulce) {
		if (dulce == null) {
            if (this.dulce != null) {
                this.dulce.setArticulo(null);
            }
        } else {
        	dulce.setArticulo(this);
        }
        this.dulce = dulce;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}
	
	
	
}
