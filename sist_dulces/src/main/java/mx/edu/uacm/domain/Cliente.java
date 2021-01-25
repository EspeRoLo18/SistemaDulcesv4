package mx.edu.uacm.domain;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.ColumnTransformer;

import com.fasterxml.jackson.annotation.JsonBackReference;


@Entity
@Table(name = "\"CLIENTE\"")
public class Cliente {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	
	private String nick_name;
	
	private String correo;
	
	@ColumnTransformer(write=" MD5(?) ", read=" MD5(?) ")
	private String password;
	
	@OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL,
			orphanRemoval = true, fetch = FetchType.LAZY)
	@JsonBackReference
	private List<Tarjeta> tarjetas;
	
	@OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL,
			orphanRemoval = true, fetch = FetchType.LAZY)
	private List<Carrito> carritos;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNick_name() {
		return nick_name;
	}

	public void setNick_name(String nick_name) {
		this.nick_name = nick_name;
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<Carrito> getCarritos() {
		return carritos;
	}

	public void setCarritos(List<Carrito> carritos) {
		this.carritos.clear();
		this.carritos = carritos;
		for (Carrito carrito: carritos) {
			carrito.setCliente(this);
		}
	}

	public List<Tarjeta> getTarjetas() {
		return tarjetas;
	}

	public void setTarjetas(List<Tarjeta> tarjetas) {
		this.tarjetas = tarjetas;
		for (Tarjeta tarjeta: tarjetas) {
			tarjeta.setCliente(this);
		}
	}
	
}
