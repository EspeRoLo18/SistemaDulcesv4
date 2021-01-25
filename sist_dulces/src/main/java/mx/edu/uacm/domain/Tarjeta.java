package mx.edu.uacm.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "\"TARJETA\"")
public class Tarjeta {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	
	private String titular;
	
	private String numero;
	
	private String cv;
	
	private String mes_valido_dese;
	
	private String anio_valido_desde;
	
	private String mes_valido_hasta;
	
	private String anio_valido_hasta;
	
	@ManyToOne
	@JoinColumn(name="tarjetas")
	private Cliente cliente;
	
	@Column(columnDefinition="BOOLEAN DEFAULT false")
	private boolean principal;
	
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="carrito_id")
	private Carrito carrito;
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getTitular() {
		return titular;
	}

	public void setTitular(String titular) {
		this.titular = titular;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getCv() {
		return cv;
	}

	public void setCv(String cv) {
		this.cv = cv;
	}

	public String getMes_valido_dese() {
		return mes_valido_dese;
	}

	public void setMes_valido_dese(String mes_valido_dese) {
		this.mes_valido_dese = mes_valido_dese;
	}

	public String getAnio_valido_desde() {
		return anio_valido_desde;
	}

	public void setAnio_valido_desde(String anio_valido_desde) {
		this.anio_valido_desde = anio_valido_desde;
	}

	public String getMes_valido_hasta() {
		return mes_valido_hasta;
	}

	public void setMes_valido_hasta(String mes_valido_hasta) {
		this.mes_valido_hasta = mes_valido_hasta;
	}

	public String getAnio_valido_hasta() {
		return anio_valido_hasta;
	}

	public void setAnio_valido_hasta(String anio_valido_hasta) {
		this.anio_valido_hasta = anio_valido_hasta;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public boolean getPrincipal() {
		return principal;
	}

	public void setPrincipal(boolean principal) {
		this.principal = principal;
	}

	public Carrito getCarrito() {
		return carrito;
	}

	public void setCarrito(Carrito carrito) {
		this.carrito = carrito;
	}
	
}
