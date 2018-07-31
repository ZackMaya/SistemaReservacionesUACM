package mx.com.restaurante.service.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;

import com.google.common.collect.Lists;

import mx.com.restaurante.domain.Reservacion;
import mx.com.restaurante.domain.repository.ReservacionRepository;
import mx.com.restaurante.service.ReservacionService;

@Service
public class ReservacionServiceImpl implements ReservacionService{

	private static final int MAX_MESAS = 3;

	private static final int MAX_PERSONAS = 10;

	public static final Logger log = Logger.getLogger(ReservacionServiceImpl.class);
	
	@Autowired
	MessageSource messages;
	
	@Autowired
	ReservacionRepository reservacionRepository;
	
	public String guardarReservacion(Reservacion reservacion) {
		String msg;
			
		if(log.isDebugEnabled())
			log.debug("**Entrando a guardarReservacion**");
		
		
		if(reservacion.getNumPersonas()>MAX_PERSONAS){
			msg =messages.getMessage("message.max.numPersonas", null, LocaleContextHolder.getLocale());
			return msg;
		}
		if(reservacion.getNumMesas()>MAX_MESAS){
			msg =messages.getMessage("message.max.numMesas", null, LocaleContextHolder.getLocale());
			return msg;
		}
		if(!(reservacion.getHorario().matches("^([0-9]|0[0-9]|1[0-9]|2[0-3]):[0-5][0-9]$"))){
			msg = messages.getMessage("message.horario", null, LocaleContextHolder.getLocale());
		}
		if(!(reservacion.getFecha().toString().matches("([0-9]{2})/([0-9]{2})/([0-9]{4})"))){
			msg = messages.getMessage("message.fecha.mal.formato",null,  LocaleContextHolder.getLocale());
			return msg;
		}
		
		reservacionRepository.save(reservacion);
		
		return null;
	}
	
	public Reservacion buscarReservacion(Long id) {
		return reservacionRepository.findOne(id);
	}

	public List<Reservacion> obtenerTodasReservaciones() {
		
		return Lists.newArrayList(reservacionRepository.findAll());
	}

}
