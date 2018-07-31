package mx.com.restaurante.test.service;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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



@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes=Application.class)
public class ClienteServiceTest {

	public static final Logger log = Logger.getLogger(ClienteServiceTest.class);
	
	@Autowired
	ClienteService clienteService;
	
	@Autowired
	MessageSource messages;
	
	@Test
	public void guardarClienteMalNombreMin(){
		
		log.debug("Entrando a guardarClienteMalNombreMin");
		String msg;
		
		Cliente cliente = new Cliente("C","12345678","chris@gmail.com","Verificando min de nombre");
		
		msg = clienteService.guardarCliente(cliente);
		
		log.debug("*************");
		
		log.debug(msg);
		
		
		log.debug("*************");
		
		String msgEsperado = messages.getMessage("message.min.nombre", null, LocaleContextHolder.getLocale());
		
		assertTrue(msgEsperado.equals(msg));
		
	}
	@Test
	public void guardarClienteMalNombreMax(){
		log.debug("Entrando a guardarClienteMalNombreMin");
		String msg;
		
		Cliente cliente = new Cliente("ChristianFuentesVillaeqrewifsjdfjhsakjlahChristianFuentesVillaeqrewifsjdfjhsakjlah",
				"12345678","chris@gmail.com","Verificando max de nombre");
		
		msg = clienteService.guardarCliente(cliente);
		
		log.debug("*************");
		
		log.debug(msg);
		
		
		log.debug("*************");
		
		String msgEsperado = messages.getMessage("message.max.nombre", null, LocaleContextHolder.getLocale());
		
		assertTrue(msgEsperado.equals(msg));
	}
	
	@Test
	public void guardarClienteMalTelefonoDigitos(){
		log.debug("Entrando a guardarClienteMalTelefonoDigitos");
		String msg;
		
		Cliente cliente = new Cliente("Christian","AD232314","chris@gmail.com", "Verificando solo digitos TEL");
		
		msg = clienteService.guardarCliente(cliente);
		
		log.debug("**************");
		log.debug(msg);
		log.debug("**************");
		
		String msgEsperado = messages.getMessage("message.digitos", null, LocaleContextHolder.getLocale());
		
		assertTrue(msgEsperado.equals(msg));
		
	}
	
	@Test
	public void guardarClienteMalTelefonoMax(){
		log.debug("Entrando a guardarClienteMalTelefonoMax");
		String msg;
		
		Cliente cliente = new Cliente("Christian","232314231231452342512314","chris@gmail.com", "Verificando solo digitos TEL");
		
		msg = clienteService.guardarCliente(cliente);
		
		log.debug("**************");
		log.debug(msg);
		log.debug("**************");
		
		String msgEsperado = messages.getMessage("message.tel.max", null, LocaleContextHolder.getLocale());
		
		assertTrue(msgEsperado.equals(msg));
		
	}
	
	@Test
	public void guardarClienteMalTelefonoMin(){
		log.debug("Entrando a guardarClienteMalTelefonoMin");
		String msg;
		
		Cliente cliente = new Cliente("Christian","23231","chris@gmail.com", "Verificando solo digitos TEL");
		
		msg = clienteService.guardarCliente(cliente);
		
		log.debug("**************");
		log.debug(msg);
		log.debug("**************");
		
		String msgEsperado = messages.getMessage("message.tel.min", null, LocaleContextHolder.getLocale());
		
		assertTrue(msgEsperado.equals(msg));
		
	}
	
	@Test
	public void guardarClienteMalEmail(){
		log.debug("Entrando a guardarClienteMalEmail");
		String msg;
		
		Cliente cliente = new Cliente("Christian","5517373377","hola@", "Verificando que este bien el correo");
		msg = clienteService.guardarCliente(cliente);
		
		log.debug("**************");
		log.debug(msg);
		log.debug("**************");
		
		String msgEsperado = messages.getMessage("message.email", null, LocaleContextHolder.getLocale());
		
		assertTrue(msgEsperado.equals(msg));
	}
	
	@Test
	public void guardarClienteMalEmailMin(){
		log.debug("Entrando a guardarClienteMalEmailMin");
		String msg;
		
		Cliente cliente = new Cliente("Christian","5517373377","h@d.com", "Verificando que este bien el correo min");
		msg = clienteService.guardarCliente(cliente);
		
		log.debug("**************");
		log.debug(msg);
		log.debug("**************");
		
		String msgEsperado = messages.getMessage("message.email.min", null, LocaleContextHolder.getLocale());
		
		assertTrue(msgEsperado.equals(msg));
	}

	@Test
	public void guardarClienteMalEmailMax(){
		log.debug("Entrando a guardarClienteMalEmailMax");
		String msg;
		
		Cliente cliente = new Cliente("Christian","5517373377","hasajghjgjhgsasdsadasdas@dasdasdasdasdasdsadasweweasa.asdasdasdasdasdasdasdasasdcom",
				"Verificando que este bien el correo max");
		msg = clienteService.guardarCliente(cliente);
		
		log.debug("**************");
		log.debug(msg);
		log.debug("**************");
		
		String msgEsperado = messages.getMessage("message.email.max", null, LocaleContextHolder.getLocale());
		
		assertTrue(msgEsperado.equals(msg));
	}
	
	@Test
	public void guardarClienteMalComentarioMax(){
		log.debug("Entrando a guardarClienteMalComentarioMax");
		String msg;
		
		Cliente cliente = new Cliente("Christian","5517373377","christian@gmail.com",
				"Verificando que no pase del limite min el comentario,Verificando que no pase del limite min el comentario"
				+ "Verificando que no pase del limite min el comentarioVerificando que no pase del limite min el comentario"
				+ "Verificando que no pase del limite min el comentarioVerificando que no pase del limite min el comentario");
		msg = clienteService.guardarCliente(cliente);
		
		String msgEsperado = messages.getMessage("message.max.coment", null, LocaleContextHolder.getLocale());
		
		assertTrue(msgEsperado.equals(msg));
	
	}
	
	@Test
	public void guardarClienteMalComentarioMin(){
		log.debug("Entrando a guardarClienteMalComentarioMin");
		String msg;
		
		Cliente cliente = new Cliente("Christian","5517373377","christian@gmail.com","min");
				
		msg = clienteService.guardarCliente(cliente);
		String msgEsperado = messages.getMessage("message.min.coment", null, LocaleContextHolder.getLocale());
		
		assertTrue(msgEsperado.equals(msg));
	
	}
	
	@Test
	public void guardarClienteTest(){
		log.debug("Entrando a gurardarClienteTest");
		
		Cliente cliente = new Cliente("Christian", "5517373377", "kriztianfv97@gmail.com", "Primera prueba de insertar clientes en la BD con su respectiva reservacion");
		
		Reservacion reservacion1 = new Reservacion(new Date(), 2, 1, "12:30", cliente);
		
		List<Reservacion> reservaciones = new ArrayList<Reservacion>();
		
		reservaciones.add(reservacion1);
		
		cliente.setReservaciones(reservaciones);
		
		clienteService.guardarCliente(cliente);
		
		Cliente clienteEncontrado = clienteService.buscarCliente(cliente.getId());
		
		assertNotNull(clienteEncontrado);
				
		
	}
	
}
