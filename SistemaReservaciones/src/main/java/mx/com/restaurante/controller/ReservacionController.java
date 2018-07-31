package mx.com.restaurante.controller;

import java.util.Date;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.core.env.Environment;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import mx.com.restaurante.domain.Cliente;
import mx.com.restaurante.domain.Reservacion;
import mx.com.restaurante.domain.repository.ReservacionRepository;
import mx.com.restaurante.service.ClienteService;

@Controller
public class ReservacionController {
	
	private JavaMailSender javaMailSender;
	
	@Autowired
	private Environment env;
	
	@Autowired
	private MessageSource messages;
	
	@Autowired
	public void setJavaMailSender(JavaMailSender javaMailSender){
		
		this.javaMailSender= javaMailSender;
		
	}
	
	private final Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	ReservacionRepository reservacionRepository;
	
	
	@Autowired
	ClienteService clienteService;
	
	@RequestMapping(value="/reservacion", method=RequestMethod.GET)
	public String reservacion(Map<String, Object> model){
		log.debug("Entre a reservacion");
		return "reservacion";
	}
	
	@RequestMapping(value="/reservacion/confirmacion", method=RequestMethod.POST)
	public String registrarReservacionCliente(@RequestParam("fecha") Date fecha,
			@RequestParam("noPersonas") Integer noPersonas, @RequestParam("noMesas") Integer noMesas,
			@RequestParam("horario") String horario, @RequestParam("idCliente") Long idCliente){
		
		
		
		
		final SimpleMailMessage email;
				
		log.debug("Entre a registrarReservacionCliente");
		Cliente clienteEncontrado = new Cliente();
		clienteEncontrado = clienteService.buscarCliente(idCliente);
		
		Reservacion reservacion = new Reservacion(fecha, noPersonas, noMesas, horario, clienteEncontrado);
		reservacionRepository.save(reservacion);
		String nombre = clienteEncontrado.getNombre();
		String correo = clienteEncontrado.getEmail();
		String telefono = clienteEncontrado.getTelefono();
		String comen = clienteEncontrado.getComentario();
		email = constructEmailMessage(correo, nombre, telefono, comen, fecha,noPersonas,noMesas, horario);
		javaMailSender.send(email);
		log.debug("Se envio el correo correctamente");
		return "confirmacion";
	}
	
	private final SimpleMailMessage constructEmailMessage(String recipientAddres, String nombre,
			String telefono, String Comentario, Date fecha, Integer nPerson, Integer nMesas, String horario){
		
		
		final String subject = messages.getMessage("message.subject", null, LocaleContextHolder.getLocale());
		final String message = messages.getMessage("message.regSucc", null, LocaleContextHolder.getLocale());
		
		final String footerContact = messages.getMessage("message.footer.contact", null, LocaleContextHolder.getLocale());
		final String footerUrl = messages.getMessage("message.footer.url", null, LocaleContextHolder.getLocale());
		
		final SimpleMailMessage email = new SimpleMailMessage();
		
		email.setTo(recipientAddres);
		email.setSubject(subject);
		email.setText(message + "\r\n\n"+"nombre: "+nombre+ "\r\n\n"+"Telefono: "+telefono + "\r\n\n"+"Comentario: "+Comentario	+
				"\r\n\n"+"Fecha: "+ fecha + "\r\n\n"+ "numero de personas: "+nPerson+ "\r\n\n"+ "numero de Mesas: "+nMesas+
				"\r\n\n"+"Horario: "+horario+"\r\n\n" + footerContact + "\r\n\n" + footerUrl);
		
		email.setFrom(env.getProperty("support.email"));
		
		return email;
		
	}
	
	
}
