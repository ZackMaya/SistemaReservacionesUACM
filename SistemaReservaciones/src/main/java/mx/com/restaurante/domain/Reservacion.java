package mx.com.restaurante.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="reservacion")
public class Reservacion {

	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="idreservacion")
	private Long id;
	
	@NotNull	
	private Date fecha;
	
	@NotNull
	private Integer numPersonas;
	
	@NotNull
	private Integer numMesas;
	
	@NotNull
	private String horario;
	
	@ManyToOne
	@JoinColumn(name="idcliente")
	private Cliente cliente;
	
	
	public Reservacion(){
		
	}

	public Reservacion(Date fecha, Integer numPersonas, Integer numMesas, String horario, Cliente cliente) {
		super();
		this.fecha = fecha;
		this.numPersonas = numPersonas;
		this.numMesas = numMesas;
		this.horario = horario;
		this.cliente = cliente;
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
	 * @return the fecha
	 */
	public Date getFecha() {
		return fecha;
	}

	/**
	 * @param fecha the fecha to set
	 */
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	/**
	 * @return the numPersonas
	 */
	public Integer getNumPersonas() {
		return numPersonas;
	}

	/**
	 * @param numPersonas the numPersonas to set
	 */
	public void setNumPersonas(Integer numPersonas) {
		this.numPersonas = numPersonas;
	}

	/**
	 * @return the numMesas
	 */
	public Integer getNumMesas() {
		return numMesas;
	}

	/**
	 * @param numMesas the numMesas to set
	 */
	public void setNumMesas(Integer numMesas) {
		this.numMesas = numMesas;
	}

	/**
	 * @return the horario
	 */
	public String getHorario() {
		return horario;
	}

	/**
	 * @param horario the horario to set
	 */
	public void setHorario(String horario) {
		this.horario = horario;
	}

	/**
	 * @return the cliente
	 */
	public Cliente getCliente() {
		return cliente;
	}

	/**
	 * @param cliente the cliente to set
	 */
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	
	
	
}
