package mx.edu.uacm.domain;

//import java.util.ArrayList;
//import java.util.Iterator;
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
import javax.persistence.Transient;


//import mx.edu.uacm.excepciones.ExcepcionCarrito;
//import mx.edu.uacm.interfaces.ICarrito;
//import mx.edu.uacm.interfaces.Vendible;

@Entity
@Table(name = "\"CARRITO\"")
public class Carrito {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	
	private Double total;
	
	@OneToMany(mappedBy = "carrito", cascade = CascadeType.ALL,
			orphanRemoval = true, fetch = FetchType.LAZY)
	private List<Articulo> articulos;
	
	@ManyToOne
	@JoinColumn(name="carritos")
	private Cliente cliente;
	
	@Column(columnDefinition="BOOLEAN DEFAULT false")
	private boolean pagado;
	
	@OneToOne(mappedBy = "carrito", cascade = CascadeType.ALL, 
			  fetch = FetchType.LAZY, optional = false)
	private Tarjeta metodoDePago;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Double getTotal() {
		return total;
	}

	public void setTotal(Double total) {
		this.total = total;
	}

	public List<Articulo> getArticulos() {
		return articulos;
	}

	public void setArticulos(List<Articulo> articulos) {
		this.articulos = articulos;
		for (Articulo articulo : articulos) {
			articulo.setCarrito(this);
		}
	}
	
	@Transient
	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public boolean getPagado() {
		return pagado;
	}

	public void setPagado(boolean pagado) {
		this.pagado = pagado;
	}

	public Tarjeta getMetodoDePago() {
		return metodoDePago;
	}

	public void setMetodoDePago(Tarjeta metodoDePago) {
		if (metodoDePago == null) {
            if (this.metodoDePago != null) {
                this.metodoDePago.setCarrito(null);
            }
        } else {
        	metodoDePago.setCarrito(this);
        }
        this.metodoDePago = metodoDePago;
	}
	
}



/*
public class Carrito {
	private List<Vendible> lista;
	private Double total;
	
	public Carrito() {
		lista = new ArrayList<Vendible>();
		total = 0.0;
	}

	@Override
	public void agregar(Vendible v) throws ExcepcionCarrito {
		if (v != null) {
			lista.add(v);
			total += v.calcularPrecio();
		} else {
			throw new ExcepcionCarrito("El vendible es nulo :(");
		}
	}

	@Override
	public void eliminar(int posicion) throws ExcepcionCarrito {
		if (posicion >= 0) {
			if (posicion < lista.size()) {
				Vendible v = lista.remove(posicion);
				total -= v.calcularPrecio();
			} else {
				throw new ExcepcionCarrito("La posicion sobrepasa el limite :(");
			}
		} else {
			throw new ExcepcionCarrito("La posicion es < 0 :(");
		}
	}

	@Override
	public double dameTotal() {
		return total;
	}

	@Override
	public Iterator<Vendible> iterator() {
		return this.lista.iterator();
	}
}
*/
