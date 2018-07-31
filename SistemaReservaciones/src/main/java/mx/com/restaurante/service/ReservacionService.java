package mx.com.restaurante.service;

import java.util.List;

import mx.com.restaurante.domain.Reservacion;

public interface ReservacionService {

	String guardarReservacion(Reservacion reservacion);
	
	Reservacion buscarReservacion(Long id);
	
	List<Reservacion> obtenerTodasReservaciones();
}
