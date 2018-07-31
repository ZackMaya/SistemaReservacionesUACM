package mx.com.restaurante.test.service;

import static org.junit.Assert.assertTrue;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import mx.com.restaurante.Application;
import mx.com.restaurante.domain.Cliente;
import mx.com.restaurante.domain.Reservacion;
import mx.com.restaurante.service.ClienteService;
import mx.com.restaurante.service.ReservacionService;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes=Application.class)
public class ReservacionServiceTest {

	public static final Logger log = Logger.getLogger(ReservacionServiceTest.class);
	
	@Autowired
	ReservacionService reservacionService;
	@Autowired
	ClienteService clienteService;
	
	@Autowired
	MessageSource messages;
	
	@Test
    public void pruebaFechaFormato() throws ParseException{
		Long idCliente = (long) 457;
		String msg;
        Date fecha= new Date();
        String date="1999/11/12";
        fecha=new SimpleDateFormat("dd/mm/yyyy").parse(date);
        Cliente clienteEncontrado = new Cliente();
		clienteEncontrado = clienteService.buscarCliente(idCliente);
		Reservacion reserva = new Reservacion(fecha, 3, 1, "12:00", clienteEncontrado);
		msg = reservacionService.guardarReservacion(reserva);
		
		log.debug("*************");
		
		log.debug(msg);
		
		
		log.debug("*************");
		
		String msgEsperado = messages.getMessage("message.fecha.mal.formato", null, LocaleContextHolder.getLocale());
		
		assertTrue(msgEsperado.equals(msg));
       
    }
	
	@Test
	public void guardarReservacionNumPersonasMal() throws ParseException{
		log.debug("Entrando a guardarReservacionNumPersonasMal");
		Long idCliente = (long) 1;
		String msg;
        Date fecha= new Date();
        String date="23/06/2017";
        fecha=new SimpleDateFormat("dd/mm/yyyy").parse(date);
		Cliente clienteEncontrado = new Cliente();
		clienteEncontrado = clienteService.buscarCliente(idCliente);
		Reservacion reserva = new Reservacion(new Date(), 11, 1, "12:00", clienteEncontrado);
		msg = reservacionService.guardarReservacion(reserva);
		
		log.debug("*************");
		
		log.debug(msg);
		
		log.debug("*************");
		
		String msgEsperado = messages.getMessage("message.max.numPersonas", null, LocaleContextHolder.getLocale());
		log.debug(msgEsperado);
		assertTrue(msgEsperado.equals(msg));		
		
	}
	
	@Test
	public void guardarReservacionNumMesasMal() throws ParseException{
		log.debug("Entrando a guardarReservacionNumMesasMal");
		Long idCliente = (long) 1;
		String msg;
        Date fecha= new Date();
        String date="23/06/2017";
        fecha=new SimpleDateFormat("dd/mm/yyyy").parse(date);
		Cliente clienteEncontrado = new Cliente();
		clienteEncontrado = clienteService.buscarCliente(idCliente);
		Reservacion reserva = new Reservacion(new Date(), 3, 4, "12:00", clienteEncontrado);
		msg = reservacionService.guardarReservacion(reserva);
		
		log.debug("*************");
		
		log.debug(msg);
					
		log.debug("*************");
			
		String msgEsperado = messages.getMessage("message.max.numMesas", null, LocaleContextHolder.getLocale());
		log.debug(msgEsperado);
		assertTrue(msgEsperado.equals(msg));
		
	}
	
	//@Test
	public void guardarReservacionHorarioMal(){
		log.debug("Entrando a guardarReservacionHorarioMal");
		String msg;
		Long idCliente = (long) 1;
		Date fecha = new Date();
		SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
		try {
			fecha = formato.parse("12/06/2017");
			System.out.println("Fecha: " + fecha);
			Cliente clienteEncontrado = new Cliente();
			clienteEncontrado = clienteService.buscarCliente(idCliente);
			Reservacion reserva = new Reservacion(new Date(), 3, 1, "12:", clienteEncontrado);
			msg = reservacionService.guardarReservacion(reserva);
			
			log.debug("*************");
			
			log.debug(msg);
			
			
			log.debug("*************");
			
			String msgEsperado = messages.getMessage("message.horario", null, LocaleContextHolder.getLocale());
			
			assertTrue(msgEsperado.equals(msg));
			
		} catch (ParseException e) {
			e.printStackTrace();
		}
				
		
	}
	
	
}
