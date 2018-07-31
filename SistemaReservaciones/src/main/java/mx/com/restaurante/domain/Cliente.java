package mx.com.restaurante.domain;


import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;


@Entity
@Table(name="cliente")
public class Cliente {

	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="idcliente")
	private Long id;
	
	@NotNull
	private String nombre;
	
	@NotNull
	private String telefono;
	
	@NotNull
	private String email;
	
	private String comentario;
	
	@OneToMany(fetch = FetchType.EAGER, mappedBy="cliente", cascade={CascadeType.ALL})
	private List<Reservacion> reservaciones;

	
	public Cliente(){
		
	}
	
	public Cliente(String nombre, String telefono, String email, String comentario) {
		this.nombre = nombre;
		this.telefono = telefono;
		this.email = email;
		this.comentario = comentario;
	}

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return the nombre
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * @param nombre the nombre to set
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	/**
	 * @return the telefono
	 */
	public String getTelefono() {
		return telefono;
	}

	/**
	 * @param telefono the telefono to set
	 */
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	
	
	/**
	 * @return the comentario
	 */
	public String getComentario() {
		return comentario;
	}

	/**
	 * @param comentario the comentario to set
	 */
	public void setComentario(String comentario) {
		this.comentario = comentario;
	}

	/**
	 * @return the reservaciones
	 */
	public List<Reservacion> getReservaciones() {
		return reservaciones;
	}

	/**
	 * @param reservaciones the reservaciones to set
	 */
	public void setReservaciones(List<Reservacion> reservaciones) {
		this.reservaciones = reservaciones;
	}

	
	
	
	
	
}
